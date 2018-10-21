package berlin.intero.sentientlighthub.common.services

import berlin.intero.sentientlighthub.common.SentientProperties
import gnu.io.CommPortIdentifier
import gnu.io.SerialPort
import java.util.*
import java.util.logging.Logger

object SerialPortService {

    private val log = Logger.getLogger(SerialPortService::class.simpleName)

    private var port: SerialPort? = null

    /**
     * Discovers COMM ports
     */
    private fun discoverPorts(): Enumeration<Any> {
        return CommPortIdentifier.getPortIdentifiers()
    }

    /**
     * Displays COMM ports
     */
    fun displayPorts() {
        val portEnum = discoverPorts()

        while (portEnum.hasMoreElements()) {
            val portIdentifier = portEnum.nextElement() as CommPortIdentifier
            log.info(portIdentifier.name + " - "
                    + getPortTypeName(portIdentifier.portType) + " - " + portIdentifier)
        }
    }

    /**
     * Returns name of a given port type
     *
     * @param portType port type
     */
    private fun getPortTypeName(portType: Int): String {
        return when (portType) {
            CommPortIdentifier.PORT_I2C -> "I2C"
            CommPortIdentifier.PORT_PARALLEL -> "Parallel"
            CommPortIdentifier.PORT_RAW -> "Raw"
            CommPortIdentifier.PORT_RS485 -> "RS485"
            CommPortIdentifier.PORT_SERIAL -> "Serial"
            else -> "unknown type"
        }
    }

    /**
     * Initializes a serial port
     */
    fun initSerialPort() {
        port!!.setSerialPortParams(SentientProperties.Serial.BAUD_RATE,
                SentientProperties.Serial.DATA_BITS,
                SentientProperties.Serial.STOP_BITS,
                SentientProperties.Serial.PARITY)
        port!!.flowControlMode = SentientProperties.Serial.FLOW_CONTROL
    }

    /**
     * Sends a value
     *
     * @param value value to send
     */
    fun sendByte(value: Int) {
        port!!.outputStream.write(value)
        port!!.outputStream.flush()
    }

    /**
     * Sends a list of values
     *
     * @param values byte array of values to send
     */
    fun sendBytesOneByOne(values: ByteArray) {
        values.forEach { port!!.outputStream.write(it.toInt()); Thread.sleep(SentientProperties.Serial.SEND_BYTE_DELAY) }
        // port!!.outputStream.flush()
    }

    /**
     * Sends a list of values
     *
     * @param values byte array of values to send
     */
    fun sendBytesAtOnce(values: ByteArray) {
        port!!.outputStream.write(values)
        // port!!.outputStream.flush()
    }

    /**
     * Sets the ready-to-send flag
     *
     * @param value value
     */
    fun setRTSSignal(value: Boolean) {

        // Write value and close port
        port!!.isRTS = value
        // commPort.close()
    }

    fun openPort(portName: String) {
        val portIdentifier = CommPortIdentifier.getPortIdentifier(portName)
        val owner = SerialPortService.toString()
        val waitingTime = SentientProperties.Serial.CONNECTION_TIMEOUT

        if (portIdentifier.isCurrentlyOwned) {
            log.severe("Port is currently in use")
        } else {
            // Open port
            port = portIdentifier.open(owner, waitingTime) as SerialPort
        }
    }

    fun closePort() {
        port!!.close()
    }
}