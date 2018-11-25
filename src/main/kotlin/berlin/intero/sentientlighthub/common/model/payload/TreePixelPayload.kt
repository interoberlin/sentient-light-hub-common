package berlin.intero.sentientlighthub.common.model.payload

import berlin.intero.sentientlighthub.common.model.actor.Colors

data class TreePixelPayload(val side: String, val strip: String, val pixel: Int, val colors: Colors)
