package collections.stream

/**
 * sorted returns a list of collection elements sorted according to their natural sort order (ascending).
 * sortedBy sorts elements according to natural sort order of the values returned by specified selector function.
 */
fun main() {

    val shuffled = listOf(5, 4, 2, 1, 3)     // 1
    val natural = shuffled.sorted()          // 2
    val inverted = shuffled.sortedBy { -it } // 3

    println("Shuffled: $shuffled")
    println("Natural order: $natural")
    println("Inverted natural order: $inverted")
}