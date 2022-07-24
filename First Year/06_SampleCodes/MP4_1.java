
/*
========================================================================
Name: Richard Maru A. Caringal
Section: 1CSA
Program Description: This program takes as input the salesperson's
expected weekly sales and outputs the wages paid under each plan in
increasing order.
========================================================================
*/


import java.util.Scanner;

public class MP4_1 {
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    //Initialization of Variables
    double expectedWeeklySales;
    String first, second, third;
    String PLANA = "A";
    String PLANB = "B";
    String PLANC = "C";
    double planA, planB, planC;
    double firstSalary, secondSalary, thirdSalary;

    //Business Name
    System.out.println("\n\n\t\t============================================== \n\t\t||\t\t\t\t\t    ||\n\t\t|| Welcome to Ride My Bike Wage Computation ||\n\t\t||\t\t\t\t\t    ||\n\t\t============================================== ");
    //Product Description
    System.out.println("\n\n This program takes as input the salesperson's expected weekly sales and outputs the wages paid under each plan in increasing order.");
    //Choices of Wage Plans
    System.out.println("\n The following below are choices of Wage Plans.");
    System.out.println("\n (A) a straight salary of 300 pesos per week.");
    System.out.println(" (B) 3.50 pesos per hour for 40 hours plus a 10% commission on sales.");
    System.out.println(" (C) a straight 15% commission on sales with no other salary.");
    //Input of expected weekly sales
    System.out.print("\n Enter your expected weekly sales: ");
    expectedWeeklySales = in.nextInt();

    //Computation
    planA = 300;
    planB = (3.50 * 40) + (expectedWeeklySales * 0.10);
    planC = expectedWeeklySales * 0.15;

    //Arrangement
    if (planA <= planB && planA <= planC) { //lowest A
      first = PLANA;
      firstSalary = planA;
      if (planB <= planC) { //planC highest
        second = PLANB;
        secondSalary = planB;
        third = PLANC;
        thirdSalary = planC;
      } else {  //plan B highest
        second = PLANC;
        secondSalary = planC;
        third = PLANB;
        thirdSalary = planB;
      }
    } else if (planB <= planA && planB <= planC) { //planB lowest
      first = PLANB;
      firstSalary = planB;
      if (planA <= planC) { //planC highest
        second = PLANA;
        secondSalary = planA;
        third = PLANC;
        thirdSalary = planC;
      } else { //plan A highest
        second = PLANC;
        secondSalary = planC;
        third = PLANA;
        thirdSalary = planA;
      }
    } else if (planC <= planA && planC <= planB) { //planC lowest
      first = PLANC;
      firstSalary = planC;
      if (planA <= planB) { //planB highest
        second = PLANA;
        secondSalary = planA;
        third = PLANB;
        thirdSalary = planB;
      } else { //planA highest
        second = PLANB;
        secondSalary = planB;
        third = PLANA;
        thirdSalary = planA;
      }
    } else {
      first = PLANA;
      firstSalary = planA;
      second = PLANB;
      secondSalary = planB;
      third = PLANC;
      thirdSalary = planC;
    }


    //Printing out of salary per wage plan
    System.out.println("\n\n\t\t==============================================");
    System.out.println("\n\t\tBelow are the salary per wage plan in increasing order:");
    System.out.println("\n\t\t\tPlan " + first + "\t\t\t" + firstSalary + " PESOS");
    System.out.println("\t\t\tPlan " + second + "\t\t\t" + secondSalary + " PESOS");
    System.out.println("\t\t\tPlan " + third + "\t\t\t" + thirdSalary + " PESOS");
    System.out.println("\n\t\t==============================================");
  }
}
