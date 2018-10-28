package berlin.intero.sentientlighthub.common.model.payload

data class RangeLEDPayload(
        val stripId: String,
        val startLedId: String,
        val stopLedId: String,
        val warmWhite: String,
        val coldWhite: String,
        val amber: String
)
