package berlin.intero.sentientlighthub.common.model

import java.util.*

data class MQTTEvent(val topic: String, val value: String, val date:Date = Date())