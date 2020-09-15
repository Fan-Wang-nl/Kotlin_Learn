package async_flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.io.*
import java.nio.charset.Charset

fun getDataFlow(): Flow<String> = flow { // flow builder
    val filePath = "/Users/nt78hh/IdeaProjects/Kotlin_Learn/src/async_flow/IPC_February_Charging File.csv"
    val inputStream: InputStream = File(filePath).inputStream()

    inputStream.bufferedReader().useLines{
        // emit every line from the iterator
        lines -> lines.forEach{emit(it)}
    }
}

fun main() = runBlocking(){
    val start = System.currentTimeMillis()

    //we can process the flow line by line here
    getDataFlow().collect{str -> println(str)}

    val end = System.currentTimeMillis()
    println("running time :" + (end - start));

}
