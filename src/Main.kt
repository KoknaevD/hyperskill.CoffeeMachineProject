package machine
import java.util.*
val scanner = Scanner(System.`in`)

fun main() {
    while (CoffeeMachine.running) {
        when (CoffeeMachine.status) {
            CoffeeMachine.Status.Ready -> println("Write action (buy, fill, take, remaining, exit):")
            CoffeeMachine.Status.CoffeeSelection -> println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: > 3")
            CoffeeMachine.Status.WaterFilling -> println("Write how many ml of water do you want to add:")
            CoffeeMachine.Status.MilkFilling -> println("Write how many ml of milk do you want to add:")
            CoffeeMachine.Status.BeansFilling -> println("Write how many g of coffee beans do you want to add:")
            CoffeeMachine.Status.CupsFilling -> println("Write how many disposable cups of coffee do you want to add:")

        }

        CoffeeMachine.input(scanner.nextLine()!!)
    }
}


object CoffeeMachine {
    enum class Status{
        Ready,
        CoffeeSelection,
        WaterFilling,
        MilkFilling,
        BeansFilling,
        CupsFilling
    }

    var status = Status.Ready
    private var total_milk = 540
    private var total_water = 400
    private var total_coffeeBeans = 120
    private var total_disposableCups = 9
    private var total_money = 550
    var running = true

    fun input(input: String) {
        when (status) {
            Status.Ready -> when (input){
                "take" -> take()
                "fill" -> changeStatusToWaterFilling()
                "buy" -> changeStatusToCoffeeSelection()
                "remaining" -> status()
                "exit" -> offMachine()
            }
            Status.CoffeeSelection -> {
                buy(input)
            }
            else -> fill(input)
        }
    }

    private fun offMachine() {
        running = false
    }

    private fun changeStatusToCoffeeSelection() {
        status = Status.CoffeeSelection
    }

    private fun changeStatusToReady() {
        status = Status.Ready
    }

    private fun changeStatusToWaterFilling() {
        status = Status.WaterFilling
    }

    private fun changeStatusToMilkFilling() {
        status = Status.MilkFilling
    }

    private fun changeStatusToBeansFilling() {
        status = Status.BeansFilling
    }

    private fun changeStatusToCupsFilling() {
        status = Status.CupsFilling
    }



    private fun buy(input: String) {

        when (input) {
            "exit" -> {
                running = false
                return
            }
            "back" -> {
                changeStatusToReady()
                return
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
        changeStatusToReady()
    }

    private fun makeCoffee(coffee: Coffee){
        total_water -= coffee.water
        total_milk -= coffee.milk
        total_disposableCups -= 1
        total_coffeeBeans -= coffee.coffeeBeans
        total_money += coffee.price
    }

    private fun fill(input: String) {
        val inputQ = input.toInt()
        when (status) {
            Status.WaterFilling -> {
                total_water += inputQ
                changeStatusToMilkFilling()
            }
            Status.MilkFilling -> {
                total_milk += inputQ
                changeStatusToBeansFilling()
            }
            Status.BeansFilling -> {
                total_coffeeBeans += inputQ
                changeStatusToCupsFilling()
            }
            Status.CupsFilling -> {
                total_disposableCups += inputQ
                changeStatusToReady()
            }
            else -> {}
        }
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

class Coffee(val water: Int, val milk: Int, val coffeeBeans: Int, val price: Int)