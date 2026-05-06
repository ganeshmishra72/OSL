package cpuscehudling;

import java.util.*;

class Process {
    int pid, at, bt, ct, tat, wt;

    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
    }
}

public class FCFS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];

        // Input
        for (int i = 0; i < n; i++) {
            System.out.println("\nProcess P" + i);
            System.out.print("Arrival Time: ");
            int at = sc.nextInt();
            System.out.print("Burst Time: ");
            int bt = sc.nextInt();

            p[i] = new Process(i, at, bt);
        }

        // Sort by Arrival Time (FCFS rule)
        Arrays.sort(p, Comparator.comparingInt(a -> a.at));

        int currentTime = 0;
        double totalTAT = 0, totalWT = 0;

        // Calculate times
        for (int i = 0; i < n; i++) {

            // If CPU is idle
            if (currentTime < p[i].at) {
                currentTime = p[i].at;
            }

            p[i].ct = currentTime + p[i].bt;
            p[i].tat = p[i].ct - p[i].at;
            p[i].wt = p[i].tat - p[i].bt;

            currentTime = p[i].ct;

            totalTAT += p[i].tat;
            totalWT += p[i].wt;
        }

        // Output Table
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (Process pr : p) {
            System.out.println("P" + pr.pid + "\t" + pr.at + "\t" + pr.bt + "\t" +
                    pr.ct + "\t" + pr.tat + "\t" + pr.wt);
        }

        // Averages
        System.out.println("\nAverage Turnaround Time: " + (totalTAT / n));
        System.out.println("Average Waiting Time: " + (totalWT / n));

        // Gantt Chart
        System.out.println("\nGantt Chart:");
        System.out.print("|");
        for (Process pr : p) {
            System.out.print(" P" + pr.pid + " |");
        }

        System.out.print("\n0");
        for (Process pr : p) {
            System.out.print("    " + pr.ct);
        }

        System.out.println();
    }
}