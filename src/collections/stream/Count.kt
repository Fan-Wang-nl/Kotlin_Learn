package collections.stream

/**
 * count functions returns either the total number of elements in a collection or the number of elements matching the given predicate.
 */
fun main() {

    val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1

    val totalCount = numbers.count()                     // 2 counts the total number of elements.
    val evenCount = numbers.count { it % 2 == 0 }        // 3 Counts the number of even elements.

    println("Total number of elements: $totalCount")
    println("Number of even elements: $evenCount")
}