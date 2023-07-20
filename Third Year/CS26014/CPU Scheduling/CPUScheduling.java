
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Richard Maru
 */
public class CPUScheduling {

    static int numOfProcesses;
    static int[] arrivalTimes;
    static int[] burstTimes;
    static char[] processID;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("CPU Scheduling Calculator ");
        System.out.println("CARINGAL, Richard Maru \n"
                + "DE GUIA, Charle Symon\n"
                + "DESIERDO, John Christian\n"
                + "PAGULAYAN, Christian Charles\n"
                + "3CSC\n\n");

        try {
            initializeParameters();
        } catch (InputMismatchException ime) {
            System.out.println("Invalid input ");
            initializeParameters();
        }
        sc.close();
    }

    private static void initializeParameters() {
        System.out.print("Input no. of processes [2-9]: ");
        numOfProcesses = sc.nextInt();

        while (!(2 <= numOfProcesses && numOfProcesses <= 9)) {
            System.out.print("Input valid request size [2-9]: ");
            numOfProcesses = sc.nextInt();
        }

        arrivalTimes = new int[numOfProcesses];
        burstTimes = new int[numOfProcesses];
        processID = new char[numOfProcesses];
        
        for (int i = 0; i < numOfProcesses; i++) {
            processID[i] = (char) (65+i);
            System.out.print("Input arrival time for Process "+processID[i]+": ");
            arrivalTimes[i] = sc.nextInt();
            System.out.print("Input burst time for Process "+processID[i]+": ");
            burstTimes[i] = sc.nextInt();
        }
        System.out.println("");
        selectAlgorithm();
    }

    private static void selectAlgorithm() {
        // Select algorithm
        System.out.println("\nSelect Scheduling Algorithm");
        System.out.println(
                "[1] First Come First Serve (Non-Preemptive)\n"
                + "[2] Shortest Job First (Non-Preemptive)\n"
                + "[3] Shortest Job First (Preemptive)\n"
                + "[4] Priority (Non-Preemptive)\n"
                + "[5] Priority (Preemptive)\n"
                + "[6] Round Robin (Preemptive)\n"
                + "[E] Exit"
        );
        System.out.print("Enter choice: ");
        String selectedAlgo = sc.next();
        switch (selectedAlgo.trim().toUpperCase()) {
            case "1": 
                NonpreemptiveScheduling.firstComeFirstServe(arrivalTimes, burstTimes, processID);
                promptRestart();
                break;
            case "2":
                NonpreemptiveScheduling.shortestJobFirst(arrivalTimes, burstTimes, processID);
                promptRestart();
                break;
            case "3":
                PreemptiveScheduling.shortestJobFirst(arrivalTimes, burstTimes, processID);
                promptRestart();
                break;
            case "4":
                NonpreemptiveScheduling.priority(processID, arrivalTimes, burstTimes);
                promptRestart();
                break;
            case "5":
                PreemptiveScheduling.priority(arrivalTimes, burstTimes, processID);
                promptRestart();
                break;
            case "6":
                PreemptiveScheduling.roundRobin(arrivalTimes, burstTimes, processID);
                promptRestart();
                break;
            case "E":
                break;
            default:
                selectAlgorithm();
        }
    }

    private static void promptRestart() {
        System.out.println("\nSelect the next action:");
        System.out.println("[A] Choose another algorithm\n"
                + "[B] Enter new values\n"
                + "[C] Exit");
        System.out.print("Enter choice: ");
        String selected = sc.next();
        switch (selected.trim().toUpperCase()) {
            case "A":
                selectAlgorithm();
                break;
            case "B":
                initializeParameters();
                break;
            case "C":
                break;
            default:
                promptRestart();
        }
    }
}
