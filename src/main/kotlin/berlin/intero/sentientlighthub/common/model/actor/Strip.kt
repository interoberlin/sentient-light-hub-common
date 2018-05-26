package berlin.intero.sentientlighthub.common.model.actor

data class Strip(
        val index: String,
        val direction: EDirection,
        val leds: List<LED>
)