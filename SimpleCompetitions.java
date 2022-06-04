/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/
import java.util.ArrayList;

public class SimpleCompetitions {

  private UserConsole console;
  private DataProvider data;
  private ArrayList<Competition> competitions;
  private boolean testMode = true;

  public SimpleCompetitions() {
    this.console = new UserConsole();
    this.competitions = new ArrayList<Competition>();
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
    this.menuLoop();
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
      this.exit(1);
    }
  }

  /**
   * Main menu loop 
   */
  private void menuLoop() {
    menuLoop : while(true) {

      System.out.println(OutputPrompts.MENU_SELECTION);
      for (String option : OutputPrompts.MENU_OPTIONS) {
        System.out.println(option);
      }

      try {
        this.console.clearBuffer();
        int selection = this.console.readBufferedNextInt();
        this.console.clearBuffer();

        switch(selection) {
          case 1: 
            // todo  create new competition
            continue menuLoop;

          case 2:
            // todo  add new entries
            continue menuLoop;

          case 3:
            // todo  draw winners 
            continue menuLoop;

          case 4: 
            // todo  get summary report
            continue menuLoop;

          case 5:
            this.exit();
            break menuLoop;

          default: 
            System.out.println(OutputErrors.UNSUPPORTED_OPTION);
            continue menuLoop;
        }
      } catch (NonNumberException e) {
        System.out.println(OutputErrors.NUMBER_EXPECTED);
        continue menuLoop;
      } catch (NullPointerException e) {
        this.exit(1);
      }
    }
  }

  private void exit(int status) {
    this.console.close();
    System.out.println(OutputPrompts.GOODBYE);
    System.exit(status);
  }

  private void exit() {
    this.exit(0);
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
