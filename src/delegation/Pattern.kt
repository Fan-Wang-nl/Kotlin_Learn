package delegation

/**
 * Kotlin supports easy implementation of the delegation pattern on the native level without any boilerplate code.
 */
interface SoundBehavior {                                                          // 1 Defines the interface SoundBehavior with one method.
    fun makeSound()
}

class ScreamBehavior(val n:String): SoundBehavior {                                // 2 The classes ScreamBehavior and RockAndRollBehavior implement the interface and contain their own implementations of the method.
    override fun makeSound() = println("${n.toUpperCase()} !!!")
}

class RockAndRollBehavior(val n:String): SoundBehavior {                           // 2
    override fun makeSound() = println("I'm The King of Rock 'N' Roll: $n")
}


/**
 * The classes TomAraya and ElvisPresley also implement the interface, but not the method.
 * Instead, they delegate the method calls to the responsible implementation.
 * The delegate object is defined after the by keyword. As you see, no boilerplate code is required.
 */
// Tom Araya is the "singer" of Slayer
class TomAraya(n:String): SoundBehavior by ScreamBehavior(n)                       // 3


// You should know ;)
class ElvisPresley(n:String): SoundBehavior by RockAndRollBehavior(n)              // 3


/**
 * When makeSound() is called on tomAraya of type TomAraya or elvisPresley of type ElvisPresley, the call is delegated to the corresponding delegate object.
 */
fun main() {
    val tomAraya = TomAraya("Thrash Metal")
    tomAraya.makeSound()                                                           // 4
    val elvisPresley = ElvisPresley("Dancin' to the Jailhouse Rock.")
    elvisPresley.makeSound()
}