package day08

import Util.combinations

fun Char.isAntinode() = this == '#'
fun Char.isNotDot() = this != '.'

class Map(private val matrix: Array<CharArray>, var recursive: Boolean = false) {

    val height = matrix.indices
    val width = matrix[0].indices

    private fun groupByFrequency() =
        matrix
            .mapIndexed { y, chars -> chars.mapIndexed { x, c -> FrequencyNode(c, Vector2(x, y)) } }
            .flatten()
            .filter { it.value.isNotDot() }
            .groupBy(FrequencyNode::value)
            .values
            .map { it.map(FrequencyNode::vector) }


    fun markAntinodes() =
        groupByFrequency()
            .forEach { it.combinations().forEach(::markAntinodesForAntennaPair) }

    fun markAntinodesForAntennaPair(pair: Pair<Vector2, Vector2>) {
        val (vectorA, vectorB) = pair
        val difference = vectorA subtract vectorB
        vectorA.markAntinodeRec(difference, Vector2::add)
        vectorB.markAntinodeRec(difference, Vector2::subtract)
    }

    private tailrec fun Vector2.markAntinodeRec(translate: Vector2, func: (Vector2, Vector2) -> Vector2) {
        val new = func(this, translate)
        if (new.isOutOfBounds()) return
        this@Map.markAntinode(new)
        if (!recursive) return
        new.markAntinodeRec(translate, func)
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




