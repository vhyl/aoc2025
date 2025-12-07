import java.io.File
import kotlin.math.max

fun readFile(fileName: String): String {
    val file = File(fileName)

    return file.readText()
}

val example = """
3-5
10-14
16-20
12-18

1
5
8
11
17
32
""".trimIndent()

fun main() {
    val file = readFile(readln()).trim().split("\n")
    val exampleInput = example.split("\n")

    val ranges = getRanges(file)
    val mergedRanges = mergeRanges(ranges)
    val numbers = getNumbers(file)
    val freshNumbers = mutableListOf<Long>()

    for (range in ranges) {
        for (number in numbers)
            if (number in range) {
                freshNumbers.add(number)
            }
    }
    println("Part1: ${freshNumbers.distinct().size}")

    val part2Count = mergedRanges.sumOf { it.last - it.first + 1 }
    println("Part2: $part2Count")
}

fun getRanges(input: List<String>): List<LongRange> {
    val ranges = mutableListOf<LongRange>()
    for (line in input) {
        if (line.contains('-')) {
            val nums = line.split('-').map { it.toLong() }
            ranges.add(nums[0]..nums[1])
        }
    }
    return ranges
}

fun getNumbers(input: List<String>): List<Long> {
    val numbers = mutableListOf<Long>()
    for (line in input) {
        if (!line.contains('-') && line.isNotBlank())
            numbers.add(line.toLong())
    }

    return numbers
}

fun mergeRanges(ranges: List<LongRange>): List<LongRange> {
    val sortedRanges = ranges.sortedBy { it.first }
    val mergedRanges = mutableListOf<LongRange>()

    var currentStart = sortedRanges[0].first
    var currentEnd = sortedRanges[0].last

    for (i in 1 until sortedRanges.size) {
        val nextRange = sortedRanges[i]

        if (nextRange.first <= currentEnd + 1) {
            currentEnd = max(currentEnd, nextRange.last)
        }
        else {
            mergedRanges.add(currentStart..currentEnd)
            currentStart = nextRange.first
            currentEnd = nextRange.last
        }
    }
    mergedRanges.add(currentStart..currentEnd)

    return mergedRanges
}