package functions

/**
 * Varargs allow you to pass any number of arguments by separating them with commas.
 */
fun main() {
    fun printAll(vararg messages: String) {  // 1 The vararg modifier turns a parameter into a vararg.
        for (m in messages) println(m)
    }
    printAll("Hello", "Hallo", "Salut", "Hola", "你好")                 // 2

    fun printAllWithPrefix(vararg messages: String, prefix: String) {  // 3
        for (m in messages) println(prefix + m)
    }

    //Using named parameters, you can set a value to prefix separately from the vararg.
    printAllWithPrefix(
            "Hello", "Hallo", "Salut", "Hola", "你好",
            prefix = "Greeting: "                                          // 4
    )

    /**
     * At runtime, a vararg is just an array. To pass it along into a vararg parameter,
     * use the special spread operator * that lets you pass in *entries (a vararg of String)
     * instead of entries (an Array<String>).
     */
    fun log(vararg entries: String) {
        printAll(entries.toString())
        printAll(*entries)  // 5
    }

    log("Hello", "Hallo", "Salut", "Hola", "你好")
}