object Util {


    fun <T> List<T>.combinations() = sequence {
        for (i in this@combinations) {
            for (j in this@combinations) {
                if (i != j) yield(Pair(i, j))
            }
        }
    }

}