/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

/**
 * Thrown when the data is not in the correct format  
 */
public class DataFormatException extends Exception {

  /**
   * @param  message  reason the error was thrown
   */
  public DataFormatException(String message) {
    super(OutputErrors.DATA_FORMAT_EXCEPTION, new Throwable(message));
  }
}
