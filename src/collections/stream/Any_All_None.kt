package collections.stream

/*************************************************** any, all, none *********************************************************/

/**
 * These functions check the existence of collection elements that match a given predicate.
 */

fun main() {


    val numbers = listOf(1, -2, 3, -4, 5, -6)

    /**
     * Function any returns true if the collection contains at least one element that matches the given predicate.
     */
    val anyNegative = numbers.any { it < 0 }             // 2 Checks if there are negative elements.

    val anyGT6 = numbers.any { it > 6 }                  // 3 Checks if there are elements greater than 6.

    println("Numbers: $numbers")
    println("Is there any number less than 0: $anyNegative")
    println("Is there any number greater than 6: $anyGT6")


    /**
     * Function all returns true if all elements in collection match the given predicate.
     */
    val allEven = numbers.all { it % 2 == 0 }            // 2

    val allLess6 = numbers.all { it < 6 }                // 3

    println("Numbers: $numbers")
    println("All numbers are even: $allEven")
    println("All numbers are less than 6: $allLess6")


    /**
     * Function none returns true if there are no elements that match the given predicate in the collection.
     */
    val allEven_ = numbers.none { it % 2 == 1 }           // 2

    val allLess6_ = numbers.none { it > 6 }               // 3

    println("Numbers: $numbers")
    println("All numbers are even: $allEven_")
    println("No element greater than 6: $allLess6_")

}