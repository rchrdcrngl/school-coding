import java.util.Scanner;
import javax.swing.JOptionPane;

public class ShadyRestRoom
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);

    System.out.println("Choose 1 for a queen bed, 2 for a king, or 3 for a king and a pull-out couch.");
    System.out.print("Choice: ");
    Integer choice = in.nextInt();
    if (choice == 1) {
      Integer price = 125;
      System.out.println("You chose " + choice + ". The price is " + price);
      System.out.println("Choose 1 for lake view or 2 for a park view.");
      Integer view = in.nextInt();
      if (view == 1) {
        System.out.println("You chose " + choice + "and a lake view. The price is " + (price + 15));
      } else if (view == 2) {
        System.out.println("You chose " + choice + "and a lake view. The price is " + price);
      } else {System.out.println("You have entered an invalid choice.");}
    } else if (choice == 2) {
      Integer price = 139;
      System.out.println("You chose " + choice + ". The price is " + price);
      System.out.println("Choose 1 for lake view or 2 for a park view.");
      Integer view = in.nextInt();
      if (view == 1) {
        System.out.println("You chose " + choice + "and a lake view. The price is " + (price + 15));
      } else if (view == 2) {
        System.out.println("You chose " + choice + "and a lake view. The price is " + price);
      } else {System.out.println("You have entered an invalid choice.");}
    } else if (choice == 3) {
      Integer price = 160;
      System.out.println("You chose " + choice + ". The price is " + price);
      System.out.println("Choose 1 for lake view or 2 for a park view.");
      Integer view = in.nextInt();
      if (view == 1) {
        System.out.println("You chose " + choice + "and a lake view. The price is " + (price + 15));
      } else if (view == 2) {
        System.out.println("You chose " + choice + "and a lake view. The price is " + price);
      } else {System.out.println("You have entered an invalid choice.");}
    } else {System.out.println("You have entered an invalid choice. The price is 0.");}
  }
}
