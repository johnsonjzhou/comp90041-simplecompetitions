/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

/**
 * For testing purposes only 
 */
public class Test {
  public static void main(String[] params) {
    
    // userInput();
    // 
    String[] test = {"111111", "1a1111", "1114345", "12353", "001101"};
    for (String t : test) {
      stringMatcher(t);
    }
  }

  public static void userInput() {
    UserConsole console = new UserConsole();

    try {
      String test = console.readBufferedNext();
      System.out.println(test);
      int test2 = console.readBufferedNextInt();
      System.out.println(test2);
      System.out.println(console.bufferedSize());
    } catch(NonNumberException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void stringMatcher(String test) {
    boolean match = test.matches("^\\d{6}$");
    System.out.println(match);
  }
}
