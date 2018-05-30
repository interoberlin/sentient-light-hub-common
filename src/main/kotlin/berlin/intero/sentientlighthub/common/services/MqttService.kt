package berlin.intero.sentientlighthub.common.services

import berlin.intero.sentientlighthub.common.SentientProperties
import berlin.intero.sentientlighthub.common.model.MQTTEvent
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.util.logging.Logger


object MqttService {

    private val log = Logger.getLogger(MqttService::class.simpleName)
    private val clientPublish = MqttClient(SentientProperties.MQTT.SERVER_URI, MqttClient.generateClientId())

    /**
     * Publishes a given message to a topic
     *
     * @param mqttEvents list of MQTT events to be published
     */
    fun publish(mqttEvents: List<MQTTEvent>) {
        log.fine("Publish")

        // Connect client
        if (!clientPublish.isConnected) {
            clientPublish.connect()
        }

        mqttEvents.forEach { mqttEvent ->
            // Build message
            val message = MqttMessage(mqttEvent.value.toByteArray())
            message.qos = 0
            message.isRetained = false

            // Publish message
            log.info("Publish ${mqttEvent.topic} : ${mqttEvent.value}")
            clientPublish.publish(mqttEvent.topic, message)
        }

        // Disconnect from MQTT broker
        log.fine("Client disconnect")
        if (clientPublish.isConnected) {
            clientPublish.disconnect()
        }
    }

    /**
     * Subscribes an MQTT topic
     *
     * @param topic topic to subscribe
     * @param callback callback
     */
    fun subscribe(topic: String, callback: MqttCallback?) {
        log.fine("MQTT subscribe")

        // Set connection options
        val connOpts = MqttConnectOptions()
        connOpts.isAutomaticReconnect = SentientProperties.MQTT.AUTOMATIC_RECONNECT
        connOpts.connectionTimeout = SentientProperties.MQTT.CONNECTION_TIMEOUT

        // Create client
        val clientSubscribe = MqttClient(SentientProperties.MQTT.SERVER_URI, MqttClient.generateClientId())

        // Set callback
        clientSubscribe.setCallback(callback)

        // Connect to MQTT broker
        if (!clientSubscribe.isConnected) {
            clientSubscribe.connect(connOpts)
        }

        // Subscribe topic
        clientSubscribe.subscribe(topic)
    }
}
