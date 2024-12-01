package day01

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Path
import kotlin.test.assertEquals


class LocationIdListTest {

    private val whiteSpace = Regex("\\s+")
    private fun readInput(file: Path) = javaClass
        .getResourceAsStream(file.toString())
        .bufferedReader()
        .readLines()
        .map { it.split(whiteSpace) }
        .map { strings -> Pair(strings[0].toInt(), strings[1].toInt()) }

    @ParameterizedTest(name = "{0} should have a distance of {1} ")
    @CsvSource("test.txt,11", "input.txt,1941353")
    fun `Total distance between two locations`(file: Path, expected: Int) {
        val input = readInput(file)
        val result = LocationIdList(input).totalDistance()
        assertEquals(expected, result)
    }

    @ParameterizedTest(name = "{0} should have a similarity score of {1} ")
    @CsvSource("test.txt,31", "input.txt,22539317")
    fun `Similarity score between two locations`(file: Path, expected: Int) {
        val input = readInput(file)
        val result = LocationIdList(input).similarityScore()
        assertEquals(expected, result)
    }
}