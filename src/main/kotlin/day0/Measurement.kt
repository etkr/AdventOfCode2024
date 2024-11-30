package day0

fun numberOfIncreasingMeasurements(measurements: List<Int>) =
    measurements
        .zipWithNext()
        .count { (first, second) -> first < second }


fun numberIncreasingWindows(measurements: List<Int>, windowSize: Int) =
    measurements
        .windowed(windowSize)
        .map { it.sum() }
        .zipWithNext()
        .count { (first, second) -> first < second }