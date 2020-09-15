package intro_var

/**
 *there is a default package for classes
 *Then entry for application is the functions.main function which specifies non parameters and return type
 * The function println is imported implicitly, and the semicolon is oprtional
 * */

var var_global = "hello";

fun main() {
    println("Hello Kotlin")
/************************varaible  declaration*******************************/
    //define mutable variable
    var a = 0;
    //Also note that semicolons are optional
    var b = "xyz"
    println(b);

    //define readonly variable
    val x_ro = 5;
    //x_ro = 6; // this wscope_function
    //define variable type
    val name: String = "Kotlin"
    // Kotlin does not allow null value for variables, but it allows in the case below
    // Types are none null by default in Kotlin
    // reference must be explicitly marked as nullable when null value is possible.
    // add a question mark at the end of type declaration
    val namex: String? = null;
    println(name)
    if(namex != null)
        println(namex)
    else
        println("hi, I do not have a name now")
    test()



}


//function introduction.test
fun test(){
    println(var_global);
}