package pagereplacement;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class pagereplacementFIFO {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of pages:");
        int n = scanner.nextInt();

        System.out.println("Enter the Page Refrences String:");
        int pages[] = new int[n];

        for (int i = 0; i < n; i++) {
            pages[i] = scanner.nextInt();
        }

        System.out.println("Enter Frame Size");
        int m = scanner.nextInt();

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int pageFault = 0;
        for (int i = 0; i < n; i++) {

            if (!set.contains(pages[i])) {

                if (set.size() == m) {
                    int removed = queue.poll();
                    set.remove(removed);
                }

                queue.add(pages[i]);
                set.add(pages[i]);
                pageFault++;
            }

            System.out.println("Frames: " + queue);
        }

        System.out.println("\nTotal Page Faults: " + pageFault);
        System.out.println("Total Page Hits: " + (n - pageFault));

        scanner.close();

    }
}
