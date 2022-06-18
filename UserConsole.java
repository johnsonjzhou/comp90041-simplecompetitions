/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

import java.util.Scanner;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * General handler for user inputs through a Scanner instance and System.in 
 */
public class UserConsole {

  private Scanner stdin;
  private ArrayList<String> inputBuffer; 

  /**
   * General constructor 
   */
  public UserConsole() {
    this.stdin = new Scanner(System.in);
    this.inputBuffer = new ArrayList<String>();
  }

  /** getter */

  /**
   * Exposes the scanner used in the class, not recommneded
   * @return  the Scanner instance 
   */
  public Scanner getScanner() {
    return this.stdin;
  }

  /** public */

  /**
   * @return  the nextLine from the input 
   */
  public String readNextLine() {
    return this.stdin.nextLine();
  }

  /**
   * Creates a read buffer by reading the entire input line 
   * and splitting at the spaces. 
   * This then enables to step through each of the input items iteratively. 
   * @param  preserveCase  <code>False</code> turns input into lower case 
   * @return  the string from the input that is next in the buffer array 
   * @throws  NoSuchElementException  when directed inputs unexpectedly end 
   */
  public String readBufferedNext(boolean preserveCase) throws NoSuchElementException {
    // return and remove the first element in the inputBuffer
    if (this.inputBuffer.size() > 0) {
      return this.inputBuffer.remove(0);
    }

    // receive from the console and add to buffer
    String input = this.stdin.nextLine();
    for (String next : input.split(" ")) {
      this.inputBuffer.add(preserveCase ? next : next.toLowerCase());
    }
    return this.readBufferedNext(preserveCase);
  }

  /**
   * Invokes readBufferNext with preserveCase parameter set to False 
   * @return  the string from the input that is next in the buffer array 
   * @throws  NoSuchElementException  when directed inputs unexpectedly end
   */
  public String readBufferedNext() throws NoSuchElementException {
    return this.readBufferedNext(false);
  }

  /**
   * Invokes <b>readBufferedNext</b> and parses to integer 
   * @return  next token in the input buffer as int 
   * @throws  NonNumberException  if string could not be parsed to integer 
   * @throws  NoSuchElementException  when directed inputs unexpectedly end 
   */
  public int readBufferedNextInt() throws 
    NoSuchElementException, NonNumberException {
    String next = this.readBufferedNext(); 
    try {
      return Integer.parseInt(next);
    } catch (NumberFormatException e) {
      throw new NonNumberException("Could not convert string to integer");
    }
  }

  /**
   * Checks whether there is another item in the buffer 
   * @return  <code>True</code> if there are more items in the buffer array 
   */
  public boolean hasBufferedNext() {
    return (this.inputBuffer.size() > 0);
  }

  /**
   * @return  the size of the buffer as int 
   */
  public int bufferedSize() {
    return this.inputBuffer.size();
  }

  /**
   * Clear the buffer 
   */
  public void clearBuffer() {
    this.inputBuffer.clear();
  }

  /**
   * Close the input 
   */
  public void close() {
    this.stdin.close();
  }
}
