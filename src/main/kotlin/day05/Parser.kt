package day05


class Parser(private val input: List<String>) {

    private val isPageOrderingRule = Regex("""\d*\|\d*""")

    fun createManual(): Manual {

        val updates = mutableListOf<Update>()
        val pageOrderingRules = mutableListOf<PageOrderingRule>()

        for (line in input) {

            if (line.matches(isPageOrderingRule)) {
                val strings = line.split('|')
                val rule = PageOrderingRule(strings[0].toInt(), strings[1].toInt())
                pageOrderingRules.add(rule)
            }

            if (line.contains(',')) {
                val integers = line.split(',').map(String::toInt)
                val update = Update(integers)
                updates.add(update)
            }
        }

        return Manual(pageOrderingRules, updates)
    }
}