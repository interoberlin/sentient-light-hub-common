package berlin.intero.sentientlighthub.common.model.mapping.actions

data class Action(
        val stripID: String,
        val ledID: String,
        var value: String
): Appliable {

    override fun apply(action: () -> Unit) {
        action.invoke()
    }
}
