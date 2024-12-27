package day10

class TopographicalMap(val map: Map<Coordinates, Node>) {
    val heights = map.values.groupBy(Node::height)
    val trails = mutableSetOf<Trial>()
    val uniqueTrailheadPeakPairs get() = trails.groupBy(Trial::trailheadPeakPair).count()
    val numberOfTrials get() = trails.size

    fun findAllTrials() = heights[0]?.forEach { it.findTrials() }

    fun Node.findTrials(currentTrial: Trial = Trial()) {
        val newTrial = currentTrial.copyWith(this)

        // Base case,end of trial
        if (height == 9) trails.add(newTrial)

        // Go up in either direction
        directions
            .filter(currentTrial::notContains)
            .filter { it.height == height + 1 }
            .forEach { it.findTrials(newTrial) }
    }
}
