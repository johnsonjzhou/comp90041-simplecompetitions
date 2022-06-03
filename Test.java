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
    // String[] test = {"111111", "1a1111", "1114345", "12353", "001101"};
    // for (String t : test) {
    //   stringMatcher(t);
    // }
    // loadMember();
    loadBill();
    
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

  public static void loadMember() {
    String csv = "111111,John,john@abc.com";
    String csv1 = "11111m,John,john@abc.com";
    String csv2 = "111111,,john@abc.com";
    String csv3 = "11111,John,john@abc.com";
    String csv4 = "111111,John,";
    String csv5 = ",John,john@abc.com";

    try {
      Member m = new Member(csv5);
      System.out.println(m);

    } catch (DataFormatException e) {
      System.out.println(e.getCause());
      System.out.println(e.getMessage());
    }
  }

  public static void loadBill() {
    // 100000,111111,300.5,false
    // 100013,,400.5,false
    // String csv = "100000,111111,300.5,false";
    // String csv = "100000,111111,300.5,true";
    // String csv = "100000,111111,30x3,true";
    // String csv = "100000,111111,,false";
    String csv = "100000,111111,300.5,";
    // String csv = "100000,111111,300.5";
    // String csv = "100013,,400.5,false";
    try {
      Bill b = new Bill(csv);
      System.out.println(b);

    } catch (DataFormatException e) {
      System.out.println(e.getCause());
      System.out.println(e.getMessage());
    }
  }
}
