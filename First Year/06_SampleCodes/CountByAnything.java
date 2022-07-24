/*
CARINGAL, Richard Maru A.
IICS - 1CSA
*/

import java.util.Scanner;

public class CountByAnything{
  public static void main(String[] args){
    String choice;
    int value;
    Scanner in = new Scanner(System.in);
    Scanner in2 = new Scanner(System.in);

    do {
      System.out.println("CountByAnything");

      //asks user the value to count by
      System.out.print("Enter a value to count by: ");
      value = in.nextInt();

      //loop for counting by the value given by user
      for(int start=value; start <= 500; start+=value){
        System.out.print(start + " ");

        //creates new line after 10 values have been displayed
        if(start%(value*10) == 0){
          System.out.println();
        }
      }

      //asks user if they would like to run the program again
      System.out.println();
      System.out.print("Do you want to run the program again (Y/N): ");
      choice = in2.nextLine();
      System.out.println();

    } while(choice.equalsIgnoreCase("Y"));
  }
}
