package berlin.intero.sentientlighthub.common

class SentientProperties {
    companion object {
        // GATT configuration
        var GATT_CONNECTION_RETRY = 10
        var GATT_CONNECTION_IDLE = 5000L
        var GATT_SCAN_RETRY = 2
        var GATT_SCAN_DURATION = 1000L

        // MQTT configuration
        var MQTT_AUTOMATIC_RECONNECT = true
        var MQTT_CONNECTION_TIMEOUT = 30
        var MQTT_SERVER_HOST = "localhost"
        var MQTT_SERVER_PORT = "8883"
        var MQTT_SERVER_URI = "tcp://${MQTT_SERVER_HOST}:${MQTT_SERVER_PORT}"

        // Scan rates / delays in milliseconds
        const val SENSORS_SCAN_RATE = 300_000L
        const val SENSOR_READ_DELAY = 1_000L

        // Logging
        const val COLOR_TASK = SentientColors.ANSI_GREEN
        const val COLOR_RESET = SentientColors.ANSI_RESET
    }
}