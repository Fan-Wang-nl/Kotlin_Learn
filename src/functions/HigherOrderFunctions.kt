package functions

/**
 * A higher-order function is a function that takes another function as parameter and/or returns a function.
 */


/**
 * Declares a higher-order function. It takes two integer parameters, x and y.
 * Additionally, it takes another function operation as a parameter, which is operation in this case.
 * The operation parameters and return type are also defined in the declaration.
 */
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    //The higher order function returns the result of operation invocation with the supplied arguments.
    return operation(x, y)
}

//fun sum(x: Int, y: Int) = x + y                                     // 3

fun main() {
    // Invokes the higher-order function passing in two integer values and the function argument ::sum.
    // :: is the notation that references a function by name in Kotlin.
    val sumResult = calculate(4, 5, ::sum)

    // Invokes the higher-order function passing in a lambda as a function argument.
    val mulResult = calculate(4, 5) { a, b -> a * b }
    println("sumResult $sumResult, mulResult $mulResult")

    /***************************** Returning Functions ****************************************/
    // Invokes operation to get the result assigned to a variable. Here func becomes square which is returned by operation.
    val func = operation()
    // Invokes func. The square function is actually executed.
    println(func(2))
}


/**
 * Returning Functions
 * Declares a higher-order function that returns a function. (Int) -> Int represents the parameters and return type of the square function.
 */
fun operation(): (Int) -> Int {                                     // 1
    return ::square
}

/**
 * Declares a function matching the signature.
 */
fun square(x: Int) = x * x                                          // 2
