import java.util.Scanner;

public class isVoweltest{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);

    System.out.print("Enter a character: ");
    char ch = in.next().charAt(0);

    if(isVowel(ch)){
      System.out.println("It's a vowel!");
    } else {
      System.out.println("It is not a vowel.");
    }
  }

  public static boolean isVowel(char letter){
    switch(Character.toUpperCase(letter))
    {
      case 'A':
      case 'E':
      case 'I':
      case 'O':
      case 'U': return true;
      default: return false;
    }
  }

}
