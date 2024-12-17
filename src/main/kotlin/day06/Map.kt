package day06

fun List<MapNode>.deepCopy(): MutableList<MapNode> {
    val newList = mutableListOf<MapNode>()
    for (item in this) {
        newList.add(item)
    }
    return newList.map { it.copy() }.toMutableList()
}

class Map(val mapNodes: List<MapNode>, private val guard: Guard) {


    companion object {


        private fun generateMaps(map: Map) = sequence {
            for (i in 0 until map.mapNodes.size) {
                if (map.isInvalidPosition(i)) continue
                val newMap = Map(map.mapNodes.deepCopy(), Guard(map.guard.startingDirection, map.guard.startingNode))
                newMap.mapNodes[i].isObstacle = true
                yield(newMap)
            }
        }

        fun findCyclePosition(map: Map): Int {
            return generateMaps(map).toList().filter { newMap ->
                newMap.walk()
                println(newMap)
                println()
                val sfsdfsd = newMap.guard.hasCycle
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

