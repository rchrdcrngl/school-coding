public class testtttt
{
  public static void main(String[] args)
  {
    //integral
    byte myNum = 100;
    System.out.println(myNum);
    short myNum1 = 5000;
    System.out.println(myNum1);
    int myNum2 = 100000;
    System.out.println(myNum2);
    long myNum3 = 15000000000L;
    System.out.println(myNum3);
    //floating point
    float myNum4 = 5.75f;
    System.out.println("\n" + myNum4);
    double myNum5 = 19.99d;
    System.out.println(myNum5);
    float f1 = 35e3f;
    double d1 = 12E4d;
    System.out.println(f1);
    System.out.println(d1);
    //textual
    char myGrade = 'B';
    System.out.println(myGrade);
    char a = 65, b = 66, c = 67;
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
    //boolean
    boolean isJavaFun = true;
    boolean isFishSalty = false;
    if (isJavaFun = true) {
      System.out.println("true");
    }
    if (isFishSalty = false) {
      System.out.println("false");
    }
    // Outputs false
    //strings
    String greeting = "Mabuhay!";
    System.out.println(greeting);
    //Widening Casting
    int myInt2 = 13;
    double myDouble = myInt2; // Automatic casting:
    System.out.println("\n" + myInt2); // Outputs 13
    System.out.println(myDouble); // Outputs 13.0
    //Narrowing Casting
    double myDouble2 = 9.78;
    int myInt3 = (int) myDouble2; // Manual casting:
                                // double to int
    System.out.println("\n" + myDouble2); // Outputs 9.78
    System.out.println(myInt3); // Outputs 9

  }
}
