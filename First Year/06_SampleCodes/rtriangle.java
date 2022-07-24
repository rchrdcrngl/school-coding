/*
Write a Java program that accepts the lengths of 2 sides of a right triangle, a and b,
respectively. The program then computes and displays the hypotenuse and area of the
triangle. The program should have the following methods:
a. hypotenuse() – compute and return the hypotenuse to main() (use Math.sqrt() )
b. area() – compute and return area to main()
c. display() – display hypotenuse and area
*/

import java.util.Scanner;

public class rtriangle {
  public static void main(String[] args){
    double a, b, hypotenuse, area;
    Scanner in = new Scanner(System.in);
    System.out.print("Enter one side of the right triangle: ");
    a = in.nextDouble();
    System.out.print("Enter another side of the right triangle: ");
    b = in.nextDouble();
    area = area(a, b);
    hypotenuse = hypotenuse(a, b);
    display(area, hypotenuse);
  }

  static double hypotenuse(double a, double b){
    double asquared = a * a;
    double bsquared = b * b;
    return Math.sqrt(asquared + bsquared);
  }

  static double area(double a, double b){
    return (a * b)/2;
  }

  static void display(double area, double hypotenuse){
    System.out.println("The area of the right triangle is "+ area + ".");
    System.out.printf("The hypotenuse of the right triangle is %.2f", hypotenuse);

  }
}
