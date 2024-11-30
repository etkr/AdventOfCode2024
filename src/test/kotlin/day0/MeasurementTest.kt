package day0

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day0Test {

    @ParameterizedTest(name = "{0} should have {1} increasing measurements")
    @CsvSource("test.txt,7", "input.txt,1390")
    fun `Count number of increasing measurements`(file: String, expected: Int) {
        val input = this::class.java.getResourceAsStream(file).bufferedReader().readLines().map(String::toInt)
        assertEquals(expected, numberOfIncreasingMeasurements(input))
    }

    @ParameterizedTest(name = "{0} should have {1} increasing windows")
    @CsvSource("test.txt,4", "input.txt,1457")
    fun `Count number of increasing windows`(file: String, expected: Int) {
        val input = this::class.java.getResourceAsStream(file).bufferedReader().readLines().map(String::toInt)
        assertEquals(expected, numberIncreasingWindows(input, 3))
    }
}