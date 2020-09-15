package collections.stream

/**
 * These functions return the first and the last element of the collection correspondingly.
 * You can also use them with a predicate; in this case, they return the first or the last element that matches the given predicate.
 *
 * If a collection is empty or doesn't contain elements matching the predicate, the functions throw NoSuchElementException.
 */
fun main() {

    val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1

    val first = numbers.first()                          // 2 Picks the first element.
    val last = numbers.last()                            // 3 Picks the last element.

    val firstEven = numbers.first { it % 2 == 0 }        // 4 Picks the first even element.
    val lastOdd = numbers.last { it % 2 != 0 }           // 5 icks the last odd element.

    println("Numbers: $numbers")
    println("First $first, last $last, first even $firstEven, last odd $lastOdd")


    /**
     * These functions work almost the same way with one difference: they return null if there are no matching elements.
     */
    val words = listOf("foo", "bar", "baz", "faz")         // 1
    val empty = emptyList<String>()                        // 2 Defines an empty collection.

    val first_ = empty.firstOrNull()                        // 3 Picks the first element from empty collection. It supposed to be null.
    val last_ = empty.lastOrNull()                          // 4 Picks the last element from empty collection. It supposed to be null as well.

    val firstF = words.firstOrNull { it.startsWith('f') }  // 5 Picks the first word starting with 'f'.
    val firstZ = words.firstOrNull { it.startsWith('z') }  // 6
    val lastF = words.lastOrNull { it.endsWith('f') }      // 7
    val lastZ = words.lastOrNull { it.endsWith('z') }      // 8

    println("First $first_, last $last_")
    println("First starts with 'f' is $firstF, last starts with 'z' is $firstZ")
    println("First ends with 'f' is $lastF, last ends with 'z' is $lastZ")
}