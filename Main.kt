import java.io.BufferedReader
import java.io.InputStreamReader

fun solve() {
    val n = readInt()
    val requestList = readInts().withIndex()
        .map { it.value to it.index }
        .sorted()
    val m = readInt()
    for (i in 1..m) {
        var (left, right, value) = readInts()
        --left
        --right
        val result = requestList.lowerBoundValue(value to left)
        if (result?.first == value && result.second <= right) {
            print(1)
        } else {
            print(0)
        }
    }
}

fun main() {
    solve()
    flush()
}

/**
 * Some functions for competitive programming with Kotlin
 * Author: Igor Smirnov (https://github.com/smirnov-i-SPbAU)
 */

/** Buffered read */
val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
fun readLine(): String = bufferedReader.readLine()!!
fun readInt(): Int = readLine().toInt()
fun readLong(): Long = readLine().toLong()
fun readDouble(): Double = readLine().toDouble()
fun readStrings(): List<String> = readLine().split(" ")
fun readInts(): List<Int> = readLine().split(" ").map { it.toInt() }
fun readDoubles(): List<Double> = readLine().split(" ").map { it.toDouble() }
fun readLongs(): List<Long> = readLine().split(" ").map { it.toLong() }
/** End buffered read */

/** Buffered print */
private val outputBuilder = StringBuilder()

fun println(something: Any?) = outputBuilder.apply {
    append(something.toString())
    append('\n')
}

fun print(something: Any?) {
    outputBuilder.append(something.toString())
}

fun flush() {
    kotlin.io.print(outputBuilder.toString())
    outputBuilder.clear()
}
/** End buffered print */

/** Comparable pair */
data class ComparablePair<T : Comparable<T>, S : Comparable<S>>(val first: T, val second: S) : Comparable<ComparablePair<T, S>> {
    override fun compareTo(other: ComparablePair<T, S>): Int {
        if (first != other.first) {
            return if (first < other.first) -1 else 1
        }
        return second.compareTo(other.second)
    }
}

infix fun <A : Comparable<A>, B : Comparable<B>> A.to(that: B): ComparablePair<A, B> = ComparablePair(this, that)
/** End comparable pair */

/** C++ like binary searches over list */
/**
 * Returns index of first element of a list that is >= `value`.
 * If there is no such element, returns size of the list.
 */
fun <T : Comparable<T>> List<T>.lowerBoundIndex(value: T): Int {
    var left = -1
    var right = size
    while (left + 1 < right) {
        val middle = (left + right) / 2
        if (get(middle) < value) {
            left = middle
        } else {
            right = middle
        }
    }
    return right
}

/**
 * Returns value of first element of a list that is >= `value`.
 * If there is no such element, returns null.
 */
fun <T : Comparable<T>> List<T>.lowerBoundValue(value: T): T? {
    val index = lowerBoundIndex(value)
    return if (index == size) {
        null
    } else {
        get(index)
    }
}

/**
 * Returns index of first element of a list that is > `value`.
 * If there is no such element, returns size of the list.
 */
fun <T : Comparable<T>> List<T>.upperBoundIndex(value: T): Int {
    var left = -1
    var right = size
    while (left + 1 < right) {
        val middle = (left + right) / 2
        if (get(middle) <= value) {
            left = middle
        } else {
            right = middle
        }
    }
    return right
}

/**
 * Returns value of first element of a list that is >= `value`.
 * If there is no such element, returns null.
 */
fun <T : Comparable<T>> List<T>.upperBoundValue(value: T): T? {
    val index = upperBoundIndex(value)
    return if (index == size) {
        null
    } else {
        get(index)
    }
}
/** End C++ like binary searches */
