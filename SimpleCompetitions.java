/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

public class SimpleCompetitions {

  private UserConsole console;
  private DataProvider data;
  private boolean testMode = true;

  public SimpleCompetitions() {
    this.console = new UserConsole();
  }

  /** private */

  private void load() {
    String fileMode, testMode, memberFile, billFile;

    System.out.println(OutputPrompts.LOAD_FILE);
    fileMode = console.readBufferedNext();
    console.clearBuffer();

    // todo file load mode 
    
    System.out.println(OutputPrompts.RUN_MODE);
    testMode = console.readBufferedNext();
    console.clearBuffer();

    // todo run mode 
    
    this.loadDataFiles();
  }

  /**
   * Initiates data provider by loading the members and bills files. 
   * Program will exit if DataAccessException or DataFormatException is thrown. 
   */
  private void loadDataFiles() {
    String memberFile, billFile; 

    System.out.println(OutputPrompts.MEMBER_FILE);
    memberFile = console.readBufferedNext();
    console.clearBuffer();

    System.out.println(OutputPrompts.BILL_FILE);
    billFile = console.readBufferedNext();
    console.clearBuffer();

    try {
      this.data = new DataProvider(memberFile, billFile);
    } catch (DataAccessException | DataFormatException e) {
      // @see  https://edstem.org/au/courses/7656/discussion/887137
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  private void exit() {
    this.console.close();
    System.out.println(OutputPrompts.GOODBYE);
  }

  /** public */

  // todo
  // public Competition addNewCompetition() {
      
  // }

  public void report() {
    
  }

  /**
  * Main program that uses the main SimpleCompetitions class
  * @param args main program arguments
  */
  public static void main(String[] args) {
    
    //Create an object of the SimpleCompetitions class
    SimpleCompetitions sc = new SimpleCompetitions();
      
    //Add your code to complete the task
    sc.load();
  }
}
