package day06


class Map(val mapNodes: List<MapNode>, private val guard: Guard) {

    companion object {

        private fun generateMaps(map: Map) = sequence {
            for (i in 0 until map.mapNodes.size) {
                if (map.isInvalidPosition(i)) continue
                val nodes = map.mapNodes.map { it.copy() }.toMutableList()
                nodes[i].isObstacle = true
                yield(Map(nodes, map.guard.copy()))
            }
        }

        fun findCyclePosition(map: Map): Int {
            return generateMaps(map).toList().filter {
                it.walk()
                println(it)
                println()
                val sfsdfsd = it.guard.hasCycle
                return@filter sfsdfsd
            }.count()
        }
    }

    fun isInvalidPosition(i: Int) = mapNodes[i].isObstacle || mapNodes[i] == guard.startingNode

    override fun toString() = buildString {
        mapNodes
            .groupBy(MapNode::y)
            .forEach {
                it.value.map(MapNode::toString).forEach(::append)
                append('\n')
            }
    }

    fun countVisitedNodes() = mapNodes.count(MapNode::visited)

    fun walk() {
        while (guard.notExited() && guard.notFoundCycles()) {
            guard.moveForward()
        }
    }
}

