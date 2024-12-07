package day07

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class EquationTest {


    @Test
    fun `Example 1 with times is 190`() {
        val equation = Equation(190, listOf(10, 19))
        assertEquals(190, equation.calcSum(listOf(Long::times)))
    }

    @Test
    fun `Example 1 with add is 29`() {
        val equation = Equation(190, listOf(10, 19))
        assertEquals(29, equation.calcSum(listOf(Long::plus)))
    }

    @Test
    fun `Example 2-1 is 3267`() {
        val equation = Equation(3267, listOf(81, 40, 27))
        assertEquals(3267, equation.calcSum(listOf(Long::times, Long::plus)))
    }

    @Test
    fun `Example 2-2 is 3267`() {
        val equation = Equation(3267, listOf(81, 40, 27))
        assertEquals(3267, equation.calcSum(listOf(Long::plus, Long::times)))
    }

    @Test
    fun `Example 2 is true`() {
        val equation = Equation(3267, listOf(81, 40, 27))
        assertTrue(equation.isTrue(listOf(Long::plus, Long::times)))
    }

    @Test
    fun `Example 3 is true`() {
        val equation = Equation(292, listOf(11 ,6 ,16 ,20))
        assertTrue(equation.isTrue(listOf(Long::plus, Long::times)))
    }

    @Test
    fun `part 2 example 1` (){
        val equation = Equation(156, listOf(15 , 6))
        assertEquals(equation.testValue, equation.calcSum(listOf(Long::concat)))
    }

    @Test
    fun `part 2 example 2` (){
        val equation = Equation(7290, listOf(6 ,8 ,6 ,15))
        assertEquals(equation.testValue, equation.calcSum(listOf(Long::times,Long::concat,Long::times)))
    }

    @Test
    fun `part 2 example 3` (){
        val equation = Equation(192, listOf(17, 8 ,14))
        assertEquals(equation.testValue, equation.calcSum(listOf(Long::concat,Long::plus)))
    }


    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test.txt,3749", "input.txt,303876485655")
    fun `Find correctly ordered updates`(file: String, expected: Long) {
        val input = javaClass.getResourceAsStream(file) ?: throw RuntimeException("Path $file not found")
        val equations = Parser.parse(input)
        val result = Equation.sumOfTrueEquations(equations)
        assertEquals(expected, result)
    }

    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test.txt,11387", "input.txt,146111650210682")
    fun `Find correctly ordered updates with concat`(file: String, expected: Long) {
        val input = javaClass.getResourceAsStream(file) ?: throw RuntimeException("Path $file not found")
        val equations = Parser.parse(input)
        val result = Equation.sumOfTrueEquationsWithConcat(equations)
        assertEquals(expected, result)
    }
}