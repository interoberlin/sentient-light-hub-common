package berlin.intero.sentientlighthub.common.model.mapping.actions

interface Appliable {

    fun apply(action: () -> Unit)
}