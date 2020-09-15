package collections


/**
 * When applied to a map, [] operator returns the value corresponding to the given key, or null if there is no such key in the map.
 *
 * getValue function returns an existing value corresponding to the given key or throws an exception if the key wasn't found.
 * For maps created with withDefault, getValue returns the default value instead of throwing an exception.
 */
fun main(args: Array<String>) {

    val map = mapOf("key" to 42)

    val value1 = map["key"]                                     // 1 Returns 42 because it's the value corresponding to the key "key".
    val value2 = map["key2"]                                    // 2 Returns null because "key2" is not in the map.

    val value3: Int = map.getValue("key")                       // 1 Returns 42 because it's the value corresponding to the key "key".

    val mapWithDefault = map.withDefault { k -> k.length }
    val value4 = mapWithDefault.getValue("key2")                // 3 Returns the default value because "key2" is absent. For this key it's 4.

    try {
        map.getValue("anotherKey")                              // 4 Throws NoSuchElementException because "anotherKey" is not in the map.
    } catch (e: NoSuchElementException) {
        println("Message: $e")
    }


    println("value1 is $value1")
    println("value2 is $value2")
    println("value3 is $value3")
    println("value4 is $value4")
}