/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */
import java.util.ArrayList;

/**
 * Repository of member and bill information, handling the loading and
 * saving of data to and from csv files 
 */
public class DataProvider {

  /** path to the member file */
  private String memberFile;

  /** path to the bill file  */
  private String billFile; 

  /** list of Member data objects */
  private ArrayList<Member> members;

  /** list of Bill data objects */
  private ArrayList<Bill> bills;

  /**
   * Constructor for creating a new DataProvider 
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

  /**
   * This will overwrite the designated filepath with a CSV containing 
   * the specified dataSet. The dataSet must implement the CommaSeparated 
   * interface.  
   * @param  dataSet  the data set to write to a CSV file 
   * @param  filepath  path name of the file to be written 
   * @throws  FileIOException  if an error occurs when writing the file 
   */
  private <T extends CommaSeparated> void writeCsvFile(ArrayList<T> dataSet, String filepath) 
  throws FileIOException {
    FileIO file = new FileIO(filepath);
    file.setWritable();
    
    for (T element : dataSet) {
      if (dataSet.indexOf(element) == 0) {
        file.overwrite(element.toCsv());
      } else {
        file.writeLine(element.toCsv());
      }
    }

    file.close();
  }

  /** public */

  /**
   * Gets the Bill for a given bill ID 
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
   * Checks whether a member ID belongs to a valid member 
   * @param  memberId  the member ID to look up
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
   * Gets a member's name from a member ID 
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
   * Returns a summary of number of members and bills in the Data Provider 
   * @return  brief summary of class showing number of members and bills 
   */
  public String getSummary() {
    return String.format("DataProvider: (%d) members, (%d) bills", 
      this.members.size(), this.bills.size()
    );
  }

  /**
   * Saves the members and bills files to CSV file 
   * @throws  FileIOException  if an error occurs when writing the file 
   */
  public void saveDataFiles() throws FileIOException {
    this.writeCsvFile(this.bills, this.billFile);
    this.writeCsvFile(this.members, this.memberFile);
  }

  /** override */

  /**
   * The string output of the class by invoking the getSummary() method 
   * @return  invokes the getSummary() method 
   */
  public String toString() {
    return this.getSummary();
  }
}
