/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

/**
 * Thrown when an error occurs in the conduct of a main menu action
 */
public class MenuException extends Exception {

  /**
   * Constructor for creating a new Exception with an error message 
   * @param  message  reason why the error was thrown 
   */
  public MenuException(String message) {
    super(message);
  }
}
