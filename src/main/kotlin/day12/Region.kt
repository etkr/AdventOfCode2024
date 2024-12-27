package day12

data class Region(val plantId: Char, val nodes: MutableSet<Node> = mutableSetOf<Node>()) {

    constructor(node: Node) : this(node.plantId) {
        findRegion(node)
    }

    private fun findRegion(node: Node) {
        nodes.add(node)
        node.directions
            .filter(::notVisited)
            .filter(::plantIdEquals)
            .forEach(::findRegion)
    }

    private fun notVisited(node: Node) = !nodes.contains(node)
    private fun plantIdEquals(node: Node) = plantId == node.plantId

    val perimeter get() = nodes.sumOf(Node::edgeCount)
    val area get() = nodes.size
    val fencePrice get() = perimeter * area
}