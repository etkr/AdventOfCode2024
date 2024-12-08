package day08

import java.io.InputStream

object Parser {

    fun parse(inputStream: InputStream): Map {

        val matrix = inputStream
            .bufferedReader()
            .readLines()
            .map(String::toCharArray)
            .toTypedArray()

        return Map(matrix)
    }
}