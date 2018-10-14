package berlin.intero.sentientlighthub.common.services

import berlin.intero.sentientlighthub.common.SentientProperties
import gnu.io.CommPortIdentifier
import gnu.io.SerialPort
import java.util.*
import java.util.logging.Logger

object SerialPortService {

    private val log = Logger.getLogger(SerialPortService::class.simpleName)

    /**
     * Discovers COMM ports
     */
    fun discoverPorts(): Enumeration<Any> {
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
        when (portType) {
            CommPortIdentifier.PORT_I2C -> return "I2C"
            CommPortIdentifier.PORT_PARALLEL -> return "Parallel"
            CommPortIdentifier.PORT_RAW -> return "Raw"
            CommPortIdentifier.PORT_RS485 -> return "RS485"
            CommPortIdentifier.PORT_SERIAL -> return "Serial"
            else -> return "unknown type"
        }
    }

    /**
     * Initializes a serial port with a given name
     *
     * @param portName port name
     */
    fun initSerialPort(portName: String) {
        val portIdentifier = CommPortIdentifier.getPortIdentifier(portName)
        val owner = SerialPortService.toString()
        val waitingTime = 6000

        if (portIdentifier.isCurrentlyOwned()) {
            log.severe("Port is currently in use")
        } else {
            // Open port
            val commPort = portIdentifier.open(owner, waitingTime)

            if (commPort is SerialPort) {
                // Configure port
                commPort.setSerialPortParams(SentientProperties.Serial.BAUD_RATE,
                        SentientProperties.Serial.DATA_BITS,
                        SentientProperties.Serial.STOP_BITS,
                        SentientProperties.Serial.PARITY)
                commPort.flowControlMode = SentientProperties.Serial.FLOW_CONTROL
            } else {
                println("Error: Only serial ports are handled by this example.")
            }
        }
    }

    /**
     * Sends value on a given serial port
     *
     * @param portName port name
     * @param value value to send
     */
    fun sendByte(portName: String, value: Int) {
        val portIdentifier = CommPortIdentifier.getPortIdentifier(portName)
        val owner = SerialPortService.toString()
        val waitingTime = SentientProperties.Serial.CONNECTION_TIMEOUT

        if (portIdentifier.isCurrentlyOwned) {
            log.severe("Port is currently in use")
        } else {
            // Open port
            val commPort = portIdentifier.open(owner, waitingTime)

            if (commPort is SerialPort) {
                // Write value and close port
                commPort.outputStream.write(value)
                commPort.outputStream.flush()
                commPort.close()
            } else {
                println("Error: Only serial ports are handled by this example.")
            }
        }
    }
}