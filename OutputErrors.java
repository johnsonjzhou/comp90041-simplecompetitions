/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

/**
 * A dictionary of output errors that can be printed to screen 
 */
public final class OutputErrors {

  /** This is a lookup class only and has no instantised fields or methods */
  public OutputErrors() {}

  /** general error */
  public static final String GENERAL_ERROR = 
    "An unexpected error occurred.";

  /** unsupported option */
  public static final String UNSUPPORTED_OPTION = 
    "Unsupported option. Please try again!";

  /** invalid run mode */
  public static final String RUN_MODE_INVALID = 
    "Invalid mode! Please choose again.";
  
  /** when a number is expected */
  public static final String NUMBER_EXPECTED = 
    "Invalid input! Numbers are expected. Please try again!";

  /** no active competition found */
  public static final String NO_ACTIVE_COMPETITION = 
    "There is no active competition. Please create one!";

  /** more than one active competition  */
  public static final String CONCURRENT_COMPETITION = 
    "There is an active competition. SimpleCompetitions does not support concurrent competitions!";

  /** no competition created yet  */
  public static final String NO_COMPETITION_CREATED = 
    "No competition has been created yet!";

  /** competition does not have any entries  */
  public static final String COMPETITION_NO_ENTRIES = 
    "The current competition has no entries yet!";

  /** competition has too few entries to draw  */
  public static final String COMPETITION_TOO_FEW_ENTRIES = 
    "Too few entries to draw winners. Please add more bills.";

  /** invalid competition type  */
  public static final String INVALID_COMPETITION_TYPE = 
    "Invalid competition type! Please choose again.";

  /**  error on competition creation  */
  public static final String COMPETITION_CREATE_ERROR = 
    "Could not create competition. Please try again.";

  /** error on competition draw  */
  public static final String COMPETITION_DRAW_ERROR = 
    "An error occurred, competition has not been drawn. Please try again.";

  /** competition has no winners  */
  public static final String COMPETITION_NO_WINNERS = 
    "There are no lucky customers in this competition!";

  /** when the bill number is invalid  */
  public static final String BILL_NUMBER = 
    "Invalid bill id! It must be a 6-digit number. Please try again.";

  /** when the bill does not exist */
  public static final String BILL_NOT_EXIST = 
    "This bill does not exist. Please try again.";

  /** bill has no member ID */
  public static final String BILL_NO_MEMBER = 
    "This bill has no member id. Please try again.";

  /** bill has already been used for a competition  */
  public static final String BILL_USED = 
    "This bill has already been used for a competition. Please try again.";

  /** 
   * open context of this message per spec 
   * https://edstem.org/au/courses/7656/discussion/887137
   */
  public static final String DATA_ACCESS_ERROR = 
    "Could not access the designated file.";

  /** data file format is incorrect   */
  public static final String DATA_FORMAT_EXCEPTION = 
    "Data file is not in expected format.";

  /** entry numbers are more than expected  */
  public static final String ENTRY_GREATERTHAN = 
    "Invalid input! More than %d numbers are provided. Please try again!";

  /** entry numbers are less than expected  */
  public static final String ENTRY_LESSTHAN = 
    "Invalid input! Fewer than %d numbers are provided. Please try again!";

  /** entry already contains this number  */
  public static final String ENTRY_NO_DUPLICATES = 
    "Invalid input! All numbers must be different!";

  /** numeric value of entry is not within valid range  */
  public static final String ENTRY_NOT_IN_RANGE = 
    "Invalid input! All numbers must be in the range from 1 to 35!";

  /** entry limit exceeded maximum allowed  */
  public static final String ENTRY_LIMIT_EXCEEDED = 
    "The number must be in the range from %d to %d. Please try again.";

  /** could not create the entry  */
  public static final String ENTRY_CREATE_ERROR = 
    "Could not create the entry. Please try again!";

  /** could not generate the entry  */
  public static final String ENTRY_GENERATE_ERROR = 
    "An error occurred. An entry could not be generated.";

  /** could not save the file  */
  public static final String FILE_SAVE_ERROR = 
    "An error occurred when trying to save the file.";

  /** could not load the file  */
  public static final String FILE_LOAD_ERROR = 
    "An error occurred when trying to load the file.";
}
