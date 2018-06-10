package berlin.intero.sentientlighthub.common

class SentientProperties {
    object Frequency {
        const val SENSORS_SCAN_RATE = 300_000L
        const val SENSOR_READ_DELAY = 1L
        const val SENTIENT_MAPPING_DELAY = 1L
        const val SENTIENT_WRITE_DELAY = 1L

        const val UNSUCCESSFUL_TASK_DELAY = 1000L
    }

    object GATT {
        var CONNECTION_RETRY = 10
        var CONNECTION_IDLE = 5000L
        var SCAN_RETRY = 2
        var SCAN_DURATION = 1000L

        const val INVALID_VALUE = -1

        object Characteristic {
            var SENSOR = "00002014-0000-1000-8000-00805f9b34fb"
            var LED    = "00004001-0000-1000-8000-00805f9b34fb"
        }
    }

    object MQTT {
        var AUTOMATIC_RECONNECT = true
        var CONNECTION_TIMEOUT = 30

        const val SERVER_HOST_LOCAL = "localhost"
        const val SERVER_PORT_LOCAL = "8883"

        const val SERVER_HOST_CUMBERBATCH = "10.42.0.1"
        const val SERVER_PORT_CUMBERBATCH = "1883"

        var SERVER_HOST = SERVER_HOST_LOCAL
        var SERVER_PORT = SERVER_PORT_LOCAL
        var SERVER_URI = "tcp://${SERVER_HOST}:${SERVER_PORT}"

        object Topic {
            var BASE = "/sentientlight"
            var SENSOR = "$BASE/floorsensor"
            var LED = "$BASE/led"
            var CONFIGURATION = "$BASE/config"
        }
    }

    object Mapping {
        var VALUE_HISTORY = 50
    }

    object Color {
        const val TASK = ANSI_COLOR.ANSI_GREEN
        const val TASK_END = ANSI_COLOR.ANSI_GREEN
        const val GATT = ANSI_COLOR.ANSI_CYAN
        const val VALUE = ANSI_COLOR.ANSI_CYAN
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