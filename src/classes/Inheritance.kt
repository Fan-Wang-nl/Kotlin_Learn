package classes

//Kot
/**
 * Kotlin classes are final by default.
 * If you want to allow the class inheritance, mark the class with the open modifier.
 */
open class Dog {                //
    /**
     * Kotlin methods are also final by default.
     * As with the classes, the open modifier allows overriding them.
     */
    open fun sayHello() {       //
        println("wow wow!")
    }
}

/**
 * A class inherits a superclass when you specify the : SuperclassName() after its name.
 * The empty parentheses () indicate an invocation of the superclass default constructor.
 */
class Yorkshire : Dog() {
    override fun sayHello() {   // Overriding methods or attributes requires the override modifier.
        println("wif wif!")
    }
}

fun main() {
    val dog: Dog = Yorkshire()
    dog.sayHello()
}