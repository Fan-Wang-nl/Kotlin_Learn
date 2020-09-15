package coroutine
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch





/**
 * If we use Java Thread, we cannot realize the same result: executing something without blocking the main thread
 * Thread.sleep is a thread blocking function
 */
fun main() {
    val start = System.currentTimeMillis()
    MultiThread()
    val end = System.currentTimeMillis()
    println("running time :" + (end - start));

}

private fun MultiThread() {
    val AThread = Thread {
        // try to launch a new Thread

        Thread.currentThread()
        Thread.sleep(1000L) // blocking delay for 1 second (default time unit is ms)
        println("World! " + Thread.currentThread().name) //
    }
    AThread.start() //start a new thread
    //AThread.run() //the new thread started earlier will execute the code in method run.
    println("Hello, " + Thread.currentThread().name) // main thread continues
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive

    println("end!")
}