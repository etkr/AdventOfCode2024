package day03

class Memory(private val memory: String) {

    // Algebraic data types in kotlin !!!
    interface Operation
    object Do : Operation;
    object Dont : Operation;
    data class Mul(val a: Int, val b: Int) : Operation

    private val regex = Regex("""(do|don't|mul)\((\d*)?,?(\d*)?\)""")

    private fun matchOperator(op: String, a: String, b: String) = when (op) {
        "do" -> Do
        "don't" -> Dont
        "mul" -> Mul(a.toInt(), b.toInt())
        else -> throw RuntimeException()
    }

    private fun parseOperation(match: MatchResult) = with(match.destructured) {
        matchOperator(component1(), component2(), component3())
    }

    /**
     * Produce a sequence of operations from the input
     */
    fun produceOperations() = regex.findAll(memory).map(::parseOperation)

    /**
     * Part 1
     */
    fun sumOfCleanMemory() = produceOperations().filterIsInstance<Mul>().sumOf { it.a * it.b }

    /**
     * Pattern matching !!! and tail recursion in kotlin !!!
     *
     * Reducer function using tail recursion
     * - when operation is do, set enabled to true, when don't set enabled to false
     * - add multiplication operation result if enabled is set.
     */
    private tailrec fun sumOperations(list: Sequence<Operation>, accumulator: Int = 0, enabled: Boolean = true): Int =
        when (val operation = list.firstOrNull()) {
            null -> accumulator
            Do -> sumOperations(list.drop(1), accumulator, enabled = true)
            Dont -> sumOperations(list.drop(1), accumulator, enabled = false)
            is Mul if (enabled) -> sumOperations(list.drop(1), accumulator + (operation.a * operation.b), enabled)
            else -> sumOperations(list.drop(1), accumulator, enabled)
        }

    /**
     * Part 2
     */
    fun sumOfCleanMemoryWithDoInstructions() = sumOperations(produceOperations())
}