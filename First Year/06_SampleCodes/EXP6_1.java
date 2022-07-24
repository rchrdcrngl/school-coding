import java.util.Scanner;

/*
CARINGAL, Richard Maru A.
IICS - 1CSA
*/

public class EXP6_1{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    //23. Given two strings, append them together (known as "concatenation") and return the result. However, if the concatenation creates a double-char, then omit one of the chars, so "abc" and "cat" yields "abcat".
    System.out.println("PROBLEM # 23");
    System.out.print("Enter a string: ");
    String stringA = in.nextLine();
    System.out.print("Enter another string: ");
    String stringB = in.nextLine();
    // checks whether the end of stringA is the starting character of stringB
    if (stringA.charAt(stringA.length()-1) == stringB.charAt(0)) {
      System.out.print("Output: " + stringA.substring(0, stringA.length()-1).concat(stringB));//removes last character of String A before concatenating string B
    }
    else
    {
      System.out.print("Output: " + stringA.concat(stringB));//concatenates string A and string B together
    }

    //27. Given two strings, append them together (known as "concatenation") and return the result. However, if the strings are different lengths, omit chars from the longer string so it is the same length as the shorter string. So "Hello" and "Hi" yield "loHi". The strings may be any length.
    System.out.println("\n\nPROBLEM # 27");
    System.out.print("Enter a string: ");
    String strA = in.nextLine();
    System.out.print("Enter another string: ");
    String strB = in.nextLine();
    int excess;
    if (strA.length() == strB.length()) { // checks if strings are of equal length
      System.out.print("Output: " + strA.concat(strB)); //concatenates strA and strB
      }
      else {
        if (strA.length() > strB.length()) { //checks if strA is longer than strB
          excess = strA.length() - strB.length();
          System.out.print("Output: " + strA.substring(excess).concat(strB)); //removes excess letter (using substring) from strA then concatenated strB
        }
        else { // process when strB is longer than strA
          excess = strB.length() - strA.length();
          System.out.print("Output: " + strA.concat(strB.substring(excess))); //concatenates strA with strB with removed excess letter (using substring)
      }
    }
  }
}
