/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */
import java.util.ArrayList;

public class DataProvider {

  private String memberFile;
  private String billFile; 

  private ArrayList<Member> members;
  private ArrayList<Bill> bills;

  /**
   * 
   * @param memberFile A path to the member file (e.g., members.csv)
   * @param billFile A path to the bill file (e.g., bills.csv)
   * @throws DataAccessException If a file cannot be opened/read
   * @throws DataFormatException If the format of the the content is incorrect
   */
  public DataProvider(String memberFile, String billFile) 
  throws DataAccessException, DataFormatException {
    
    this.memberFile = memberFile;
    this.billFile = billFile;

    this.members = new ArrayList<Member>();
    this.bills = new ArrayList<Bill>();

    this.loadDataFiles();
  }

  /** private */

  /**
   * Loads member and bill data from specified files 
   */
  private void loadDataFiles() 
  throws DataAccessException, DataFormatException {
    
    try {
      FileIO memberFile = new FileIO(this.memberFile);
      ArrayList<String> memberFileLines = memberFile.readContentsAsArray();

      for (String csv : memberFileLines) {
        this.members.add(new Member(csv));
      }

      FileIO billFile = new FileIO(this.billFile);
      ArrayList<String> billFileLines = billFile.readContentsAsArray();

      for (String csv : billFileLines) {
        this.bills.add(new Bill(csv));
      }
    } catch (FileIOException e) {
      throw new DataAccessException(e.getCause().getMessage());
    }
  }

  /** public */

  /**
   * @param  billId  id of the bill to retrieve
   * @return  the Bill designated by the billId or null
   */
  public Bill getBill(String billId) {
    for (Bill bill : this.bills) {
      if (bill.equals(billId)) {
        return bill;
      }
    }
    return null;
  }

  /**
   * @return  <code>True</code> if memberId matches a Member 
   */
  public boolean isValidMember(String memberId) {
    for (Member member : this.members) {
      if (member.equals(memberId)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param  memberId  search for this member id
   * @return  the member name, or a 0 length string if not found 
   */
  public String getMemberName(String memberId) {
    String name = "";
    for (Member member : this.members) {
      if (member.equals(memberId)) {
        name = member.getName();
      }
    }
    return name;
  }

  /**
   * @return  brief summary of class showing number of members and bills 
   */
  public String getSummary() {
    return String.format("DataProvider: (%d) members, (%d) bills", 
      this.members.size(), this.bills.size()
    );
  }

  /** override */

  /**
   * @return  invokes the getSummary() method 
   */
  public String toString() {
    return this.getSummary();
  }
}
