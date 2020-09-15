package async_flow

import kotlinx.coroutines.*

/**
 * When these values are computed by asynchronous code we can mark the simple function with a suspend modifier,
 * so that it can perform its work without blocking and return the result as a list
 */
suspend fun simple(): List<Int> {
    delay(1000) // pretend we are doing something asynchronous here
    return listOf(1, 2, 3)
}

/**
 * this coroutines help to reduce delay
 * but we can only return all the values at once using the List<Int> result type,.
 */
fun main() = runBlocking<Unit> {
    val start = System.currentTimeMillis()
    simple().forEach { value -> println(value) }    //This code prints the numbers after waiting for 5 seconds.
    val end = System.currentTimeMillis()
    println("running time :" + (end - start));

}