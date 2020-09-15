package delegation


/**
 * The Kotlin standard library contains a bunch of useful delegates, like lazy, observable, and others.
 * You may use them as is. For example lazyis used for lazy initialization.
 */
class LazySample {
    init {
        println("created!")            // 1 Property lazy is not initialized on object creation.
    }

    val lazyStr: String by lazy {
        println("computed!")          // 2 The first call to get() executes the lambda expression passed to lazy() as an argument and saves the result.
        "my lazy"
    }
}

fun main() {
    val sample = LazySample()         // 1
    println("lazyStr = ${sample.lazyStr}")  // 2
    println(" = ${sample.lazyStr}")  // 3 Further calls to get() return the saved result.


    /**
     * You can delegate mutable properties to a map as well.
     * In this case, the map will be modified upon property assignments. Note that you will need MutableMap instead of read-only Map.
     */
    val user = User(mapOf(
            "name" to "John Doe",
            "age"  to 25
    ))

    println("name = ${user.name}, age = ${user.age}")
}



/**
 * Property delegation can be used for storing properties in a map. This is handy for tasks like parsing JSON or doing other "dynamic" stuff.
 */
class User(val map: Map<String, Any?>) {
    val name: String by map                // 1 Delegates take values from the map by the string keys - names of properties.
    val age: Int     by map                // 1
}