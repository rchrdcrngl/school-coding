
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author JCDes
 */
public class PreemptiveScheduling {

    private static Scanner sc = new Scanner(System.in);

    //METHOD FOR SJF
    public static void shortestJobFirst(int[] arrivalTimes, int[] burstTimes, char[] processID) {
        System.out.println("\nSelected Algorithm: Shortest Job First (Preemptive)");
        int n = processID.length;
        int total = 0;
        int currTime = 0;
        int prevIndex = -1;
        int changeInTime = 0;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        int[] completeTimes = new int[n];
        int[] turnaroundTimes = new int[n];
        int[] waitingTimes = new int[n];
        int[] completed = new int[n];
        int[] burstTimes2 = Arrays.copyOf(burstTimes, n);
        List<GanttNode> chart = new ArrayList<>();

        while (true) {
            int min = Integer.MAX_VALUE;
            int j = n;
            if (total == n) {
                break;
            }
            for (int i = 0; i < n; i++) {
                if ((arrivalTimes[i] <= currTime) && (completed[i] == 0) && (burstTimes2[i] < min)) {
                    min = burstTimes2[i];
                    j = i;
                    if (prevIndex == -1) {
                        prevIndex = i;
                    }
                }
            }

            if (j == n) {
                currTime++;
            } else {
                if (prevIndex != j && completed[prevIndex] != 1) {
                    chart.add(new GanttNode(processID[prevIndex], currTime - changeInTime, currTime));
                    changeInTime = 0;
                    prevIndex = j;
                }
                burstTimes2[j]--;
                currTime++;
                changeInTime++;

                // Process done executing
                if (burstTimes2[j] == 0) {
                    completeTimes[j] = currTime;
                    completed[j] = 1;
                    total++;
                    chart.add(new GanttNode(processID[j], completeTimes[j] - burstTimes[j], currTime));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            turnaroundTimes[i] = completeTimes[i] - arrivalTimes[i];
            waitingTimes[i] = turnaroundTimes[i] - burstTimes[i];
            totalWaitingTime += waitingTimes[i];
            totalTurnaroundTime += turnaroundTimes[i];
        }

        GanttNode.createGanttChart(chart);
        System.out.println("Process\tArrival\tBurst\tWaiting\tTurnaround");
        for (int i = 0; i < n; i++) {
            System.out.println(processID[i] + "\t" + arrivalTimes[i] + "\t" + burstTimes[i] + "\t" + waitingTimes[i] + "\t" + turnaroundTimes[i]);
        }
        System.out.println("Average Waiting Time=" + totalWaitingTime / n);
        System.out.println("Average Turnaround Time=" + totalTurnaroundTime / n);
    }

    //METHOD FOR RR
    public static void roundRobin(int[] arrivalTimes, int[] burstTimes, char[] processID) {
        System.out.println("\nSelected Algorithm: Round Robin (Preemptive)");
        int quantum = 5;
        int currentTime = 0;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        Queue<Process> readyQueue = new LinkedList<>();
        Process[] processes = new Process[processID.length];
        List<Process> completedProcesses = new ArrayList<>();
        List<GanttNode> chart = new ArrayList<>();

        System.out.print("Enter time quantum: ");
        quantum = sc.nextInt();

        for (int i = 0; i < processID.length; i++) {
            processes[i] = new Process(processID[i], arrivalTimes[i], burstTimes[i]);
        }

        Arrays.sort(processes, Comparator.comparing(Process::getArrivalTime));

        List<Process> notArrivedProcesses = new ArrayList<>();

        for (Process p : processes) {
            if (p.getArrivalTime() <= currentTime) {
                readyQueue.add(p);
            } else {
                notArrivedProcesses.add(p);
            }
        }
        
        while (!readyQueue.isEmpty() || !notArrivedProcesses.isEmpty()) {
            if (readyQueue.isEmpty()) {
                currentTime++;
            } else {
                Process currentProcess = readyQueue.poll();
                int remainingBurstTime = currentProcess.getBurstTime();

                if (remainingBurstTime <= quantum) {
                    chart.add(new GanttNode(currentProcess.getId(), currentTime, currentTime + remainingBurstTime));
                    currentTime += remainingBurstTime;
                    currentProcess.setCompletionTime(currentTime);
                    currentProcess.setTurnaroundTime(currentProcess.getCompletionTime() - currentProcess.getArrivalTime());
                    currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getInitialBurstTime());
                    completedProcesses.add(currentProcess);
                } else {
                    chart.add(new GanttNode(currentProcess.getId(), currentTime, currentTime + quantum));
                    currentTime += quantum;
                    currentProcess.setBurstTime(remainingBurstTime - quantum);
                    //readyQueue.add(currentProcess);
                }

                // Check if there are any processes that have arrived while the current process is executing
                List<Process> arrivedProcessesToRemove = new ArrayList<>();
                for (Process process : notArrivedProcesses) {
                    if (process.getArrivalTime() <= currentTime) {
                        readyQueue.add(process);
                        arrivedProcessesToRemove.add(process);
                    }
                }
                notArrivedProcesses.removeAll(arrivedProcessesToRemove);
                if (remainingBurstTime > quantum) readyQueue.add(currentProcess);
            }
        }

        for (Process process : completedProcesses) {
            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
        }

        GanttNode.createGanttChart(chart);
        System.out.println("Process\tArrival\tBurst\tWaiting\tTurnaround");
        for (Process process : processes) {
            System.out.println(process.getId() + "\t" + process.getArrivalTime() + "\t" + process.getInitialBurstTime() + "\t" + process.getWaitingTime() + "\t" + process.getTurnaroundTime());
        }
        System.out.println("Average Waiting Time=" + totalWaitingTime / processID.length);
        System.out.println("Average Turnaround Time=" + totalTurnaroundTime / processID.length);
    }

    //METHOD FOR PRIORITY
    public static void priority(int[] arrivalTimes, int[] burstTimes, char[] processID) {
        System.out.println("\nSelected Algorithm: Priority (Preemptive)");
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        Process[] processes = new Process[processID.length];
        List<GanttNode> chart = new ArrayList<>();

        System.out.println();
        for (int i = 0; i < processID.length; i++) {
            System.out.print("Set priority for Process " + processID[i] + ": ");
            int priority = sc.nextInt();
            processes[i] = new Process(processID[i], arrivalTimes[i], burstTimes[i], priority);
        }

        Arrays.sort(processes, Comparator.comparing(Process::getArrivalTime));

        int currentTime = 0;
        int changeInTime = 0;
        int totalProcesses = processes.length;
        int completedProcesses = 0;
        int prevProcessIndex = -1;

        while (completedProcesses < totalProcesses) {
            int minPriority = Integer.MAX_VALUE;
            int selectedProcessIndex = -1;

            //Select the process to do
            for (int i = 0; i < totalProcesses; i++) {
                Process currentProcess = processes[i];
                if (currentProcess.getArrivalTime() <= currentTime && currentProcess.getBurstTime() != 0 && currentProcess.getPriority() < minPriority) {
                    minPriority = currentProcess.getPriority();
                    selectedProcessIndex = i;
                    if (prevProcessIndex == -1) {
                        prevProcessIndex = selectedProcessIndex;
                    }
                }
            }

            //If there are no processes earlier or equal to current time, continue to next iteration
            if (selectedProcessIndex == -1) {
                currentTime++;
                continue;
            }

            //Selected process has changed
            if (prevProcessIndex != selectedProcessIndex) {
                chart.add(new GanttNode(processes[prevProcessIndex].getId(), currentTime - changeInTime, currentTime));
                changeInTime = 0;
                prevProcessIndex = selectedProcessIndex;
            }
            Process currentProcess = processes[selectedProcessIndex];
            currentProcess.setBurstTime(currentProcess.getBurstTime() - 1);
            currentTime++;
            changeInTime++;

            if (currentProcess.getBurstTime() == 0) {
                currentProcess.setCompletionTime(currentTime);
                currentProcess.setTurnaroundTime(currentProcess.getCompletionTime() - currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getInitialBurstTime());
                totalTurnaroundTime += currentProcess.getTurnaroundTime();
                totalWaitingTime += currentProcess.getWaitingTime();
                completedProcesses++;
            }
        }
        chart.add(new GanttNode(processes[prevProcessIndex].getId(), currentTime - changeInTime, currentTime));

        GanttNode.createGanttChart(chart);
        System.out.println("Process\tArrival\tBurst\tWaiting\tTurnaround");
        for (Process process : processes) {
            System.out.println(process.getId() + "\t" + process.getArrivalTime() + "\t" + process.getInitialBurstTime() + "\t" + process.getWaitingTime() + "\t" + process.getTurnaroundTime());
        }
        System.out.println("Average Waiting Time=" + totalWaitingTime / processID.length);
        System.out.println("Average Turnaround Time=" + totalTurnaroundTime / processID.length);
    }

}
