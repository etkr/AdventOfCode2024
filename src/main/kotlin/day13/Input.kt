package day13

data class Input(val buttonACount: Long, val buttonBCount: Long) {
    fun cost() = buttonACount * 3 + buttonBCount
}