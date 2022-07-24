
/*
=======================================
CARINGAL, Richard Maru A.
IICS - 1CSA
======================================
*/

import java.util.Scanner;


public class MP1_1 {
  public static void main(String[] args)
  {
    // use Scanner to get user input
    Scanner input = new Scanner(System.in);
    // Business Name
    System.out.println("\n\n\t\t========================================== \n\t\t||\t\t\t\t\t||\n\t\t||\t Welcome to MyWay Subway \t||\n\t\t||\t\t\t\t\t||\n\t\t========================================== ");
    // Program Instructions
    System.out.println("\n\n You will be given choices for building your sandwich. \n Please enter your selection after each prompt \n and then press the Enter key.");
    System.out.println("\n\n We are now going to build your sandwich.");
    //Prompt user to choose sandwich
    System.out.println(" Please enter your sandwich choice among the following:");
    System.out.print(" Ham, Beef, Reuben, PBJ, Cheese or Vegetarian: ");
    String sandwich = input.nextLine();
    //Prompts user to choose bread
    System.out.println("\n Now enter your choice of bread.");
    System.out.print(" Rye, Wheat, White, Sourdough or Pumpernickel: ");
    String bread = input.nextLine();
    //Prompts user to choose condiments
    System.out.println("\n Enter one condiment of your choice.");
    System.out.print(" Mayo, Mustard, Ketchup, or none: ");
    String condiment = input.nextLine();
    //Prompts user to choose drink
    System.out.println("\n Please enter your drink choice.");
    System.out.print(" Coke, Diet, Tea, Coffee, Water: ");
    String drink = input.nextLine();
    //Prompts user to choose between half sandwich and whole sandwich
    System.out.println("\n We're almost there!");
    System.out.print(" Please enter 150 if you would like a half sandwich and 250 for a whole sandwich: ");
    String price = input.nextLine();
    //Program outputs a summary of the user's order
    System.out.println("\n\n\t\t ++++++++++++++++++++++++++++++++ \n\t\t Here is a summary of your order:");
    System.out.println("\n\n\t\t\t Pizza: \t" + sandwich);
    System.out.println("\t\t\t Bread: \t" + bread);
    System.out.println("\t\t\t Condiment: \t" + condiment);
    System.out.println("\t\t\t Drink: \t"+ drink);
    System.out.println("\n\n\t\t\t Cost: \t" + "PHP. " + price + ".00");
    System.out.println("\t\t ++++++++++++++++++++++++++++++++");

  }
}
