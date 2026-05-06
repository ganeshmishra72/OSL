package pagereplacement;

import java.util.*;

public class pagereplacementOptimal {

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
                pageHits++;
            } else {

                if (frameList.size() < frames) {
                    frameList.add(page);
                } else {

                    int indexToRemove = -1;
                    int farthest = i + 1;

                    for (int j = 0; j < frameList.size(); j++) {

                        int current = frameList.get(j);
                        int k;

                        for (k = i + 1; k < n; k++) {
                            if (pages[k] == current) {
                                if (k > farthest) {
                                    farthest = k;
                                    indexToRemove = j;
                                }
                                break;
                            }
                        }

                        // If page not found in future
                        if (k == n) {
                            indexToRemove = j;
                            break;
                        }
                    }

                    frameList.set(indexToRemove, page);
                }

                pageFaults++;
            }

            System.out.println("Frames: " + frameList);
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);

        sc.close();
    }
}