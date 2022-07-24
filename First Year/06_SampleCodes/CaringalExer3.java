import java.util.Scanner;
/* Write a JAVA program that allows the user to
enter a value for hours worked in a day.
The program calculates the hours worked in a
five-day week and hours worked in a 252-day work year.
The program outputs all the results.
*/

public class CaringalExer3
{
  public static void main(String[] args)
  {
    // use Scanner to get inputs from user
    Scanner input = new Scanner(System.in);
    // initialize integers used in the program
    int day_hoursWorked, week_hoursWorked, year_hoursWorked;
    // ask for input of the hours worked  in a day
    System.out.print("Enter hours worked in a day > ");
    day_hoursWorked = input.nextInt();
    // process to get the hours worked in a 5-day week and in a 252-day work year
    week_hoursWorked = day_hoursWorked * 5;
    year_hoursWorked = day_hoursWorked * 252;
    // output results
    System.out.println("The hours worked in a five-day week is " + week_hoursWorked);
    System.out.println("The hours worked in a 252-day work year is " + year_hoursWorked);
  }
}
