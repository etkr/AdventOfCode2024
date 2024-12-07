package day05

data class Update(private val pageNumbers: List<Int>) {

    private val index = pageNumbers.mapIndexed { index, i -> i to index }.toMap()

    fun isCorrectlyOrdered(rules: List<PageOrderingRule>) =
        rules.filter(::includes).all(::checkRule)

    fun includes(pageOrderingRule: PageOrderingRule) =
        index.contains(pageOrderingRule.pageNumber) && index.contains(pageOrderingRule.beforePageNumber)

    fun checkRule(pageOrderingRule: PageOrderingRule) =
        index.getValue(pageOrderingRule.pageNumber) < index.getValue(pageOrderingRule.beforePageNumber)

    fun middlePageNumber() = pageNumbers[pageNumbers.size / 2]

    fun fixOrdering(pageOrderingRules: List<PageOrderingRule>): Update {

        val rules = pageOrderingRules.filter(::includes)
        val nodes = pageNumbers.associateWith { RuleNode(it) }

        // Link nodes
        for (rule in rules) {
            val node = nodes[rule.pageNumber]
            val before = nodes[rule.beforePageNumber]
            if (node == null || before == null) continue
            node.before.add(before)
        }

        val sorted = topologicalSort(nodes.values)
        return Update(sorted.map(RuleNode::pageNumber))
    }
}