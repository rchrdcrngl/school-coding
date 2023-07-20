
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author JCDes
 */
public class NonpreemptiveScheduling {

    private static Scanner sc = new Scanner(System.in);

    //METHOD FOR FCFS
    public static void firstComeFirstServe(int[] arrivalTimes, int[] burstTimes, char[] processID) {
        System.out.println("\nSelected Algorithm: First Come First Serve (Non-Preemptive)");
        Process[] processes = new Process[processID.length];
        List<Process> sequence = new ArrayList<>();
        List<GanttNode> chart = new ArrayList<>();
        int currentTime = 0;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        for (int i = 0; i < processID.length; i++) {
            processes[i] = new Process(processID[i], arrivalTimes[i], burstTimes[i]);
        }

        Arrays.sort(processes, Comparator.comparing(Process::getArrivalTime));

        for (Process p : processes) {
            int burstTime = p.getBurstTime();
            int waitingTime = currentTime - p.getArrivalTime();
            int turnaroundTime = waitingTime + burstTime;

            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;

            p.setCompletionTime(currentTime + burstTime);
            p.setWaitingTime(waitingTime);
            p.setTurnaroundTime(turnaroundTime);
            
            chart.add(new GanttNode(p.getId(), currentTime, currentTime + burstTime));
            sequence.add(p);
            currentTime += burstTime;
        }
        GanttNode.createGanttChart(chart);
        System.out.println("Process\tArrival\tBurst\tWaiting\tTurnaround");
        for (Process p : processes) {
            System.out.println(p.getId() + "\t" + p.getArrivalTime() + "\t" + p.getBurstTime() + "\t" + p.getWaitingTime() + "\t" + p.getTurnaroundTime());
        }
        System.out.println("Average Waiting Time=" + totalWaitingTime / processID.length);
        System.out.println("Average Turnaround Time=" + totalTurnaroundTime / processID.length);
    }

    //METHOD FOR SJF
    public static void shortestJobFirst(int[] arrivalTimes, int[] burstTimes, char[] processID) {
        System.out.println("\nSelected Algorithm: Shortest Job First (Non-Preemptive)");
        int total = 0;
        int currTime = 0;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        int[] completeTime = new int[processID.length];
        int[] turnaroundTime = new int[processID.length];
        int[] waitingTime = new int[processID.length];
        int[] completed = new int[processID.length];
        List<GanttNode> chart = new ArrayList<>();

        while (true) {
            int min = Integer.MAX_VALUE;
            int j = processID.length;
            if (total == processID.length) {
                break;
            }
            for (int i = 0; i < processID.length; i++) {
                if ((arrivalTimes[i] <= currTime) && (completed[i] == 0) && (burstTimes[i] < min)) {
                    min = burstTimes[i];
                    j = i;
                }
            }

            if (j == processID.length) {
                currTime++;
            } else {
                chart.add(new GanttNode(processID[j], currTime, currTime + burstTimes[j]));  
                completeTime[j] = currTime + burstTimes[j];
                currTime += burstTimes[j];
                turnaroundTime[j] = completeTime[j] - arrivalTimes[j];
                waitingTime[j] = turnaroundTime[j] - burstTimes[j];
                completed[j] = 1;
                total++;
            }
        }

        for (int i = 0; i < processID.length; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }
        GanttNode.createGanttChart(chart);
        System.out.println("Process\tArrival\tBurst\tWaiting\tTurnaround");
        for (int i = 0; i < processID.length; i++) {
            System.out.println(processID[i] + "\t" + arrivalTimes[i] + "\t" + burstTimes[i] + "\t" + waitingTime[i] + "\t" + turnaroundTime[i]);
        }
        System.out.println("Average Waiting Time=" + totalWaitingTime / processID.length);
        System.out.println("Average Turnaround Time=" + totalTurnaroundTime / processID.length);
    }

    //METHOD FOR PRIORITY
    public static void priority(char[] processID, int[] arrivalTimes, int[] burstTimes) {
        System.out.println("\nSelected Algorithm: Priority Scheduling (Non-Preemptive)");
        int total = 0;
        int currTime = 0;
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

        while (true) {
            int minPriority = Integer.MAX_VALUE;
            int j = processID.length;
            if (total == processID.length) {
                break;
            }
            for (int i = 0; i < processID.length; i++) {
                Process process = processes[i];
                if (process.getArrivalTime() <= currTime && process.getCompletionTime() == 0 && process.getPriority() < minPriority) {
                    minPriority = process.getPriority();
                    j = i;
                }
            }

            if (j == processID.length) {
                currTime++;
            } else {
                Process process = processes[j];
                chart.add(new GanttNode(process.getId(), currTime, currTime + process.getBurstTime()));  
                process.setCompletionTime(currTime + process.getBurstTime());
                currTime += process.getBurstTime();
                process.setTurnaroundTime(process.getCompletionTime() - process.getArrivalTime());
                process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());
                total++;
            }
        }

        for (Process process : processes) {
            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
        }
        
        GanttNode.createGanttChart(chart);
        System.out.println("Process\tArrival\tBurst\tWaiting\tTurnaround");
        for (Process process : processes) {
            System.out.println(process.getId() + "\t" + process.getArrivalTime() + "\t" + process.getBurstTime() + "\t" + process.getWaitingTime() + "\t" + process.getTurnaroundTime());
        }
        System.out.println("Average Waiting Time=" + totalWaitingTime / processID.length);
        System.out.println("Average Turnaround Time=" + totalTurnaroundTime / processID.length);
    }
}

class GanttNode {
    final public char id;
    final public int startTime;
    final public int finishTime;

    public GanttNode(char id, int startTime, int finishTime) {
        this.id = id;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
    
    public static void createGanttChart(List<GanttNode> chartData){
        System.out.println();
        System.out.println("Gantt Chart:");
        for (GanttNode data : chartData){
            System.out.print("|   ");
            System.out.print(data.id+"("+data.startTime+"-"+data.finishTime+")");
            System.out.print("   |");
        }
        System.out.println();
    }
}