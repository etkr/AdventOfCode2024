package day08

data class Vector2(val x: Int, val y: Int) {
    infix fun subtract(other: Vector2) = Vector2(this.x - other.x, this.y - other.y)
    infix fun add(other: Vector2) = Vector2(this.x + other.x, this.y + other.y)
}