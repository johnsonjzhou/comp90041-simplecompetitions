/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

/**
 * Thrown when could not access data files 
 */
public class DataAccessException extends Exception {

  /**
   * Constructor for creating the exception with an error message 
   * @param  message  reason the error was thrown 
   */
  public DataAccessException(String message) {
    super(OutputErrors.DATA_ACCESS_ERROR, new Throwable(message));
  }
}
