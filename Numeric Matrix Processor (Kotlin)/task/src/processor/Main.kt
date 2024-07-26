package processor

import java.util.*
import kotlin.system.exitProcess

class NumberMatrixProcessor {
    init {
        val scanner = Scanner(System.`in`)

        do {
            println(
                "1. Add matrices\n" +
                        "2. Multiply matrix by a constant\n" +
                        "3. Multiply matrices\n" +
                        "4. Transpose matrix\n" +
                        "5. Calculate a determinant\n" +
                        "0. Exit"
            )
            print("Your choice: > ")

            when (scanner.nextInt()) {
                1 -> addMatrices(scanner)
                2 -> multiplyMatrixByConstant(scanner)
                3 -> multiplyMatrices(scanner)
                4 -> transposeMatrix(scanner)
                5 -> calculateDeterminant(scanner)
                0 -> exitProcess(0)
                else -> println("Invalid choice. Please try again.")
            }
        } while (true)
    }

    private fun addMatrices(scanner: Scanner) {
        println("Enter size of first matrix: > ")
        val n1 = scanner.nextInt()
        val m1 = scanner.nextInt()
        println("Enter first matrix: ")
        val matrix1 = Array(n1) { DoubleArray(m1) { scanner.nextDouble() } }
        println("Enter size of second matrix: > ")
        val n2 = scanner.nextInt()
        val m2 = scanner.nextInt()
        if (n1 != n2 || m1 != m2) {
            println("The operation cannot be performed.")
            return
        }
        println("Enter second matrix: ")
        val matrix2 = Array(n2) { DoubleArray(m2) { scanner.nextDouble() } }
        val resultMatrix = Array(n1) { DoubleArray(m1) }
        (0 until n1).forEach { i ->
            (0 until m1).forEach { j ->
                resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j]
            }
        }
        println("The result is:")
        resultMatrix.forEach { row ->
            println(row.joinToString(" "))
        }
    }

    private fun multiplyMatrixByConstant(scanner: Scanner) {
        println("Enter size of matrix: > ")
        val n = scanner.nextInt()
        val m = scanner.nextInt()
        println("Enter matrix: ")
        val matrix = Array(n) { DoubleArray(m) { scanner.nextDouble() } }
        println("Enter constant: > ")
        val constant = scanner.nextDouble()
        val resultMatrix = Array(n) { DoubleArray(m) }

        (0 until n).forEach { i ->
            (0 until m).forEach { j ->
                resultMatrix[i][j] = matrix[i][j] * constant
            }
        }

        println("The result is:")
        resultMatrix.forEach { row ->
            println(row.joinToString(" "))
        }
    }

    private fun multiplyMatrices(scanner: Scanner) {
        println("Enter size of first matrix: > ")
        val n1 = scanner.nextInt()
        val m1 = scanner.nextInt()
        println("Enter first matrix: ")
        val matrix1 = Array(n1) { DoubleArray(m1) { scanner.nextDouble() } }
        println("Enter size of second matrix: > ")
        val n2 = scanner.nextInt()
        val m2 = scanner.nextInt()

        if (m1 != n2) {
            println("The operation cannot be performed.")
            return
        }

        println("Enter second matrix: ")
        val matrix2 = Array(n2) { DoubleArray(m2) { scanner.nextDouble() } }
        val resultMatrix = Array(n1) { DoubleArray(m2) }

        (0 until n1).forEach { i ->
            (0 until m2).forEach { j ->
                resultMatrix[i][j] = 0.0
                (0 until m1).forEach { k ->
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j]
                }
            }
        }

        println("The result is:")
        resultMatrix.forEach { row ->
            println(row.joinToString(" "))
        }
    }

    private fun transposeMatrix(scanner: Scanner) {
        println(
            "1. Main diagonal\n" +
                    "2. Side diagonal\n" +
                    "3. Vertical line\n" +
                    "4. Horizontal line\n"
        )
        print("Your choice: > ")
        val choice = scanner.nextInt()
        println("Enter matrix size: > ")
        val n = scanner.nextInt()
        val m = scanner.nextInt()
        println("Enter matrix: ")
        val matrix = Array(n) { DoubleArray(m) { scanner.nextDouble() } }

        val resultMatrix = when (choice) {
            1 -> transposeMainDiagonal(matrix)
            2 -> transposeSideDiagonal(matrix)
            3 -> transposeVerticalLine(matrix)
            4 -> transposeHorizontalLine(matrix)
            else -> {
                println("Invalid choice.")
                return
            }
        }

        println("The result is:")
        resultMatrix.forEach { row ->
            println(row.joinToString(" "))
        }
    }

    private fun transposeMainDiagonal(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val n = matrix.size
        val m = matrix[0].size
        val resultMatrix = Array(m) { DoubleArray(n) }
        (0 until n).forEach { i ->
            (0 until m).forEach { j ->
                resultMatrix[j][i] = matrix[i][j]
            }
        }
        return resultMatrix
    }

    private fun transposeSideDiagonal(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val n = matrix.size
        val m = matrix[0].size
        val resultMatrix = Array(m) { DoubleArray(n) }
        (0 until n).forEach { i ->
            (0 until m).forEach { j ->
                resultMatrix[m - j - 1][n - i - 1] = matrix[i][j]
            }
        }
        return resultMatrix
    }

    private fun transposeVerticalLine(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val n = matrix.size
        val m = matrix[0].size
        val resultMatrix = Array(n) { DoubleArray(m) }
        (0 until n).forEach { i ->
            (0 until m).forEach { j ->
                resultMatrix[i][m - j - 1] = matrix[i][j]
            }
        }
        return resultMatrix
    }

    private fun transposeHorizontalLine(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val n = matrix.size
        val m = matrix[0].size
        val resultMatrix = Array(n) { DoubleArray(m) }
        (0 until n).forEach { i ->
            (0 until m).forEach { j ->
                resultMatrix[n - i - 1][j] = matrix[i][j]
            }
        }
        return resultMatrix
    }

    private fun calculateDeterminant(scanner: Scanner) {
        println("Enter matrix size: > ")
        val n = scanner.nextInt()
        val m = scanner.nextInt()
        if (n != m) {
            println("The operation cannot be performed.")
            return
        }
        println("Enter matrix: ")
        val matrix = Array(n) { DoubleArray(m) { scanner.nextDouble() } }
        val result = determinant(matrix)
        println("The result is:")
        println(result)
    }

    private fun determinant(matrix: Array<DoubleArray>): Double {
        val n = matrix.size
        if (n == 1) return matrix[0][0]
        if (n == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]

        var det = 0.0
        (0 until n).forEach { j ->
            det += matrix[0][j] * cofactor(matrix, 0, j)
        }
        return det
    }

    private fun cofactor(matrix: Array<DoubleArray>, row: Int, col: Int): Double {
        return minor(matrix, row, col) * if ((row + col) % 2 == 0) 1 else -1
    }

    private fun minor(matrix: Array<DoubleArray>, row: Int, col: Int): Double {
        val subMatrix = Array(matrix.size - 1) { DoubleArray(matrix.size - 1) }
        var subI = 0
        matrix.indices.forEach { i ->
            if (i == row) return@forEach
            var subJ = 0
            matrix.indices.forEach { j ->
                if (j == col) return@forEach
                subMatrix[subI][subJ] = matrix[i][j]
                subJ++
            }
            subI++
        }
        return determinant(subMatrix)
    }
}

fun main() {
    NumberMatrixProcessor()
}
