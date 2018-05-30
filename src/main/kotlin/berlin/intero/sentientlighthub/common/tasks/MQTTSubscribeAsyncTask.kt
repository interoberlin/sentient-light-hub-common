package berlin.intero.sentientlighthub.common.tasks

import berlin.intero.sentientlighthub.common.SentientProperties
import berlin.intero.sentientlighthub.common.services.MqttService
import org.eclipse.paho.client.mqttv3.MqttCallback
import java.util.logging.Logger

/**
 * This async task starts subscription of a MQTT topic. Results are passed via a callback.
 *
 * @param topic topic to subscribe
 * @param callback callback for subscription events
 */
class MQTTSubscribeAsyncTask(
        private val topic: String,
        private val callback: MqttCallback
) : Runnable {

    companion object {
        private val log: Logger = Logger.getLogger(MQTTSubscribeAsyncTask::class.simpleName)
    }

    override fun run() {
        log.info("${SentientProperties.Color.TASK}-- MQTT SUBSCRIBE TASK${SentientProperties.Color.RESET}")
        log.fine("topic $topic")

        MqttService.subscribe(topic, callback)
    }
}
