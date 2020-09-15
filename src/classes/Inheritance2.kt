package classes

/*******Inheritance with Parameterized Constructor*******/

open class Tiger(val origin: String) {
    fun sayHello() {
        println("A tiger from $origin says: grrhhh!")
    }
}

/**
 * If you want to use a parameterized constructor of the superclass when creating a subclass,
 * provide the constructor arguments in the subclass declaration.
 */
class SiberianTiger : Tiger("Siberia")                  //It only has a default constructor

/**
 * Passing Constructor Arguments to Superclass
 */
class SiberianTiger2 (private val name: String): Tiger(name)

fun main() {
    val tiger: Tiger = SiberianTiger()
    tiger.sayHello()

    val sibTiger : SiberianTiger = SiberianTiger()
    sibTiger.sayHello()

    val sibTiger2 : SiberianTiger2 = SiberianTiger2("My Tiger")
    sibTiger2.sayHello();
}