package pagereplacement;

import java.util.*;

public class pagereplacementLRU {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int pages[] = new int[n];

        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        List<Integer> frameList = new ArrayList<>();

        int pageFaults = 0;
        int pageHits = 0;

        for (int i = 0; i < n; i++) {

            int page = pages[i];

            if (frameList.contains(page)) {

                // Page Hit
                frameList.remove((Integer) page);
                frameList.add(page);
                pageHits++;

            } else {

                if (frameList.size() == frames) {
                    frameList.remove(0);
                }

                frameList.add(page);
                pageFaults++;
            }

            System.out.println("Frames: " + frameList);
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);

        sc.close();
    }
}