/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

/**
 * Classes implementing CommaSeparated can parse from and convert to data CSV
 */
public interface CommaSeparated {
  
  /**
   * Parses data from one csv line 
   * @param  csv  comma separate data from csv file 
   * @throws  DataFormatException  if the CSV data format is incorrect 
   */
  public void parseCsv(String csv) throws DataFormatException;

  /**
   * Converts the class data into a CSV string 
   * @return data as csv line
   */
  public String toCsv();
}
