package day01

import kotlin.math.abs

/**
 * Represents two list of locations with their respective ids
 * @constructor Create a LocationIdList from a list of pairs
 */
class LocationIdList(input: List<Pair<Int, Int>>) {

    private val left: List<Int> = input.map { it.first }
    private val right: List<Int> = input.map { it.second }

    /**
     * Find the smallest item in the left list and calculate the difference with the smallest item in the right list,
     * repeat the process for the second-smallest items and so on and sum the differences
     * @return the total distance
     */
    fun totalDistance() = left.sorted().zip(right.sorted()).sumOf {  abs(it.first - it.second) }

    /**
     * Calculate the similarity score by multiplying the location in the left list
     * with the number of times it appears in the right list
     * @return the similarity score
     */
    fun similarityScore(): Int {
        val rightOccurrenceIndex = right.groupingBy { it }.eachCount()
        return left.sumOf { it * (rightOccurrenceIndex[it] ?: 0) }
    }
}

