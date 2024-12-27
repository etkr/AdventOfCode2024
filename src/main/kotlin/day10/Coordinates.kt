package day10

data class Coordinates(val x: Int, val y: Int) {
    val up get() = this.copy(x, y - 1)
    val down get() = this.copy(x, y + 1)
    val left get() = this.copy(x - 1, y)
    val right get() = this.copy(x + 1, y)
}