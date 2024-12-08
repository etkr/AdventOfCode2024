package day06

data class Guard(val startingDirection: Direction, val startingNode: MapNode) {

    var currentPosition: MapNode = startingNode
    var currentDirection: Direction = startingDirection
    var exited = false
    var hasCycle = false

    fun notFoundCycles() = !hasCycle

    tailrec fun moveForward() {
        currentPosition.visitedDirections.add(currentDirection)

        val nextNode = when (currentDirection) {
            Direction.UP -> currentPosition.up
            Direction.RIGHT -> currentPosition.right
            Direction.DOWN -> currentPosition.down
            Direction.LEFT -> currentPosition.left
        }

        if (nextNode == null) {
            exited = true
            return
        }

        if (nextNode.visitedDirections.contains(currentDirection)) {
            hasCycle = true
            return
        }

        if (nextNode.isObstacle) {
            turnRight()
            moveForward()
            return
        }

        currentPosition = nextNode
    }

    private fun turnRight() {
        currentDirection = currentDirection.next()
    }

    fun notExited() = !exited
}