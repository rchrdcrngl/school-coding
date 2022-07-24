/*
Caringal, Richard Maru A.
IICS- 1CSA
Write a Java program that prompts the user
to input the elapsed time for an event in
seconds.
*/
import java.util.Scanner;

public class MP_1
{
  public static void main(String[] args)
  {
    // used to get input from user
    Scanner input = new Scanner(System.in);
    // program description
    System.out.println("This program allows you to enter elapsed time in seconds and it will output it in a HH:MM:SS format.");
    // input elapsed time in seconds
    System.out.print("Please enter elapsed time in seconds: ");
    int elapsed = input.nextInt();
    // computation for hour, minutes, seconds
    int hour = elapsed/3600;// divide by 3600 because there are 3600 secs in an hour
    int minutes = (elapsed-(hour*3600))/60;// get the remaining value from hour and converts it into minutes
    int seconds = (elapsed-((hour*3600)+(minutes*60)));// get the remaining value from hour & minute
    // outputs the elapsed time in a HH:MM:SS format
    System.out.println("The elapsed time in HH:MM:SS format is: ");
    System.out.println(hour + ":" + minutes + ":" + seconds);
  }
}
