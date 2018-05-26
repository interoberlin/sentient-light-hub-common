package berlin.intero.sentientlighthubplayground.tasks.async

import berlin.intero.sentientlighthub.common.SentientColors
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
        val topic: String,
        val callback: MqttCallback
) : Runnable {

    companion object {
        private val log: Logger = Logger.getLogger(MQTTSubscribeAsyncTask::class.simpleName)
    }

    override fun run() {
        log.info("${SentientColors.ANSI_GREEN}-- MQTT SUBSCRIBE TASK${SentientColors.ANSI_RESET}")

        MqttService.subscribe(SentientProperties.MQTT_SERVER_URI, topic, callback)
    }
}
