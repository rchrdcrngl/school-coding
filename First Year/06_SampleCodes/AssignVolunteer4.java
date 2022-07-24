import java.util.Scanner;

public class AssignVolunteer4 {
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int donationType;
    String volunteer;
    String message;
    final int FURNITURE_CODE = 2;
    final int ELECTRONICS_CODE = 3;
    final int OTHERS_CODE = 4;
    final int CLOTHING_CODE = 1;
    //final int OTHERS_CODE = 2;
    final String CLOTHING_PRICER = "Regina";
    final String OTHERS_PRICER = "Marco";
    final String FURNITURE_PRICER = "Walter";
    final String ELECTRONICS_PRICER = "Lydia";
    System.out.print("Enter an integer: ");
    donationType = in.nextInt();
    switch(donationType)
    {
      case(CLOTHING_CODE):{
        volunteer = CLOTHING_PRICER;
        message = "a clothing donation.";
        break;
      }
      case(FURNITURE_CODE):{
        volunteer = FURNITURE_PRICER;
        message = "a furniture donation.";
        break;
      }
      case(ELECTRONICS_CODE):{
        volunteer = ELECTRONICS_PRICER;
        message = "an electronics donation.";
        break;
      }
      case(OTHERS_CODE):{
        volunteer = OTHERS_PRICER;
        message = "another type of donation.";
        break;
      }
      default:{
        volunteer = "<invalid>";
        message = "an invalid type of donation.";
      }
    }

    System.out.println("You have entered <" + donationType + "> " + message);
    System.out.println("You may refer to " + volunteer + " for the donations.");
  }
}
