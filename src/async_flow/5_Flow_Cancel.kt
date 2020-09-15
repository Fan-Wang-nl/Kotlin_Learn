package async_flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Flow adheres to the general cooperative cancellation of coroutines.
 * As usual, flow collection can be cancelled when the flow is suspended in a cancellable suspending function (like delay).
 * The following example shows how the flow gets cancelled on a timeout when running in a withTimeoutOrNull block and stops executing its code
 */
fun simple5(): Flow<Int> = flow {
    for (i in 1..4) {
        delay(1000)
        println("Emitting $i")
        emit(i)
    }
}

/**
 * the output will be only 1 and 2
 */
fun main() = runBlocking<Unit> {
    withTimeoutOrNull(2500) { // Timeout after 250ms
        simple5().collect { value -> println(value) }
    }
    println("Done")
}