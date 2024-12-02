package day02

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Path
import java.util.stream.Stream

class ReportTest {
    private fun readInput(file: Path) = javaClass
        .getResourceAsStream(file.toString())
        .bufferedReader()
        .readLines()


    companion object {
        @JvmStatic
        fun unsafeRecords(): Stream<List<Int>> = Stream.of(listOf(1, 3, 2, 4, 5), listOf(1, 2, 7, 8, 9))
    }

    @ParameterizedTest(name = "Report {0} should be unsafe")
    @MethodSource("unsafeRecords")
    fun `Reports should be unsafe`(levels: List<Int>) {
        val report = Report(levels)
        assertFalse(report.safe())
    }

    @ParameterizedTest(name = "{0} should have a count of {1}")
    @CsvSource("test.txt,2", "input.txt,526")
    fun `Number of safe reports`(file: Path, expected: Int) {
        val input = readInput(file)
        val result = ReportList(input).numberOfSafeReports()
        assertEquals(expected, result)
    }


    @ParameterizedTest(name = "{0} should have a count of {1}")
    @CsvSource("test.txt,4", "input.txt,566")
    fun `Number of safe reports with tolerance`(file: Path, expected: Int) {
        val input = readInput(file)
        val result = ReportList(input).numberOfTolerantSafeReports()
        assertEquals(expected, result)
    }

}