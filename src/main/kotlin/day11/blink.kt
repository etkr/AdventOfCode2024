package day11

fun ULong.splitInHalf(): Pair<ULong, ULong> {
    val string = this.toString()
    val half = string.length / 2
    return Pair(string.substring(0, half).toULong(), string.substring(half).toULong())
}


fun ULong.hasEvenDigits() = this.toString().length % 2 == 0

data class CacheEntry(val stone: ULong, val depth: Int)

fun blink(stone: ULong, cache: MutableMap<CacheEntry, ULong>, depth: Int): ULong {
    if (depth == 0) return 1u
    val entry = CacheEntry(stone, depth)
    val value = cache[entry]
    if (value != null) {
        return value
    }

    val count = if (stone == 0UL) {
        blink(1u, cache, depth - 1)
    } else if (stone.hasEvenDigits()) {
        val (first, second) = stone.splitInHalf()
        blink(first, cache, depth - 1) + blink(second, cache, depth - 1)
    } else {
        blink(stone * 2024u, cache, depth - 1)
    }
    cache[entry] = count
    return count

}

fun doBlink(input: List<ULong>, depth: Int): ULong {
    val map = mutableMapOf<CacheEntry, ULong>()
    return input.sumOf { blink(it, map, depth) }
}


