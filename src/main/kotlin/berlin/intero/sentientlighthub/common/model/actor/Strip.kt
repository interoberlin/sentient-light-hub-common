package berlin.intero.sentientlighthub.common.model.actor

data class Strip(
        val index: String,
        val location: String,
        val leds: List<LED>
)