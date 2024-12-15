package day11


fun Int.splitInHalf(): Pair<Int, Int> {
    val string = this.toString()
    val half = string.length / 2
    return Pair(string.substring(0, half).toInt(), string.substring(half).toInt())
}

fun Int.isEven() = this % 2 == 0


fun dotTheThing(initialState: List<Int>) {
    val newState = initialState.toMutableList()
    splitAndInsert(initialState, newState)
    turnZeroIntoOne(initialState, newState)
}


fun splitAndInsert(oldState: List<Int>, newState: MutableList<Int>) {
    for (i in oldState.indices)
        if (state[i].isEven()) {
            val (first, second) = state[i].splitInHalf()
            state[i] = first
            state.add(i + 1, second)
        }
}


fun turnZeroIntoOne(index: List<Int>, newState: MutableList<Int>) {
    for (i in state.indices)
        if (state[i] == 0) {
            state[i] = 1
        }
}


