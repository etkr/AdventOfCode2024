package day18

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

object MapTest {

    @Test
    fun `Position equals`() {
        val a = Position(0, 71, 71)
        val b = Position(0, 71, 71)
        assertTrue { a == b }
    }

    @Test
    fun `Position not equals`() {
        val a = Position(0, 70, 71)
        val b = Position(0, 71, 71)
        assertFalse { a == b }
    }

    @Test
    fun `Test example part 1`() {
        val stream = javaClass.getResourceAsStream("test.txt")!!
        val corrupted = Parser(stream).getCorruptedSet(12)
        val map = Map(corrupted, size = 6)
        val path = map.search()
        assertEquals(22, path.size)
    }

    @Test
    fun `Test puzzle input part 1`() {
        val stream = javaClass.getResourceAsStream("input.txt")!!
        val corrupted = Parser(stream).getCorruptedSet(1024)
        val map = Map(corrupted, size = 70)
        val path = map.search()
        map.printPath(path.toSet())
        assertEquals(454, path.size)
    }

    @Test
    fun `Test example input part 2`() {
        val stream = javaClass.getResourceAsStream("test.txt")!!
        val parser = Parser(stream)
        val position = Solver.findFirstNotValid(parser.positions, 12, 6)
        val result = "${position.x},${position.y}"
        assertEquals("6,1", result)
    }

    @Test
    fun `Test puzzle input part 2`() {
        val stream = javaClass.getResourceAsStream("input.txt")!!
        val parser = Parser(stream)
        val position = Solver.findFirstNotValid(parser.positions, 1024, 70)
        val result = "${position.x},${position.y}"
        assertEquals("8,51", result)
    }
}