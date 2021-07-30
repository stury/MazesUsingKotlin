package main.kotlin

operator fun String.times(copies: Int) : String {
    var result = ""

    for (i in 1..copies) {
        result += this
    }

    return result
}