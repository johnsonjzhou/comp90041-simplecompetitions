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
