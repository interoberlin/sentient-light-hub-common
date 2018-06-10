package berlin.intero.sentientlighthub.common.model.sensor

data class Cable(
        val name: String,
        var enabled: Boolean = true,
        val sensors: List<Sensor>)