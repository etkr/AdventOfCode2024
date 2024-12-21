package day17

fun main(args: Array<String>) {
    val input = { }.javaClass.getResourceAsStream("input.txt") ?: throw RuntimeException("Resource not found")
    val computer = Parser.parse(input)
    val result = computer.findOutputOfSelf()
    println(result)
}