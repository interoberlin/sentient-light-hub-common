package berlin.intero.sentientlighthub.common.services

object PayloadConverterService {

    fun convertStringToByte(value: String): Byte {
        val integerValue = value.toInt()


        return integerValue.toByte();
    }
}