package day17

import kotlin.jvm.optionals.getOrNull
import kotlin.math.pow
import kotlin.streams.asStream

class Computer(
    private var registerA: ULong,
    private var registerB: ULong,
    private var registerC: ULong,
    private val instructions: List<Int>
) {
    companion object {
        /**
         * Div function for adv, bdc and cdv
         */
        private infix fun ULong.div(other: ULong) = this / 2.0.pow(other.toDouble()).toULong()


    }

    private var instructionsPointer: Int = 0
    private val output: MutableList<Int> = mutableListOf()
    val program = instructions.joinToString(separator = ",")

    /**
     * Convert instruction list to operation objects
     */
    private val operations: List<Operation> =
        instructions.chunked(2).map(::InstructionSet).map(InstructionSet::createOperation)

    /**
     * Intermediary class for parsing instructions
     */
    private inner class InstructionSet(private val opcode: Int, private val operand: Int) {
        constructor(list: List<Int>) : this(list[0].toInt(), list[1])

        fun createOperation(): Operation = when (opcode) {
            0 -> ADV(operand)
            1 -> BXL(operand)
            2 -> BST(operand)
            3 -> JNZ(operand)
            4 -> BXC(operand)
            5 -> OUT(operand)
            6 -> BDV(operand)
            7 -> CDV(operand)
            else -> throw RuntimeException("Unknown opcode $opcode")
        }
    }

    private interface Operation {
        val operand: Int
        fun exec()
    }

    private abstract inner class OperationBase(override val operand: Int) : Operation {
        /**
         * Lazy property to evaluate the operand to its value
         */
        val literal: ULong
            get() = when (operand) {
                0 -> 0u
                1 -> 1u
                2 -> 2u
                3 -> 3u
                4 -> registerA
                5 -> registerB
                6 -> registerC
                else -> throw RuntimeException()
            }
    }

    private inner class ADV(operand: Int) : OperationBase(operand) {
        override fun exec() {
            registerA = registerA div literal
            instructionsPointer++
        }
    }

    private inner class BXL(operand: Int) : OperationBase(operand) {
        override fun exec() {
            registerB = registerB xor literal
            instructionsPointer++
        }
    }

    private inner class BST(operand: Int) : OperationBase(operand) {
        override fun exec() {
            registerB = literal % 8uL
            instructionsPointer++
        }
    }

    private inner class JNZ(operand: Int) : OperationBase(operand) {
        override fun exec() {
            if (registerA == 0uL) {
                instructionsPointer++
                return
            }
            instructionsPointer = literal.toInt()
        }
    }

    private inner class BXC(operand: Int) : OperationBase(operand) {
        override fun exec() {
            registerB = registerB xor registerC
            instructionsPointer++
        }
    }

    private inner class OUT(operand: Int) : OperationBase(operand) {
        override fun exec() {
            output.add((literal % 8u).toInt())
            instructionsPointer++
        }
    }

    private inner class BDV(operand: Int) : OperationBase(operand) {
        override fun exec() {
            registerB = registerA div literal
            instructionsPointer++
        }
    }

    private inner class CDV(operand: Int) : OperationBase(operand) {
        override fun exec() {
            registerC = registerA div literal
            instructionsPointer++
        }
    }

    fun executeProgram(): String {
        while (instructionsPointer < operations.size) {
            operations[instructionsPointer].exec()
        }
        return output.joinToString(separator = ",")
    }

    fun whereOutputMatchesProgram(i: ULong): Boolean {
        if (i % 10000000uL == 0uL) {
            println("progress: ${i.toDouble() * 100 / ULong.MAX_VALUE.toDouble()}%")
        }
        val computer = Computer(registerA = i, registerB = 0uL, registerC = 0uL, instructions)
        val result = computer.executeProgram()
        return result == program
    }

    fun findOutputOfSelf() =
        (UInt.MAX_VALUE.toULong()..ULong.MAX_VALUE)
            .asSequence()
            .asStream()
            .parallel()
            .filter(::whereOutputMatchesProgram)
            .findFirst()
            .getOrNull()
            ?: throw RuntimeException("Did not find any matches")
}