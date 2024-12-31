package day18

import java.util.*
import kotlin.math.abs

class Map(val corrupted: Set<Position>, val size: Int) {

    val end = Position(0, size, size)

    fun distanceToFinish(x: Int, y: Int) = abs(size - x) + abs(size - y)
    fun withinBounds(pair: Pair<Int, Int>) = pair.first in 0..size && pair.second in 0..size

    /**
     * Generate possible steps to move within bounds
     */
    fun Position.generateDirections() = sequence {
        yield(Pair(x + 1, y))
        yield(Pair(x - 1, y))
        yield(Pair(x, y + 1))
        yield(Pair(x, y - 1))
    }
        .filter(::withinBounds)
        .map { (x, y) ->
            Position(
                estimatedDistanceToFinish = distanceToFinish(x, y),
                x = x,
                y = y,
                path = path.toMutableList().apply { add(this@generateDirections) }
            )
        }


    fun search(): List<Position> {
        val start = Position(estimatedDistanceToFinish = distanceToFinish(0, 0), 0, 0)

        // Store paths sorted by lowest cost
        val queue = PriorityQueue<Position>(1).apply { add(start) }
        val visited = mutableSetOf<Position>()

        while (queue.isNotEmpty()) {

            // Get best candidate
            val currentPosition = queue.remove()

            // Found a solution
            if (currentPosition == end) return currentPosition.path

            // Generate new candidates
            for (newPosition in currentPosition.generateDirections()) {

                // Filter blocked paths
                if (corrupted.contains(newPosition)) continue
                if (visited.contains(newPosition)) continue
                queue.add(newPosition)
            }
            visited.add(currentPosition)
        }
        throw RuntimeException("Solution not found")
    }

    fun printPath(path: Set<Position>) {
        for (y in 0..size) {
            for (x in 0..size) {
                val position = Position(0, x, y)
                val char =
                    if (path.contains(position)) 'O'
                    else if (corrupted.contains(position)) '#'
                    else '.'
                print(char)
            }
            print('\n')
        }
    }
}