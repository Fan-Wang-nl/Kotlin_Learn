package specialClasses

/**
 * Enum classes are used to model types that represent a finite set of distinct values, such as directions, states, modes, and so forth.
 */


/**
 * Defines a simple enum class with three enum instances. The number of instances is always finite and they are all distinct.
 */
enum class State {
    IDLE, RUNNING, FINISHED                           // 1
}

fun main() {
    val state = State.RUNNING                         // 2 Accesses an enum instance via the class name.\

    /**
     * With enums, the compiler can infer if a when-expression is exhaustive so that you don't need the else-case.
     */
    val message = when (state) {                      // 3
        State.IDLE -> "It's idle"
        State.RUNNING -> "It's running"
        State.FINISHED -> "It's finished"
    }
    println(message)

/******************************************* second demo ************************************************************/

    val red = Color.RED
    println(red)                                      // 4 The default toString returns the name of the instance, here "RED".
    println(red.containsRed())                        // 5 Calls a method on an enum instance.
    println(Color.BLUE.containsRed())                 // 6 Calls a method via enum class name.
}

/**
 * Enums may contain properties and methods like other classes, separated from the list of instances by a semicolon.
 */
enum class Color(val rgb: Int) {                      // 1 Defines an enum class with a property and a method.
    RED(0xFF0000),                               // 2 Each instance must pass an argument for the constructor parameter.
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    YELLOW(0xFFFF00);           //separated by a semicolon

    fun containsRed() = (this.rgb and 0xFF0000 != 0)  // 3 Enum class members are separated from the instance definitions by a semicolon.
}