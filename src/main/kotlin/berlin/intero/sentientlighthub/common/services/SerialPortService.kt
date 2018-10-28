package berlin.intero.sentientlighthub.common.services

import berlin.intero.sentientlighthub.common.SentientProperties
import gnu.io.CommPortIdentifier
import gnu.io.NoSuchPortException
import gnu.io.SerialPort
import java.io.IOException
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
        port?.setSerialPortParams(SentientProperties.Serial.BAUD_RATE,
                SentientProperties.Serial.DATA_BITS,
                SentientProperties.Serial.STOP_BITS,
                SentientProperties.Serial.PARITY)
        port?.flowControlMode = SentientProperties.Serial.FLOW_CONTROL
    }

    /**
     * Sends a value
     *
     * @param value value to send
     */
    fun sendByte(value: Int) {
        port?.outputStream?.write(value)
        port?.outputStream?.flush()
    }

    /**
     * Sends a list of values
     *
     * @param values byte array of values to send
     */
    fun sendBytesOneByOne(values: ByteArray) {
        log.fine("sendBytesOneByOne")
        try {
            values.forEach {
                port?.outputStream?.write(it.toInt());
                Thread.sleep(SentientProperties.Serial.SEND_BYTE_DELAY)
            }
            port?.outputStream?.flush()
        } catch (e: IOException) {
            log.severe("${SentientProperties.Color.ERROR}${e.message}${SentientProperties.Color.RESET}")
        }
    }

    /**
     * Sends a list of values
     *
     * @param values byte array of values to send
     */
    fun sendBytesAtOnce(values: ByteArray) {
        log.fine("sendBytesAtOnce")
        try {
            Thread.sleep(SentientProperties.Serial.SEND_BYTE_DELAY)
            port?.outputStream?.write(values)
            port?.outputStream?.flush()
            Thread.sleep(SentientProperties.Serial.SEND_BYTE_DELAY)
        } catch (e: IOException) {
            log.severe("${SentientProperties.Color.ERROR}${e}${SentientProperties.Color.RESET}")
        }
    }

    /**
     * Sets the ready-to-send flag
     *
     * @param value value
     */
    fun setRTSSignal(value: Boolean) {
        log.fine("set RTS $value")
        port?.isRTS = value
        log.fine("get RTS ${port?.isRTS}")
    }

    /**
     * Sets the data-termial-ready flag
     *
     * @param value value
     */
    fun setDTSSignal(value: Boolean) {
        log.fine("set DTR $value")
        port?.isDTR = value
        log.fine("get DTR ${port?.isDTR}")
    }

    fun openPort(portName: String) {
        log.info("open port $portName")
        try {
            val portIdentifier = CommPortIdentifier.getPortIdentifier(portName)
            val owner = SerialPortService.toString()
            val waitingTime = SentientProperties.Serial.CONNECTION_TIMEOUT

            if (portIdentifier.isCurrentlyOwned) {
                log.warning("Port is currently in use")
            } else {
                // Open port
                port = portIdentifier.open(owner, waitingTime) as SerialPort
            }
        } catch (nspe: NoSuchPortException) {
            log.severe("${SentientProperties.Color.ERROR}${nspe}${SentientProperties.Color.RESET}")
        }
    }

    fun closePort() {
        port?.close()
    }
}