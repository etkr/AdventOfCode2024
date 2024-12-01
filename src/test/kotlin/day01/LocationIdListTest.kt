package day01

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class LocationIdListTest {

    private val whiteSpace = "\\s+".toRegex()

    @ParameterizedTest(name = "{0} should have a distance of {1} ")
    @CsvSource("test.txt,11", "input.txt,1941353")
    fun `Total distance between two locations`(file: String, expected: Int) {
        val input = javaClass
            .getResourceAsStream(file)
            .bufferedReader()
            .readLines()
            .map { it.split(whiteSpace) }
            .map { strings -> Pair(strings[0].toInt(), strings[1].toInt()) }

        val result =  LocationIdList(input).totalDistance()
        assertEquals(expected, result)
    }

    @ParameterizedTest(name = "{0} should have a similarity score of {1} ")
    @CsvSource("test.txt,31", "input.txt,22539317")
    fun `Similarity score between two locations`(file: String, expected: Int) {
        val input = javaClass
            .getResourceAsStream(file)
            .bufferedReader()
            .readLines()
            .map { it.split(whiteSpace) }
            .map { strings -> Pair(strings[0].toInt(), strings[1].toInt()) }

        val result =  LocationIdList(input).similarityScore()
        assertEquals(expected, result)
    }
}