package day10

import kotlin.test.Test
import kotlin.test.assertEquals

object TopographicalMapTest {

    @Test
    fun `Test input part 1`() {
        val stream = javaClass.getResourceAsStream("test.txt")!!
        val parser = Parser(stream)
        val map = parser.topographicalMap
        map.findAllTrials()
        assertEquals(36, map.uniqueTrailheadPeakPairs)
    }

    @Test
    fun `Puzzle input part 1`() {
        val stream = javaClass.getResourceAsStream("input.txt")!!
        val parser = Parser(stream)
        val map = parser.topographicalMap
        map.findAllTrials()
        assertEquals(659, map.uniqueTrailheadPeakPairs)
    }

    @Test
    fun `Test input part 2`() {
        val stream = javaClass.getResourceAsStream("test.txt")!!
        val parser = Parser(stream)
        val map = parser.topographicalMap
        map.findAllTrials()
        assertEquals(81, map.numberOfTrials)
    }

    @Test
    fun `Puzzle input part 2`() {
        val stream = javaClass.getResourceAsStream("input.txt")!!
        val parser = Parser(stream)
        val map = parser.topographicalMap
        map.findAllTrials()
        assertEquals(1463, map.numberOfTrials)
    }
}