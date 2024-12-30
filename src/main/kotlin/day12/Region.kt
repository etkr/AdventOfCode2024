package day12

data class Region(val plantId: Char, val nodes: Set<Node>) {
    companion object {
        private fun findRegion(node: Node): Set<Node> {
            val visited = mutableSetOf<Node>()
            node.findRegionRec(visited)
            return visited
        }

        private fun Node.findRegionRec(visited: MutableSet<Node>) {
            visited.add(this)
            directions
                .filter { !visited.contains(it) }
                .filter { plantId == it.plantId }
                .forEach { it.findRegionRec(visited) }
        }
    }

    constructor(node: Node) : this(node.plantId, findRegion(node))

    val corners get() = nodes.sumOf(Node::numberOfCorners)
    val perimeter get() = nodes.sumOf(Node::edgeCount)
    val area get() = nodes.size
    val fencePrice get() = perimeter * area
    val sidesFencePrice get() = corners * area
}