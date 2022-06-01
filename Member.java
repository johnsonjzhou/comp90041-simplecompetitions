/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

public class Member implements CommaSeparated {

  private String id; 
  private String name;
  private String email;

  public Member(String csv) throws DataFormatException {
    this.parseCsv(csv);
  }

  /** public */

  /**
   * Parses member data from csv
   * @param  csv  comma separate data from csv file 
   */
  public void parseCsv(String csv) throws DataFormatException {
    String[] values = csv.split(",");

    // validate columns  
    if (values.length != 3) {
      throw new DataFormatException("Member data requires 3 columns");
    }

    // validate id
    if (!values[0].matches("^\\d{6}$")) {
      throw new DataFormatException("Member ID should be 6 digits");
    }

    // validate name 
    if (values[1].length() < 1) {
      throw new DataFormatException("Member name not provided");
    }

    // valid email
    if (values[2].length() < 1) {
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
