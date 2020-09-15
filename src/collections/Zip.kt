package collections

/**
 * zip function merges two given collections into a new collection.
 * By default, the result collection contains Pairs of source collection elements with the same index.
 * However, you can define own structure of the result collection element.
 *
 * The size of the result collection equals to the minimum size of a source collection.
 */
fun main() {

    val A = listOf("a", "b", "c")
    val B = listOf(1, 2, 3, 4)                     // 1 Defines two collections with different size

    val resultPairs = A zip B                      // 2 Merges them into a list of pairs. The infix notation is used here.
    val resultPairs2 = A.zip(B)
    val resultReduce = A.zip(B) { a, b -> "$a$b" } // 3 Merges them into a list of String values by the given transformation.

    println("A to B: $resultPairs")
    println("A to B: $resultPairs2")
    println("\$A\$B: $resultReduce")
}