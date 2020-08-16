package machine
import java.util.*
val scanner = Scanner(System.`in`)

fun main() {
    CoffeeMachine.loadIngredients()
    CoffeeMachine.start()


}


object CoffeeMachine {
    private var totalMilk = 0
    private var totalWater = 0
    private var totalCoffeeBeans = 0
    fun loadIngredients() {
        println("Write how many ml of water the coffee machine has:")
        totalWater = scanner.nextInt()

        println("Write how many ml of milk the coffee machine has:")
        totalMilk = scanner.nextInt()

        println("Write how many grams of coffee beans the coffee machine has:")
        totalCoffeeBeans = scanner.nextInt()
    }

    fun start() {
        println("Write how many cups of coffee you will need:")
        val cupsCount = scanner.nextInt()
        countCoffeeCups(cupsCount)
    }

    fun countCoffeeCups(cupsCount: Int) {
        val milk = totalMilk / CoffeeCup.milk
        val water = totalWater / CoffeeCup.water
        val coffeeBeans = totalCoffeeBeans / CoffeeCup.coffeeBeans
        val count = Math.min(Math.min(milk, water), coffeeBeans)

        when {
            count < cupsCount -> {
                println("No, I can make only $count cups of coffee")
            }
            count == 1 -> {
                println("Yes, I can make that amount of coffee")
            }
            else -> {
                println("Yes, I can make that amount of coffee (and even ${count - 1} more than that)")
            }
        }

    }

    object CoffeeCup {
        const val water = 200
        const val milk = 50
        const val coffeeBeans = 15

        fun printIngredients(count: Int) { //TODO delete this
            println("For $count cups of coffee you will need:")
            println("${count * water} ml of water")
            println("${count * milk} ml of milk")
            println("${count * coffeeBeans} g of coffee beans")
        }
    }
}