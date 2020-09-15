package coroutine
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.*

/**
 * Cancelling coroutine execution
 *
 * In a long-running application you might need fine-grained control on your background coroutines.
 * For example, a user might have closed the page that launched a coroutine and now its result is no longer needed and its operation can be cancelled.
 * The launch function returns a Job that can be used to cancel the running coroutine.
 */
private suspend fun CoroutineScope.Cancel_Coroutine() {
    val job = launch {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ..." + Thread.currentThread().name)
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!" + Thread.currentThread().name)
    /**
     * As soon as main invokes job.cancel, we don't see any output from the other coroutine because it was cancelled.
     * There is also a Job extension function cancelAndJoin that combines cancel and join invocations.
     */
    job.cancel() // cancels the job
    job.join() // waits for job's completion
    println("main: Now I can quit.")
}


/**
 * Cancellation is cooperative
 *
 * A coroutine code has to cooperate to be cancellable. All the suspending functions in kotlinx.coroutines are cancellable.
 * They check for cancellation of coroutine and throw CancellationException when cancelled.
 * However, if a coroutine is working in a computation and does not check for cancellation, then it cannot be cancelled, like the following example shows:
 *
 */
private suspend fun CoroutineScope.Cancel_Example2() {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

/**
 * Making computation code cancellable
 *
 * There are two approaches to making computation code cancellable.
 * The first one is to periodically invoke a suspending function that checks for cancellation. There is a yield function that is a good choice for that purpose.
 * The other one is to explicitly check the cancellation status. Let us try the latter approach.
 * Replace while (i < 5) in the previous example with while (isActive) and rerun it.
 */
private suspend fun Cancel_Example3() = runBlocking{                        //runBlocking creates a scope automatically
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // cancellable computation loop
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}


/**
 * Closing resources with finally
 *
 * Cancellable suspending functions throw CancellationException on cancellation which can be handled in the usual way.
 * For example, try {...} finally {...} expression and Kotlin use function execute their finalization actions normally when a coroutine is cancelled
 */
private suspend fun Cancel_Example4() = runBlocking{
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("job: I'm running finally")
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

/**
 * Run non-cancellable block
 *
 * Any attempt to use a suspending function in the finally block of the previous example causes CancellationException, because the coroutine running this code is cancelled.
 * Usually, this is not a problem, since all well-behaving closing operations (closing a file, cancelling a job, or closing any kind of a communication channel)
 * are usually non-blocking and do not involve any suspending functions.
 *
 * However, in the rare case when you need to suspend in a cancelled coroutine you can wrap the corresponding code in withContext(NonCancellable) {...} using withContext function and NonCancellable context
 * as the following example shows:
 */
private suspend fun Cancel_Example5() = runBlocking{
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

/**
 * time out
 *
 * The most obvious practical reason to cancel execution of a coroutine is because its execution time has exceeded some timeout.
 * While you can manually track the reference to the corresponding Job and launch a separate coroutine to cancel the tracked one after delay,
 * there is a ready to use withTimeout function that does it. Look at the following example:
 */
private suspend fun Cancel_Example6() = runBlocking{
    try {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
    }catch (e: TimeoutCancellationException)
    {
        e.printStackTrace()
    }
}

/**
 * withTimeoutOrNull will not cause an exception
 */
private suspend fun Cancel_Example7() = runBlocking{
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
        "Done" // will get cancelled before it produces this result
    }
    println("Result is $result")
}

suspend fun main()  {
    val start = System.currentTimeMillis()
    //Cancel_Coroutine()
//    Cancel_Example2()
//    Cancel_Example3()
//    Cancel_Example4()
//    Cancel_Example5()
//    Cancel_Example6()
    Cancel_Example7()
    val end = System.currentTimeMillis()
    println("running time :" + (end - start));

}

