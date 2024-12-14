package day13

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