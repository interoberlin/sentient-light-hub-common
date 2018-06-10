package berlin.intero.sentientlighthub.common.model.sensor

data class SensorDevice(
        val address: String,
        val owner: String,
        val description: String,
        val location: String,
        val enabled: Boolean = true,
        val cables: List<Cable>)