package day18

import java.util.*

data class Position(
    /**
     * H-Cost for A*
     */
    val estimatedDistanceToFinish: Int,
    val x: Int,
    val y: Int,
    /**
     * Current best path from start to position
     */
    val path: List<Position> = emptyList()
) : Comparable<Position> {

    /**
     * G-Cost for A*
     */
    val distanceFromStart get() = path.size

    /**
     * F-Cost for A*
     */
    val cost get() = distanceFromStart + estimatedDistanceToFinish

    override fun compareTo(other: Position) = cost - other.cost
    override fun equals(other: Any?) = other !is Position || other.x == x && other.y == y
    override fun hashCode() = Objects.hash(x, y)
}