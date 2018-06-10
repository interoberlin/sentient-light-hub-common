
import berlin.intero.sentientlighthub.common.services.ConfigurationService
import org.junit.Test


class ConfigurationServiceTest {

    @Test
    fun testSensorConfig() {
        ConfigurationService.loadSensorConfigFile("sensors-test.json")
        assert(ConfigurationService.sensorConfig != null)
        assert(ConfigurationService.sensorConfig?.sensorDevices?.get(0) != null)
        assert(ConfigurationService.sensorConfig?.sensorDevices?.get(0)?.address == "F6:56:9D:78:F0:2E")
        assert(ConfigurationService.sensorConfig?.sensorDevices?.get(0)?.owner == "Test")
        assert(ConfigurationService.sensorConfig?.sensorDevices?.get(0)?.description == "Maker Faire 2018 Floor Sensor large")
        assert(ConfigurationService.sensorConfig?.sensorDevices?.get(0)?.location == "")
    }
}