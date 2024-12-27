package day10

import java.util.*

data class Node(
    val height: Int,
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

        if (height != other.height) return false
        if (x != other.x) return false
        if (y != other.y) return false
        if (left != other.left) return false
        if (right != other.right) return false
        if (up != other.up) return false
        if (down != other.down) return false
        if (directions != other.directions) return false
        if (coordinates != other.coordinates) return false

        return true
    }

    override fun hashCode() = Objects.hash(height, x, y)
    override fun toString(): String = "Node(height=$height, x=$x, y=$y)"
}