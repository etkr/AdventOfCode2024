package day18

import java.io.InputStream

class Parser(stream: InputStream) {

    companion object {
        private fun parsePosition(string: String) = with(string.split(",")) {
            val x = component1().toInt()
            val y = component2().toInt()
            Position(0, x, y)
        }
    }

    val positions = stream
        .bufferedReader()
        .readLines()
        .map(::parsePosition)

    fun getCorruptedSet(include: Int) = positions
        .take(include)
        .toSet()
}