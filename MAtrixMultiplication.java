// import java.util.Random;
// import java.util.Scanner;

// public class MAtrixMultiplication {

//     static int[][] A;
//     static int[][] B;
//     static int[][] resultSequential;
//     static int[][] resultMultithreaded;

//     public static void main(String[] args) throws InterruptedException {
//         Scanner scanner = new Scanner(System.in);

//         // Input matrix dimensions
//         System.out.print("Enter the number of rows for Matrix A: ");
//         int rowsA = scanner.nextInt();
//         System.out.print("Enter the number of columns for Matrix A: ");
//         int colsA = scanner.nextInt();

//         System.out.print("Enter the number of rows for Matrix B: ");
//         int rowsB = scanner.nextInt();
//         System.out.print("Enter the number of  columns for Matrix B: ");
//         int colsB = scanner.nextInt();

//         // Check if multiplication is possible
//         if (colsA != rowsB) {
//             System.out.println("Matrix multiplication is not possible: "
//                     + "Columns of A (" + colsA + ") must equal Rows of B (" + rowsB + ").");
//             return;
//         }

//         // Initialize matrices
//         A = new int[rowsA][colsA];
//         B = new int[rowsB][colsB];
//         resultSequential = new int[rowsA][colsB];
//         resultMultithreaded = new int[rowsA][colsB];

//         initializeMatrices(rowsA, colsA, rowsB, colsB);

//         // Sequential multiplication
//         long startTimeSequential = System.currentTimeMillis();
//         sequentialMatrixMultiplication(rowsA, colsA, colsB);
//         long endTimeSequential = System.currentTimeMillis();

//         System.out.println("Sequential Time: " + (endTimeSequential - startTimeSequential) + " ms");

//         // Multithreaded multiplication
//         long startTimeMultithreaded = System.currentTimeMillis();
//         multithreadedMatrixMultiplication(rowsA, colsA, colsB);
//         long endTimeMultithreaded = System.currentTimeMillis();

//         System.out.println("Multithreaded Time: " + (endTimeMultithreaded - startTimeMultithreaded) + " ms");

//         // Compare results
//         if (compareResults(rowsA, colsB)) {
//             System.out.println("Results match!");
//         } else {
//             System.out.println("Results do not match.");
//         }

//         scanner.close();
//     }

//     // Initialize matrices with random values
//     private static void initializeMatrices(int rowsA, int colsA, int rowsB, int colsB) {
//         Random random = new Random();
//         for (int i = 0; i < rowsA; i++) {
//             for (int j = 0; j < colsA; j++) {
//                 A[i][j] = random.nextInt(10);
//             }
//         }
//         for (int i = 0; i < rowsB; i++) {
//             for (int j = 0; j < colsB; j++) {
//                 B[i][j] = random.nextInt(10);
//             }
//         }
//     }

//     // Sequential Matrix Multiplication
//     private static void sequentialMatrixMultiplication(int rowsA, int colsA, int colsB) {
//         for (int i = 0; i < rowsA; i++) {
//             for (int j = 0; j < colsB; j++) {
//                 for (int k = 0; k < colsA; k++) {
//                     resultSequential[i][j] += A[i][k] * B[k][j];
//                 }
//             }
//         }
//     }

//     // Multithreaded Matrix Multiplication
//     private static void multithreadedMatrixMultiplication(int rowsA, int colsA, int colsB) throws InterruptedException {
//         Thread[] threads = new Thread[rowsA];
//         for (int i = 0; i < rowsA; i++) {
//             final int row = i;
//             threads[i] = new Thread(() -> multiplyRow(row, colsA, colsB));
//             threads[i].start();
//         }

//         // Wait for all threads to finish
//         for (Thread thread : threads) {
//             thread.join();
//         }
//     }

//     private static void multiplyRow(int row, int colsA, int colsB) {
//         for (int j = 0; j < colsB; j++) {
//             for (int k = 0; k < colsA; k++) {
//                 resultMultithreaded[row][j] += A[row][k] * B[k][j];
//             }
//         }
//     }

//     // Compare results of both methods
//     private static boolean compareResults(int rowsA, int colsB) {
//         for (int i = 0; i < rowsA; i++) {
//             for (int j = 0; j < colsB; j++) {
//                 if (resultSequential[i][j] != resultMultithreaded[i][j]) {
//                     return false;
//                 }
//             }
//         }
//         return true;
//     }
// }












// rowsA = matrixA.length: The number of rows in matrixA.
// colsA = matrixA[0].length: The number of columns in matrixA. It assumes that matrixA is not empty.
// colsB = matrixB[0].length: The number of columns in matrixB. It assumes that matrixB is not empty.

public class MAtrixMultiplication {

    // Function for sequential matrix multiplication
    public static int[][] multiplySequential(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;
        int[][] result = new int[rowsA][colsB];

        // Perform matrix multiplication (Sequential)
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }

    // Function for multithreaded matrix multiplication
    public static int[][] multiplyMultithreaded(int[][] matrixA, int[][] matrixB) throws InterruptedException {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;
        int[][] result = new int[rowsA][colsB];

        // Create threads for each row of the result matrix
        Thread[] threads = new Thread[rowsA];

        for (int i = 0; i < rowsA; i++) {
            final int row = i; // Final variable to be used in the thread
            threads[i] = new Thread(() -> {
                for (int j = 0; j < colsB; j++) {
                    for (int k = 0; k < colsA; k++) {
                        result[row][j] += matrixA[row][k] * matrixB[k][j];
                    }
                }
            });
            threads[i].start(); // Start each thread
        }

        // Wait for all threads to finish
        for (int i = 0; i < rowsA; i++) {
            threads[i].join();
        }

        return result;
    }

    // Method to print the matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Define two example matrices for multiplication
        int[][] matrixA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrixB = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        // Sequential matrix multiplication
        long startTime = System.nanoTime(); // Start time in nanoseconds
        int[][] sequentialResult = multiplySequential(matrixA, matrixB);
        long endTime = System.nanoTime(); // End time in nanoseconds
        System.out.println("Sequential Multiplication Result:");
        printMatrix(sequentialResult);
        System.out.println("Time taken for Sequential Multiplication: " + (endTime - startTime) + " ns");

        // Multithreaded matrix multiplication
        startTime = System.nanoTime(); // Start time in nanoseconds
        int[][] multithreadedResult = multiplyMultithreaded(matrixA, matrixB);
        endTime = System.nanoTime(); // End time in nanoseconds
        System.out.println("Multithreaded Multiplication Result:");
        printMatrix(multithreadedResult);
        System.out.println("Time taken for Multithreaded Multiplication: " + (endTime - startTime) + " ns");
    }
}