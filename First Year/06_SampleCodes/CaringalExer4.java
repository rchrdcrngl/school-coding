import java.util.Scanner;

/* Write a JAVA program that allows the user to enter two values.
The program outputs the sum of and the difference between the two values
*/

public class CaringalExer4
{
  public static void main(String[] args)
  {
    // Use Scanner to get input from user
    Scanner input = new Scanner(System.in);
    // initialize integers in the program
    int valueA, valueB, sum, diff;
    // ask for input of a value
    System.out.print("Input a value : ");
    valueA = input.nextInt();
    // ask for input of another value
    System.out.print("Input another value : ");
    valueB = input.nextInt();
    // process for getting the sum & difference
    sum = valueA + valueB;
    diff = valueA - valueB;
    // output the results
    System.out.println("The sum of " + valueA + " and " + valueB + " is " + sum);
    System.out.print("The difference between " + valueA + " and " + valueB + " is " + diff);
  }
}
