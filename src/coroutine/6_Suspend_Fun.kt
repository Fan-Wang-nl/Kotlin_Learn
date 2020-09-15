package coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * This section covers various approaches to composition of suspending functions.
 */

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    println("Current working Thread 1: " + Thread.currentThread().name)
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    println("Current working Thread 2: " + Thread.currentThread().name)
    return 29
}

/**
 * What do we do if we need them to be invoked sequentially — first doSomethingUsefulOne and then doSomethingUsefulTwo, and compute the sum of their results?
 * We use a normal sequential invocation, because the code in the coroutine, just like in the regular code, is sequential by default.
 * The following example demonstrates it by measuring the total time it takes to execute both suspending functions:
 */
fun measureExeTime() = runBlocking{
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
}


/**
 * Concurrent using async
 * What if there are no dependencies between invocations of doSomethingUsefulOne and doSomethingUsefulTwo and we want to get the answer faster, by doing both concurrently?
 * This is where async comes to help.
 *
 * Conceptually, async is just like launch. It starts a separate coroutine which works concurrently with all the other coroutines.
 * The difference is that launch returns a Job and does not carry any resulting value, while async returns a Deferred — a light-weight non-blocking future that represents a promise to provide a result later.
 * You can use .await() on a deferred value to get its eventual result, but Deferred is also a Job, so you can cancel it if needed.
 */
fun measureExeTime2() = runBlocking{
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}


/**
 * Lazily started async
 *
 * Optionally, async can be made lazy by setting its start parameter to CoroutineStart.LAZY.
 * In this mode it only starts the coroutine when its result is required by await, or if its Job's start function is invoked.
 * Run the following example:
 */
fun measureExeTime3() = runBlocking{
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) {
            doSomethingUsefulOne()

        }
        val two = async(start = CoroutineStart.LAZY) {
            doSomethingUsefulTwo()

        }
        // some computation
        one.start() // start the first one
        two.start() // start the second one
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}


/**
 * Async-style functions
 * We can define async-style functions that invoke doSomethingUsefulOne and doSomethingUsefulTwo asynchronously using the async coroutine builder with an explicit GlobalScope reference.
 * We name such functions with the "…Async" suffix to highlight the fact that they only start asynchronous computation and one needs to use the resulting deferred value to get the result.
 */
fun measureExeTime4() = runBlocking{
    val time = measureTimeMillis {
        // we can initiate async actions outside of a coroutine
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        // but waiting for a result must involve either suspending or blocking.
        // here we use `runBlocking { ... }` to block the main thread while waiting for the result
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}

// The result type of somethingUsefulOneAsync is Deferred<Int>
fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

// The result type of somethingUsefulTwoAsync is Deferred<Int>
fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}


/**
 * Structured concurrency with async
 *
 * Let us take the Concurrent using async example and extract a function that concurrently performs doSomethingUsefulOne and doSomethingUsefulTwo and returns the sum of their results.
 * Because the async coroutine builder is defined as an extension on CoroutineScope, we need to have it in the scope and that is what the coroutineScope function provides:
 */
fun measureExeTime5() = runBlocking{
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}

suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}


/**
 * Cancellation is always propagated through coroutines hierarchy
 */
fun measureExeTime6() = runBlocking{
    try {
        failedConcurrentSum()
    } catch(e: ArithmeticException) {
        println("Computation failed with ArithmeticException")
    }
}

/**
 * Note how both the first async and the awaiting parent are cancelled on failure of one of the children (namely, two):
 */
suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}

fun main() {
    val start = System.currentTimeMillis()
//    measureExeTime()
//    measureExeTime2()//This is twice as fast, because the two coroutines execute concurrently.
//    measureExeTime3()
//    measureExeTime4()
//    measureExeTime5()
    measureExeTime6()
    val end = System.currentTimeMillis()
    println("running time :" + (end - start));

}