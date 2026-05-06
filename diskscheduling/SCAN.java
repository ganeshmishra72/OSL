package diskscheduling;

import java.util.*;

public class SCAN {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of requests: ");
        int n = sc.nextInt();

        int[] req = new int[n];

        System.out.println("Enter request sequence:");
        for (int i = 0; i < n; i++) {
            req[i] = sc.nextInt();
        }

        System.out.print("Enter initial head position: ");
        int head = sc.nextInt();

        System.out.print("Enter disk size (max track number): ");
        int diskSize = sc.nextInt();

        System.out.print("Enter direction (left/right): ");
        String direction = sc.next();

        Arrays.sort(req);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Divide requests
        for (int r : req) {
            if (r < head)
                left.add(r);
            else
                right.add(r);
        }

        int totalSeekTime = 0;
        List<Integer> seekSequence = new ArrayList<>();

        seekSequence.add(head);

        if (direction.equalsIgnoreCase("left")) {

            // Move left
            Collections.reverse(left);
            for (int r : left) {
                totalSeekTime += Math.abs(head - r);
                head = r;
                seekSequence.add(head);
            }

            // Go to 0 (end)
            totalSeekTime += Math.abs(head - 0);
            head = 0;
            seekSequence.add(head);

            // Move right
            for (int r : right) {
                totalSeekTime += Math.abs(head - r);
                head = r;
                seekSequence.add(head);
            }

        } else {

            // Move right
            for (int r : right) {
                totalSeekTime += Math.abs(head - r);
                head = r;
                seekSequence.add(head);
            }

            // Go to end
            totalSeekTime += Math.abs(head - (diskSize - 1));
            head = diskSize - 1;
            seekSequence.add(head);

            // Move left
            Collections.reverse(left);
            for (int r : left) {
                totalSeekTime += Math.abs(head - r);
                head = r;
                seekSequence.add(head);
            }
        }

        // Output
        System.out.println("\nSeek Sequence:");
        for (int x : seekSequence) {
            System.out.print(x + " -> ");
        }

        System.out.println("\n\nTotal Seek Time: " + totalSeekTime);
        System.out.println("Average Seek Time: " + (double) totalSeekTime / n);
    }
}