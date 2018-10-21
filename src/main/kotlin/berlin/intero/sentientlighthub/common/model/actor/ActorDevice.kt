package berlin.intero.sentientlighthub.common.model.actor

data class ActorDevice(
        val address: String,
        val port: String,
        val owner: String,
        val description: String,
        val location: String,
        val strips: List<Strip>
)