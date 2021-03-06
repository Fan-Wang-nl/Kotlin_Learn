package scope_function

/**
 * Like let, run is another scoping function from the standard library.
 * Basically, it does the same: executes a code block and returns its result.
 * The difference is that inside run the object is accessed by this.
 * This is useful when you want to call the object's methods rather than pass it as an argument.
 */
fun main() {
    fun getNullableLength(ns: String?) {
        println("for \"$ns\":")
        val len = ns?.run {                                                  // 1 Calls the given block on a nullable variable.
            println("\tis empty? " + isEmpty())                    // 2 Inside run, the object's members are accessed without its name.
            println("\tlength = $length")
            length                                                 // 3 run returns the length of the given String if it's not null.
        }
        println("\tlength = $len")
    }
    getNullableLength(null)
    getNullableLength("")

    getNullableLength("some string with Kotlin")
}