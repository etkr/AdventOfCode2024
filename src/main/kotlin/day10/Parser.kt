package day10

import java.io.InputStream

class Parser(stream: InputStream) {

    private val map = stream
        .bufferedReader()
        .readLines()
        .flatMapIndexed(::processLine)
        .associateBy(Node::coordinates)

    private fun processLine(y: Int, line: String) = line
        .map(Char::digitToInt)
        .mapIndexed { x, height -> Node(height = height, x = x, y = y) }

    private fun link(node: Node) {
        node.up = map[node.coordinates.up]
        node.down = map[node.coordinates.down]
        node.right = map[node.coordinates.right]
        node.left = map[node.coordinates.left]
    }

    init {
        map.values.forEach(::link)
    }

    val topographicalMap get() = TopographicalMap(map)
}