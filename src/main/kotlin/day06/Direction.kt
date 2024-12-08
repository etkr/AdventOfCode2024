package day06

enum class Direction {
    UP, RIGHT, DOWN, LEFT;

    fun next() = entries[(this.ordinal + 1) % entries.size]
}