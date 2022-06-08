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
    "A number is expected. Please try again.";

  public static final String NO_ACTIVE_COMPETITION = 
    "There is no active competition. Please create one!";

  public static final String CONCURRENT_COMPETITION = 
    "There is an active competition. SimpleCompetitions does not support concurrent competitions!";

  public static final String NO_COMPETITION_CREATED = 
    "No competition has been created yet!";

  public static final String COMPETITION_NO_ENTRIES = 
    "The current competition has no entries yet!";

  public static final String INVALID_COMPETITION_TYPE = 
    "Invalid competition type! Please choose again.";

  public static final String COMPETITION_CREATE_ERROR = 
    "Could not create competition. Please try again.";

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

  public static final String ENTRY_GREATER_7 = 
    "Invalid input! More than 7 numbers are provided. Please try again!";

  public static final String ENTRY_LESS_7 = 
    "Invalid input! Fewer than 7 numbers are provided. Please try again!";

  public static final String ENTRY_NO_DUPLICATES = 
    "Invalid input! All numbers must be different!";

  public static final String ENTRY_NOT_IN_RANGE = 
    "Invalid input! All numbers must be in the range from 1 to 35!";
}
