package berlin.intero.sentientlighthub.common.model.sensor

data class Sensor(
        val index: Int,
        val checkerboardID: String,
        var active: Boolean = false
)
