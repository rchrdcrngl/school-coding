
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*
 * CARINGAL, Richard Maru
 * PAGULAYAN, Christian Charles
 * 3CSC
 */

public class DiskSchedulingCalculator {

    static int currentPosition = 0;
    static int seekRate = 1;
    static int trackSize = 200;
    static int requestSize = 10;
    static int[] requests = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Disk Scheduling Calculator ");
        System.out.println("CARINGAL, Richard Maru \n"
                + "PAGULAYAN, Christian Charles\n"
                + "3CSC\n\n");

        try {
            initializeParameters();
            selectAlgorithm();
        } catch (InputMismatchException ime) {
            System.out.println("Invalid input ");
            initializeParameters();
        }
        sc.close();
    }

    private static void initializeParameters() {
        // Get track size
        System.out.print("\nInput track size: ");
        trackSize = sc.nextInt();

        // Get request size
        System.out.print("Input request size (1-10): ");
        requestSize = sc.nextInt();
        while (!(0 < requestSize && requestSize <= 10)) {
            System.out.print("Input valid request size (1-10): ");
            requestSize = sc.nextInt();
        }
        requests = new int[requestSize];

        // Get current position
        System.out.print("Input current position: ");
        currentPosition = sc.nextInt();
        while (!(0 <= currentPosition && currentPosition < trackSize)) {
            System.out.print("Input valid current position: ");
            currentPosition = sc.nextInt();
        }
        System.out.println("");

        // Get requests
        for (int i = 0; i < requestSize; i++) {
            System.out.print("Location " + i + ": ");
            int request = sc.nextInt();
            while (!(0 <= request && request <= (trackSize - 1))) {
                System.out.print("Enter valid request for location " + i + ": ");
                request = sc.nextInt();
            }
            requests[i] = request;
        }

        System.out.print("\nInput seek rate (default is 1): ");
        seekRate = sc.nextInt();
        System.out.println("");
    }

    private static void selectAlgorithm() {
        // Select algorithm
        System.out.println("\nSelect Disk Algorithm");
        System.out.println("[A] First Come First Serve\n" +
                "[B] Shortest Seek Time First\n" +
                "[C] Scan\n" +
                "[D] Circular Scan (CSCAN)\n" +
                "[E] Look\n" +
                "[F] Circular Look (CLOOK)\n" +
                "[G] Exit");
        System.out.print("Enter choice: ");
        String selectedAlgo = sc.next();
        switch (selectedAlgo.trim().toUpperCase()) {
            case "A":
                runFCFS();
                break;
            case "B":
                runSSTF();
                break;
            case "C":
                runScan();
                break;
            case "D":
                runCScan();
                break;
            case "E":
                runLook();
                break;
            case "F":
                runCLook();
                break;
            case "G":
                break;
            default:
                selectAlgorithm();
        }
    }

    private static void promptRestart() {
        System.out.println("\nSelect the next action:");
        System.out.println("[A] Choose another algorithm\n" +
                "[B] Enter new values\n" +
                "[C] Exit");
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

    private static void runFCFS() {
        System.out.println("\nSelected Algorithm: First Come First Serve (FCFS) ");
        List<Integer> sequence = new ArrayList<>();
        sequence.add(currentPosition);

        for (int loc : requests) {
            sequence.add(loc);
        }

        int totalHeadMovement = Math.abs(currentPosition - requests[0]);

        if (requests.length > 1) {
            for (int i = 1; i < requests.length; i++) {
                totalHeadMovement += Math.abs(requests[i - 1] - requests[i]);
            }
            System.out.println();
        }

        printSequence(sequence);
        System.out.println("Total Head Movement: " + totalHeadMovement + " cylinders");
        System.out.println("Seek Time: approx. " + totalHeadMovement*seekRate + " ms");
        promptRestart();
    }

    private static void runSSTF() {
        System.out.println("\nSelected Algorithm: Shortest Seek Time First (SSTF) ");
        int head = currentPosition, totalHeadMovement = 0;
        Node[] difference = new Node[requestSize];
        List<Integer> sequence = new ArrayList<>();

        for (int i = 0; i < difference.length; i++) {
            difference[i] = new Node();
        }

        for (int i = 0; i < requests.length; i++) {
            sequence.add(head);
            calculateDifference(requests, head, difference);
            int index = findMinNode(difference);
            difference[index].accessed = true;
            totalHeadMovement += difference[index].distance;
            head = requests[index];
        }
        sequence.add(head);

        printSequence(sequence);
        System.out.println("Total Head Movement: " + totalHeadMovement + " cylinders");
        System.out.println("Seek Time: approx. " + totalHeadMovement*seekRate + " ms");
        promptRestart();
    }

    private static void runScan() {
        System.out.println("\nSelected Algorithm: Scan ");
        int head = currentPosition, totalHeadMovement = 0;
        boolean movingRight = false;

        List<Integer> right = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> sequence = new ArrayList<>();

        for (int i = 0; i < requestSize; i++) {
            if (requests[i] == currentPosition) {
                right.add(requests[i]);
                left.add(requests[i]);
            } else if (requests[i] > currentPosition) {
                right.add(requests[i]);
            } else {
                left.add(requests[i]);
            }
        }

        Collections.sort(right);
        Collections.sort(left, Comparator.reverseOrder());

        System.out.print("Enter direction (0 for left, 1 for right): ");
        int direction = sc.nextInt();

        if (direction == 1) {
            movingRight = true;
        }

        sequence.add(currentPosition);
        if (movingRight) {
            totalHeadMovement += Math.abs(right.get(0) - head);
            for (int i = 0; i <= right.size() - 2; i++) {
                head = right.get(i);
                sequence.add(head);
                totalHeadMovement += Math.abs(right.get(i + 1) - head);
            }

            sequence.add(right.get(right.size() - 1));
            totalHeadMovement += Math.abs((trackSize - 1) - right.get(right.size() - 1));
            sequence.add((trackSize - 1));
            totalHeadMovement += Math.abs((trackSize - 1) - left.get(0));

            for (int i = 0; i <= left.size() - 2; i++) {
                if (left.get(i).equals(currentPosition))
                    continue;
                head = left.get(i);
                sequence.add(head);
                totalHeadMovement += Math.abs(left.get(i + 1) - head);
            }
            sequence.add(left.get(left.size() - 1));
        } else {
            totalHeadMovement += Math.abs(left.get(0) - head);
            for (int i = 0; i <= left.size() - 2; i++) {
                head = left.get(i);
                sequence.add(head);
                totalHeadMovement += Math.abs(left.get(i + 1) - head);
            }

            sequence.add(left.get(left.size() - 1));
            totalHeadMovement += Math.abs(0 - left.get(left.size() - 1));
            sequence.add(0);
            totalHeadMovement += Math.abs(0 - right.get(0));

            for (int i = 0; i <= right.size() - 2; i++) {
                if (right.get(i).equals(currentPosition))
                    continue;
                head = right.get(i);
                sequence.add(head);
                totalHeadMovement += Math.abs(right.get(i + 1) - head);
            }
            sequence.add(right.get(right.size() - 1));
        }

        printSequence(sequence);
        System.out.println("Total Head Movement: " + totalHeadMovement + " cylinders");
        System.out.println("Seek Time: approx. " + totalHeadMovement*seekRate + " ms");
        promptRestart();
    }

    private static void runCScan() {
        System.out.println("\nSelected Algorithm: Circular Scan ");
        int seekTime = 0;
        int head = currentPosition, totalHeadMovement = 0;

        System.out.print("\nInput alpha (greater than or equal to 0): ");
        int alpha = sc.nextInt();
        while (alpha < 0){
            System.out.print("\nInput alpha (greater than or equal to 0): ");
            alpha = sc.nextInt();
        }

        List<Integer> right = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> sequence = new ArrayList<>();

        for (int i = 0; i < requestSize; i++) {
            if (requests[i] == currentPosition) {
                right.add(requests[i]);
                left.add(requests[i]);
            } else if (requests[i] > currentPosition) {
                right.add(requests[i]);
            } else {
                left.add(requests[i]);
            }
        }

        sequence.add(currentPosition);
        Collections.sort(right);
        Collections.sort(left);
        totalHeadMovement += Math.abs(right.get(0) - head);
        for (int i = 0; i <= right.size() - 2; i++) {
            head = right.get(i);
            sequence.add(head);
            totalHeadMovement += Math.abs(right.get(i + 1) - head);
        }

        sequence.add(right.get(right.size() - 1));
        totalHeadMovement += Math.abs((trackSize - 1) - right.get(right.size() - 1));
        sequence.add((trackSize - 1));
        totalHeadMovement += Math.abs((trackSize - 1) - 0);
        sequence.add(0);
        totalHeadMovement += Math.abs(left.get(0) - 0);

        for (int i = 0; i <= left.size() - 2; i++) {
            if (left.get(i).equals(currentPosition))
                continue;
            head = left.get(i);
            sequence.add(head);
            totalHeadMovement += Math.abs(left.get(i + 1) - head);
        }
        sequence.add(left.get(left.size() - 1));
        seekTime = totalHeadMovement - (trackSize - 1) + alpha;

        printSequence(sequence);
        System.out.println("Total Head Movement: " + totalHeadMovement + " cylinders");
        System.out.println("Seek Time: approx. " + seekTime*seekRate + " ms");
        promptRestart();
    }

    private static void runLook() {
        System.out.println("\nSelected Algorithm: LOOK ");
        int head = currentPosition, totalHeadMovement = 0;
        boolean movingRight = false;

        List<Integer> right = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> sequence = new ArrayList<>();

        for (int i = 0; i < requestSize; i++) {
            if (requests[i] > currentPosition) {
                right.add(requests[i]);
            } else {
                left.add(requests[i]);
            }
        }
        
        System.out.print("Enter direction (0 for left, 1 for right): ");
        int direction = sc.nextInt();

        if (direction == 1) {
            movingRight = true;
        }

        sequence.add(currentPosition);
        if (movingRight) {
            Collections.sort(right);
            Collections.sort(left, Comparator.reverseOrder());

            totalHeadMovement += Math.abs(right.get(0) - head);
            for (int i = 0; i < right.size(); i++) {
                head = right.get(i);
                sequence.add(head);
                if (i < right.size() - 1) {
                    totalHeadMovement += Math.abs(right.get(i + 1) - head);
                }
            }

            totalHeadMovement += Math.abs(left.get(0) - right.get(right.size() - 1));
            for (int i = 0; i < left.size(); i++) {
                head = left.get(i);
                sequence.add(head);
                if (i < left.size() - 1) {
                    totalHeadMovement += Math.abs(left.get(i + 1) - head);
                }
            }
        } else {
            Collections.sort(right);
            Collections.sort(left, Comparator.reverseOrder());

            totalHeadMovement += Math.abs(left.get(0) - head);
            for (int i = 0; i <= left.size() - 2; i++) {
                head = left.get(i);
                sequence.add(head);
                totalHeadMovement += Math.abs(left.get(i + 1) - head);
            }

            sequence.add(left.get(left.size() - 1));
            totalHeadMovement += Math.abs(right.get(0) - left.get(left.size() - 1));

            for (int i = 0; i <= right.size() - 2; i++) {
                if (right.get(i).equals(currentPosition))
                    continue;
                head = right.get(i);
                sequence.add(head);
                totalHeadMovement += Math.abs(right.get(i + 1) - head);
            }
            sequence.add(right.get(right.size() - 1));
        }

        printSequence(sequence);
        System.out.println("Total Head Movement: " + totalHeadMovement + " cylinders");
        System.out.println("Seek Time: approx. " + totalHeadMovement*seekRate + " ms");
        promptRestart();
    }

    private static void runCLook() {
        System.out.println("\nSelected Algorithm: C-LOOK ");
        int head = currentPosition, totalHeadMovement = 0;
        int seekTime = 0;

        System.out.print("\nInput alpha (greater than or equal to 0): ");
        int alpha = sc.nextInt();
        while (alpha < 0){
            System.out.print("\nInput alpha (greater than or equal to 0): ");
            alpha = sc.nextInt();
        }

        List<Integer> right = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> sequence = new ArrayList<>();

        for (int i = 0; i < requestSize; i++) {
            if (requests[i] > currentPosition) {
                right.add(requests[i]);
            } else {
                left.add(requests[i]);
            }
        }

        sequence.add(currentPosition);
        Collections.sort(right);
        Collections.sort(left);

        totalHeadMovement += Math.abs(right.get(0) - head);
        for (int i = 0; i < right.size(); i++) {
            head = right.get(i);
            sequence.add(head);
            if (i < right.size() - 1) {
                totalHeadMovement += Math.abs(right.get(i + 1) - head);
            }
        }

        totalHeadMovement += Math.abs(left.get(0) - right.get(right.size() - 1));
        for (int i = 0; i < left.size(); i++) {
            head = left.get(i);
            sequence.add(head);
            if (i < left.size() - 1) {
                totalHeadMovement += Math.abs(left.get(i + 1) - head);
            }
        }
        seekTime = totalHeadMovement - Math.abs(left.get(0) - right.get(right.size() - 1)) + alpha;

        printSequence(sequence);
        System.out.println("Total Head Movement: " + totalHeadMovement + " cylinders");
        System.out.println("Seek Time: approx. " + seekTime*seekRate + " ms");
        promptRestart();
    }

    public static void printSequence(List<Integer> sequence) {
        System.out.print("Sequence of Locations: " + sequence.get(0));
        for (int i = 1; i < sequence.size(); i++)
            System.out.print(" -> " + sequence.get(i));
        System.out.println();
    }

    public static int findMinNode(Node diff[]) {
        int index = -1, minimum = Integer.MAX_VALUE;
        for (int i = 0; i < diff.length; i++) {
            if (!diff[i].accessed && minimum > diff[i].distance) {
                minimum = diff[i].distance;
                index = i;
            }
        }
        return index;
    }

    public static void calculateDifference(int queue[], int head, Node diff[]) {
        for (int i = 0; i < diff.length; i++)
            diff[i].distance = Math.abs(queue[i] - head);
    }
}

class Node {
    int distance = 0;
    boolean accessed = false;
}