/*
CARINGAL, Richard Maru A.
1CSA

Write a program that will accept a number n and display the sum of even numbers and sum of odd numbers from 1 to n.
*/

import java.util.Scanner;

public class While1{
  public static  void main(String[] args){
    Scanner input = new Scanner(System.in);
    int counter = 1;
    int sumOdd = 0;
    int sumEven = 0;
    System.out.println("This program will display the sum of even numbers and sum of odd numbers from 1 to n.");
    System.out.print("Enter a number: ");
    int number = input.nextInt();
    while(counter <= number){
      if (counter%2 == 0) sumEven += counter;
      if (counter%2 == 1) sumOdd += counter;
      counter++;
    }
    System.out.println("The sum of odd numbers from 1 to " + number + " is " + sumOdd);
    System.out.println("The sum of even numbers from 1 to " + number + " is " + sumEven);
  }
}
