package day13

import java.io.InputStream


class Parser(val enablePrecisionMode: Boolean = false) {

    val regexString = Regex("""Button A: X\+(\d*), Y\+(\d*)\nButton B: X\+(\d*), Y\+(\d*)\nPrize: X=(\d*), Y=(\d*)""")


    private fun parsePrice(x: Int, y: Int): Prize {
        val xPrize = if (enablePrecisionMode) x + 10000000000000L else x.toLong()
        val yPrize = if (enablePrecisionMode) y + 10000000000000L else y.toLong()
        return Prize(x = xPrize, y = yPrize)
    }


    private fun parseClaw(groups: MatchResult.Destructured): ClawMachine {
        val buttonA = Button(groups.component1().toInt(), groups.component2().toInt())
        val buttonB = Button(groups.component3().toInt(), groups.component4().toInt())
        val prize = parsePrice(groups.component5().toInt(), groups.component6().toInt())
        return ClawMachine(buttonA, buttonB, prize)
    }

    fun parse(input: InputStream): Arcade {
        val string = input.bufferedReader().readText()

        val clawMachines = regexString
            .findAll(string)
            .map(MatchResult::destructured)
            .map(::parseClaw)
            .toList()

        return Arcade(clawMachines)
    }

}