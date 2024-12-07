package day07

import java.util.*


fun Long.concat(other: Long) = (this.toString() + other.toString()).toLong()

data class Equation(val testValue: Long, val operators: List<Long>) {

    companion object {
        // Part 1
        fun sumOfTrueEquations(equations: List<Equation>) =
            equations
                .filter { it.isTrue(listOf(Long::plus, Long::times)) }
                .sumOf(Equation::testValue)

        // Part 2
        fun sumOfTrueEquationsWithConcat(equations: List<Equation>) =
            equations
                .filter { it.isTrue(listOf(Long::plus, Long::times, Long::concat)) }
                .sumOf(Equation::testValue)

        private fun applyFunction(previous: Long, pair: Pair<Long, (Long, Long) -> Long>): Long {
            val (next, function) = pair
            return function(previous, next)
        }
    }

    fun calcSum(addOrMult: Collection<(Long, Long) -> Long>) =
        operators
            .drop(1)
            .zip(addOrMult)
            .fold(operators.first(), Companion::applyFunction)

    fun isTrue(operatorFunctions: List<(Long, Long) -> Long>) =
        operatorFunctions
            .product(operators.size) // product function similar to itertools.product from python
            .map(::calcSum)
            .any { it == testValue }
}

/**
 * Credits deadshot, 2020
 * https://stackoverflow.com/questions/63433335/java-alternative-of-product-function-of-python-form-itertools
 */
fun <T> Collection<T>.product(r: Int): List<Collection<T>> {
    var result = Collections.nCopies<Collection<T>>(1, emptyList())
    for (pool in Collections.nCopies(r, LinkedHashSet(this))) {
        val temp: MutableList<Collection<T>> = ArrayList()
        for (x in result) {
            for (y in pool) {
                val z: MutableCollection<T> = ArrayList(x)
                z.add(y)
                temp.add(z)
            }
        }
        result = temp
    }
    return result
}