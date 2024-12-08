package day05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Path
import java.nio.file.Paths

class ManualTest {

    private fun readInput(file: Path) = javaClass
        .getResourceAsStream(file.toString())
        .bufferedReader()
        .readLines()

    @Test
    fun testParser() {
        val input = readInput(Paths.get("test.txt"))
        val manual = Parser(input).createManual()
        assertEquals(21, manual.pageOrderingRules.size)
        assertEquals(6, manual.updates.size)
    }

    companion object {
        @JvmStatic
        fun updates() = listOf(
            Arguments.of(listOf(97, 75, 47, 61, 53), listOf(75, 97, 47, 61, 53)),
            Arguments.of(listOf(61, 29, 13), listOf(61, 13, 29)),
            Arguments.of(listOf(97, 75, 47, 29, 13), listOf(97, 13, 75, 29, 47))
        )
    }

    @ParameterizedTest
    @MethodSource("updates")
    fun assertOrder(expected: List<Int>, actual: List<Int>) {
        val input = readInput(Paths.get("test.txt"))
        val manual = Parser(input).createManual()
        assertEquals(Update(expected), Update(actual).fixOrdering(manual.pageOrderingRules))
    }

    @Test
    fun incorrectlyOrderShouldBeCorrectlyOrdered() {
        val input = readInput(Paths.get("test.txt"))
        val manual = Parser(input).createManual()
        assertEquals(3, manual.fixIncorrectOrdered().count { it.isCorrectlyOrdered(manual.pageOrderingRules) })
    }

    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test.txt,143", "input.txt,6034")
    fun `Find correctly ordered updates`(file: Path, expected: Int) {
        val input = readInput(file)
        val manual = Parser(input).createManual()
        val result = manual.findCorrectlyOrderedUpdates()
        assertEquals(expected, result)
    }


    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test.txt,123", "input.txt,6305")
    fun `Fix incorrectly ordered updates`(file: Path, expected: Int) {
        val input = readInput(file)
        val manual = Parser(input).createManual()
        val result = manual.sumOfFixedIncorrectOrdered()
        assertEquals(expected, result)
    }
}