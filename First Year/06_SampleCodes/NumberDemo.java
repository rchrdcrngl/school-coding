/*
Caringal, Richard Maru
Go, Jaden Ezriel
1-CSA
*/



import java.util.Scanner;

public class NumberDemo{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int a = 3;
    int b = 5;
    displayTwiceTheNumber(a);
    displayNumberSquared(a);
    displayNumberPlusFive(a);
    displayTwiceTheNumber(b);
    displayNumberSquared(b);
    displayNumberPlusFive(b);
  }

  public static void displayTwiceTheNumber(int x){
    System.out.println("Twice of " + x + " is " + (x * 2));
  }

  public static void displayNumberPlusFive(int x){
    System.out.println("Five plus " + x + " is " + (x+5));
  }

  public static void displayNumberSquared(int x){
    System.out.println("The square of " + x + " is " + (x*x));
  }
}
