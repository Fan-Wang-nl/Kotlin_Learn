package scope_function

/**
 * Person is define in Apply.kt
 */
//data class Person(var name: String, var age: Int, var about: String) {
//    constructor() : this("", 0, "")
//}

/**
 * also works like apply: it executes a given block and returns the object called.
 * Inside the block, the object is referenced by it, so it's easier to pass it as an argument.
 * This function is handy for embedding additional actions, such as logging in call chains.
 */
fun writeCreationLog(p: Person) {
    println("A new person ${p.name} was created.")
}

fun main() {
    val jake = Person("Jake", 30, "Android developer")   // 1 Creates a Person() object with the given property values.
            .also {                                          // 2 Applies the given code block to the object. The return value is the object itself.
                it.name = "Jay"
                writeCreationLog(it)                         // 3 Calls the logging function passing the object as an argument.
            }
    println(jake.toString())                                 //the name of the person has been changed
}