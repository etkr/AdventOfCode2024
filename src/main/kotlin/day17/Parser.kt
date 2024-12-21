package day17

import java.io.InputStream

object Parser {

    fun parse(stream: InputStream): Computer {
        val lines = stream.bufferedReader().lines()
        var registerA = 0uL
        var registerB = 0uL
        var registerC = 0uL
        var program = emptyList<Int>()
        for (line in lines) {
            if (line.startsWith("Register A: ")) {
                registerA = line.removePrefix("Register A: ").toULong()
            } else if (line.startsWith("Register B: ")) {
                registerB = line.removePrefix("Register B: ").toULong()
            } else if (line.startsWith("Register C: ")) {
                registerC = line.removePrefix("Register C: ").toULong()
            } else if (line.startsWith("Program: ")) {
                program = line.removePrefix("Program: ").split(',').map(String::toInt)
            }
        }
        return Computer(registerA, registerB, registerC, program)
    }
}