package async_flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


/**
 * Transform operator
 *
 * Among the flow transformation operators, the most general one is called transform.
 *
 * It can be used to imitate simple transformations like map and filter, as well as implement more complex transformations. Using the transform operator, we can emit arbitrary values an arbitrary number of times.
 * For example, using transform we can emit a string before performing a long-running asynchronous request and follow it with a response:
 *
 */
suspend fun performRequest2(request: Int): String {
    delay(1000) // imitate long-running asynchronous work
    return "response $request"
}

fun main() = runBlocking<Unit> {
    (1..3).asFlow() // a flow of requests
        .transform { request ->
            emit("Making request $request")
            emit(performRequest2(request))
        }
        .collect { response -> println(response) }
}