/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */
import java.io.Serializable;
import java.lang.Comparable;

/**
 * Implements Comparable so it can be sorted in the list 
 * Implements Serializable so it can be saved to file 
 */
public class Entry implements Comparable<Entry>, Serializable {

  private int entryId;
  private String billId;
  private String memberId;
  private boolean valid;
  private int prize; 

  public Entry(int entryId, String billId, String memberId) {
    this.entryId = entryId;
    this.billId = billId;
    this.memberId = memberId;
    this.valid = false;
    this.prize = 0;
  }

  /**
   * @return  the entry id
   */
  public int getId() {
    return this.entryId;
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
   * @param  set the prize to this value 
   */
  public void setPrize(int prize) {
    this.prize = prize;
  }

  /**
   * @return  the prize associated with this entry 
   */
  public int getPrize() {
    return this.prize;
  }

  /**
   * @return  summary of entry with id 
   * Entry ID: 2      
   */
  public String summary() {
    return String.format(OutputFormat.ENTRY_ID, this.entryId);
  }

  /** Comparable */

  /**
   * To enable sorting of entries by entry Id
   * Note: this class has a natural ordering that is inconsistent with equals.
   * @param  entry  an entry object to compare to
   */
  public int compareTo(Entry entry) {
    return this.entryId - entry.getId();
  }
}
