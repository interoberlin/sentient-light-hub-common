package berlin.intero.sentientlighthub.common.tasks

import berlin.intero.sentientlighthub.common.SentientProperties
import berlin.intero.sentientlighthub.common.services.SerialPortService
import java.util.logging.Logger


/**
 * This asyn task discovers and displays available COM ports
 */
class SerialDiscoverPortsAsyncTask: Runnable {

    companion object {
        private val log: Logger = Logger.getLogger(SerialDiscoverPortsAsyncTask::class.simpleName)
    }

    override fun run() {
        log.info("${SentientProperties.Color.TASK}-- SERIAL DISCOVER PORTS${SentientProperties.Color.RESET}")
        SerialPortService.displayPorts()
    }
}