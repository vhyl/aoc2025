import java.io.File
fun readFile(fileName: String): String {
    val file = File(fileName)

    return file.readText()
}

const val EXAMPLE = """
    11-22,95-115,998-1012,1188511880-1188511890,222220-222224,
1698522-1698528,446443-446449,38593856-38593862,565653-565659,
824824821-824824827,2121212118-2121212124
"""

fun main() {
    val file = readFile(readln()).trimIndent().split(',')
    var res1 = 0L
    var res2 = 0L
    val ex = EXAMPLE.split(',').map { it.trim() }
    for (line in file) {
        val range = line.split('-').map { it.toLong() }
        for (num in range[0]..range[1]) {
            val numStr = num.toString()
            val len = numStr.length
            if (len % 2 == 0) {
                val middle = len / 2
                val firstHalf = numStr.substring(0, middle)
                val secondHalf = numStr.substring(middle)
                if (firstHalf == secondHalf) {
                    res1 += num
                }
            }

            for (subLen in 1..len / 2) {
                var str = ""
                if (len % subLen == 0) {
                    val pattern = numStr.substring(0, subLen)
                    val n = len / subLen

                    repeat(n) {
                        str += pattern
                    }

                    if (str == numStr) {
                        res2 += num
                        break
                    }
                }
            }
        }
    }
    println("Part1: $res1")
    println("Part2: $res2")
}