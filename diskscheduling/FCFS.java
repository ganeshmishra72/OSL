package diskscheduling;

import java.util.*;

public class FCFS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of disk requests: ");
        int n = sc.nextInt();

        int[] request = new int[n];

        System.out.println("Enter disk request sequence:");
        for (int i = 0; i < n; i++) {
            request[i] = sc.nextInt();
        }

        System.out.print("Enter initial head position: ");
        int head = sc.nextInt();

        int totalSeekTime = 0;

        System.out.println("\nSeek Sequence:");
        System.out.print(head);

        // FCFS Disk Scheduling
        for (int i = 0; i < n; i++) {
            int distance = Math.abs(request[i] - head);
            totalSeekTime += distance;

            head = request[i];

            System.out.print(" -> " + head);
        }

        System.out.println("\n\nTotal Seek Time: " + totalSeekTime);
        System.out.println("Average Seek Time: " + (double) totalSeekTime / n);
    }
}