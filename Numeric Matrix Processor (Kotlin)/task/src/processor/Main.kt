package processor
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val (n, m) = scanner.nextLine().split(" ").map { it.toInt() }

    val matrix = Array(n) { IntArray(m) }
    (0 until n).forEach { i ->
        matrix[i] = scanner.nextLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val constant = scanner.nextInt()

    val resultMatrix = Array(n) { IntArray(m) }
    (0 until n).forEach { i ->
        for (j in 0 until m) {
            resultMatrix[i][j] = matrix[i][j] * constant
        }
    }

    resultMatrix.forEach { row ->
        println(row.joinToString(" "))
    }
}
