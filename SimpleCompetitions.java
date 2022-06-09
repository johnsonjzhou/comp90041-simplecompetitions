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
  private boolean testMode = false;

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
    
    modeLoop : while(true) {
      System.out.println(OutputPrompts.RUN_MODE);
      testMode = console.readBufferedNext();
      console.clearBuffer();

      switch (testMode) {
        case "t":
          this.testMode = true;
        case "n": 
          break modeLoop;

        default:
          System.out.println(OutputErrors.UNSUPPORTED_OPTION);
          continue modeLoop;
      }
    }
    
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
    memberFile = console.readBufferedNext(true);
    console.clearBuffer();

    System.out.println(OutputPrompts.BILL_FILE);
    billFile = console.readBufferedNext(true);
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
            this.addNewCompetition();
            continue menuLoop;

          case 2:
            this.addEntries();
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
      } catch (MenuException e) {
        System.out.println(e.getMessage());
        continue menuLoop;
      } catch (NullPointerException e) {
        this.exit(1);
      }
    }
  }

  /**
   * Creates a new competition
   * @throws  MenuException  if there is already an active competition 
   */
  private void addNewCompetition() throws MenuException {
    int sequence = 1;
    String type, name;

    // check all current competitions and see if they are active 
    // and get the highest competition id number to advance the sequence 
    for (Competition competition : this.competitions) {
      if (competition.isActive()) {
        throw new MenuException(OutputErrors.CONCURRENT_COMPETITION);
      }
      
      if (competition.getId() >= sequence) {
        sequence = competition.getId();
      }
    }

    // create the competition
    System.out.println(OutputPrompts.COMPETITION_TYPE);

    typeLoop : while(true) {
      this.console.clearBuffer();
      type = this.console.readBufferedNext();
      this.console.clearBuffer();

      switch(type) {
        case "l": case"r": 
          break typeLoop;

        default: 
          System.out.println(OutputErrors.INVALID_COMPETITION_TYPE);
          continue typeLoop;
      }
    }

    System.out.println(OutputPrompts.COMPETITION_NAME);
    name = this.console.readBufferedNext(true);
    this.console.clearBuffer();

    switch(type) {
      case "l": 
        this.competitions.add(new LuckyNumbersCompetition(sequence, name, this.testMode));
        break;

      case "r":
        this.competitions.add(new RandomPickCompetition(sequence, name, this.testMode));
        break;

      default:
        throw new MenuException(OutputErrors.COMPETITION_CREATE_ERROR);
    }
  }

  /**
   * @return  the active competition
   * @throws  MenuException  if no active competition is found 
   */
  private Competition getActiveCompetition() throws MenuException {
    for (Competition competition : this.competitions) {
      if (competition.isActive()) {
        return competition;
      }
    }

    throw new MenuException(OutputErrors.NO_ACTIVE_COMPETITION);
  }

  /**
   * Creates entries by invoking the addEntries method of the active Competition 
   */
  private void addEntries() throws MenuException {
    Competition activeCompetition = this.getActiveCompetition();
    activeCompetition.addEntries(this.console, this.data);
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
