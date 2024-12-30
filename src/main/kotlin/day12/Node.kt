package day12

import java.util.*

data class Node(
    val plantId: Char,
    val x: Int,
    val y: Int,
    var left: Node? = null,
    var right: Node? = null,
    var up: Node? = null,
    var down: Node? = null,
) {
    val coordinates get() = Coordinates(x, y)


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (plantId != other.plantId) return false
        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    val upLeft get() = up?.left
    val upRight get() = up?.right
    val downLeft get() = down?.left
    val downRight get() = down?.right

    fun Triple<Node?, Node?, Node?>.isInsideCorner() =
        first?.plantId == plantId
                && second?.plantId == plantId
                && third?.plantId != plantId

    fun Triple<Node?, Node?, Node?>.isOutsideCorner() = isRegionEdge(first) && isRegionEdge(second)

    val directions = sequence<Node> {
        up?.let { yield(it) }
        down?.let { yield(it) }
        right?.let { yield(it) }
        left?.let { yield(it) }
    }

    private val allDirections = sequence { yieldAll(listOf(up, down, left, right)) }

    val sideTriples = sequence {
        yield(Triple(up, left, upLeft))
        yield(Triple(up, right, upRight))
        yield(Triple(down, left, downLeft))
        yield(Triple(down, right, downRight))
    }

    val numberOfCorners get() = sideTriples.count { it.isOutsideCorner() || it.isInsideCorner() }
    val edgeCount get() = allDirections.count(::isRegionEdge)

    override fun hashCode() = Objects.hash(plantId, x, y)
    override fun toString(): String = "Node(plantId=$plantId, x=$x, y=$y)"
    private fun isRegionEdge(node: Node?) = node == null || node.plantId != plantId
}