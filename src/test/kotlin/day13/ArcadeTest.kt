package day13

import kotlin.test.Test
import kotlin.test.assertEquals

object ArcadeTest {

    @Test
    fun `Parser test claw machine`() {
        val input = javaClass.getResourceAsStream("test.txt") ?: throw RuntimeException("Resource not found")
        val arcade = Parser().parse(input)
        assertEquals(4, arcade.clawMachines.size)
    }

    @Test
    fun `Part 1 example 1 should require 280 tokens`() {
        val clawMachine = ClawMachine(
            buttonA = Button(x = 94, y = 34),
            buttonB = Button(x = 22, y = 67),
            prize = Prize(x = 8400, y = 5400)
        )
        val result = clawMachine.tokensToPrize()
        assertEquals(280, result)
    }

    @Test
    fun `Part 1 test result`() {
        val input = javaClass.getResourceAsStream("test.txt") ?: throw RuntimeException("Resource not found")
        val arcade = Parser().parse(input)
        val result = arcade.totalTokensToPrize()
        assertEquals(480, result)
    }

    @Test
    fun `Part 1 puzzle input`() {
        val input = javaClass.getResourceAsStream("input.txt") ?: throw RuntimeException("Resource not found")
        val arcade = Parser().parse(input)
        val result = arcade.totalTokensToPrize()
        assertEquals(36250, result)
    }

    @Test
    fun `Part 2 test result`() {
        val input = javaClass.getResourceAsStream("test.txt") ?: throw RuntimeException("Resource not found")
        val arcade = Parser(enablePrecisionMode = true).parse(input)
        val result = arcade.totalTokensToPrize()
        assertEquals(875318608908, result)
    }

    @Test
    fun `Part 2 example 1`() {
        val clawMachine = ClawMachine(
            buttonA = Button(x = 94, y = 34),
            buttonB = Button(x = 22, y = 67),
            prize = Prize(x = 10000000008400, y = 10000000005400)
        )
        val result = clawMachine.tokensToPrize()
        assertEquals(0, result)
    }

    @Test
    fun `Part 2 example 2`() {
        val clawMachine = ClawMachine(
            buttonA = Button(x = 26, y = 66),
            buttonB = Button(x = 67, y = 21),
            prize = Prize(x = 10000000012748, y = 10000000012176)
        )
        val result = clawMachine.tokensToPrize()
        assertEquals(459236326669, result)
    }

    @Test
    fun `Part 2 example 3`() {
        val clawMachine = ClawMachine(
            buttonA = Button(x = 17, y = 86),
            buttonB = Button(x = 84, y = 37),
            prize = Prize(x = 10000000007870, y = 10000000006450)
        )
        val result = clawMachine.tokensToPrize()
        assertEquals(0, result)
    }

    @Test
    fun `Part 2 example 4`() {
        val clawMachine = ClawMachine(
            buttonA = Button(x = 69, y = 23),
            buttonB = Button(x = 27, y = 71),
            prize = Prize(x = 10000000018641, y = 10000000010279)
        )
        val result = clawMachine.tokensToPrize()
        assertEquals(416082282239, result)
    }

    @Test
    fun `Part 2 puzzle input`() {
        val input = javaClass.getResourceAsStream("input.txt") ?: throw RuntimeException("Resource not found")
        val arcade = Parser(enablePrecisionMode = true).parse(input)
        val result = arcade.totalTokensToPrize()
        assertEquals(83232379451012, result)
    }
}
