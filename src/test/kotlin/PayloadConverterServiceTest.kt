import berlin.intero.sentientlighthub.common.services.PayloadConverterService
import org.junit.Assert
import org.junit.Test

class PayloadConverterServiceTest {

    @Test
    fun test0Conversion() {
        Assert.assertEquals(0x00.toByte(), PayloadConverterService.convertStringToByte("0"))
    }

    @Test
    fun test127Conversion() {
        Assert.assertEquals(0x7F.toByte(), PayloadConverterService.convertStringToByte("127"))
    }

    @Test
    fun test255Conversion() {
        Assert.assertEquals(0xFF.toByte(), PayloadConverterService.convertStringToByte("255"))
    }

    @Test
    fun testMinus1Conversion() {
        Assert.assertEquals(0xFF.toByte(), PayloadConverterService.convertStringToByte("-1"))
    }
}