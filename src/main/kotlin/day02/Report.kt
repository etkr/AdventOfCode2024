package day02

import kotlin.math.abs

data class Report(val levels: List<Int>) {

    private fun allIncreasing() = levels.zipWithNext().all { it.first < it.second }

    private fun allDecreasing() = levels.zipWithNext().all { it.first > it.second }

    private fun allSafeDiff() = levels.zipWithNext().all { abs(it.first - it.second) in 1..3 }

    fun safe() = (allIncreasing() or allDecreasing()) and allSafeDiff()

    fun generateSubRecords() = iterator {
        repeat(levels.size) {
            val listWithoutOne = levels.toMutableList().apply { removeAt(it) }
            yield(Report(listWithoutOne))
        }
    }
}

class ReportList(input: List<String>) {

    private val reports = input.map { it.split(' ').map(String::toInt) }.map(::Report)

    fun numberOfSafeReports() = reports.count(Report::safe)

    fun numberOfTolerantSafeReports() = reports.count { it.generateSubRecords().asSequence().any(Report::safe) }
}