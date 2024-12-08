package day06

data class MapNode(
    val x: Int,
    val y: Int,
    var isObstacle: Boolean = false,
    val visitedDirections: MutableSet<Direction> = mutableSetOf()
) {
    var left: MapNode? = null
    var right: MapNode? = null
    var up: MapNode? = null
    var down: MapNode? = null

    val visited get() = visitedDirections.isNotEmpty()

    override fun toString(): String {
        if (isObstacle) return "#"
        if (visited) return "X"
        return "."
    }


}