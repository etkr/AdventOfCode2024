package day10

import Util.copyAppend

data class Trial(val nodes: Array<Node> = emptyArray<Node>()) {
    internal fun copyWith(node: Node): Trial = Trial(this.nodes.copyAppend(node))
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Trial

        return nodes.contentEquals(other.nodes)
    }

    val trailheadPeakPair get() = Pair(nodes.first(), nodes.last())
    override fun hashCode() = nodes.contentHashCode()
    fun contains(node: Node) = nodes.contains(node)
    fun notContains(node: Node) = !nodes.contains(node)
    override fun toString() = nodes.contentToString()
}
