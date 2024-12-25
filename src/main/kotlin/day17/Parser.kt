package day17

import java.io.InputStream

object Parser {

    fun parse(stream: InputStream): Computer {
        val lines = stream.bufferedReader().lines()
        var registerA = 0L
        var registerB = 0L
        var registerC = 0L
        var program = emptyList<Int>()
        for (line in lines) {
            if (line.startsWith("Register A: ")) {
                registerA = line.removePrefix("Register A: ").toLong()
            } else if (line.startsWith("Register B: ")) {
                registerB = line.removePrefix("Register B: ").toLong()
            } else if (line.startsWith("Register C: ")) {
                registerC = line.removePrefix("Register C: ").toLong()
            } else if (line.startsWith("Program: ")) {
                program = line.removePrefix("Program: ").split(',').map(String::toInt)
            }
        }
        return Computer(registerA, registerB, registerC, program)
    }
}