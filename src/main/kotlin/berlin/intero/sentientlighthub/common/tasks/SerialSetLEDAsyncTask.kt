package berlin.intero.sentientlighthub.common.tasks

import berlin.intero.sentientlighthub.common.SentientProperties
import berlin.intero.sentientlighthub.common.services.SerialPortService
import java.util.logging.Logger

/**
 * This async task sets a given LED via COM port.
 *
 * @param portName port name
 * @param ledID ID of the LED to be set
 * @param warmWhite warm white value
 * @param coldWhite cold white value
 * @param amber amber value
 */
class SerialSetLEDAsyncTask(
        private val portName: String,
        private val ledID: Short,
        private val warmWhite: Byte,
        private val coldWhite: Byte,
        private val amber: Byte
) : Runnable {

    companion object {
        private val log: Logger = Logger.getLogger(SerialSetLEDAsyncTask::class.simpleName)
    }

    override fun run() {
        log.info("${SentientProperties.Color.TASK}-- SERIAL SET LED${SentientProperties.Color.RESET}")
        log.fine("portName $portName")
        log.fine("ledID $ledID")
        log.fine("warmWhite $warmWhite")
        log.fine("coldWhite $coldWhite")
        log.fine("amber $amber")

        val values = byteArrayOf(0x01, 0x00, ledID.toByte(), warmWhite, coldWhite, amber)


        SerialPortService.setRTSSignal(true)
        SerialPortService.sendBytes(values)
        SerialPortService.setRTSSignal(false)

        // After
        SerialPortService.closePort()
    }
}
