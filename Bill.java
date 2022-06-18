/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

public class Bill implements CommaSeparated {

  public static final String ID_FORMAT = "^\\d{6}$";
  private static final int COL_NUMBER = 4;

  private String id; 
  private String memberId; 
  private double amount; 
  private boolean used; 

  /**
   * @param  csv  comma separate data from csv file, invokes parseCsv
   * @throws  DataFormatException  if the CSV data format is incorrect 
   */
  public Bill(String csv) throws DataFormatException {
    this.parseCsv(csv);
  }

  /** public */

  /**
   * Parses bill data from csv
   * @param  csv  comma separate data from csv file 
   * @throws  DataFormatException  if the CSV data format is incorrect 
   */
  public void parseCsv(String csv) throws DataFormatException {
    // 100000,111111,300.5,false
    // 100013,,400.5,false

    String[] values = csv.split(",");

    // validate columns  
    if (values.length != Bill.COL_NUMBER) {
      throw new DataFormatException("Bill data column number incorrect");
    }

    // validate id 
    if (!values[0].matches(Bill.ID_FORMAT)) {
      throw new DataFormatException("Bill ID format incorrect");
    }

    // validate member id 
    if (values[1].length() > 0 && !values[1].matches(Member.ID_FORMAT)) {
      throw new DataFormatException("Member ID should be 6 digits");
    }

    // validate amount 
    if (values[2].length() < 1) {
      throw new DataFormatException("Bill amount not provided");
    }

    // validate used 
    if (values[3].length() < 1) {
      throw new DataFormatException("Bill used not provided");
    }

    this.id = values[0];
    this.memberId = values[1];

    try {
      this.amount = Double.parseDouble(values[2]);
    } catch (NumberFormatException e) {
      throw new DataFormatException("Bill amount is invalid");
    }

    switch(values[3]) {
      case "true": 
        this.used = true;
        break;
      case "false":
        this.used = false;
        break;
      default:
        throw new DataFormatException("Bill used is not of boolean type");
    }
  } 

  /**
   * @return  bill information as csv 
   */
  public String toCsv() {
    return String.format("%s,%s,%.1f,%b", 
      this.id, this.memberId, this.amount, this.used
    );
  }

  /**
   * @return  bill id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @return  the member id associated with this bill 
   */
  public String getMemberId() {
    return this.memberId;
  }

  /**
   * @return  the amount of this bill 
   */
  public double getAmount() {
    return this.amount;
  }

  /**
   * @return  <code>True</code> if the bill has been used for an entry 
   */
  public boolean isUsed() {
    return this.used;
  }

  /**
   * Marks the bill as being used for an entry 
   */
  public void markUsed() {
    this.used = true;
  }

  /**
   * @param  billId  the bill id to compare with 
   * @return  <code>True</code> if id equals provided billId 
   */
  public boolean equals(String billId) {
    return this.id.equals(billId);
  }

  /**
   * @return  bill information as csv by invoking toCsv() method
   */
  @Override
  public String toString() {
    return this.toCsv();
  }
}
