package day04

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.test.assertFalse

class MatrixTest {

    private fun readInput(file: Path) = javaClass
        .getResourceAsStream(file.toString())
        .bufferedReader()
        .readLines()


    @Test
    fun testView() {
        val lines = readInput(Paths.get("test.txt"))
        val matrix = Matrix(lines)
        val view = matrix.view(0, 0)
        val expected = Matrix(
            arrayOf(
                arrayOf('M', 'M', 'M', 'S'),
                arrayOf('M', 'S', 'A', 'M'),
                arrayOf('A', 'M', 'X', 'S'),
                arrayOf('M', 'S', 'A', 'M')

            )
        )
        assertEquals(expected, view)
    }

    companion object {
        @JvmStatic
        fun matrixViews() = listOf(
            Arguments.of(
                2,
                arrayOf(
                    arrayOf('S', 'X', 'S', 'X'),
                    arrayOf('S', 'A', 'M', 'A'),
                    arrayOf('M', 'A', 'M', 'M'),
                    arrayOf('S', 'A', 'S', 'X')
                )
            ),
            Arguments.of(
                2,
                arrayOf(
                    arrayOf('S', 'X', 'S', 'S'),
                    arrayOf('S', 'A', 'A', 'A'),
                    arrayOf('M', 'M', 'M', 'M'),
                    arrayOf('X', 'A', 'S', 'X')
                )
            ),
            Arguments.of(
                0,
                arrayOf(
                    arrayOf('X', 'M', 'A', 'S'),
                    arrayOf('M', 'M', 'A', 'A'),
                    arrayOf('A', 'M', 'A', 'M'),
                    arrayOf('S', 'A', 'M', 'X')
                )
            )

        )
    }

    @Test
    fun testIsCrossMas() {
        val matrix = Matrix(
            arrayOf(
                arrayOf('M', '/', 'S'),
                arrayOf('/', 'A', '/'),
                arrayOf('M', '/', 'S'),
            )
        )
        assertTrue(matrix.isCrossMas())
    }

    @Test
    fun testIsNotCrossMas() {
        val matrix = Matrix(
            arrayOf(
                arrayOf('M', 'M', 'S'),
                arrayOf('M', 'M', 'A'),
                arrayOf('M', 'M', 'S'),
            )
        )
        assertFalse(matrix.isCrossMas())
    }

    @ParameterizedTest
    @MethodSource("matrixViews")
    fun rotateAndCheck(expected: Int, multiArray: Array<Array<Char>>) {
        assertEquals(expected, checkDiagonals(Matrix(multiArray)))
    }

    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test.txt,18", "input.txt,2583")
    fun `Find XMAS`(file: Path, expected: Int) {
        val input = readInput(file)
        val result = Matrix(input).countXmas()
        assertEquals(expected, result)
    }

    @ParameterizedTest(name = "{0} should have a sum of {1}")
    @CsvSource("test.txt,9", "input.txt,1978")
    fun `Find crossMas`(file: Path, expected: Int) {
        val input = readInput(file)
        val result = Matrix(input).countCrossMas()
        assertEquals(expected, result)
    }
}