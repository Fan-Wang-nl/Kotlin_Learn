package collections

/**
 * A map is a collection of key/value pairs, where each key is unique and is used to retrieve the corresponding value.
 * For creating maps, there are functions mapOf() and mutableMapOf().
 * A read-only view of a mutable map can be obtained by casting it to Map.
 */

const val POINTS_X_PASS: Int = 15
val EZPassAccounts: MutableMap<Int, Int> = mutableMapOf(1 to 100, 2 to 100, 3 to 100)   // 1 Creates a mutable Map.
val EZPassReport: Map<Int, Int> = EZPassAccounts                                        // 2 Creates a read-only view of the Map.

fun updatePointsCredit(accountId: Int) {
    if (EZPassAccounts.containsKey(accountId)) {                                        // 3 Checks if the Map's key exists.
        println("Updating $accountId...")
        EZPassAccounts[accountId] = EZPassAccounts.getValue(accountId) + POINTS_X_PASS  // 4 Reads the corresponding value and increments it with a constant value.
    } else {
        println("Error: Trying to update a non-existing account (id: $accountId)")
    }
}

fun accountsReport() {
    println("EZ-Pass report:")
    EZPassReport.forEach {                                                              // 5 Iterates immutable Map and prints key/value pairs.
        k, v -> println("ID $k: credit $v")
    }
}

fun main() {
    accountsReport()                                                                    // 6 Reads the account points balance, before updates.
    updatePointsCredit(1)                                                      // 7 Updates an existing account two times.
    updatePointsCredit(1)
    updatePointsCredit(5)                                                      // 8 Tries to update a non-existing account: prints an error message.
    accountsReport()                                                                    // 9 Reads the account points balance, after updates.
}