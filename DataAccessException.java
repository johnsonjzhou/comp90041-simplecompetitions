/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

public class DataAccessException extends Exception {

  public DataAccessException(String message) {
    super(OutputErrors.DATA_ACCESS_ERROR, new Throwable(message));
  }
}
