import java.util.*
val scanner = Scanner(System.`in`)

fun main() {
    println("Write how many cups of coffee you will need:")
    val cupsCount = scanner.nextInt()
    CoffeeCup.printIngredients(cupsCount)

}



object CoffeeCup {
    private const val water = 200
    private const val milk = 50
    private const val coffeeBeans = 15

    fun printIngredients(count: Int) {
        println("For $count cups of coffee you will need:")
        println("${count * water} ml of water")
        println("${count * milk} ml of milk")
        println("${count * coffeeBeans} g of coffee beans")
    }
}