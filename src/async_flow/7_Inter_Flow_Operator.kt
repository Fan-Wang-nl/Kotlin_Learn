package async_flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Intermediate flow operators
 *
 * Flows can be transformed with operators, just as you would with collections and sequences. Intermediate operators are applied to an upstream flow and return a downstream flow.
 * These operators are cold, just like flows are. A call to such an operator is not a suspending function itself. It works quickly, returning the definition of a new transformed flow.
 *
 * The basic operators have familiar names like map and filter. The important difference to sequences is that blocks of code inside these operators can call suspending functions.
 */
suspend fun performRequest(request: Int): String {
    delay(500) // imitate long-running asynchronous work
    return "response $request"
}

fun main() = runBlocking<Unit> {
    /**
     * For example, a flow of incoming requests can be mapped to the results with the map operator,
     * even when performing a request is a long-running operation that is implemented by a suspending function
     */
    (1..3).asFlow() // a flow of requests
        .map { request -> performRequest(request) }
        .collect { response -> println(response) }
}