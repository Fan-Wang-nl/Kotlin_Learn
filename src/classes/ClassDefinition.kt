package classes

/**
 * The class declaration consists of the class name, the class header (specifying its type parameters, the primary constructor etc.)
 * and the class body, surrounded by curly braces.
 * Both the header and the body are optional; if the class has no body, curly braces can be omitted.
 */

/**
 * Declares a class named Customer without any properties or user-defined constructors.
 * A non-parameterized default constructor is created by Kotlin automatically.
 */
class Customer                                  // 1

/**
 * Declares a class with two properties: immutable id and mutable email,
 * and a constructor with two parameters id and email.
 */
class Contact(val id: Int, var email: String)   // 2

fun main() {

    val customer = Customer()                   // 3

    //Creates an instance of the class Contact using the constructor with two arguments.
    val contact = Contact(1, "mary@gmail.com")  // 4

    println(contact.id)                         // 5
    contact.email = "jane@gmail.com"            // 6
    println(contact.email)
}