import java.util.Scanner;

public class EvenOdd {
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    System.out.print("Please enter an integer: ");
    int num = in.nextInt();
    if (num%2 == 0) {
      System.out.println("The number " + num + " is even.");
    } else {
      System.out.println("The number " + num + " is odd.");
    }
  }
}
