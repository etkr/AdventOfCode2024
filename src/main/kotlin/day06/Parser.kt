package day06

import java.io.InputStream

object Parser {

    private var guard: Guard? = null

    private fun parseChar(x: Int, y: Int, char: Char) = when (char) {
        '.' -> MapNode(x, y)
        '^' -> {
            val node = MapNode(x, y)
            guard = Guard(Direction.UP, node)
            node
        }

        '#' -> MapNode(x, y, isObstacle = true)
        else -> throw RuntimeException()
    }

    fun parse(inputStream: InputStream): Map {

        val nodes = inputStream
            .bufferedReader()
            .readLines()
            .mapIndexed { y, line -> line.mapIndexed { x, char -> parseChar(x, y, char) } }
            .flatten()

        val index = nodes.associateBy { Pair(it.x, it.y) }

        for (node in index.values) {
            index[Pair(node.x, node.y - 1)]?.let { node.up = it }
            index[Pair(node.x, node.y + 1)]?.let { node.down = it }
            index[Pair(node.x - 1, node.y)]?.let { node.left = it }
            index[Pair(node.x + 1, node.y)]?.let { node.right = it }
        }

        if (guard == null) throw RuntimeException()
        return Map(nodes, guard!!)
    }
}