package day00

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MeasurementTest {

    @ParameterizedTest(name = "{0} should have {1} increasing measurements")
    @CsvSource("test.txt,7", "input.txt,1390")
    fun `Count number of increasing measurements`(file: String, expected: Int) {
        val input = javaClass.getResourceAsStream(file).bufferedReader().readLines().map(String::toInt)
        val result = numberOfIncreasingMeasurements(input)
        assertEquals(expected, result)
    }

    @ParameterizedTest(name = "{0} should have {1} increasing windows")
    @CsvSource("test.txt,5", "input.txt,1457")
    fun `Count number of increasing windows`(file: String, expected: Int) {
        val input = javaClass.getResourceAsStream(file).bufferedReader().readLines().map(String::toInt)
        val result = numberIncreasingWindows(input, 3)
        assertEquals(expected, result)
    }
}