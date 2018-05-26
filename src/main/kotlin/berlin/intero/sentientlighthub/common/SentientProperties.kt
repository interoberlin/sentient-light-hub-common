package berlin.intero.sentientlighthub.common

class SentientProperties {
    companion object {
        // GATT configuration
        var GATT_CONNECTION_RETRY = 10
        var GATT_CONNECTION_IDLE = 5000L
        var GATT_SCAN_RETRY = 2
        var GATT_SCAN_DURATION = 1000L

        // Scan rates / delays in milliseconds
        const val SENSORS_SCAN_RATE = 300_000L
        const val SENSOR_READ_DELAY = 1_000L
    }
}