import java.util.Scanner;

public class  AscendingDescending {
  public static void main(String[] args) {
    int num1, num2, num3;
    Scanner in = new Scanner(System.in);
    System.out.print("Enter integer: ");
    num1 = in.nextInt();
    System.out.print("Enter integer: ");
    num2 = in.nextInt();
    System.out.print("Enter integer: ");
    num3 = in.nextInt();
    if (num1 <= num2 && num1 <= num3) {
        if (num2 <= num3){
          System.out.println("Ascending: " + num1 + "" + num2 + "" + num3);
          System.out.println("Descending: " + num3 + "" + num2 + "" + num1);
        } else {
          System.out.println("Ascending: " + num1 + "" + num3 + "" + num2);
          System.out.println("Descending: " + num2 + "" + num3 + "" + num1);
        }
    }
    else if (num2 <= num1 && num2 <= num3) {
        if (num1 <= num3){
          System.out.println("Ascending: " + num2 + "" + num1 + "" + num3);
          System.out.println("Descending: " + num3 + "" + num1 + "" + num2);
        } else {
          System.out.println("Ascending: " + num2 + "" + num3 + "" + num1);
          System.out.println("Descending: " + num1 + "" + num3 + "" + num2);
        }
    }
    else if (num3 <= num2 && num3 <= num1) {
          if (num1 <= num2){
            System.out.println("Ascending: " + num3 + "" + num1 + "" + num2);
            System.out.println("Descending: " + num2 + "" + num1 + "" + num3);
          } else {
            System.out.println("Ascending: " + num3 + "" + num2 + "" + num1);
            System.out.println("Descending: " + num1 + "" + num2 + "" + num3);
          }
    }
  }
}
