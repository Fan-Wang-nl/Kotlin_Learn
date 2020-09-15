package classes

/**
 *Generic Classes
 *The first way to use generics in Kotlin is creating generic classes
 */

/**
 * Defines a generic class MutableStack<E> where E is called the generic type parameter.
 * At use-site, it is assigned to a specific type such as Int by declaring a MutableStack<Int>.
 */
class MutableStack<E>(vararg items: E) {              // 1

    private val elements = items.toMutableList()

    // 2 Inside the generic class, E can be used as a parameter like any other type.
    fun push(element: E) = elements.add(element)

    fun peek(): E = elements.last()                     // 3 You can also use E as a return type.

    fun pop(): E = elements.removeAt(elements.size - 1)

    fun isEmpty() = elements.isEmpty()

    fun size() = elements.size

    override fun toString() = "MutableStack(${elements.joinToString()})"
}

fun main() {
    val stack = MutableStack(0.62, 3.14, 2.7)
    stack.push(9.87)
    println(stack)

    println("peek(): ${stack.peek()}")
    println(stack)

    for (i in 1..stack.size()) {
        println("pop(): ${stack.pop()}")
        println(stack)
    }


    val stack2= mutableStackOf(0.62, 3.14, 2.7)
    println(stack2)
}

/**
 * You can also generify functions if their logic is independent of a specific type.
 * For instance, you can write a utility function to create mutable stacks
 */
fun <E> mutableStackOf(vararg elements: E) = MutableStack(*elements)