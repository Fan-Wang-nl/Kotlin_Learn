package coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * Bridging blocking and non-blocking worlds
 * This is an example mixes non-blocking delay(...) and blocking Thread.sleep(...) in the same code.
 *
 * runBlocking is a global function which can be applied anywhere, but it useless to block main thread most of the time
 */
private fun runBlocking_Test() {
    GlobalScope.launch {
        // launch a new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,") // main thread continues here immediately


    /**
     * The main thread invoking runBlocking blocks until the coroutine inside runBlocking completes.
     */
    runBlocking {
        // but this expression blocks the main thread
        delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
    }
    println("end!")
}

/**
 * runBlocking is a global function, which runs a new coroutine and blocks the current thread interruptibly until its completion.
 * This function should not be used from a coroutine.
 * It is designed to bridge regular blocking code to libraries that are written in suspending style, to be used in main functions and in tests.
 *
 * Here runBlocking<Unit> { ... } works as an adaptor that is used to start the top-level main coroutine.
 * We explicitly specify its Unit return type.
 */
fun runBlocking_Test2() = runBlocking<Unit>{// start main coroutine
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,") // main coroutine continues here immediately
    delay(2000L)      // delaying for 2 seconds to keep JVM alive
}


/**
 *  Delaying for a time while another coroutine is working is not a good approach.
 *  Let's explicitly wait (in a non-blocking way) until the background Job that we have launched is complete.
 *
 *  This improves the efficiency of the code. Note: main thread is a global cope thread
 */
fun runBlocking_Test3() = runBlocking {

    /**
     * Coroutine job is created with launch coroutine builder. It runs a specified block of code and completes on completion of this block.
     *
     * Conceptually, an execution of a job does not produce a result value. Jobs are launched solely for their side-effects.
     */
    val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
        println("Current working Thread: " + Thread.currentThread().name)
    }

    println("Hello,")
    println("Current working Thread: " + Thread.currentThread().name)
    job.join() // wait until child coroutine completes
}


/**
 * When we use GlobalScope.launch, we create a top-level coroutine. Even though it is light-weight, it still consumes some memory resources while it runs.
 * If we forget to keep a reference to the newly launched coroutine, it still runs.
 * What if the code in the coroutine hangs (for example, we erroneously delay for too long), what if we launched too many coroutines and ran out of memory? Having to manually keep references to all the launched coroutines and join them is error-prone.
 *
 * There is a better solution. We can use structured concurrency in our code.
 * Instead of launching coroutines in the GlobalScope, just like we usually do with threads (threads are always global),
 * we can launch coroutines in the specific scope of the operation we are performing.
 *
 * In this example, we have a function that is turned into a coroutine using the runBlocking coroutine builder.
 * Every coroutine builder, including runBlocking, adds an instance of CoroutineScope to the scope of its code block.
 * We can launch coroutines in this scope without having to join them explicitly,
 * because an outer coroutine (runBlocking in our example) does not complete until all the coroutines launched in its scope complete.
 * Thus, we can make our example simpler. Note: launch create a sub scope inside the runBlocking scope.
 */
fun runBlocking_Test4() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine in the scope of runBlocking
        delay(1000L)
        println("World!")
        println("Current working Thread W: " + Thread.currentThread().name)//execution in main thread
    }
    println("Hello,")
    println("Current working Thread H: " + Thread.currentThread().name)//execution in main thread
}

fun main() {

    val start = System.currentTimeMillis()
    //runBlocking_Test()
    //runBlocking_Test2()
    //runBlocking_Test3()
    runBlocking_Test4()
    val end = System.currentTimeMillis()
    println("running time :" + (end - start));

}

