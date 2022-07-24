/*
==========================
CARINGAL, Richard Maru A.
IICS - 1CSA
==========================
*/

import java.util.Scanner;
public class Temperature_1{
  final static Scanner in = new Scanner(System.in);
  final static String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

  public static void main(String[] args){
    Double[][] temp = new Double[12][2]; // initializes temp array with 12 rows (for each month) and 2 columns (highest and lowest)
    getData(temp); // calls getData() method to declare and print the elements of temp_array
    System.out.printf("\nThe average of the highest temperatures in the year is %.2f", averageHigh(temp)); // calls averageHigh
    System.out.printf("\nThe average of the lowest temperatures in the year is %.2f", averageLow(temp)); // calls averageLow
    System.out.printf("\nThe lowest temperature in the year is %.2f", temp[indexLowTemp(temp)][1]); // gets an element of temp with the index from indexLowTemp
    System.out.printf("\nThe highest temperature in the year is %.2f", temp[indexHighTemp(temp)][0]);// gets an element of temp with the index from indexHighTemp
  }

  public static void getData(Double[][] temp){
    // input data to temp array
    System.out.println("This program will output the average high, average low, and highest and lowest temperatures of the year.");
    for(int row=0; row<temp.length; row++){
      for(int col=0; col<temp[row].length; col++){
        if (col == 0) {
          System.out.print("Input highest temp of " + months[row] + " : ");
        } else {
          System.out.print("Input lowest temp of " + months[row] + " : ");
        }
        temp[row][col] = in.nextDouble();
      }
    }
    System.out.println();

    // prints out data from temp array
    System.out.println("\nHighest and Lowest Temperature per month:");
    for(int row=0; row<temp.length; row++){
      System.out.print("\t" + months[row]); // prints out the months
      for(int col=0; col<temp[row].length; col++){
        System.out.print("\t" + temp[row][col]);
      }
      System.out.println();
    }
  }

  // method that returns average of the high temperatures in every month
  public static Double averageHigh(Double[][] array){
    Double sum = 0.0;
    for(int i = 0; i < array.length; i++){
      sum += array[i][0]; // value of the first column in every row is added
    }
    return sum/(array.length*1.0); // calculates average with conversion to double
  }

  // method that returns average of the low temperatures in every month
  public static Double averageLow(Double[][] array){
    Double sum = 0.0;
    for(int i = 0; i < array.length; i++){
      sum += array[i][1]; // value of the second column in every row is added
    }
    return sum/(array.length*1.0); // calculates average with conversion to double
  }

  // method that returns the index of the Highest Temperature among all months
  public static int indexHighTemp(Double[][] array){
    Double temp_high = array[0][0]; // sets temp_highest to the highest temp of the first month
    int index = 0;
    for(int row = 0; row < array.length; row++){
      if (array[row][0] > temp_high){ // checks if element is greater than temporary highest
        index = row;
        temp_high = array[row][0]; // sets the highest temp to current element
      }
    }
    return index;
  }

  // method that returns the index of the Lowest Temperature among all months
  public static int indexLowTemp(Double[][] array){
    Double temp_low = array[0][1]; // sets temp_highest to the lowest temp of the first month
    int index = 0;
    for(int row = 0; row < array.length; row++){
      if (array[row][1] < temp_low) { // checks if element is less than temporary lowest
        index = row;
        temp_low = array[row][1]; // sets the lowest temp to current element
      }
    }
    return index;
  }

}
