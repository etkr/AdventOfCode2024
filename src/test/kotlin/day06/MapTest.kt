package day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MapTest {

    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test.txt,41", "input.txt,4988")
    fun `Count visited after exit`(file: String, expected: Int) {
        val input = javaClass.getResourceAsStream(file) ?: throw RuntimeException("Path $file not found")
        val map: Map = Parser.parse(input)
        map.walk()
        println(map)
        val result = map.countVisitedNodes()
        assertEquals(expected, result)
    }

    @Disabled
    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test.txt,6", "input.txt,4988")
    fun `Find cycles`(file: String, expected: Int) {
        val input = javaClass.getResourceAsStream(file) ?: throw RuntimeException("Path $file not found")
        val map: Map = Parser.parse(input)
        val result = Map.findCyclePosition(map)
        assertEquals(expected, result)
    }
}