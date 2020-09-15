package coroutine
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * Here we are launching a new coroutine in the GlobalScope, meaning that the lifetime of the new coroutine is limited only by the lifetime of the whole application.
 * The code in GlobalScope has the same life time with the application (main function)
 */
private fun GB_Launch() {
    /**
     * Global scope is used to launch top-level coroutines which are operating on the whole application lifetime and are not cancelled prematurely.
     *
     * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a Job.
     * The coroutine is cancelled when the resulting job is cancelled.
     */
    GlobalScope.launch {
        // launch a new coroutine in background and continue
        /**
         *  delay is a special suspending function that does not block a thread, but suspends the coroutine, and it can be only used from a coroutine.
         */
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
        println("Current working Thread W: " + Thread.currentThread().name)//

    }
    println("Hello,") // main thread continues while coroutine is delayed
    println("Current working Thread H: " + Thread.currentThread().name)//

    //delay(20000) //this will cause compiling error, because delay is a coroutine function
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive. The JVN will exit if we do not wait here

    println("end!")
}

/**
 * Global coroutines are like daemon threads
 * The following code launches a long-running coroutine in GlobalScope that prints "I'm sleeping" twice a second and then returns from the main function after some delay
 * These background coroutines will stop when JVM exiting
 */
private fun  GB_Launch2()= runBlocking {
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ..." + Thread.currentThread().name)
            delay(500L)
        }
    }
    delay(1300L) // just quit after delay
}


/**
 * it takes time to launch a coroutine, but a coroutine is very light
 */
private fun Launch_delay() = runBlocking{
    launch {
        // launch a new coroutine in background and continue

        println("World, second!") // print after delay
        println("Current working Thread W: " + Thread.currentThread().name)//

    }
    println("Hello, first") // main thread continues while coroutine is delayed
    println("Current working Thread H: " + Thread.currentThread().name)//

}


fun main() {
    val start = System.currentTimeMillis()
    //GB_Launch()
    //Launch_delay()
    GB_Launch2()
    val end = System.currentTimeMillis()
    println("running time :" + (end - start));
}

