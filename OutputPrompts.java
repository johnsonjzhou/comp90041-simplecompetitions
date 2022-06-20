/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

/**
 * Dictionary for program prompts 
 */
public final class OutputPrompts {

  /** This is a lookup class only and has no instantised fields or methods */
  public OutputPrompts() {}

  /** application welcome message  */
  public static final String WELCOME_MESSAGE = 
    "----WELCOME TO SIMPLE COMPETITIONS APP----";

  /** should the user load state from file */
  public static final String LOAD_FILE = 
    "Load competitions from file? (Y/N)?";

  /** should the user save state to fil */
  public static final String SAVE_FILE = 
    "Save competitions to file? (Y/N)?";

  /** successfully saved file state  */
  public static final String SAVE_FILE_SUCCESS = 
    "Competitions have been saved to file.";

  /** for user to select test or normal mode  */
  public static final String RUN_MODE = 
    "Which mode would you like to run? (Type T for Testing, and N for Normal mode):";

  /** file name entry prompt  */
  public static final String FILE_NAME = 
    "File name:";

  /** member file entry prompt  */
  public static final String MEMBER_FILE = 
    "Member file: ";  //! trailing whitespace

  /** bill file entry prompt  */
  public static final String BILL_FILE = 
    "Bill file: ";  //! trailing whitespace

  /** the bill file has been updated successfully  */
  public static final String BILL_FILE_UPDATED = 
    "The bill file has also been automatically updated.";

  /** menu prompt  */
  public static final String MENU_SELECTION = 
    "Please select an option. Type 5 to exit.";

  /** menu options  */
  public static final String[] MENU_OPTIONS = {
    "1. Create a new competition", 
    "2. Add new entries", 
    "3. Draw winners", 
    "4. Get a summary report", 
    "5. Exit"
  };

  /**  prompt for type of competition */
  public static final String COMPETITION_TYPE = 
    "Type of competition (L: LuckyNumbers, R: RandomPick)?:";

  /** prompt to enter the competition name  */
  public static final String COMPETITION_NAME = 
    "Competition name: ";  //! trailing whitespace 

  /** competition created successfully  */
  public static final String COMPETITION_CREATED = 
    "A new competition has been created!";

  /** prompt for bill ID  */
  public static final String BILL_ID = 
    "Bill ID: ";  //! trailing whitespace 

  /** header for listing generated entries  */
  public static final String ENTRIES_GENERATED = 
    "The following entries have been automatically generated:";

  /** prompt to enter number of manual entries  */
  public static final String MANUAL_ENTRIES = 
    "How many manual entries did the customer fill up?: ";

  /** prompt to enter lucky number manual entry numbers  */
  public static final String LUCKYNUMBER_ENTRY = 
    "Please enter 7 different numbers (from the range 1 to 35) separated by whitespace.";

  /** header to list entries */
  public static final String ENTRIES_ADDED = 
    "The following entries have been added:";

  /** prompt to select whether or not to add more entries  */
  public static final String ADD_MORE_ENTRIES = 
    "Add more entries (Y/N)?";

  /** header to list winning entries  */
  public static final String WINNING_ENTRIES = 
    "Winning entries:";

  /** header for summary report  */
  public static final String SUMMARY_HEADER = 
    "----SUMMARY REPORT----";

  /** exit message  */
  public static final String GOODBYE = 
    "Goodbye!";
}
