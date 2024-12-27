package day17

class Computer(
    private var registerA: Long,
    private var registerB: Long = 0,
    private var registerC: Long = 0,
    val instructions: List<Long>
) {
    companion object {
        /**
         * Div function for adv, bdc and cdv
         */
        private infix fun Long.div(other: Long) = this ushr other.toInt()
        private fun String.toOctal() = this.split(',').joinToString(separator = "").toLong().toString(8)
        private fun List<Long>.toOctal() = this.joinToString(separator = "").toLong().toString(8)

    }

    private var instructionsPointer: Int = 0
    private val output: MutableList<Long> = mutableListOf()
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
        constructor(list: List<Long>) : this(list[0].toInt(), list[1].toInt())

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
        val literal: Long
            get() = when (operand) {
                0 -> 0
                1 -> 1
                2 -> 2
                3 -> 3
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
            registerB = literal % 8
            instructionsPointer++
        }
    }

    private inner class JNZ(operand: Int) : OperationBase(operand) {
        override fun exec() {
            if (registerA == 0L) {
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
            output.add(literal % 8)
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

    fun executeProgram(): List<Long> {
        while (instructionsPointer < operations.size) {
            operations[instructionsPointer].exec()
        }
        return output
    }

    fun whereOutputMatchesProgram(input: Long): Boolean {
        val computer = Computer(registerA = input, registerB = 0, registerC = 0, instructions)
        val result = computer.executeProgram()
        println("---------------")
        println("register A: ${input.toString(8)}")
        println("result:     ${result.toOctal()}")
        println("program:    ${program.toOctal()}")
        println("---------------")
        return result == instructions
    }

    private fun findBits(a: Long, output: Long): List<Long> {
        val variants = mutableListOf<Long>()
        for (bits in 0b000..0b111) {
            val answ = (a shl 3) + bits
            val computer = Computer(registerA = registerA, instructions = instructions)
            val result = computer.executeProgram()
            if (result.size == 1 && result.first() == output) {
                variants += answ
            }
        }
        return variants
    }

    fun findBit(input: Long): Long {
        for (i in 0L until 8L) {
            val registerA = (input shl 3) + i
            val computer = Computer(registerA = registerA, instructions = instructions)
            val result = computer.executeProgram()
            val first = result.first()
            if (first == input) {
                return first
            }
        }
        throw RuntimeException("Found None")
    }

    fun findSolution(): Long {
        var candidates = listOf(0L)
        instructions.reversed().forEach { insn ->
            candidates = candidates.flatMap { a ->
                findBits(a, insn)
            }
        }
        return candidates.min()
    }

}
