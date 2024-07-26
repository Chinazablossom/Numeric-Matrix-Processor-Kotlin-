package processor

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val (n1, m1) = scanner.nextLine().split(" ").map { it.toInt() }
    val matrixA = Array(n1) { IntArray(m1) }
    for (i in 0 until n1) {
        matrixA[i] = scanner.nextLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val (n2, m2) = scanner.nextLine().split(" ").map { it.toInt() }
    val matrixB = Array(n2) { IntArray(m2) }
    for (i in 0 until n2) {
        matrixB[i] = scanner.nextLine().split(" ").map { it.toInt() }.toIntArray()
    }

    if (n1 != n2 || m1 != m2) {
        println("ERROR")
        return
    }

    val resultMatrix = Array(n1) { IntArray(m1) }

    for (i in 0 until n1) {
        for (j in 0 until m1) {
            resultMatrix[i][j] = matrixA[i][j] + matrixB[i][j]
        }
    }

    for (row in resultMatrix) {
        println(row.joinToString(" "))
    }
}

