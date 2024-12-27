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
    val directions = sequence<Node> {
        up?.let { yield(it) }
        down?.let { yield(it) }
        right?.let { yield(it) }
        left?.let { yield(it) }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (plantId != other.plantId) return false
        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode() = Objects.hash(plantId, x, y)
    override fun toString(): String = "Node(plantId=$plantId, x=$x, y=$y)"
    private val allDirections = sequence { yieldAll(listOf(up, down, left, right)) }
    private fun isRegionEdge(node: Node?) = node == null || node.plantId != plantId
    val edgeCount get() = allDirections.count(::isRegionEdge)
}