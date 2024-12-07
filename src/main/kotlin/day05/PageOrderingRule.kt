package day05

data class PageOrderingRule(val pageNumber: Int, val beforePageNumber: Int)

data class RuleNode(val pageNumber: Int, val before: MutableList<RuleNode> = mutableListOf()) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as RuleNode
        return pageNumber == other.pageNumber
    }

    override fun hashCode(): Int {
        return pageNumber
    }
}