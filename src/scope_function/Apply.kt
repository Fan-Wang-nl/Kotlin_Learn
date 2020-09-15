package scope_function


/**
 * apply executes a block of code on an object and returns the object itself.
 * Inside the block, the object is referenced by this. This function is handy for initializing objects.
 */
data class Person(var name: String, var age: Int, var about: String) {
    constructor() : this("x", 0, "none")
}

fun main() {
    val jake = Person()                                     // 1 Creates a Person() instance with default property values.
    println(jake.name + "\t" + jake.age + "\t" + jake.about)

    val stringDescription = jake.apply {                    // 2 Applies the code block (next 3 lines) to the instance.
        name = "Jake"                                       // 3 Inside apply, it's equivalent to jake.name = "Jake"
        age = 30
        about = "Android developer"
    }.toString()                                            // 4 The return value is the instance itself, so you can chain other operations.

    println(jake.name + "\t" + jake.age + "\t" + jake.about)
    println(stringDescription)
}