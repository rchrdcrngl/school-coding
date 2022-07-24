import java.util.Scanner;

public class exc_9241 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    double base, height, area, perimeter, side1, side2, side3;
    System.out.println("Please choose one:");
    System.out.println("A. Area");
    System.out.println("B. Perimeter");
    System.out.print("Choice: ");
    char choice = in.next().charAt(0);
    switch(choice)
    {
      case 'a':
      case 'A':         System.out.println("Area Computation:");
                        System.out.print("Base: ");
                        base = in.nextDouble();
                        System.out.print("Height: ");
                        height = in.nextDouble();
                        area = base * height * 0.5;
                        System.out.println("The Area of the triangle is: " + area);
                        break;
      case 'b':
      case 'B':         System.out.println("Perimeter Computation:");
                        System.out.print("Side 1: ");
                        side1 = in.nextDouble();
                        System.out.print("Side 2: ");
                        side2 = in.nextDouble();
                        System.out.print("Side 3: ");
                        side3 = in.nextDouble();
                        perimeter = side1 + side2 + side3;
                        System.out.println("The perimeter of the triange is: " + perimeter);
                        break;
      default:          System.out.println("Please enter a valid input.");

    }
  }
}
