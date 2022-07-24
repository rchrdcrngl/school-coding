import java.util.Scanner;


public class QuizScoreStatistics{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int highScore=0;
    int lowScore=0;
    int sumScore=0;
    int inputScore;
    int numScore=1;
    double aveScore;

    System.out.println("Reminder: Score must be greater than 0 and less than 10");
    System.out.print("Enter a test score: ");
    inputScore = in.nextInt();

    // initialization of lowest, highest, and sum
    if (inputScore>0 && inputScore<10){
      highScore = inputScore;
      lowScore = inputScore;
      sumScore = inputScore;
    }


    while (inputScore != 99) {
      // data check
      while(inputScore<=0 || inputScore>=10){
        if (inputScore == 99) break;
        System.out.println("Test scores should be greater than 0 and less than 10!");
        System.out.print("Enter a test score: ");
        inputScore = in.nextInt();
      }


      if (inputScore >= 0 && inputScore <= 10) { //data check
        //check for highest score
        if(inputScore > highScore) {
          highScore = inputScore;

          // check for lowest score
        } else if ((lowScore == 0) || (inputScore < lowScore)) {
          lowScore = inputScore;
        }
        //calculate for sum and number of test scores
        sumScore = sumScore + inputScore;
        numScore++;
      }


      //input test score again
      System.out.print("Enter a test score: ");
      inputScore = in.nextInt();
    }

    //calculate average test scores
    aveScore = sumScore/numScore;

    //print data
    System.out.println("The highest test score is: " + highScore);
    System.out.println("The lowest test score is: " + lowScore);
    System.out.println("The average test score is: " + aveScore);


  }
}
