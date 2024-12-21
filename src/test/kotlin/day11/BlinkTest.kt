package day11

import kotlin.test.Test
import kotlin.test.assertEquals


object BlinkTest {

    @Test
    fun `splitInHalf should split int in two`() {
        assertEquals(Pair(12UL, 34UL), 1234UL.splitInHalf())
    }

    @Test
    fun TestPart1() {
        val stream = javaClass.getResourceAsStream("test.txt") ?: throw RuntimeException("File not found")
        val input = stream.bufferedReader().readText().trim().split(' ').map(String::toULong).toList()
        val count = doBlink(input, 25)
        assertEquals(55312UL, count)
    }

    @Test
    fun `Part 1 using puzzle input`() {
        val stream = javaClass.getResourceAsStream("input.txt") ?: throw RuntimeException("File not found")
        val input = stream.bufferedReader().readText().trim().split(' ').map(String::toULong).toList()
        val count = doBlink(input, 25)
        assertEquals(189092UL, count)
    }

    @Test
    fun `Part 2 using puzzle input`() {
        val stream = javaClass.getResourceAsStream("input.txt") ?: throw RuntimeException("File not found")
        val input = stream.bufferedReader().readText().trim().split(' ').map(String::toULong).toList()
        val count = doBlink(input, 75)
        assertEquals(224869647102559UL, count)
    }
}