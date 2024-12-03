package day03

class Memory(private val memory: String) {

    interface Operation
    object Do : Operation;
    object Dont : Operation;
    data class Mul(val a: Int, val b: Int) : Operation

    private val regex = Regex("""(do|don't|mul)\((\d*)?,?(\d*)?\)""")

    private fun parseOperation(match: MatchResult) = with(match.destructured) {
        when (component1()) {
            "do" -> Do
            "don't" -> Dont
            "mul" -> Mul(component2().toInt(), component3().toInt())
            else -> throw RuntimeException()
        }
    }

    fun produceOperations() = regex.findAll(memory).map(::parseOperation)

    /* Part 1 */
    fun sumOfCleanMemory() = produceOperations().filterIsInstance<Mul>().sumOf { it.a * it.b }

    data class Accumulator(val value: Int = 0, val enabled: Boolean = true)

    private fun reducer(accumulator: Accumulator, operation: Operation) = when (operation) {
        Do -> Accumulator(accumulator.value, true)
        Dont -> Accumulator(accumulator.value, false)
        is Mul if (accumulator.enabled) -> Accumulator(accumulator.value + operation.a * operation.b, accumulator.enabled)
        else -> accumulator
    }

    /* Part 2 */
    fun sumOfCleanMemoryWithDoInstructions() = produceOperations().fold(Accumulator(), ::reducer).value
}