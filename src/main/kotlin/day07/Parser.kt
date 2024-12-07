package day07

import java.io.InputStream

object Parser {
    fun parse(input: InputStream) = input
        .bufferedReader()
        .readLines()
        .map { it.split(':') }
        .map { Pair(it.first().toLong(), it.elementAt(1).trim().split(' ').map(String::toLong)) }
        .map { Equation(it.first, it.second) }
}