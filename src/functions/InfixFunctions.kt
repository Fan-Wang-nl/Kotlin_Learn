/**
 *Member functions and extensions with a single parameter can be turned into infix functions.
 * The parameter should be only one and no default value
 * Infix functions can be invoked without dot and round brackets
 */

package functions

fun main() {

    //Defines an infix extension function on Int.
    infix fun Int.times(str: String) = str.repeat(this)        // 1
    //Calls the infix function.
    println(2 times "Bye ")                                    // 2
    println(2.times("Bye"))

    //Creates a Pair by calling the infix function to from the standard library.
    var pair = "Ferrari" to "Katrina"                          // 3
    println(pair)
    pair = "xyz".to("abc")
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)   // 4 Here's your own implementation of to creatively called onto.
    val myPair = "McLaren" onto "Lucas"
    println(myPair)

    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia                                       // 5 Infix notation also works on members functions (methods)
    for(item in sophia.likedPeople)
        println(item.name)
}

class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other: Person) { likedPeople.add(other) }  // 6 The containing class becomes the first parameter.
}