import java.util.Scanner;
/*
CARINGAL, RICHARD MARU A.
IICS - 1CSA
*/

public class MP3_1 {
  public static void main(String[] args)
  {
    String myName = "Richard Maru A. Caringal";
    System.out.println("a. " + myName);
    String yourName = "richard maru a. caringal";
    System.out.println("b. " + yourName);
    String message = "             I love ";
    System.out.println("c. " + message);
    System.out.println("d. " + myName.charAt(0));
    System.out.println("e. " + myName.length());
    System.out.println("f. " + myName.equals(yourName));
    System.out.println("g. " + myName.equalsIgnoreCase(yourName));
    System.out.println("h. " + message.concat(myName));
    Scanner in = new Scanner(System.in);
    System.out.print("i. Enter string:");
    String input = in.nextLine();
    System.out.println("i. " + input.equals(myName));
    System.out.println("j. " + yourName.toUpperCase());
    System.out.println("k. " + myName.toUpperCase());
    System.out.println("l. " + yourName.charAt(yourName.length()-1));
    System.out.println("m. " + message.charAt(0));
    System.out.println("n. " + yourName.length());
    System.out.println("o. " + yourName.replace('i', '!'));
    System.out.println("p. " + myName.substring(3, 7));
    System.out.println("q. " + message.trim());
    System.out.println("r. " + message.length());
    System.out.println("s. " + myName.equals(yourName));
    System.out.println("t. " + message.trim().substring(2, 6));
  }
}
