import java.util.Scanner;
public class MorganBonuses_1{
  final static Scanner in = new Scanner(System.in);
  final static String[] weeks = {"0", "1", "2", "3", "4", "5", "6+"};
  final static int[][] bonuses = {
    {5, 9, 16, 22, 30},
    {10, 12, 18, 24, 36},
    {20, 25, 32, 42, 53},
    {32, 38, 45, 55, 68},
    {46, 54, 65, 77, 90},
    {60, 72, 84, 96, 120},
    {85, 100, 120, 140, 175}
  };

  public static void main(String[] args){
    Scanner in2 = new Scanner(System.in);
    boolean isTrue = true;
    String response = "";
    int weeks = 0, reviews = 0;

    System.out.println("This program will display your appropriate bonus depending on full weeks worked and number of positive customer reviews");
    System.out.println();
    displayTable();

    while (isTrue){
      System.out.print("Full Weeks Worked: ");
      weeks = in.nextInt();
      System.out.print("Positive Online Customer Reviews: ");
      reviews = in.nextInt();
      System.out.println("Your appropriate bonus is " + getBonus(weeks, reviews));
      System.out.print("Continue? Y/N: ");
      response = in2.nextLine();
      if(response.equalsIgnoreCase("N")) isTrue = false;
      System.out.println();
    }
  }

  public static int getBonus(int weeks, int reviews){
    int row=weeks, col=reviews;
    if (weeks >=6) row=6;
    if (reviews >=4) col=4;
    return bonuses[row][col];
  }

  public static void displayTable(){
    System.out.println("Full Weeks\t0\t1\t2\t3\t4 or More");
    for(int row=0; row<bonuses.length; row++){
      System.out.print("\t" + weeks[row]);
      for(int col=0; col<bonuses[row].length; col++){
        System.out.print("\t" + bonuses[row][col]);
      }
      System.out.println();
    }
    System.out.println();
  }
}
