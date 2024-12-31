package day18

object Solver {
    fun findFirstNotValid(corruptedList: List<Position>, start: Int = 0, size: Int = 70): Position {

        for (i in start..corruptedList.size) {
            val corruptedSet = corruptedList.take(i).toSet()
            val map = Map(corruptedSet, size)
            try {
                map.search()
            } catch (_: RuntimeException) {
                return corruptedList[i - 1]
            }
        }
        throw RuntimeException("Did not find any solution")
    }
}