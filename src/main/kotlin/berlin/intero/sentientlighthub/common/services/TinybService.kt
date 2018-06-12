package berlin.intero.sentientlighthub.common.services

import berlin.intero.sentientlighthub.common.SentientProperties
import tinyb.*
import java.util.logging.Logger

/**
 * This service bundles all bluetooth related operations convered by tinyb including
 *
 * <li> scanning for devices
 * <li> connecting to a device
 * <li> reading values from devices' characteristics
 * <li> sending values to devices' characteristics
 */
object TinybService : BluetoothNotification<ByteArray> {

    private val log: Logger = Logger.getLogger(TinybService::class.simpleName)

    val scannedDevices: MutableList<BluetoothDevice> = ArrayList()

    // --------------------
    // Scan
    // --------------------

    /**
     * Scans for bluetooth devices
     */
    @Throws(InterruptedException::class)
    fun scanDevices(): List<BluetoothDevice> {
        log.fine("Start scan")

        val manager = BluetoothManager.getBluetoothManager()
        try {
            manager.startDiscovery()
        } catch (e: BluetoothException) {
            log.severe("$e")
            return emptyList()
        }

        for (i in 0 until SentientProperties.GATT.SCAN_RETRY) {
            log.fine(".")
            Thread.sleep(SentientProperties.GATT.SCAN_DURATION)
        }

        try {
            manager.stopDiscovery()
        } catch (e: BluetoothException) {
            log.severe("Discovery could not be stopped.")
        }

        scannedDevices.clear()
        scannedDevices.addAll(manager.devices)
        return scannedDevices
    }

    // --------------------
    // Connect
    // --------------------

    fun connectDevice(device: BluetoothDevice): Boolean {
        log.fine("Connect device ${device.address} ${device.name}")

        try {
            return if (device.connect()) {
                log.fine("Paired : ${device.paired}")
                log.fine("Trusted: ${device.trusted}")
                true
            } else {
                log.warning("Could not connect device ${device.address}")
                false
            }
        } catch (e: BluetoothException) {
            log.severe("$e")
        }

        return false
    }

    /**
     * Tries to connect to a given bluetooth device if it is not already connected
     *
     * @param device bluetooth device to connect to
     * @return true device could be connected
     */
    @Throws(BluetoothException::class)
    fun ensureConnection(device: BluetoothDevice): Boolean {
        log.fine("Ensure connection")

        repeat(SentientProperties.GATT.CONNECTION_RETRY, {
            if (!connectDevice(device)) {
                log.finer(".")
                Thread.sleep(SentientProperties.GATT.CONNECTION_IDLE)
            } else {
                log.info("Connected ${device.address}")
                return true
            }
        })

        return false
    }

    // --------------------
    // Read / Write
    // --------------------

    /**
     * Reads a characteristics current value
     *
     * @param device bluetooth device
     * @param characteristicID UUID of the characteristic to read
     */
    fun readCharacteristic(device: BluetoothDevice, characteristicID: String): ByteArray {
        log.info("Read characteristicID $characteristicID")
        device.services.forEach { service ->
            service.characteristics.forEach { characteristic ->
                if (characteristicID == characteristic.uuid) {
                    ensureConnection(device)
                    return characteristic.readValue()
                }
            }
        }

        return ByteArray(0)
    }

    /**
     * Writes an array of @param bytes into the specified @param characteristicID of a @param device
     */
    fun writeCharacteristic(device: BluetoothDevice, characteristicID: String, bytes: ByteArray) {
        log.info("Write Characteristic dev ${device.address} / char $characteristicID")

        val characteristic = getCharacteristicByID(device, characteristicID)

        if (characteristic != null) {
            ensureConnection(device)

            try {
                characteristic.writeValue(bytes)
            } catch (e: BluetoothException) {
                log.severe("$e")
            }
        }
    }

    // --------------------
    // Debugging
    // --------------------

    /**
     * Displays a list of bluetooth devices
     *
     * @para devices list of bluetooth devices to be displayed
     */
    fun showDevices(devices: List<BluetoothDevice>) {
        log.fine("Show devices")

        devices.forEach { device ->
            log.info("Device ${SentientProperties.Color.GATT} ${device.address} ${SentientProperties.Color.RESET} ${device.name} ${device.connected}")
        }
    }

    /**
     * Displays all services of a given bluetooth device
     *
     * @param device bluetooth device
     */
    fun showServices(device: BluetoothDevice) {
        log.fine("Show service")

        ensureConnection(device)

        device.services.forEach { service ->
            log.info(" Service ${SentientProperties.Color.GATT} ${service.uuid} ${SentientProperties.Color.RESET}")
            showCharacteristics(service)
        }
    }

    /**
     * Displays all characteristics of a given bluetooth service
     */
    fun showCharacteristics(service: BluetoothGattService) {
        service.characteristics.forEach { characteristic ->
            log.info("  Characteristic ${SentientProperties.Color.GATT} ${characteristic.uuid} ${SentientProperties.Color.RESET}")
        }
    }

    // --------------------
    // Util
    // --------------------

    /**
     * Returns a service's characteristic that matches a given ID
     *
     * @param device bluetooth device
     * @param characteristicID charactertics UUID
     */
    private fun getCharacteristicByID(device: BluetoothDevice, characteristicID: String): BluetoothGattCharacteristic? {
        device.services.forEach { service ->
            service.characteristics.forEach { characteristic ->
                if (characteristicID == characteristic.uuid) {
                    return characteristic
                }
            }
        }

        return null
    }

    override fun run(value: ByteArray?) {
        log.fine("PING")
    }
}