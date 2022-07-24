/*
CARINGAL, Richard Maru A.
1CSA

Write a program that will compute for n! which is the product of all the numbers from 1 to n.
*/

import java.util.Scanner;

public class While2{
  public static  void main(String[] args){
    Scanner input = new Scanner(System.in);
    int productNumber = 1;
    System.out.println("This program will will compute for n!");
    System.out.print("Enter a number: ");
    int number = input.nextInt();
    while (number > 0){
      productNumber = productNumber * number;
      number--;
    }
    System.out.print("The factorial of n is " + productNumber);
  }
}
