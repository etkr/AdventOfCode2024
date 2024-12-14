package day13

data class Prize(val x: Long, val y: Long)

data class Button(val x: Int, val y: Int)

data class Input(val buttonACount: Long, val buttonBCount: Long) {
    fun cost() = buttonACount * 3 + buttonBCount
}

data class ClawMachine(val buttonA: Button, val buttonB: Button, val prize: Prize) {

    private fun Input.isSolvable() =
        buttonA.x * buttonACount + buttonB.x * buttonBCount == prize.x &&
                buttonA.y * buttonACount + buttonB.y * buttonBCount == prize.y

    private fun calcInput(): Input? {
        val det = buttonA.x * buttonB.y - buttonA.y * buttonB.x
        val a = (prize.x * buttonB.y - prize.y * buttonB.x) / det
        val b = (buttonA.x * prize.y - buttonA.y * prize.x) / det
        val input = Input(a, b)
        return if (input.isSolvable()) input else null
    }

    fun tokensToPrize() = calcInput()?.cost() ?: 0
}

class Arcade(val clawMachines: List<ClawMachine>) {
    fun totalTokensToPrize() = clawMachines.sumOf(ClawMachine::tokensToPrize)
}