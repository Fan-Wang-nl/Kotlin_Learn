package specialClasses

import java.util.Random

/**
 * Classes and objects in Kotlin work the same way as in most object-oriented languages:
 * a class is a blueprint, and an object is an instance of a class.
 * Usually, you define a class and then create multiple instances of that class
 */
class LuckDispatcher {                    //1
    fun getNumber() {                     //2
        var objRandom = Random()
        println(objRandom.nextInt(90))
    }
}

fun main() {
    val d1 = LuckDispatcher()             //3
    val d2 = LuckDispatcher()

    d1.getNumber()                        //4
    d2.getNumber()


    rentPrice(10, 2, 1)                     //5 Calls the function. This is when the object is actually created.

    DoAuth.takeParams("foo", "qwerty")                      //6 call the method from an object

    BigBen.getBongs(12)                                                //7 Calls the companion object method via the class name.
}


/**
 * In Kotlin you also have the object keyword. It is used to obtain a data type with a single implementation.
 * The instance is also a lazy one, which means it will be created once when the object is accessed.
 */
fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {  //1 Creates a function with parameters.

    /**
     * Here is a basic typical usage of an object expression: a simple object/properties structure.
     * Objects like this are often created in Java as anonymous class instances.
     */
    val dayRates = object {                                                     //2 Creates an object to use when calculating the result value.
        var standard: Int = 30 * standardDays
        var festivity: Int = 50 * festivityDays
        var special: Int = 100 * specialDays
    }

    val total = dayRates.standard + dayRates.festivity + dayRates.special       //3 Accesses the object's properties.

    print("Total price: $$total")                                               //4 Prints the result.

}

/**
 * You can also use the object declaration. It isn't an expression, and can't be used in a variable assignment.
 * You should use it to directly access its members, and this is similar to anonymous function
 */
object DoAuth {                                                 //1 Creates an object declaration.
    fun takeParams(username: String, password: String){         //2 Defines the object method.
        println("input Auth parameters = $username:$password")
    }
}


/**
 * An object declaration inside a class defines another useful case: the companion object.
 * Syntactically it's similar to the static methods in Java: you call object members using its class name as a qualifier.
 * If you plan to use a companion object in Kotlin, consider using a package-level function instead.
 */
class BigBen {                                  //1 Defines a class.
    companion object Bonger {                   //2 Defines a companion. Its name can be omitted.
        fun getBongs(nTimes: Int) {             //3 Defines a companion object method.
            for (i in 1 .. nTimes) {
                print("BONG ")
            }
        }
    }
}
