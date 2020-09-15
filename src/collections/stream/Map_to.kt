package collections.stream


/**
 * map extension function enables you to apply a transformation to all elements in a collection.
 * It takes a transformer function as a lambda-parameter.
 */
fun main() {

    val numbers = listOf(1, -2, 3, -4, 5, -6)     // 1 Defines a collection of numbers.

    val doubled = numbers.map { x -> x * 2 }      // 2 Doubles numbers.

    val tripled = numbers.map { it * 3 }          // 3 Uses the shorter it notation to triple the numbers.

    val quared = numbers.map{y -> y *y}           //4 {1, 4, 9, 16, 25, 36}

    println("Numbers: $numbers")
    println("Doubled Numbers: $doubled")
    println("Tripled Numbers: $tripled")
    println("Tripled Numbers: $quared")
}