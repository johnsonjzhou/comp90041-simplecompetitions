/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

/**
 * Representing a member and associated information 
 */
public class Member implements CommaSeparated {

  /** regex expression for non-empty string */
  public static final String NON_EMPTY = "(.+)";

  /** id must not be empty string  */
  public static final String ID_FORMAT = NON_EMPTY;

  /** csv has 3 columns */
  private static final int COL_NUMBER = 3;

  /** the Member ID */
  private String id; 

  /** the Member name */
  private String name;

  /** the Member email  */
  private String email;

  /**
   * @param  csv  comma separate data from csv file, invokes parseCsv
   * @throws  DataFormatException  if the CSV data format is incorrect 
   */
  public Member(String csv) throws DataFormatException {
    this.parseCsv(csv);
  }

  /** public */

  /**
   * Parses member data from csv
   * @param  csv  comma separate data from csv file 
   * @throws  DataFormatException  if the CSV data format is incorrect 
   */
  public void parseCsv(String csv) throws DataFormatException {
    // 111111,John,john@abc.com 
    
    String[] values = csv.split(",");

    // validate columns  
    if (values.length != Member.COL_NUMBER) {
      throw new DataFormatException("Member data column number incorrect");
    }

    // validate id
    if (!values[0].matches(Member.ID_FORMAT)) {
      throw new DataFormatException("Member ID must not be empty");
    }

    // validate name 
    if (!values[1].matches(Member.NON_EMPTY)) {
      throw new DataFormatException("Member name not provided");
    }

    // valid email
    if (!values[2].matches(Member.NON_EMPTY)) {
      throw new DataFormatException("Member email not provided");
    }

    this.id = values[0];
    this.name = values[1];
    this.email = values[2];
  } 

  /**
   * @return  member info as csv 
   */
  public String toCsv() {
    return String.format("%s,%s,%s", this.id, this.name, this.email);
  }

  /**
   * Checks whether member ID matches test string 
   * @param  test  the string to test against 
   * @return  <code>True</code> if test string matches 
   */
  public boolean equals(String test) {
    return this.id.equals(test);
  }

  /**
   * @return  member ID 
   */
  public String getId() {
    return this.id;
  }

  /**
   * @return  member name 
   */
  public String getName() {
    return this.name;
  }
  
  /** overrides */

  /**
   * @return  member info as csv by invoking toCsv() method
   */
  @Override
  public String toString() {
    return this.toCsv();
  }
  
}
