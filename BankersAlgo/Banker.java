package BankersAlgo;

import java.util.*;

public class Banker {

    static int n, m; // n = processes, m = resources

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();

        System.out.print("Enter number of resources: ");
        m = sc.nextInt();

        int[][] allocation = new int[n][m];
        int[][] max = new int[n][m];
        int[][] need = new int[n][m];
        int[] available = new int[m];

        // Input Allocation Matrix
        System.out.println("\nEnter Allocation Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocation[i][j] = sc.nextInt();
            }
        }

        // Input Max Matrix
        System.out.println("\nEnter Max Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        // Input Available Resources
        System.out.println("\nEnter Available Resources:");
        for (int i = 0; i < m; i++) {
            available[i] = sc.nextInt();
        }

        // Calculate Need Matrix = Max - Allocation
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        // Display Need Matrix
        System.out.println("\nNeed Matrix:");
        printMatrix(need);

        // Banker's Algorithm
        boolean[] finish = new boolean[n];
        int[] safeSequence = new int[n];
        int[] work = Arrays.copyOf(available, m);

        int count = 0;

        while (count < n) {
            boolean found = false;

            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (need[i][j] > work[j])
                            break;
                    }

                    if (j == m) {
                        // Process can execute
                        for (int k = 0; k < m; k++) {
                            work[k] += allocation[i][k];
                        }

                        safeSequence[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("\n❌ System is NOT in safe state!");
                return;
            }
        }

        // Print Safe Sequence
        System.out.println("\n✅ System is in SAFE state.");
        System.out.print("Safe Sequence: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + safeSequence[i]);
            if (i != n - 1)
                System.out.print(" -> ");
        }
        System.out.println();
    }

    // Function to print matrix
    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}