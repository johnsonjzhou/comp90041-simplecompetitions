/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */
import java.io.IOException;

/**
 * Thrown when file based input output operations has an error 
 */
public class FileIOException extends IOException {

  /** the general error message  */
  public static final String GEN_MESSAGE = "An error occurred while loading the file.";

  /**
   * General constructor consuming the GEN_MESSAGE 
   */
  public FileIOException() {
    super(FileIOException.GEN_MESSAGE);
  }

  /**
   * Customisable constructor to provide detailed reason for error being thrown
   * @param  message  reason for error thrown
   */
  public FileIOException(String message) {
    super(FileIOException.GEN_MESSAGE, new Throwable(message));
  }
}
