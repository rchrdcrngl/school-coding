import java.util.Scanner;

public class HighLow {
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    double highest;
    double lowest;
    double score;

    System.out.println("This program will get the highest and lowest test score. Enter -1 to quit.");
    System.out.print("Enter test score: ");
    score = in.nextDouble();
    if(score<0) score=0;
    highest = score;
    lowest = score;

    while(score >0){
      System.out.print("Enter test score: ");
      score = in.nextDouble();
      if(score >=0){
        if (score > highest) {highest = score;}
        if (score < lowest) {lowest = score;}
      }
    }

    System.out.printf("The highest test score is %.2f", highest);
    System.out.printf(" while the lowest test score is %.2f", lowest);
  }
}
