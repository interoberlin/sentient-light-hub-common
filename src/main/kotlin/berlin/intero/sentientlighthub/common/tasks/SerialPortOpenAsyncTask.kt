package berlin.intero.sentientlighthub.common.tasks

import berlin.intero.sentientlighthub.common.SentientProperties
import berlin.intero.sentientlighthub.common.services.SerialPortService
import java.util.logging.Logger

/**
 * This async task opens and initializes a given COM port.
 *
 * @param portName port name
 */
class SerialPortOpenAsyncTask(
        private val portName: String
) : Runnable {

    companion object {
        private val log: Logger = Logger.getLogger(SerialPortOpenAsyncTask::class.simpleName)
    }

    override fun run() {
        log.info("${SentientProperties.Color.TASK}-- SERIAL PORT OPEN${SentientProperties.Color.RESET}")
        log.fine("portName $portName")

        SerialPortService.openPort(portName)
        SerialPortService.initSerialPort()
    }
}
