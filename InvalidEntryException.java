/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

/**
 * Thrown when there are some problems with the entry being created 
 */
public class InvalidEntryException extends Exception {

  /**
   * @param  message  reason for error being thrown 
   */
  public InvalidEntryException(String message) {
    super(message);
  }
}
