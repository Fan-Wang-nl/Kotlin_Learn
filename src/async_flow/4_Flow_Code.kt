package async_flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Flows are cold streams similar to sequences — the code inside a flow builder does not run until the flow is collected.
 */
fun simple4(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(1000)
        println("emit: " + i.toString())
        emit(i)
    }
}

/**
 * This is a key reason the simple function (which returns a flow) is not marked with suspend modifier.
 * By itself, simple() call returns quickly and does not wait for anything.
 * The flow starts every time it is collected, that is why we see "Flow started" when we call collect again.
 */
fun main() = runBlocking<Unit> {
    println("Calling simple function...")
    val flow = simple4()
    delay(2000)
    println("Calling collect...")
    flow.collect { value -> println(value) }

    /**
     * The flow starts every time it is collected, that is why we see "Flow started" when we call collect again.
     */
    println("Calling collect again...")
    flow.collect { value -> println(value) }
}