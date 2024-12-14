package day13

class Arcade(val clawMachines: List<ClawMachine>) {
    fun totalTokensToPrize() = clawMachines.sumOf(ClawMachine::tokensToPrize)
}