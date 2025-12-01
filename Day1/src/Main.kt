import java.io.File
fun readFile(fileName: String): String {
    val file = File(fileName)

    return file.readText()
}
val example = """
    L68
    L30
    R48
    L5
    R60
    L55
    L1
    L99
    R14
    L82
""".trimIndent()

fun main() {
    var dial = 50
    var prevDial: Int
    var pass = 0
    var pass2 = 0
    val file = readFile(readln()).split("\n")
    val exampleInput = example.split("\n")

    for (line in file) {
        if (line.isEmpty())
            continue
        val num = line.drop(1).toInt()
        val direction = line.first()
        prevDial = dial
        when (direction) {
            'R' -> dial += num
            'L' -> dial -= num
            else -> println("Error")
        }

        while (dial > 99) {
            dial -= 100
            if (dial > 0)
                pass2++
        }

        while (dial < 0) {
            dial += 100
            if (prevDial != 0) {
                pass2++
            }
            prevDial = dial
        }

        if (dial == 0)
            pass++
    }

    println("Part1 password: $pass")
    println("Part2 password: ${pass + pass2}")
}