package day05

class Manual(
    val pageOrderingRules: List<PageOrderingRule>,
    val updates: List<Update>
) {
    fun findCorrectlyOrderedUpdates() = updates
        .filter { it.isCorrectlyOrdered(pageOrderingRules) }
        .sumOf(Update::middlePageNumber)

    fun fixIncorrectOrdered() = updates
        .filter { !it.isCorrectlyOrdered(pageOrderingRules) }
        .map { it.fixOrdering(pageOrderingRules) }

    fun sumOfFixedIncorrectOrdered() = fixIncorrectOrdered().sumOf(Update::middlePageNumber)


}
