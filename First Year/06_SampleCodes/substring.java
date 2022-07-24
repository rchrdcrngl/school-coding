public class substring{
  public static void main(String[] args){
    /*
    substring() method: returns a substring of a given String based on a passed index.
    The length of returned substring is always endIndex - beginIndex.
    beginIndex should be > 0 or < endIndex and endIndex < stringlength.
    */

    String str = "abcdefghijklmnopqrstuvwxyz";
    System.out.println("String: " + str);
    // output substring passing a beginIndex

    int beginIndex = 5;
    System.out.println("\nbeginIndex: " + beginIndex);
    System.out.println("substring output: " + str.substring(beginIndex));

    // output substring passing both beginIndex & endIndex
    int endIndex = 10;
    System.out.println("\nendIndex: " + endIndex);
    System.out.println("substring output: " + str.substring(beginIndex, endIndex));
    // length of output string is beginIndex - endIndex (starts after beginIndex)

    /*
    StringIndexOutOfBoundsException error when:
    a.) beginIndex < 0
    b.) beginIndex > endIndex
    c.) endIndex > string_length
    */
  }
}
