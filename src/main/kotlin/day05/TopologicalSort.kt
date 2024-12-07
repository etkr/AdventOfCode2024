package day05

fun topologicalSort(
    graph: Collection<RuleNode>,
    visited: MutableSet<RuleNode> = mutableSetOf(),
    topologicalOrder: MutableList<RuleNode> = mutableListOf()
): List<RuleNode> {
    graph.forEach { dfs(it, visited, topologicalOrder) }
    return topologicalOrder.reversed()
}

private fun dfs(node: RuleNode, visited: MutableSet<RuleNode>, topologicalOrder: MutableList<RuleNode>) {
    if (node in visited) return
    visited.add(node)
    node.before.forEach { dfs(it, visited, topologicalOrder) }
    topologicalOrder.add(node)
}
