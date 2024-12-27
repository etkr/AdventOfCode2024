package day17

import java.io.InputStream

object Parser {

    fun parse(stream: InputStream): Computer {
        val lines = stream.bufferedReader().lines()
        var registerA = 0L
        var registerB = 0L
        var registerC = 0L
        var program = emptyList<Long>()
        for (line in lines) {
            when {
                line.startsWith("Register A: ") -> registerA = line.removePrefix("Register A: ").toLong()
                line.startsWith("Register B: ") -> registerB = line.removePrefix("Register B: ").toLong()
                line.startsWith("Register C: ") -> registerC = line.removePrefix("Register C: ").toLong()
                line.startsWith("Program: ") -> program = line.removePrefix("Program: ").split(',').map(String::toLong)
            }
        }
        return Computer(registerA, registerB, registerC, program)
    }
}