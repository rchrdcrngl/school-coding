import java.util.Scanner;

public class recursion {

  static Scanner in = new Scanner(System.in);
  static int sum = 0;

  public static int factorial(int n) {
    if(n==0) return 1;
    else return n * factorial(n-1);
  }

  /*
  factorial(3) > 3 * factorial(2)
  factorial(2) > 2 * factorial(1)
  factorial(1) > 1 * factorial(0)
  factorial(0) > 1
  */

  public static int power(int base, int n){
    if (n == 0) return 1;
    else return base * power(base, n-1);
  }
  /*
  power(2,3) > 2 * power(2,2)
  power(2,2) > 2 * power(2,1)
  power(2,1) > 2 * power(2,0)
  power(2,0) > 1
  */

  public static void Tail(int i){
    if(i>0){
      System.out.print(i + "");
      Tail(i-1);
    }
  }

  /*
  Tail(3) > 3  | Tail(2)
  Tail(2) > 2  | Tail(1)
  Tail(1) > 1  | Tail(0)
  Tail(0)
  Output: 3 2 1
  */

  public static void nonTail(int i){
    if(i>0){
      nonTail(i-1);
      System.out.print(i + "");
    }
  }

  /*
  nonTail works like a stacknonTail works like a stack (last in first out)
  nonTail(3) -> nonTail(2) 3
  nonTail(2) -> nonTail(1) 2
  nonTail(1) -> nonTail(0) 1
  Output: 1 2 3
  */

  public static int fibo(int n){
    if(n==0 || n==1) return n;
    else return fibo(n-1) + fibo(n-2);
  }

  public static int sumOfDigits(int i){
    if(i == 0) return sum;
    else {
      sum += i%10;
      return sumOfDigits(i/10);
    }
  }
  /*
                      sum             return
  sumOfDigits(234) =  0 + 4           sumOfDigits(23)
  sumOfDigits(23) =   0 + 4 + 3       sumOfDigits(2)
  sumOfDigits(2) =    0 + 4 + 3 + 2   sumOfDigits(0)
  sumOfDigits(0)                      0 + 4 + 3 + 2
  */


  public static void main(String[] args){
    int num;
    System.out.print("Enter a number: ");
    num = in.nextInt();
    System.out.println("The factorial is " + factorial(num));
    System.out.println("The power is " + power(2,num));
    System.out.println("The fibonacci sequence is " + fibo(num));
    System.out.println("The sum of digits is " + sumOfDigits(num));
    System.out.print("Tail: ");
    Tail(num);
    System.out.println();
    System.out.print("Non Tail: ");
    nonTail(num);
  }
}
