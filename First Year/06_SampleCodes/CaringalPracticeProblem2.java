/*
Caringal, Richard Maru A.
IICS- 1CSA
Write a program that prompts the user to input five decimal numbers.
The program should then add the five decimal numbers, convert the sum
to the nearest integer, and print the result.
*/
import java.util.Scanner;

public class CaringalPracticeProblem2
{
  public static void main(String[] args)
  {
    // get user input
    Scanner input = new Scanner(System.in);
    // initialize double
    double counter = 1d;
    double sum = 0d;
    // program description
    System.out.println("This program will allow you to input five decimal numbers and convert its sum to the nearest integer");
    //input first value
    System.out.print("Enter a decimal number: ");
    double value = input.nextDouble();
    for (counter = 1d; counter <=5d; counter++) {
      // processing of sum
      sum = sum + value;
      // input other values
      if (counter < 5d) {
        System.out.print("\nEnter another decimal number: ");
        value = input.nextDouble();
      }
    }
    int converted = (int)Math.round(sum);
    System.out.print("The sum in the nearest integer is: " + converted);
  }
}
