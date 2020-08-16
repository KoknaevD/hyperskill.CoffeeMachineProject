package machine
import java.util.*
val scanner = Scanner(System.`in`)

fun main() {
    CoffeeMachine.start()
}


object CoffeeMachine {
    private var total_milk = 540
    private var total_water = 400
    private var total_coffeeBeans = 120
    private var total_disposableCups = 9
    private var total_money = 550
    private var running = true

    fun start() {

        while (running) {
            println("Write action (buy, fill, take, remaining, exit):")
            when (scanner.nextLine()!!) {
                "buy" -> buy()
                "take" -> take()
                "fill" -> fill()
                "remaining" -> status()
                "exit" -> running = false
            }
        }
    }

    private fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: > 3")
        var input = ""
        main_loop@while (scanner.hasNext()) {
            input = scanner.nextLine()!!
            when (input) {
                in "1".."3" -> break@main_loop
                "exit" -> {
                    running = false
                    return
                }
                "back" -> return
            }
        }

        val sortOfCoffee = input.toInt()
        if (!coffeeList.containsKey(sortOfCoffee)) {return}

        val currentCoffee = coffeeList.getValue(sortOfCoffee)

        when {
            total_milk < currentCoffee.milk -> println("Sorry, not enough milk!")
            total_water < currentCoffee.water -> println("Sorry, not enough water!")
            total_coffeeBeans < currentCoffee.coffeeBeans -> println("Sorry, not enough coffee beans!")
            total_disposableCups < 1 -> println("Sorry, not enough disposable cups!")
            else -> {
                println("I have enough resources, making you a coffee!")
                makeCoffee(currentCoffee)
            }
        }
    }

    private fun makeCoffee(coffee: Coffee){
        total_water -= coffee.water
        total_milk -= coffee.milk
        total_disposableCups -= 1
        total_coffeeBeans -= coffee.coffeeBeans
        total_money += coffee.price
    }

    private fun fill() {
        println("Write how many ml of water do you want to add:")
        total_water += scanner.nextInt()

        println("Write how many ml of milk do you want to add:")
        total_milk += scanner.nextInt()

        println("Write how many grams of coffee beans do you want to add:")
        total_coffeeBeans += scanner.nextInt()

        println("Write how many disposable cups of coffee do you want to add:")
        total_disposableCups += scanner.nextInt()

    }

    private fun take() {
        println("I gave you $total_money")
        total_money = 0
    }

    private fun status() {
        println("\nThe coffee machine has: ")
        println("$total_water of water")
        println("$total_milk of milk")
        println("$total_coffeeBeans of coffee beans")
        println("$total_disposableCups of disposable cups")
        println("$total_money of money\n")
    }

    private val coffeeList = mapOf(1 to Coffee(250, 0, 16, 4),
            2 to Coffee(350, 75, 20, 7),
            3 to Coffee(200, 100, 12, 6))

}

class Coffee(val water: Int, val milk: Int, val coffeeBeans: Int, val price: Int) {

}