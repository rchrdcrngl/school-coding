import java.util.Scanner;
public class Airline_1{
  final static String[] rows = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
  final static Scanner in = new Scanner(System.in);
  final static Scanner in2 = new Scanner(System.in);

  public static void main(String[] args){
    boolean isTrue = true;
    String response = "";
    String[][] seats = new String[13][6];
    String ticketType = "";
    int row = 0, col=0;
    initSeats(seats);
    displaySeats(seats);
    while(isTrue){
      System.out.print("Please input ticket type: ");
      ticketType = in2.nextLine();
      showRow(seats, ticketType);
      System.out.println("Your seat has been reserved.");
      System.out.print("Reserve another ticket? Y/N: ");
      response = in2.nextLine();
      if (response.equalsIgnoreCase("N")) isTrue = false;
    }
  }

  public static void initSeats(String[][] seats){
    for(int row=0; row<seats.length; row++){
      for(int col=0; col<seats[row].length; col++){
        seats[row][col] = "*";
      }
    }
  }

  public static void showRow(String[][] seats, String ticketType){
    int row_first=0, lastrow=0;
    if (ticketType.equalsIgnoreCase("A")) {row_first = 0; lastrow = 1;}
    else if (ticketType.equalsIgnoreCase("B")) {row_first = 2; lastrow = 6;}
    else {row_first = 7; lastrow = 12;}
    System.out.println("AVAILABLE SEATS:");
    System.out.println("Row\tA\tB\tC\tD\tE\tF");
    for(int row=row_first; row<=lastrow; row++){
      System.out.print(rows[row]);
      for(int col=0; col<seats[row].length; col++){
        System.out.print("\t" + seats[row][col]);
      }
      System.out.println();
    }

    System.out.println("* indicates that the seat is available.");
    System.out.println("X indicates that the seat has been assigned.");
    selectSeat(seats, row_first, lastrow);
  }

  public static void selectSeat(String[][] seats, int row, int lastrow){
    int rw=0;
    String col = "";
    System.out.print("Please input row from " + (row+1) + " to " + (lastrow+1) + " : ");
    rw = in.nextInt();
    System.out.print("Please input column of desired seat : ");
    col = in2.nextLine();
    seats[rw-1][getColumn(col)] = "X";
    displaySeats(seats);
  }

  public static int getColumn(String col){
    int column =0;
    switch(col){
      case "A": column=0; break;
      case "B": column=1; break;
      case "C": column=2; break;
      case "D": column=3; break;
      case "E": column=4; break;
      case "F": column=5; break;
      default: column=0;
    }
    return column;
  }

  public static void displaySeats(String[][] seats){
    System.out.println();
    System.out.println("AVAILABLE SEATS:");
    System.out.println("Row\tA\tB\tC\tD\tE\tF");
    for(int row=0; row<seats.length; row++){
      System.out.print(rows[row]);
      for(int col=0; col<seats[row].length; col++){
        System.out.print("\t" + seats[row][col]);
      }
      System.out.println();
    }

    System.out.println();
    System.out.println("* indicates that the seat is available.");
    System.out.println("X indicates that the seat has been assigned.");
    System.out.println("[A] Rows 1 & 2 are FIRST CLASS.");
    System.out.println("[B] Rows 3 to 7 are BUSINESS CLASS.");
    System.out.println("[C] Rows 8 to 13 are ECONOMY CLASS.");
    System.out.println();
  }
}
