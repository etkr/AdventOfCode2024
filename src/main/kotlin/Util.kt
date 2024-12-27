object Util {


    fun <T> List<T>.combinations() = sequence {
        for (i in this@combinations) {
            for (j in this@combinations) {
                if (i != j) yield(Pair(i, j))
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> Array<T>.copyAppend(item: T): Array<T> {
        val new = this.copyOf(size + 1)
        new[size] = item
        return new as? Array<T> ?: throw RuntimeException()
    }


}