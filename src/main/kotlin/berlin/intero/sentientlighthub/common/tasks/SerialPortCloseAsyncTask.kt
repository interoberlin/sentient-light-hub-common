package berlin.intero.sentientlighthub.common.tasks

import berlin.intero.sentientlighthub.common.SentientProperties
import berlin.intero.sentientlighthub.common.services.SerialPortService
import java.util.logging.Logger

/**
 * This async task closes COM port.
 *
 */
class SerialPortCloseAsyncTask(
        private val portName: String
) : Runnable {

    companion object {
        private val log: Logger = Logger.getLogger(SerialPortCloseAsyncTask::class.simpleName)
    }

    override fun run() {
        log.info("${SentientProperties.Color.TASK}-- SERIAL PORT CLOSE${SentientProperties.Color.RESET}")

        SerialPortService.closePort()
    }
}
