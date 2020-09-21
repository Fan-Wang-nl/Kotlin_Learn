package functions.lambda_compare

import kotlin.reflect.KFunction1
/******************************************FunctionN type in Kotlin**********************************************************/
/**
 * Kotlin allows function type, which represented as interface FunctionN<R>
 *
 *  The essence of lambda is that the function can be used as a kind of parameter
 */
fun a_original(funParam: KFunction1<Int, String>): String {
    return funParam(1)
}

/**
 * Kotlin has function type interface, but it is difficult to use it explicitly
 * Actually, we have to specify the parameters and return types of the input function, so we use lambda
 */
fun a(funParam : (Int)->String): String {
    return funParam(1)
}


fun b(param: Int): String {
    return param.toString()
}



fun main(){
    /**
     * use function b as a parameter
     * Note: :: is Function Reference, and ::b means an instance of function b
     */
    println(a(::b))
    println(a_original(::b))
    //::b means an instance of function b
    println((::b)(5))

    /******************************************Anonymous function**********************************************************/
    /**
     * Anonymous function
     * The following expressions are not correct, because anonymous function means there is no names for functions
     */
//    a(fun b2(param: Int): String {
//        return param.toString()
//    });
//    val d = fun b2(param: Int): String {
//        return param.toString()
//    }

    //x is a string
    val x = a(fun(param: Int): String {
        return param.toString()
    });
    //d is an anonymous function
    val d = fun(param: Int): String {
        return param.toString()
    }
    println(x)
    /******************************************lambda function**********************************************************/
    /**
     * if the lambda is the last parameter of a function, we can put it outside the round bracket
     */
//    view.setOnClickListener(param1, param2) { v: View ->
//        switchToNextPage()
//    }

    /**
     * if the lambda is the only parameter, we can directly omit the round bracket
     */
//    view.setOnClickListener { v: View ->
//        switchToNextPage()
//    }

    /**
     * if the lambda has only one parameter, we can also omit the parameter type declaration as well
     * Kotlin Compiler will get enough information by type inference
     * Kotlin has named the only parameter as 'it'
     */
//    view.setOnClickListener {
//        switchToNextPage()
//    }

    //f is a instance of anonymous lambda fucntion
    val f = { param: Int -> param.toString() }
    println(f(6))
}