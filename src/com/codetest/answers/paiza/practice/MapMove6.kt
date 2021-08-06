package com.codetest.answers.paiza.practice

import java.lang.RuntimeException

/** @see <a href="https://paiza.jp/works/mondai/a_rank_level_up_problems/a_rank_snake_mapmove_step6">paiza learning</a> */
fun main() {
    val line = readLine()!!.split(' ').map{ it.toInt() }
    val H = line[0]
    val W = line[1]
    val numOfMove = line[4]

    var point = line[2] to line[3] // y to x
    var currentDirection = 0;

    val map = List(H) {
        readLine()!!.chunked(1)
    }
    println(map)

    val clock = mutableMapOf<Int, String>()
    var command: String? = readLine()
    while(command != null) {
        command.split(" ").let{ clock.put(it[0].toInt(), it[1]) }
        command = readLine()
    }

    for(i in 0 until 99) {
        clock[i]?.run { currentDirection = updateDirection(currentDirection, this) }
        point = tryMove(point, currentDirection)
        if (moveSucceed(point, map)) {
            println("${point.first} ${point.second}")
            continue
        } else {
            println("Stop")
            return
        }
    }
}


fun updateDirection(currentDirection: Int, turn: String): Int {
    var newDirection = when(turn) {
        "R" -> currentDirection + 1
        "L" -> currentDirection - 1
        else -> throw RuntimeException("something went wrong: given - $turn")
    }

    if (newDirection > 3) {
        newDirection -= 4
    } else if (newDirection < 0) {
        newDirection += 4
    }

    return newDirection
}

// direction
// 0: north
// 1: east
// 2: south
// 3: west
fun tryMove(point: Pair<Int, Int>, direction: Int): Pair<Int, Int> {
    var (y, x) = point
    return when(direction) {
        0 -> y-1 to x
        1 -> y to x+1
        2 -> y+1 to x
        3 -> y to x-1
        else -> throw RuntimeException("something went wrong: given - $direction")
    }
}

fun moveSucceed(point: Pair<Int, Int>, map: List<List<String>>) = !(isOutOfMap(point, map.size to map.first().size) || isObstacle(point, map))

fun isOutOfMap(point: Pair<Int, Int>, mapSize: Pair<Int, Int>): Boolean {
    return (point.first >= mapSize.first || point.second >= mapSize.second)
            || (point.first < 0 || point.second < 0)
}

const val OBSTACLE = "#"

fun isObstacle(point: Pair<Int, Int>, map: List<List<String>>): Boolean {
    return map[point.first][point.second] == OBSTACLE
}


