package coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Let's extract the block of code inside launch { ... } into a separate function.
 * When you perform "Extract function" refactoring on this code, you get a new function with the suspend modifier.
 * Suspending functions can be used inside coroutines just like regular functions, but their additional feature is that they can,
 * in turn, use other suspending functions (like delay in this example) to suspend execution of a coroutine.
 */
fun main() = runBlocking {
    launch { doWorld1() }
    println("Hello,")

    test()
}

// this is your first suspending function
suspend fun doWorld() {
    //delay(1000L)
    println("World!")
}

/**
 *
 */
fun doWorld1() = runBlocking {
    delay(1000L)
    println("World!")
}

/**
 *
 */
suspend fun doWorld2() = coroutineScope {
    delay(1000L)
    println("World!")
}


/**
 * It launches 100K coroutines and, after 5 seconds
 */
fun test() = runBlocking {
    repeat(100_000) { // launch a lot of coroutines
        launch {
            delay(5000L)
            println("Current working Thread W: " + Thread.currentThread().name)//
        }
    }
}