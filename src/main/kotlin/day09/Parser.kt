package day09

class Parser {

    var empty = false
    var id = -1

    private fun createFile(n: Int) =
        if (empty) {
            empty = false
            List(n) { null }

        } else {
            empty = true
            id++
            List(n) { id }
        }

    fun createDisk(input: String) = Disk(
        filesystem = input
            .trim()
            .toCharArray()
            .map(Char::digitToInt)
            .flatMap(::createFile)
            .toMutableList()
    )
}