import java.util.Scanner;

public class factorial{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int num;
    int nFactorial = 1;
    System.out.print("Enter number to compute: ");
    num = in.nextInt();

    for(int i=1; i<=num; i++){
      nFactorial = nFactorial * i;
    }
    System.out.println("The factorial of " + num + " is " + nFactorial);
  }
}
