/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

public class NonNumberException extends Exception {

  public NonNumberException(String message) {
    super(OutputErrors.NUMBER_EXPECTED, new Throwable(message));
  }
}
