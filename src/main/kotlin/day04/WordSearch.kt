package day04

val xmas = arrayOf('X', 'M', 'A', 'S')
val smax = xmas.reversedArray()

fun Array<Char>.isXmas(): Boolean {
    return this.contentEquals(xmas) || this.contentEquals(smax)
}

fun checkDiagonals(matrix: Matrix): Int {
    val a = if (matrix.diagonal().isXmas()) 1 else 0
    val b = if (matrix.otherDiagonal().isXmas()) 1 else 0
    return a + b
}

fun isCrossMasRec(matrix: Matrix, count: Int = 0): Boolean {
    if (matrix.isCrossMasArray()) return true;
    if (count == 4) return false
    return isCrossMasRec(matrix.rotate90(3), count + 1)
}

data class Matrix(private val chars: Array<Array<Char>>) {

    constructor(input: List<String>) : this(input.map { it.toCharArray().toTypedArray() }.toTypedArray())

    fun view(row: Int, col: Int, size: Int = 4): Matrix {
        val array = arrayOf(CharArray(size), CharArray(size), CharArray(size), CharArray(size))
        for (i in 0 until size) {
            for (j in 0 until size) {
                array[i][j] = chars[col + i][row + j]
            }
        }
        return Matrix(array.map { it.toTypedArray() }.toTypedArray());
    }

    fun isCrossMasArray(): Boolean {
        return chars[0][0] == 'M' &&
                chars[0][2] == 'S' &&
                chars[1][1] == 'A' &&
                chars[2][0] == 'M' &&
                chars[2][2] == 'S'
    }

    fun isCrossMas(): Boolean = isCrossMasRec(this)

    val height get() = chars.size
    val width get() = chars[0].size

    private fun generateWindowed(size: Int) = sequence {
        for (col in 0..width - size) {
            for (row in 0..height - size) {
                yield(view(row, col, size))
            }
        }
    }

    fun otherDiagonal() = arrayOf(chars[0][3], chars[1][2], chars[2][1], chars[3][0])

    fun diagonal() = arrayOf(chars[0][0], chars[1][1], chars[2][2], chars[3][3])


    fun countXmas(): Int {
        val diagonals = generateWindowed(4).sumOf(::checkDiagonals)
        val rows = generateRows(4).count(Array<Char>::isXmas)
        val cols = generateCols()
            .flatMap { it.toList().windowed(4).map(List<Char>::toTypedArray) }
            .count(Array<Char>::isXmas)
        return diagonals + rows + cols
    }

    private fun generateCols() = sequence {
        val sdf = mutableListOf<Char>()
        for (i in 0 until width) {
            for (j in 0 until height) {
                sdf.add(chars[j][i])
            }
            yield(sdf.toTypedArray())
            sdf.clear()
        }

    }

    private fun generateRows(size: Int) = chars
        .flatMap { it.toList().windowed(size) }
        .map { it.toTypedArray() }.asSequence()


    // Stolen from https://www.geeksforgeeks.org/rotate-a-matrix-by-90-degree-in-clockwise-direction-without-using-any-extra-space/
    fun rotate90(size: Int): Matrix {

        // Initialize the result matrix with zeros
        val array = arrayOf(CharArray(size), CharArray(size), CharArray(size), CharArray(size))

        // Flip the matrix clockwise using nested loops
        for (i in 0 until size) {
            for (j in 0 until size) {
                array[j][size - i - 1] = chars[i][j];
            }
        }

        return Matrix(array.map { it.toTypedArray() }.toTypedArray());
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        return chars.contentDeepEquals(other.chars)
    }

    override fun hashCode(): Int {
        return chars.contentDeepHashCode()
    }

    fun countCrossMas() = generateWindowed(3).count(Matrix::isCrossMas)

}


