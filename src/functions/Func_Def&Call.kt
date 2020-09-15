/**         Default Parameter Values, Named Arguments and return value
 *
 *
 * */


package functions

/**
 * A simple function that takes a parameter of type String and returns Unit (i.e., no return value).
 * Unit means no return value
 */
fun printMessage(message: String): Unit {                               // 1
    println(message)
}

/**
 * A function that takes a second optional parameter with default value Info.
 * he return type is omitted, meaning that it's actually Unit.
 */
fun printMessageWithPrefix(message: String, prefix: String = "Info") {  // 2
    println("[$prefix] $message")
}

/**
 * A function that returns an integer.
 */
fun sum(x: Int, y: Int): Int {                                          // 3
    return x + y
}

/**
 * A single-expression function that returns an integer (inferred)
 * It has an expression body and inferred return type:
 */
fun multiply(x: Int, y: Int) = x * y                                    // 4

fun main() {
    printMessage("Hello")                                               // 5
    printMessageWithPrefix("Hello", "Log")               // 6
    //Calls the same function omitting the second one. The default value Info is used
    printMessageWithPrefix("Hello")                             // 7
    //Calls the same function using named arguments and changing the order of the arguments
    printMessageWithPrefix(prefix = "Log", message = "Hello")            // 8
    println(sum(1, 2))                                            // 9
}