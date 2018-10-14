package berlin.intero.sentientlighthub.common.tasks

import berlin.intero.sentientlighthub.common.SentientProperties
import berlin.intero.sentientlighthub.common.exceptions.BluetoothConnectionException
import berlin.intero.sentientlighthub.common.services.TinybService
import tinyb.BluetoothException
import java.util.logging.Logger

/**
 * This async task writes a value to a device's characteristic
 *
 * @param address MAC address of the device
 * @param characteristicID ID of the characteristic that the value should be written to
 * @param value value to write
 */
class GATTWriteAsyncTask(
        val address: String,
        val characteristicID: String,
        val value: ByteArray
) : Runnable {

    companion object {
        private val log: Logger = Logger.getLogger(GATTWriteAsyncTask::class.simpleName)
    }

    override fun run() {
        log.info("${SentientProperties.Color.TASK}-- GATT WRITE TASK${SentientProperties.Color.RESET}")

        val scannedDevices = TinybService.scannedDevices

        try {
            val device = scannedDevices.first { d -> d.address == this.address }

            // Ensure connection
            TinybService.ensureConnection(device)

            // Write raw value
            TinybService.writeCharacteristic(device, characteristicID, value)
        } catch (ex: Exception) {
            when (ex) {
                is BluetoothException -> {
                    log.severe("Generic bluetooth exceptions")
                }
                is BluetoothConnectionException -> {
                    log.severe("Cannot connect to device ${this.address}")
                }
                is NoSuchElementException -> {
                    log.severe("Cannot find device ${this.address}")
                }
                else -> throw ex
            }
        }
    }
}
