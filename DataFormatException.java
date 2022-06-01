/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

public class DataFormatException extends Exception {

  public DataFormatException(String message) {
    super(OutputErrors.DATA_FORMAT_EXCEPTION, new Throwable(message));
  }
}
