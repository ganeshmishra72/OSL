package cpuscehudling;

import java.util.*;

class Process {
    int pid, at, bt, rt, ct, tat, wt;

    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.rt = bt; // remaining time
    }
}

public class RoundRobin {

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

        System.out.print("\nEnter Time Quantum: ");
        int tq = sc.nextInt();

        // Sort by Arrival Time
        Arrays.sort(p, Comparator.comparingInt(a -> a.at));

        Queue<Process> queue = new LinkedList<>();
        List<String> gantt = new ArrayList<>();

        int currentTime = 0, completed = 0, index = 0;

        // Add first process
        if (p[0].at <= currentTime) {
            queue.add(p[0]);
            index = 1;
        } else {
            currentTime = p[0].at;
            queue.add(p[0]);
            index = 1;
        }

        while (completed < n) {

            if (queue.isEmpty()) {
                currentTime = p[index].at;
                queue.add(p[index++]);
                continue;
            }

            Process curr = queue.poll();

            gantt.add("P" + curr.pid);

            if (curr.rt > tq) {
                currentTime += tq;
                curr.rt -= tq;
            } else {
                currentTime += curr.rt;
                curr.rt = 0;

                curr.ct = currentTime;
                curr.tat = curr.ct - curr.at;
                curr.wt = curr.tat - curr.bt;

                completed++;
            }

            // Add newly arrived processes
            while (index < n && p[index].at <= currentTime) {
                queue.add(p[index++]);
            }

            // Re-add current process if not finished
            if (curr.rt > 0) {
                queue.add(curr);
            }
        }

        double totalTAT = 0, totalWT = 0;

        // Output Table
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (Process pr : p) {
            System.out.println("P" + pr.pid + "\t" + pr.at + "\t" + pr.bt + "\t" +
                    pr.ct + "\t" + pr.tat + "\t" + pr.wt);

            totalTAT += pr.tat;
            totalWT += pr.wt;
        }

        System.out.println("\nAverage Turnaround Time: " + (totalTAT / n));
        System.out.println("Average Waiting Time: " + (totalWT / n));

        // Gantt Chart
        System.out.println("\nGantt Chart:");
        System.out.print("|");
        for (String g : gantt) {
            System.out.print(" " + g + " |");
        }
        System.out.println();
    }
}