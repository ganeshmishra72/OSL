package diskscheduling;

import java.util.*;

public class SSTF {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of disk requests: ");
        int n = sc.nextInt();

        int[] request = new int[n];
        boolean[] visited = new boolean[n];

        System.out.println("Enter disk request sequence:");
        for (int i = 0; i < n; i++) {
            request[i] = sc.nextInt();
        }

        System.out.print("Enter initial head position: ");
        int head = sc.nextInt();

        int totalSeekTime = 0;

        System.out.println("\nSeek Sequence:");
        System.out.print(head);

        for (int i = 0; i < n; i++) {

            int minDist = Integer.MAX_VALUE;
            int index = -1;

            // Find nearest unvisited request
            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    int distance = Math.abs(request[j] - head);

                    if (distance < minDist) {
                        minDist = distance;
                        index = j;
                    }
                }
            }

            // Move head
            visited[index] = true;
            totalSeekTime += minDist;
            head = request[index];

            System.out.print(" -> " + head);
        }

        System.out.println("\n\nTotal Seek Time: " + totalSeekTime);
        System.out.println("Average Seek Time: " + (double) totalSeekTime / n);
    }
}