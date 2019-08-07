package csd.gisc.ywc16

/**
 * Created by admin on 10/30/2018
 */
object Main {

    private val SUIT = arrayOf("C", "D", "H", "S")
    private val VALUE = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")

    @JvmStatic
    fun main(args: Array<String>) {
        val message = cardAt(48)
        println(message)
    }

    @Throws(IndexOutOfBoundsException::class)
    private fun cardAt(n: Int): String {
        val result: String
        val suitIndex = n / VALUE.size
        val valueIndex = n % VALUE.size

        if (suitIndex >= SUIT.size) {
            return "Out of bound"
        }

        result = SUIT[suitIndex] + VALUE[valueIndex]
        return result
    }
}