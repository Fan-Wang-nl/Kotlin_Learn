package functions


/**
 * Lambda functions ("lambdas") are a simple way to create functions ad-hoc.
 * Lambdas can be denoted very concisely in many cases thanks to type inference and the implicit it variable.
 * Java 8 also does introduce Lambda functions
 */
fun main() {
    // All examples create a function object that performs upper-casing.
    // So it's a function from String to String

    /**
     * A lambda in all its glory, with explicit types everywhere.
     * The lambda is the part in curly braces, which is assigned to a variable of type (String) -> String (a function type).
     */
    val upperCase1: (String) -> String = { str: String -> str.toUpperCase() } // 1

    /**
     * Type inference inside lambda: the type of the lambda parameter is inferred from the type of the variable it's assigned to.
     */
    val upperCase2: (String) -> String = { str -> str.toUpperCase() }         // 2

    /**
     * Type inference outside lambda: the type of the variable is inferred from the type of the lambda parameter and return value.
     */
    val upperCase3 = { str: String -> str.toUpperCase() }                     // 3

    /**
     * You cannot do both together, the compiler has no chance to infer the type that way.
     */
    // val upperCase4 = { str -> str.toUpperCase() }                          // 4

    /**
     * For lambdas with a single parameter, you don't have to explicitly name it.
     * Instead, you can use the implicit it variable. This is especially useful when the type of it can be inferred (which is often the case).
     */
    val upperCase5: (String) -> String = { it.toUpperCase() }                 // 5

    //val upperCaseT = { it : String -> it.toUpperCase()}

    /**
     * If your lambda consists of a single function call, you may use function pointers (::) .
     */
    val upperCase6: (String) -> String = String::toUpperCase                  // 6

    println(upperCase1("hello"))
    println(upperCase2("hello"))
    println(upperCase3("hello"))
    println(upperCase5("hello"))
    println(upperCase6("hello"))
}