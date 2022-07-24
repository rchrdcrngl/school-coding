import java.util.Scanner;

public class power {
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    double power, base, sum = 0;
    double counter = 0;
    System.out.print("Enter base: ");
    base = in.nextDouble();
    System.out.print("Enter exponent: ");
    power = in.nextDouble();
    //checking
    if (power < 0) {
      System.out.println("Input positive exponent!");
      System.out.print("Enter exponent: ");
      power = in.nextDouble();
    }

    while(counter < power){
      sum = sum + exp(base, counter);
      counter++;
    }

    System.out.println("Output: " + sum);

  }

  public static Double exp(double x, double y){
    double product = 1;
    while(y>=0){
      product = product * x;
      y--;
    }
    return product;
  }
}
