package coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * In addition to the coroutine scope provided by different builders, it is possible to declare your own scope using the coroutineScope builder.
 * It creates a coroutine scope and does not complete until all launched children complete.
 *
 * runBlocking and coroutineScope may look similar because they both wait for their body and all its children to complete.
 * The main difference is that the runBlocking method blocks the current thread for waiting, while coroutineScope just suspends, releasing the underlying thread for other usages.
 * Because of that difference, runBlocking is a regular function and coroutineScope is a suspending function.
 */
fun multiScope() = runBlocking { // this: CoroutineScope
    println("Current working Thread M: " + Thread.currentThread().name)//
    launch {
        delay(200L)
        println("Task from runBlocking")
        println("Current working Thread 1: " + Thread.currentThread().name)//
    }

    coroutineScope { // Creates a coroutine scope
        launch {
            delay(500L)
            println("Task from nested launch")
            println("Current working Thread 2: " + Thread.currentThread().name)//
        }

        delay(100L)
        println("Task from coroutine scope") // This line will be printed before the nested launch
        println("Current working Thread M: " + Thread.currentThread().name)//
    }

    println("Current working Thread M: " + Thread.currentThread().name)//
    println("Coroutine scope is over") // This line is not printed until the nested launch completes
}


/**
 * Note that right after the "Task from coroutine scope" message (while waiting for nested launch) "Task from runBlocking" is executed and printed —
 * even though the coroutineScope is not completed yet.
 */
fun main() {
    val start = System.currentTimeMillis()
    multiScope()
    val end = System.currentTimeMillis()
    println("running time :" + (end - start));
}