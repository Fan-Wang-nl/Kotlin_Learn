package async_flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import util.log

/**
 * Using the List<Int> result type, means we can only return all the values at once.
 * To represent the stream of values that are being asynchronously computed,
 * we can use a Flow<Int> type just like we would use the Sequence<Int> type for synchronously computed values
 */

/**
 * A builder function for Flow type is called flow.
 * Code inside the flow { ... } builder block can suspend.
 * The simple3 function is no longer marked with suspend modifier.
 * Values are emitted from the flow using emit function.
 * Values are collected from the flow using collect function.
 */



fun simple3(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(1000) // pretend we are doing something useful here
        log("start: $i")
        emit(i) // emit next value
//        log("end: $i")
    }
}

fun flow_Test() = runBlocking<Unit> {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(1000)
        }
    }
    // Collect the flow
    simple3().collect { value -> println(value) }

}

fun main(){
    val start = System.currentTimeMillis()
    flow_Test()
    val end = System.currentTimeMillis()
    println("running time :" + (end - start));

}