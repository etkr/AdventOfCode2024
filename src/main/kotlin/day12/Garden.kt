package day12

class Garden(private val map: Map<Coordinates, Node>) {
    val regions = map.values.map(::Region).toSet()
    val totalFenceCost get() = regions.sumOf(Region::fencePrice)
}

