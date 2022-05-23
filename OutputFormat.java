/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

public final class OutputFormat {
  
  // Competition ID: 1, Competition Name: Easter Holidays, Type: RandomPickCompetition
  public static final String COMPETITION_CREATED = 
    "Competition ID: %d, Competition Name: %s, Type: $s";

  // This bill ($300.5) is eligible for 6 entries.
  public static final String BILL_ELIGIBLE = 
    "This bill ($%.1f) is eligible for %d entries.";

  // Entry ID: 1     
  public static final String GENERATED_ENTRY = 
    "Entry ID: %d     ";  //! trailing whitespace 
}
