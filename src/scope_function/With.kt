package scope_function


/**
 * with is a non-extension function that can access members of its argument concisely:
 * you can omit the instance name when referring to its members.
 */
class Configuration(var host: String, var port: Int)

fun main() {
    val configuration = Configuration(host = "127.0.0.1", port = 9000)

    with(configuration) {
        println("$host:$port")
    }

    // instead of:
    println("${configuration.host}:${configuration.port}")
}