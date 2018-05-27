package berlin.intero.sentientlighthub.common.tasks

import berlin.intero.sentientlighthub.common.SentientProperties
import berlin.intero.sentientlighthub.common.model.MQTTEvent
import berlin.intero.sentientlighthub.common.services.MqttService
import java.util.logging.Logger

/**
 * This async task publishes a given value to a MQTT topic
 *
 * @param topic topic to be published to
 * @param value value to publish
 */
class MQTTPublishAsyncTask(
        private val mqttEvents: List<MQTTEvent>
        ) : Runnable {

    companion object {
        private val log: Logger = Logger.getLogger(MQTTPublishAsyncTask::class.simpleName)
    }

    override fun run() {
        log.info("${SentientProperties.Color.TASK}-- MQTT PUBLISH TASK${SentientProperties.Color.RESET}")

        // Publish value
        MqttService.publish(SentientProperties.MQTT.SERVER_URI, mqttEvents)
    }
}
