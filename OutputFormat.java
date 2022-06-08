/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

public final class OutputFormat {
  
  // Competition ID: 1, Competition Name: Easter Holidays, Type: RandomPickCompetition
  public static final String COMPETITION_CREATED = 
    "Competition ID: %d, Competition Name: %s, Type: %s";

  // This bill ($300.5) is eligible for 6 entries.
  public static final String BILL_ELIGIBLE = 
    "This bill ($%.1f) is eligible for %d entries.";

  // Entry ID: 1     
  public static final String ENTRY_ID = 
    "Entry ID: %-6d";  //! trailing whitespace 

  // Member ID: 444444, Member Name: Long, Prize: 50000
  public static final String WINNING_ENTRY = 
    "Member ID: %s, Member Name: %s, Prize: %d";

  // Numbers:  5  7 16 21 23 26 28 [Auto]
  // * fixed 2-width nunmbers 
  public static final String ENTRY_NUMBERS = 
    "Numbers: %2d %2d %2d %2d %2d %2d %2d %s";

  // --> Entry ID: 3, Numbers:  2  7 14 18 22 25 35 [Auto]
  public static final String ENTRY_SUMMARY = 
    "--> Entry ID: %d, %s";

  public static final String SUMMARY_REPORT = 
    "----SUMMARY REPORT----\n+Number of completed competitions: %d\n+Number of active competitions: %d\n";

  // Competition ID: 1, name: Easter Holidays, active: no
  public static final String SUMMARY_COMPETITION = 
    "Competition ID: %d, name: %s, active: %s";

  // Number of entries: 12
  public static final String SUMMARY_ENTRIES = 
    "Number of entries: %d";
  
  // Number of winning entries: 2
  public static final String SUMMARY_WINNING_ENTRIES = 
    "Number of winning entries: %d";

  // Total awarded prizes: 55000
  public static final String SUMMARY_PRIZES = 
    "Total awarded prizes: %d";

}
