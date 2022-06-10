/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

public class Entry {

  private int entryId;
  private String billId;
  private String memberId;
  private boolean valid;

  public Entry(int entryId, String billId, String memberId) {
    this.entryId = entryId;
    this.billId = billId;
    this.memberId = memberId;
    this.valid = false;
  }

  /**
   * Sets the valid state to True 
   */
  public void setValid() {
    this.valid = true;
  }

  /**
   * @return  the valid state 
   */
  public boolean isValid() {
    return this.valid;
  }

  /**
   * @return  the bill id associated with this entry 
   */
  public String getBillId() {
    return this.billId;
  }

  /**
   * @return  the member id associated with this entry
   */
  public String getMemberId() {
    return this.memberId;
  }

  /**
   * @return  summary of entry with id 
   * Entry ID: 2      
   */
  public String summary() {
    return String.format(OutputFormat.ENTRY_ID, this.entryId);
  }
}
