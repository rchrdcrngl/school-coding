import java.util.Scanner;

public class AssignVolunteer2 {
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int donationType;
    String volunteer;
    String message;
    final int CLOTHING_CODE = 1;
    final int OTHERS_CODE = 2;
    final String CLOTHING_PRICER = "Regina";
    final String OTHERS_PRICER = "Marco";
    System.out.println("What type of donation are you going to make?");
    System.out.println("Enter " + CLOTHING_CODE + " for clothing and " + OTHERS_CODE + " for anything else.");
    System.out.print("Donation Type: ");
    donationType = in.nextInt();
    if (donationType == CLOTHING_CODE) {
      volunteer = CLOTHING_PRICER;
      message = "a clothing donation.";
    } else {
      volunteer = OTHERS_PRICER;
      message = "other donations.";
    }
    System.out.println("You have entered <" + donationType + "> " + message);
    System.out.println("You may refer to " + volunteer + " for the donations.");
  }
}
