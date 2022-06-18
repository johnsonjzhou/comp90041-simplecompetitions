/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

/**
 * Thrown when a field is not numeric when it's expected to be numeric
 */
public class NonNumberException extends Exception {

  /**
   * @param  message  reason why the error was thrown 
   */
  public NonNumberException(String message) {
    super(OutputErrors.NUMBER_EXPECTED, new Throwable(message));
  }
}
