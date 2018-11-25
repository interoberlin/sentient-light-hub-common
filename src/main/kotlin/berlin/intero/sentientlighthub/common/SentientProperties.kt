package berlin.intero.sentientlighthub.common

import gnu.io.SerialPort

object SentientProperties {
    val MAX_SENSORS_PER_CABLE = 5

    object Frequency {
        const val SENSORS_SCAN_RATE = 300_000L
        const val SENSOR_READ_DELAY = 10L
        const val SENTIENT_MAPPING_DELAY = 10L
        const val SENTIENT_WRITE_DELAY = 10L

        const val SENTIENT_MESSAGE_PROCESS_DELAY = 10L

        const val UNSUCCESSFUL_TASK_DELAY = 1000L
    }

    object GATT {
        val CONNECTION_RETRY = 10
        val CONNECTION_IDLE = 5000L
        val SCAN_RETRY = 2
        val SCAN_DURATION = 1000L

        const val INVALID_VALUE = -1

        object Characteristic {
            val SENSOR = "00002014-0000-1000-8000-00805f9b34fb"
            val LED    = "00004001-0000-1000-8000-00805f9b34fb"
        }
    }

    object MQTT {
        val AUTOMATIC_RECONNECT = true
        val CONNECTION_TIMEOUT = 30

        const val SERVER_HOST_LOCAL = "localhost"
        const val SERVER_PORT_LOCAL = "8883"

        const val SERVER_HOST_CUMBERBATCH = "10.42.0.1"
        const val SERVER_PORT_CUMBERBATCH = "1883"

        val SERVER_HOST = SERVER_HOST_LOCAL
        val SERVER_PORT = SERVER_PORT_LOCAL
        val SERVER_URI = "tcp://${SERVER_HOST}:${SERVER_PORT}"

        object Topic {
            val BASE = "/sentientlight"
            val SENSOR = "$BASE/floorsensor"
            val LED = "$BASE/led"
            val MACRO = "$BASE/macro"

            object Macro {
                val RANGE = "$MACRO/range"
            }

            val TREE = "$BASE/tree"

            object Tree {
                val SET_PIXEL = "$TREE/setpixel"
                val SET_ALL = "$TREE/setall"
            }

            val CONFIGURATION = "$BASE/config"
        }
    }

    object Serial {
        val BAUD_RATE = 115_200
        val DATA_BITS = SerialPort.DATABITS_8
        val STOP_BITS = SerialPort.STOPBITS_1
        val PARITY = SerialPort.PARITY_NONE
        val FLOW_CONTROL = SerialPort.FLOWCONTROL_NONE
        val CONNECTION_TIMEOUT = 6_000
        val SEND_BYTE_DELAY = 1L
    }

    object Mapping {
        val VALUE_HISTORY = 50
    }

    object Color {
        const val TASK = ANSI_COLOR.ANSI_GREEN
        const val TASK_END = ANSI_COLOR.ANSI_GREEN
        const val GATT = ANSI_COLOR.ANSI_CYAN
        const val VALUE = ANSI_COLOR.ANSI_CYAN
        const val ERROR = ANSI_COLOR.ANSI_RED
        const val RESET = ANSI_COLOR.ANSI_RESET

        const val DEBUG = ANSI_COLOR.ANSI_GREEN

        const val CONDITION_TRIGGERED_POS = ANSI_COLOR.ANSI_GREEN_BACKGROUND + " " + ANSI_COLOR.ANSI_BLACK
        const val CONDITION_TRIGGERED_NEG = ANSI_COLOR.ANSI_YELLOW_BACKGROUND + " " + ANSI_COLOR.ANSI_BLACK
    }

    private object ANSI_COLOR {
        // ANSI colors that can be used in log messages
        const val ANSI_RESET = "\u001B[0m"
        const val ANSI_BLACK = "\u001B[30m"
        const val ANSI_RED = "\u001B[31m"
        const val ANSI_GREEN = "\u001B[32m"
        const val ANSI_YELLOW = "\u001B[33m"
        const val ANSI_BLUE = "\u001B[34m"
        const val ANSI_PURPLE = "\u001B[35m"
        const val ANSI_CYAN = "\u001B[36m"
        const val ANSI_WHITE = "\u001B[37m"

        const val ANSI_BLACK_BACKGROUND = "\u001B[40m"
        const val ANSI_RED_BACKGROUND = "\u001B[41m"
        const val ANSI_GREEN_BACKGROUND = "\u001B[42m"
        const val ANSI_YELLOW_BACKGROUND = "\u001B[43m"
        const val ANSI_BLUE_BACKGROUND = "\u001B[44m"
        const val ANSI_PURPLE_BACKGROUND = "\u001B[45m"
        const val ANSI_CYAN_BACKGROUND = "\u001B[46m"
        const val ANSI_WHITE_BACKGROUND = "\u001B[47m"
    }
}