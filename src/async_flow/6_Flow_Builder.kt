package async_flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


/**
 * The flow { ... } builder from the previous examples is the most basic one. There are other builders for easier declaration of flows:
 *
 * Various collections and sequences can be converted to flows using .asFlow() extension functions.
 * flowOf builder that defines a flow emitting a fixed set of values.
 */
fun main() = runBlocking<Unit> {
    // Convert an integer range to a flow

    //convert collections to flows
    (1..3).asFlow().collect { value -> println(value) }

    //defines a flow emitting a fixed set of values
    flowOf(1..3).collect { value -> println(value) }
}