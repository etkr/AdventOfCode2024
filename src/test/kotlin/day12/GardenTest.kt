package day12

import kotlin.test.Test
import kotlin.test.assertEquals

object GardenTest {
    @Test
    fun `Test input part 1`() {
        val stream = javaClass.getResourceAsStream("test.txt")!!
        val parser = Parser(stream)
        val garden = parser.garden
        assertEquals(1930, garden.totalFenceCost)
    }

    @Test
    fun `Puzzle input part 1`() {
        val stream = javaClass.getResourceAsStream("input.txt")!!
        val parser = Parser(stream)
        val garden = parser.garden
        assertEquals(1533024, garden.totalFenceCost)
    }
}