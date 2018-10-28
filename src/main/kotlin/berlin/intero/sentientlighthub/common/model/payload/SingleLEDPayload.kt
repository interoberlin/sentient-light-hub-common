package berlin.intero.sentientlighthub.common.model.payload

data class SingleLEDPayload(
        val stripId: String,
        val ledId: String,
        val warmWhite: String,
        val coldWhite: String,
        val amber: String
)
