import java.util.Scanner;

public class polygon {
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    double sides, numberSides, apothem;
    double area, perimeter;
    System.out.print("Please input number of sides: ");
    numberSides = input.nextDouble();
    System.out.print("Please input sides: ");
    sides = input.nextDouble();
    System.out.print("Please input apothem: ");
    apothem = input.nextDouble();
    perimeter = perimeter(sides, numberSides);
    area = area(apothem, perimeter);
    display(perimeter, area);
  }

  static double perimeter(double s, double n){
    return s * n;
  }

  static double area(double r, double p){
    return (r * p)/2;
  }

  static void display(double P, double A){
    System.out.println("The perimeter is " + P + ".");
    System.out.println("The area is " + A + ".");
  }
}
