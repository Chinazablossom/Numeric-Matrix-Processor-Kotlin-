package processor

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    while (true) {
        println("1. Add matrices")
        println("2. Multiply matrix by a constant")
        println("3. Multiply matrices")
        println("0. Exit")
        print("Your choice: > ")

        when (scanner.nextInt()) {
            1 -> addMatrices(scanner)
            2 -> multiplyMatrixByConstant(scanner)
            3 -> multiplyMatrices(scanner)
            0 -> return
            else -> println("Invalid choice. Please try again.")
        }
    }
}

fun addMatrices(scanner: Scanner) {
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

fun multiplyMatrixByConstant(scanner: Scanner) {
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

fun multiplyMatrices(scanner: Scanner) {
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

