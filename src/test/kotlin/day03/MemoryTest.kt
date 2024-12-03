package day03

import day03.Memory.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Path
import java.nio.file.Paths

class MemoryTest {

    private fun readInput(file: Path) = javaClass
        .getResourceAsStream(file.toString())
        .bufferedReader()
        .readText()

    @Test
    fun regexTest(){
        val input = readInput(Paths.get("test_part2.txt"))
        val ops = Memory(input).produceOperations().toList()
        val expected = listOf(Mul(2, 4), Dont, Mul(5,5), Mul(11,8), Do, Mul(8,5))
        assertEquals(expected,ops)
    }

    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test_part1.txt,161", "input.txt,178538786")
    fun `Sum of clean memory`(file: Path, expected: Int) {
        val input = readInput(file)
        val result = Memory(input).sumOfCleanMemory()
        assertEquals(expected, result)
    }

    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test_part2.txt,48", "input.txt,102467299")
    fun `Sum of clean memory with do instructions`(file: Path, expected: Int) {
        val input = readInput(file)
        val result = Memory(input).sumOfCleanMemoryWithDoInstructions()
        assertEquals(expected, result)
    }

}