package com.codetest.answers.paiza.practice

import java.lang.RuntimeException

const val MAX = 999

/** @see <a href="https://paiza.jp/works/mondai/a_rank_level_up_problems/a_rank_camp_step5">paiza learning: board game 5</a> */
fun main() {
    val line = readLine()!!.split(' ').map { it.toInt() }
    val H = line[0]
    val W = line[1]
    val numOfTarget = line[2]

    val map: MutableList<MutableList<String>> = MutableList(H) {
        readLine()!!.chunked(1).toMutableList()
    }

    val targets = Array(H * W) { false }
        .apply {
            repeat(numOfTarget) { this[readLine()!!.toInt()] = true }
        }

    val dp = Array(H) { Array(W) { MAX } }

    val start = findStart(map).also {
        dp[it.first][it.second] = 0
    }

    seek(start, map, dp, targets)
    for (y in 0 until H) {
        for (x in 0 until W) {
            if (dp[y][x] != MAX) map[y][x] = if (targets[dp[y][x]]) "?" else "*"
            print(map[y][x])
        }
        println()
    }
}

fun seek(
    current: Pair<Int, Int>,
    map: MutableList<MutableList<String>>,
    dp: Array<Array<Int>>,
    targets: Array<Boolean>
) {
    val (y, x) = current
    tryMove(y + 1 to x, current, map, dp, targets)
    tryMove(y - 1 to x, current, map, dp, targets)
    tryMove(y to x + 1, current, map, dp, targets)
    tryMove(y to x - 1, current, map, dp, targets)
}

private fun tryMove(
    point: Pair<Int, Int>,
    prevPoint: Pair<Int, Int>,
    map: MutableList<MutableList<String>>,
    dp: Array<Array<Int>>,
    targets: Array<Boolean>
) {
    if (!canMove(point, map)) return

    val (y, x) = point
    val min = chMin(dp[y][x], dp[prevPoint.first][prevPoint.second] + 1)

    if (dp[y][x] == min) return

    dp[y][x] = min
    seek(point, map, dp, targets)
}

private fun chMin(a: Int, b: Int): Int {
    return if (a < b) a else b
}

private fun hasMoved(point: Pair<Int, Int>, map: List<List<String>>) =
    map[point.first][point.second] == "*" || map[point.first][point.second] == "?"

private fun canMove(point: Pair<Int, Int>, map: List<List<String>>) =
    !(isOutOfMap(point, map.size to map.first().size) || isObstacle(point, map))

private fun isOutOfMap(point: Pair<Int, Int>, mapSize: Pair<Int, Int>): Boolean {
    return (point.first >= mapSize.first || point.second >= mapSize.second)
            || (point.first < 0 || point.second < 0)
}

private const val OBSTACLE = "#"
private fun isObstacle(point: Pair<Int, Int>, map: List<List<String>>): Boolean =
    map[point.first][point.second] == OBSTACLE


private fun findStart(map: List<List<String>>): Pair<Int, Int> {
    for (i in 0 until map.size - 1) {
        for (j in 0 until map.first().size - 1) {
            if (map[i][j] == "*") {
                return i to j
            }
        }
    }

    throw RuntimeException("something went wrong.. ")
}