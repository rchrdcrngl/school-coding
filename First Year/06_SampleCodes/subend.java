import java.util.Scanner;

public class subend
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter endwith or substring: ");
    String ans = input.nextLine();
    if (ans.equals("")) {
        System.out.println("Enter valid answer!");
    } else if (ans.equals("endwith")) {
        System.out.println("endsWith() method checks whether a String ends with a specified string. The result is returned with a boolean value.");
        System.out.print("Enter a String: ");
        String inputStr = input.nextLine();
        System.out.print("\nEnter a String to check if the String above ends with it: ");
        String endwithStr = input.nextLine();
        endwth(inputStr, endwithStr);
    } else if (ans.equals("substring")) {
        System.out.print("\nsubstring() method: returns a substring of a given String based on a passed index. The length of returned substring is always endIndex - beginIndex. beginIndex should be > 0 or < endIndex and endIndex < stringlength.");
        System.out.print("\nEnter a string: ");
        String inputStr2 = input.nextLine();
        System.out.print("\nEnter beginIndex: ");
        int beginIndex = input.nextInt();
        System.out.print("\nEnter endIndex: ");
        int endIndex = input.nextInt();
        st(inputStr2, beginIndex, endIndex);
    }
  }

  public static void endwth(String input,String endwith) {
    boolean endwithBoolean = input.endsWith(endwith);
    System.out.println("First string ends with Second string: " + endwithBoolean);
  }

  public static void st(String input, int begin, int end) {
    if (end != 0) {
      System.out.println("Output: " + input.substring(begin, end));
    } else {
      System.out.println("Output: " + input.substring(begin));
    }
  }

}





/*

import java.util.Scanner;

public class subend
{
  public static void main(String[] args)
  {

    System.out.println("endsWith() method checks whether a String ends with a specified string. The result is returned with a boolean value.");
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a String: ");
    String inputStr = input.nextLine();
    System.out.print("\nEnter a String to check if the String above ends with it: ");
    String endwithStr = input.nextLine();
    boolean endwithBoolean = inputStr.endsWith(endwithStr);
    System.out.println("First string ends with Second string: " + endwithBoolean);


    System.out.print("\nsubstring() method: returns a substring of a given String based on a passed index. The length of returned substring is always endIndex - beginIndex. beginIndex should be > 0 or < endIndex and endIndex < stringlength.");
    System.out.print("\nEnter a string: ");
    String inputStr2 = input.nextLine();
    System.out.print("\nEnter beginIndex: ");
    int beginIndex = input.nextInt();
    System.out.print("\nEnter endIndex: ");
    int endIndex = input.nextInt();
    if (endIndex != 0) {
      System.out.println("Output: " + inputStr2.substring(beginIndex, endIndex));
    }
    System.out.println("Output: " + inputStr2.substring(beginIndex));
  }
}

*/
