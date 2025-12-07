import java.io.File

fun readFile(fileName: String): String {
    val file = File(fileName)

    return file.readText()
}

val example = """
    ..@@.@@@@.
    @@@.@.@.@@
    @@@@@.@.@@
    @.@@@@..@.
    @@.@@@@.@@
    .@@@@@@@.@
    .@.@.@.@@@
    @.@@@.@@@@
    .@@@@@@@@.
    @.@.@@@.@.
""".trimIndent()

fun main() {
    val file = readFile(readln()).trim().split("\n")
    val exampleInput = example.split("\n")
    val grid = file.map { it.toCharArray().toMutableList() }.toMutableList()

    val maxLength = file[0].length
    val maxHeight = file.size
    var i: Int
    var y: Int
    var rollCount: Int
    var res = 0
    var rollsRemoved = 1
    while (rollsRemoved > 0) {
        i = 0
        rollsRemoved = 0
        while (i < maxHeight) {
            y = 0
            while (y < maxLength) {
                rollCount = 0
                if (grid[i][y] == '@') {
                    if (i + 1 < maxHeight && y + 1 < maxLength)
                        if (grid[i + 1][y + 1] == '@') rollCount++
                    if (i + 1 < maxHeight)
                        if (grid[i + 1][y] == '@') rollCount++
                    if (y + 1 < maxLength)
                        if (grid[i][y + 1] == '@') rollCount++
                    if (i - 1 >= 0 && y - 1 >= 0)
                        if (grid[i - 1][y - 1] == '@') rollCount++
                    if (i - 1 >= 0)
                        if (grid[i - 1][y] == '@') rollCount++
                    if (y - 1 >= 0)
                        if (grid[i][y - 1] == '@') rollCount++
                    if (i - 1 >= 0 && y + 1 < maxLength)
                        if (grid[i - 1][y + 1] == '@') rollCount++
                    if (i + 1 < maxHeight && y - 1 >= 0)
                        if (grid[i + 1][y - 1] == '@') rollCount++
                    if (rollCount < 4) {
                        res++
                        grid[i][y] = '.'
                        rollsRemoved++
                    }
                }
                y++
            }
            i++
        }
    }
    println(res)
}
