package control_flow

/**
 * Kotlin uses == for structural comparison and === for referential comparison.
 * More precisely, a == b compiles down to a == null ? b == null : a.equals(b).
 */
fun main(args: Array<String>) {

    val authors = setOf("Shakespeare", "Hemingway", "Twain")
    val writers = setOf("Twain", "Shakespeare", "Hemingway")

    println(authors == writers)   // 1 Returns true because it calls authors.equals(writers) and sets ignore element order.
    println(authors === writers)  // 2 Returns false because authors and writers are distinct references.
}