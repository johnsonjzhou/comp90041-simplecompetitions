/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

public final class OutputErrors {

  public static final String UNSUPPORTED_OPTION = 
    "Unsupported option. Please try again!";

  public static final String RUN_MODE_INVALID = 
    "Invalid mode! Please choose again.";
  
  public static final String NUMBER_EXPECTED = 
    "Invalid input! Numbers are expected. Please try again!";

  public static final String NO_ACTIVE_COMPETITION = 
    "There is no active competition. Please create one!";

  public static final String CONCURRENT_COMPETITION = 
    "There is an active competition. SimpleCompetitions does not support concurrent competitions!";

  public static final String NO_COMPETITION_CREATED = 
    "No competition has been created yet!";

  public static final String COMPETITION_NO_ENTRIES = 
    "The current competition has no entries yet!";

  public static final String COMPETITION_TOO_FEW_ENTRIES = 
    "Too few entries to draw winners. Please add more bills.";

  public static final String INVALID_COMPETITION_TYPE = 
    "Invalid competition type! Please choose again.";

  public static final String COMPETITION_CREATE_ERROR = 
    "Could not create competition. Please try again.";

  public static final String COMPETITION_DRAW_ERROR = 
    "An error occurred, competition has not been drawn. Please try again.";

  public static final String COMPETITION_NO_WINNERS = 
    "There are no lucky customers in this competition!";

  public static final String BILL_NUMBER = 
    "Invalid bill id! It must be a 6-digit number. Please try again.";

  public static final String BILL_NOT_EXIST = 
    "This bill does not exist. Please try again.";

  public static final String BILL_NO_MEMBER = 
    "This bill has no member id. Please try again.";

  public static final String BILL_USED = 
    "This bill has already been used for a competition. Please try again.";

  // * open context of this message per spec 
  // @see  https://edstem.org/au/courses/7656/discussion/887137
  public static final String DATA_ACCESS_ERROR = 
    "Could not access the designated file.";

  public static final String DATA_FORMAT_EXCEPTION = 
    "Data file is not in expected format.";

  public static final String ENTRY_GREATERTHAN = 
    "Invalid input! More than %d numbers are provided. Please try again!";

  public static final String ENTRY_LESSTHAN = 
    "Invalid input! Fewer than %d numbers are provided. Please try again!";

  public static final String ENTRY_NO_DUPLICATES = 
    "Invalid input! All numbers must be different!";

  public static final String ENTRY_NOT_IN_RANGE = 
    "Invalid input! All numbers must be in the range from 1 to 35!";

  public static final String ENTRY_LIMIT_EXCEEDED = 
    "Manual entries cannot be greater than total eligible entries. Please try again!";

  public static final String ENTRY_CREATE_ERROR = 
    "Could not create the entry. Please try again!";

  public static final String ENTRY_GENERATE_ERROR = 
    "An error occurred. An entry could not be generated.";

  public static final String FILE_SAVE_ERROR = 
    "An error occurred when trying to save the file.";

  public static final String FILE_LOAD_ERROR = 
    "An error occurred when trying to load the file.";
}
