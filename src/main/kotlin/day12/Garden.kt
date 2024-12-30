package day12

class Garden(private val map: Map<Coordinates, Node>) {
    val height = 0..map.keys.maxOf(Coordinates::y)
    val width = 0..map.keys.maxOf(Coordinates::x)
    val regions = map.values.map(::Region).toSet()
    val totalFenceCost get() = regions.sumOf(Region::fencePrice)
    val sidesFenceCost get() = regions.sumOf { it.sidesFencePrice }
    override fun toString() = buildString {
        height.forEach { y ->
            width.forEach { x -> map[Coordinates(x, y)]?.let { append(it.numberOfCorners) } }
            append('\n')
        }
    }
}

