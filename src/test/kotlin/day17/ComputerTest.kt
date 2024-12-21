package day17

import kotlin.test.Test
import kotlin.test.assertEquals

object ComputerTest {

    @Test
    fun `Part 1 test input`() {
        val input = javaClass.getResourceAsStream("test.txt") ?: throw RuntimeException("Resource not found")
        val computer = Parser.parse(input)
        val result = computer.executeProgram()
        assertEquals("4,6,3,5,6,3,5,2,1,0", result)
    }

    @Test
    fun `Part 1 puzzle input`() {
        val input = javaClass.getResourceAsStream("input.txt") ?: throw RuntimeException("Resource not found")
        val computer = Parser.parse(input)
        val result = computer.executeProgram()
        assertEquals("1,7,2,1,4,1,5,4,0", result)
    }

    @Test
    fun `Part 1 example with register A = 117440 is 0,3,5,4,3,0`() {
        val program = listOf(0, 3, 5, 4, 3, 0)
        val computer = Computer(117440u, 0u, 0u, program)
        val result = computer.executeProgram()
        val expected = program.joinToString(",")
        assertEquals(expected, result)
    }

    @Test
    fun `Part 2 test input`() {
        val input = javaClass.getResourceAsStream("test2.txt") ?: throw RuntimeException("Resource not found")
        val computer = Parser.parse(input)
        val result = computer.findOutputOfSelf()
        assertEquals(117440u, result)
    }

    @Test
    fun `Part 2 puzzle input`() {
        val input = javaClass.getResourceAsStream("input.txt") ?: throw RuntimeException("Resource not found")
        val computer = Parser.parse(input)
        val result = computer.findOutputOfSelf()
        assertEquals(117440u, result)
    }

    @Test
    fun `asd`() {
        assertEquals(UInt.MAX_VALUE.toDouble(), (ULong.MAX_VALUE.toDouble() / 2) * 100 / ULong.MAX_VALUE.toDouble())
    }
}