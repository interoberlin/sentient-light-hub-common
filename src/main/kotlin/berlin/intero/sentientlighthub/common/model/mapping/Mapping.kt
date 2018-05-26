package berlin.intero.sentientlighthub.common.model.mapping

import berlin.intero.sentientlighthub.common.model.mapping.actions.Action
import berlin.intero.sentientlighthub.common.model.mapping.conditions.Fulfillable

data class Mapping(
        val condition: Fulfillable,
        val action: Action
)
