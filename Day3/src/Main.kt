import java.io.File

fun readFile(fileName: String): String {
    val file = File(fileName)

    return file.readText()
}

val example = """
987654321111111
811111111111119
234234234234278
818181911112111
""".trimIndent()

fun main() {
    val file = readFile(readln()).trim().split("\n")
    val exampleInput = example.split("\n")
    var res = 0L
    var res2 = 0L

    for (line in file) {

        res += getResult(line, 2)
        res2 += getResult(line, 12)

    }
    println("$res")
    println("$res2")
}

fun getResult(line: String, maxDigits: Int): Long {
    var currentLine = line
    var res = ""
    var digits = maxDigits
    while (digits > 0) {
        val windowSize = currentLine.length - digits + 1
        val currentWindow = currentLine.take(windowSize)
        val leftMaxIndex = findLeftMaxIndex(currentWindow)

        res += currentWindow[leftMaxIndex]
        currentLine =
            if (leftMaxIndex + 1 < currentLine.length) {
                currentLine.substring(leftMaxIndex + 1)
            } else {
                ""
            }
        digits--
    }
    return res.toLong()
}

fun findLeftMaxIndex(leftSub: String): Int {
    var max = '1'
    var index = 0

    for ((i, c) in leftSub.withIndex()) {
        if (c > max) {
            max = c
            index = i
        }
    }
    return index
}