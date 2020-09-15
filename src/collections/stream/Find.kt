package collections.stream


/**
 * find and findLast functions return the first or the last collection element that matches the given predicate.
 * If there are no such elements, functions return null.
 */
fun main() {

    val words = listOf("Lets", "find", "something", "in", "collection", "somehow")          // 1 Defines a collection of words.

    val first = words.find { it.startsWith("some") }                                // 2 Looks for the first word starting with "some".
    val last = words.findLast { it.startsWith("some") }                             // 3 Looks for the last word starting with "some".

    val nothing = words.find { it.contains("nothing") }                             // 4 Looks for the first word containing "nothing".

    println("The first word starting with \"some\" is \"$first\"")
    println("The last word starting with \"some\" is \"$last\"")
    println("The first word containing \"nothing\" is ${nothing?.let { "\"$it\"" } ?: "null"}")
}