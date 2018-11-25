package berlin.intero.sentientlighthub.common.tasks

import berlin.intero.sentientlighthub.common.SentientProperties
import berlin.intero.sentientlighthub.common.model.MQTTEvent
import berlin.intero.sentientlighthub.common.services.MqttService
import java.util.logging.Logger

/**
 * This async task publishes a given list of {@param mqttEvents} on a MQTT broker
 *
 * @param mqttEvents MQTT events to be published
 */
class MQTTPublishAsyncTask(
        private val mqttEvents: List<MQTTEvent>
) : Runnable {

    companion object {
        private val log: Logger = Logger.getLogger(MQTTPublishAsyncTask::class.simpleName)
    }

    override fun run() {
        log.fine("${SentientProperties.Color.TASK}-- MQTT PUBLISH TASK${SentientProperties.Color.RESET}")

        // Publish value
        MqttService.publish(mqttEvents)
    }
}
