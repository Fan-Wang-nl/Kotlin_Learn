package async_flow

fun foo(): Sequence<Int> = sequence {
    for(i in 1..3){
        Thread.sleep(1000) //pretend it's time-consuming to generate a digit
        yield(i)                //Yields a value to the Iterator being built and suspends until the next value is requested.
    }

}

fun main(){
    val start = System.currentTimeMillis()
    foo().forEach { value -> println(value) }
    val end = System.currentTimeMillis()
    println("running time :" + (end - start));

}