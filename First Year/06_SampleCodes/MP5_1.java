/*
CARINGAL, Richard Maru A.
1CSA
*/

import java.util.*;

public class MP5_1{
  static Scanner console = new Scanner(System.in);

  public static void main(String[] args){
    double radius;
    double height;

    System.out.println("This program can calculate " + "the area of a rectangle, the area " + "of a circle, or volume of a cylinder.");
    System.out.println("To run the Program: ");
    System.out.println("1: To find the area of a rectangle. ");
    System.out.println("2: To find the area of a circle.");
    System.out.println("3: To find the volume of a cylinder.");

    System.out.println("-1: To terminate the Program: ");
    System.out.println("To run the Program: ");
    int choice; // move declaration above assignment
    choice = console.nextInt();
    System.out.println();


    while (choice != -1) // change from equal to not equal
      {
        switch(choice){ // put switch case here
          case 3: System.out.print("Enter the radius of the base and " + " the height of the cylinder: ");// change case value from 1 to 3
                  radius = console.nextDouble();
                  height = console.nextDouble();
                  System.out.println();
                  System.out.printf("Volume = %.2f \n", cylinder(radius, height)); //change method call to cylinder() as well as parameters
                  // include backslash
                  break;
          case 2: // change case condition value from 3 to 2
                  System.out.print("Enter the radius of the circle: ");
                  radius = console.nextDouble();
                  System.out.println();
                  System.out.printf("Area = %.2f\n", circle(radius));//change method call to circle()
                  // include backslash
                  break;
          case 1: System.out.print("Enter the length and the width " + " of the rectangle: "); //change case condition value from 2 to 1
                  double length, width;// move to case 1
                  length = console.nextDouble();
                  width = console.nextDouble();
                  System.out.println();
                  System.out.printf("Area = %.2f\n", rectangle(length, width));// change method call to rectangle() as well as the parameters
                  // include backslash
                  break;
          default:
                  System.out.println("Invalid choice!");
        }
        System.out.println("To run the Program: ");
        System.out.println("1: To find the area of a rectangle. ");
        System.out.println("2: To find the area of a circle.");
        System.out.println("3: To find the volume of a cylinder.");
        System.out.println("-1: To terminate the Program: ");
        choice = console.nextInt();
        System.out.println();
      }// closing brace for while loop
    }// closing brace for main method



  public static double rectangle(double l, double w)
    {
      return l * w; // change r to w
    }

  public static double circle(double r)
    {
      return Math.PI * r * r; // change w to r & include return
    }

  public static double cylinder(double bR, double h)
    {
      return Math.PI * bR * bR * h; // change l to h
    }
}
