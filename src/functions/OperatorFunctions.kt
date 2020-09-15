package functions

/**
 * Certain functions can be "upgraded" to operators, allowing their calls with the corresponding operator symbol.
 */
fun main() {
    // 1 This takes the infix function from above one step further using the operator modifier.
    operator fun Int.times(str: String) = str.repeat(this)
    println(2 * "Bye ")  // 2 The operator symbol for times() is * so that you can call the function using 2 * "Bye".

    operator fun String.get(range: IntRange) = substring(range)  // 3 An operator function allows easy range access on strings.
    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str[0..14])                                          // 4 The get() operator enables bracket-access syntax.
}