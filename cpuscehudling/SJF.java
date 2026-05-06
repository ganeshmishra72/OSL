package cpuscehudling;

import java.util.*;

class Process {
    int pid, at, bt, ct, tat, wt;
    boolean completed = false;

    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
    }
}

public class SJF {

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

        int completed = 0, currentTime = 0;
        double totalTAT = 0, totalWT = 0;

        List<Integer> gantt = new ArrayList<>();

        while (completed < n) {

            int idx = -1;
            int minBT = Integer.MAX_VALUE;

            // Find shortest job among arrived processes
            for (int i = 0; i < n; i++) {
                if (!p[i].completed && p[i].at <= currentTime) {
                    if (p[i].bt < minBT) {
                        minBT = p[i].bt;
                        idx = i;
                    }
                }
            }

            if (idx != -1) {
                // Execute process
                currentTime += p[idx].bt;
                p[idx].ct = currentTime;
                p[idx].tat = p[idx].ct - p[idx].at;
                p[idx].wt = p[idx].tat - p[idx].bt;

                p[idx].completed = true;
                completed++;

                totalTAT += p[idx].tat;
                totalWT += p[idx].wt;

                gantt.add(p[idx].pid);
            } else {
                // CPU Idle
                currentTime++;
            }
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
        for (int id : gantt) {
            System.out.print(" P" + id + " |");
        }

        System.out.print("\n0");
        for (int id : gantt) {
            for (Process pr : p) {
                if (pr.pid == id) {
                    System.out.print("    " + pr.ct);
                }
            }
        }

        System.out.println();
    }
}