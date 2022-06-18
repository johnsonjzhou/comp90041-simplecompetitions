/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

public class SimpleCompetitions {

  private UserConsole console;
  private DataProvider data;
  private State state;

  public SimpleCompetitions() {
    this.console = new UserConsole();
    this.state = new State();
  }

  /** private */

  /**
   * @return  True if user selected "y", False if user selected "n", otherwise loop 
   */
  private boolean userSelectYes() {
    this.console.clearBuffer();
    choosing : while(true) {
      String choice = this.console.readBufferedNext();
      this.console.clearBuffer();

      switch(choice) {
        case "y":
          return true;

        case "n":
          return false;

        default:
          System.out.println(OutputErrors.UNSUPPORTED_OPTION);
          continue choosing;
      }
    }
  }

  private void load() {
    System.out.println(OutputPrompts.LOAD_FILE);

    try {
      if (this.userSelectYes()) {
        // load the state from file 
        this.loadState();

      } else {
        // ask the user to choose a run mode 
        modeLoop : while(true) {
          System.out.println(OutputPrompts.RUN_MODE);
          String testMode = console.readBufferedNext();
          console.clearBuffer();
    
          switch (testMode) {
            case "t":
              this.state.setTestMode(true);
            case "n": 
              break modeLoop;
    
            default:
              System.out.println(OutputErrors.UNSUPPORTED_OPTION);
              continue modeLoop;
          }
        }
      }
      
      this.loadDataFiles();

    } catch (DataAccessException | DataFormatException e) {
      // @see  https://edstem.org/au/courses/7656/discussion/887137
      System.out.println(e.getMessage());
      this.exit(1);
    }
    
    this.menuLoop();
  }

  /**
   * Initiates data provider by loading the members and bills files. 
   * Program will exit if DataAccessException or DataFormatException is thrown. 
   * @throws  DataAccessException  if error in accessing member or bill file 
   * @throws  DataFormatException  if error in the format of bill or member files 
   */
  private void loadDataFiles() throws DataAccessException, DataFormatException{
    String memberFile, billFile; 

    System.out.println(OutputPrompts.MEMBER_FILE);
    memberFile = console.readBufferedNext(true);
    console.clearBuffer();

    System.out.println(OutputPrompts.BILL_FILE);
    billFile = console.readBufferedNext(true);
    console.clearBuffer();

    this.data = new DataProvider(memberFile, billFile);
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
            this.drawWinners();
            continue menuLoop;

          case 4: 
            this.displaySummary();
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
    for (Competition competition : this.state.getCompetitions()) {
      if (competition.isActive()) {
        throw new MenuException(OutputErrors.CONCURRENT_COMPETITION);
      }
      
      if (competition.getId() >= sequence) {
        sequence = competition.getId() + 1;
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
    name = this.console.readNextLine();

    switch(type) {
      case "l": 
        this.state.addCompetition(new LuckyNumbersCompetition(sequence, name));
        break;

      case "r":
        this.state.addCompetition(new RandomPickCompetition(sequence, name));
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
    for (Competition competition : this.state.getCompetitions()) {
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

  /**
   * Draws winners by invoking the drawWinners method of the active Competition 
   */
  private void drawWinners() throws MenuException {
    Competition activeCompetition = this.getActiveCompetition();
    activeCompetition.drawWinners(this.data);
  }

  /**
   * Displays a summary about all the competitions 
   */
  private void displaySummary() {
    // summary about competitions 
    System.out.println(OutputPrompts.SUMMARY_HEADER);

    int completedCompetitions = 0;
    int activeCompetitions = 0; 

    for (Competition competition : this.state.getCompetitions()) {
      if (competition.isActive()) {
        activeCompetitions++;
      } else {
        completedCompetitions++;
      }
    }

    System.out.println(String.format(OutputFormat.SUMMARY_COMPLETED_COMPETITIONS, 
      completedCompetitions
    ));

    System.out.println(String.format(OutputFormat.SUMMARY_ACTIVE_COMPETITIONS, 
      activeCompetitions
    ));

    // details about each competition 
    for (Competition competition : this.state.getCompetitions()) {
      System.out.println();
      competition.report();
    }
  }

  /** 
   * Loads the state from a file 
   */
  private void loadState() throws DataAccessException {
    System.out.println(OutputPrompts.FILE_NAME);
    this.console.clearBuffer();
    String filepath = this.console.readBufferedNext();
    this.console.clearBuffer();

    try {
      FileIO file = new FileIO(filepath);
      Object loaded = file.readObject();

      // save the state if valid 
      if (loaded instanceof State) {
        this.state = new State((State) loaded);
        return;
      } 

      // otherwise abort
      throw new DataAccessException(OutputErrors.FILE_LOAD_ERROR);
    } catch (FileIOException e) {
      throw new DataAccessException(OutputErrors.FILE_LOAD_ERROR);
    }

  }

  /** 
   * Saves the state to a file 
   * @throws  MenuException  if there was a problem writing the file 
   */
  private void saveState() throws MenuException {
    System.out.println(OutputPrompts.SAVE_FILE);
    
    if (!this.userSelectYes()) {
      return;
    }

    try {
      System.out.println(OutputPrompts.FILE_NAME);
      this.console.clearBuffer();
      String filepath = this.console.readBufferedNext();
      this.console.clearBuffer();

      FileIO file = new FileIO(filepath);
      file.writeObject(this.state);

      this.data.saveDataFiles();

    } catch (FileIOException e) {
      throw new MenuException(OutputErrors.FILE_SAVE_ERROR);
    }
  }

  private void exit(int status) {
    this.console.close();
    System.out.println(OutputPrompts.GOODBYE);
    System.exit(status);
  }

  /**
   * @throws  MenuException  if there was a problem writing the file 
   */
  private void exit()  throws MenuException {
    this.saveState();
    this.exit(0);
  }

  /** public */

  @Deprecated
  public void report() {
    // moved to displaySummary 
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
