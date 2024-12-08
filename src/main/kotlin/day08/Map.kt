package day08

import Util.combinations

fun Char.isAntinode() = this == '#'
fun Char.isNotDot() = this != '.'

class Map(private val matrix: Array<CharArray>) {

    val height = matrix.indices
    val width = matrix[0].indices

    private fun frequencyIndex() =
        matrix
            .mapIndexed { y, chars -> chars.mapIndexed { x, c -> FrequencyNode(c, Vector2(x, y)) } }
            .flatten()
            .filter { it.value.isNotDot() }
            .groupBy(FrequencyNode::value)
            .values
            .map { it.map(FrequencyNode::vector) }

    fun markAntinodes(recursive: Boolean = false) {
        for (nodes in frequencyIndex()) {
            for ((vectorA, vectorB) in nodes.combinations()) {
                val difference = vectorA subtract vectorB
                markAntinodeRec(vectorA, difference, Vector2::add, recursive)
                markAntinodeRec(vectorB, difference, Vector2::subtract, recursive)
            }
        }
    }

    private tailrec fun markAntinodeRec(
        base: Vector2,
        translate: Vector2,
        func: (Vector2, Vector2) -> Vector2,
        recursive: Boolean = true
    ) {
        val new = func(base, translate)
        if (new.isOutOfBounds()) return
        markAntinode(new)
        if (!recursive) return
        markAntinodeRec(new, translate, func)
    }

    fun countAntinodes() = matrix.map { it.toList() }.flatten().count(Char::isAntinode)
    fun countAntinodesAndAntennas() = matrix.map { it.toList() }.flatten().count(Char::isNotDot)

    fun markAntinode(vector: Vector2) {
        if (vector.isOutOfBounds()) return
        matrix[vector.y][vector.x] = '#'
    }

    private fun Vector2.isOutOfBounds() = this.x !in width || this.y !in height

    override fun toString() = buildString {
        for (y in height) {
            for (x in width) {
                append(matrix[y][x])
            }
            appendLine()
        }
    }
}




