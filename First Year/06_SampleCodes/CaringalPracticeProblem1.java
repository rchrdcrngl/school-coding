/*
Caringal, Richard Maru A.
IICS - 1CSA

Write a program that prompts the user to enter five test scores
and then prints the average test score.
*/

import java.util.Scanner;

public class CaringalPracticeProblem1
{
  public static void main(String[] args)
  {
    // get user input
    Scanner input = new Scanner(System.in);
    // initialize integers
    int counter = 1;
    int sum = 0;
    // program description
    System.out.println("This program will allow you to enter 5 test scores and then it will output the average test score.");
    //input first value
    System.out.print("Enter a test score: ");
    int value = input.nextInt();
    for (counter = 1; counter <=5; counter++) {
      // processing of sum
      sum = sum + value;
      // input other values
      if (counter < 5) {
        System.out.print("\nEnter another test score: ");
        value = input.nextInt();
      }
    }
    // print out average
    int average = sum/5;
    System.out.print("The average test score is: " + average);
  }
}
