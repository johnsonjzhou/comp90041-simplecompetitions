/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

/**
 * Thrown when a user input does not match supported format 
 */
public class UnsupportedInputException extends Exception {

  /** general message for this exception */
  public final static String GENERAL_MESSAGE = "Unsupported input detected.";

  /** 
   * Basic constructor without a message
   */
  public UnsupportedInputException() {
    super(GENERAL_MESSAGE);
  }
  
  /**
   * Constructor that takes a reason message 
   * @param  message  reason why the error was thrown 
   */
  public UnsupportedInputException(String message) {
    super(GENERAL_MESSAGE, new Throwable(message));
  }

}
