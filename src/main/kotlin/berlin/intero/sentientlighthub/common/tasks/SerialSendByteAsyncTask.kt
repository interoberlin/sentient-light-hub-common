package berlin.intero.sentientlighthub.common.tasks

import berlin.intero.sentientlighthub.common.SentientProperties
import berlin.intero.sentientlighthub.common.services.SerialPortService
import java.util.logging.Logger

/**
 * This async task send a value to a given COM port.
 *
 * @param portName port name
 * @param value value to be sent
 */
class SerialSendByteAsyncTask(
        private val portName: String,
        private val value: Int
) : Runnable {

    companion object {
        private val log: Logger = Logger.getLogger(SerialSendByteAsyncTask::class.simpleName)
    }

    override fun run() {
        log.info("${SentientProperties.Color.TASK}-- SERIAL SEND BYTE${SentientProperties.Color.RESET}")
        log.fine("portName $portName")
        log.fine("value $value")

        SerialPortService.sendByte(portName, value)
    }
}
