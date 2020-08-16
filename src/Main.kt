package machine
import java.util.*
val scanner = Scanner(System.`in`)

fun main() {
    CoffeeMachine.start()


}


object CoffeeMachine {
    private var total_milk = 0
    private var total_water = 0
    private var total_coffeeBeans = 0
    private var total_disposableCups = 0
    private var total_money = 0


    fun start() {

        while (true) {
            status()
            println("Write action (buy, fill, take):")
            val action = scanner.next()!!
            when (action) {
                "buy" -> buy()
                "take" -> take()
                "fill" -> fill()
            }
        }
    }

    fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: > 3")
        val sortOfCoffee = scanner.nextInt()
        if (!coffeeList.containsKey(sortOfCoffee)) {return}

        val currentCoffee = coffeeList.getValue(sortOfCoffee)


    }

    private fun fill() {
        println("Write how many ml of water do you want to add:")
        total_water = scanner.nextInt()

        println("Write how many ml of milk do you want to add:")
        total_milk = scanner.nextInt()

        println("Write how many grams of coffee beans do you want to add:")
        total_coffeeBeans = scanner.nextInt()

        println("Write how many disposable cups of coffee do you want to add:")
        total_disposableCups = scanner.nextInt()

    }

    private fun take() {
        println("I gave you $total_money")
        total_money = 0
    }

    private fun status() {
        println("The coffee machine has: ")
        println("$total_water of water")
        println("$total_milk of milk")
        println("$total_coffeeBeans of coffee beans")
        println("$total_disposableCups of disposable cups")
        println("$total_money of money\n")
    }

//    fun countCoffeeCups(cupsCount: Int) {
//        val milk = total_Milk / CoffeeCup.milk
//        val water = total_Water / CoffeeCup.water
//        val coffeeBeans = total_CoffeeBeans / CoffeeCup.coffeeBeans
//        val count = Math.min(Math.min(milk, water), coffeeBeans)
//
//        when {
//            count < cupsCount -> {
//                println("No, I can make only $count cups of coffee")
//            }
//            count == 1 -> {
//                println("Yes, I can make that amount of coffee")
//            }
//            else -> {
//                println("Yes, I can make that amount of coffee (and even ${count - 1} more than that)")
//            }
//        }
//
//    }

    val coffeeList = mapOf(1 to Coffee(200, 0, 16, 4),
            2 to Coffee(350, 75, 20, 7),
            3 to Coffee(200, 100, 12, 6))

}

class Coffee(val water: Int, val milk: Int, val coffeeBeans: Int, val price: Int) {

}