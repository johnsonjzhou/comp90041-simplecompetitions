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
   */
  public void parseCsv(String csv) throws DataFormatException;

  /**
   * @return data as csv line
   */
  public String toCsv();
}
