/**
 * Kotlin supports all the commonly used loops: for, while, do-while
 */
package control_flow

fun main(args: Array<String>) {
    val cakes = listOf("carrot", "cheese", "chocolate")

    /*****************************************loop: for***********************************************/
    //for in Kotlin works the same way as in most languages.
    for (cake in cakes) {                               // 1 Loops through each cake in the list.
        println("Yummy, it's a $cake cake!")
    }


    /*****************************************loop: while***********************************************/
    //while and do-while constructs work similarly to most languages as well.
    var cakesEaten = 0
    var cakesBaked = 0

    while (cakesEaten < 5) {                    // 1 Executes the block while the condition is true.
        eatACake()
        cakesEaten ++
    }

    do {                                        // 2 Executes the block first and then checking the condition.
        bakeACake()
        cakesBaked++
    } while (cakesBaked < cakesEaten)


    /*****************************************loop: Iterators***********************************************/
    //You can define your own iterators in your classes by implementing the iterator operator in them.
    val zoo = Zoo(listOf(Animal("zebra"), Animal("lion")))

    for (animal in zoo) {                                   // 3
        println("Watch out, it's a ${animal.name}")
    }
}

fun eatACake() = println("Eat a Cake")
fun bakeACake() = println("Bake a Cake")


class Animal(val name: String)

class Zoo(private val animals: List<Animal>) {

    operator fun iterator(): Iterator<Animal> {             // 1
        return animals.iterator()                           // 2
    }
}