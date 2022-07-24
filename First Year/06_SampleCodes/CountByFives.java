/*
CARINGAL, Richard Maru A.
IICS - 1CSA
*/

import java.util.Scanner;

public class CountByFives{
  public static void main(String[] args){
    String choice;
    Scanner in = new Scanner(System.in);

    do {
      System.out.println("CountByFives");

      //loop for counting by 5
      for(int start=5; start <= 500; start+=5){
        System.out.print(start + " "); //prints value of start per iteration
        if(start%50 == 0){ //checks value of start if its a multiple of 50
          System.out.println(); //creates a new line
        }
      }

      //Asks user if they would like to run the program again
      System.out.println();
      System.out.print("Do you want to run the program again (Y/N): ");
      choice = in.nextLine();
      System.out.println();

    } while(choice.equalsIgnoreCase("Y"));
  }
}
