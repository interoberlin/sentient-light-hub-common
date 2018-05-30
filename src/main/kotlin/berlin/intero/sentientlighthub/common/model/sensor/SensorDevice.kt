package berlin.intero.sentientlighthub.common.model.sensor

data class SensorDevice(
        val address: String,
        val description: String,
        val active: Boolean,
        val sensors: List<Sensor>)