package day08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MapTest {


    @Test
    fun parseMap() {
        val input = javaClass.getResourceAsStream("test.txt") ?: throw RuntimeException("Path test.txt not found")
        val map = Parser.parse(input)
        println(map)
        assertEquals(12, map.height.last + 1)
        assertEquals(12, map.width.last + 1)
    }

    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test.txt,14", "input.txt,240")
    fun `Count antinodes`(file: String, expected: Int) {
        val input = javaClass.getResourceAsStream(file) ?: throw RuntimeException("Path $file not found")
        val map = Parser.parse(input)
        map.markAntinodes()
        val result = map.countAntinodes()
        println("------ Map ------")
        println(map)
        println("-----------------")
        assertEquals(expected, result)
    }


    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test.txt,34", "part2-test2.txt,9", "input.txt,955")
    fun `Count part2 antinodes`(file: String, expected: Int) {
        val input = javaClass.getResourceAsStream(file) ?: throw RuntimeException("Path $file not found")
        val map = Parser.parse(input)
        map.markAntinodes(recursive = true)
        val result = map.countAntinodesAndAntennas()
        println("------ Map ------")
        println(map)
        println("-----------------")

        assertEquals(expected, result)
    }
}