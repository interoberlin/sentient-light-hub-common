package berlin.intero.sentientlighthub.common.services

import berlin.intero.sentientlighthub.common.model.actor.ActorConfig
import berlin.intero.sentientlighthub.common.model.actor.ActorDevice
import berlin.intero.sentientlighthub.common.model.mapping.MappingConfig
import berlin.intero.sentientlighthub.common.model.mapping.conditions.Fulfillable
import berlin.intero.sentientlighthub.common.model.sensor.SensorConfig
import berlin.intero.sentientlighthub.common.util.FulfillableDeserializer
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import org.apache.commons.io.IOUtils
import java.io.IOException
import java.nio.charset.Charset
import java.util.logging.Logger

object ConfigurationService {

    private val log: Logger = Logger.getLogger(ConfigurationService::class.simpleName)

    private const val sensorConfigFileName = "sensors.json"
    private const val actorConfigFileName = "actors.json"
    private const val mappingConfigFileName = "mappings.json"

    var sensorConfig: SensorConfig? = null
    var actorConfig: ActorConfig? = null
    var mappingConfig: MappingConfig? = null

    init {
        loadSensorConfigFile(sensorConfigFileName)
        loadActorConfigFile(actorConfigFileName)
        loadMappingConfigFile(mappingConfigFileName)
    }

    /**
     * Load sensor configuration from a string
     *
     * @param value json formatted string
     */
    fun loadSensorConfig(value: String) = try {
        this.sensorConfig = GsonBuilder().create().fromJson(value, SensorConfig::class.java) as SensorConfig
    } catch (ex: Exception) {
        when (ex) {
            is JsonSyntaxException -> log.severe("$ex")
            else -> throw ex
        }
    }

    /**
     * Load sensor configuration from a file
     *
     * @param value name of the configuration file
     */
    fun loadSensorConfigFile(configFileName: String) = try {
        val value = IOUtils.toString(javaClass.classLoader.getResourceAsStream(configFileName), Charset.defaultCharset())
        loadSensorConfig(value)
    } catch (ex: Exception) {
        when (ex) {
            is IOException -> log.severe("$ex")
            else -> throw ex
        }
    }

    /**
     * Load actor configuration from a string
     *
     * @param value json formatted string
     */
    fun loadActorConfig(value: String) = try {
        this.actorConfig = GsonBuilder().create().fromJson(value, ActorConfig::class.java) as ActorConfig
    } catch (ex: Exception) {
        when (ex) {
            is JsonSyntaxException -> log.severe("$ex")
            else -> throw ex
        }
    }

    /**
     * Load actor configuration from a file
     *
     * @param value name of the configuration file
     */
    fun loadActorConfigFile(configFileName: String) = try {
        val value = IOUtils.toString(javaClass.classLoader.getResourceAsStream(configFileName), Charset.defaultCharset())
        loadActorConfig(value)
    } catch (ex: Exception) {
        when (ex) {
            is IOException -> log.severe("$ex")
            else -> throw ex
        }
    }

    /**
     * Load mapping configuration from a string
     *
     * @param value json formatted string
     */
    fun loadMappingConfig(value: String) = try {
        val gson = GsonBuilder()
        gson.registerTypeAdapter(Fulfillable::class.java, FulfillableDeserializer())
        this.mappingConfig = gson.create().fromJson(value, MappingConfig::class.java) as MappingConfig
    } catch (ex: Exception) {
        when (ex) {
            is JsonSyntaxException -> log.severe("$ex")
            else -> throw ex
        }
    }

    /**
     * Load mapping configuration from a file
     *
     * @param value name of the configuration file
     */
    fun loadMappingConfigFile(configFileName: String) = try {
        val value = IOUtils.toString(javaClass.classLoader.getResourceAsStream(configFileName), Charset.defaultCharset())
        loadMappingConfig(value)
    } catch (e: IOException) {
        log.severe("$e")
    }

    /**
     * Determines an actor device by its {@param stripID} and {@param ledID}
     *
     * @param stripID strip ID
     * @param ledID LED ID
     */
    fun getActor(stripID: String?, ledID: String?): ActorDevice? {
        return actorConfig?.actorDevices?.filter { d ->
            d.strips.any { (stripIndex, _, leds) ->
                stripID != null && stripIndex == stripID && leds.any { (ledIndex) ->
                    ledID != null && ledID == ledIndex
                }
            }
        }?.firstOrNull()
    }
}
