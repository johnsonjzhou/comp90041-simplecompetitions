/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.File;

import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.lang.SecurityException;

/**
 * Handles file based operations  
 */
public class FileIO {

  /** File instance to work on */
  private File file;

  /** Scanner instance for file reading */
  private Scanner reader;

  /** Whether the file can be written to, default is False */
  private boolean readonly = true;

  /**
   * Constructor to create a new FileIO 
   * @param  filepath  path to the file as String 
   * @throws  NullPointerException  passthrough exception from File class 
   */
  public FileIO(String filepath) throws NullPointerException {
    this.file = new File(filepath);
  }

  /**
   * Constructor for creating a new FileIO 
   * @param  filepath  path to the file as String 
   * @param  readonly  <code>True</code> read only (default), 
   *                   <code>False</code> writable 
   * @throws  NullPointerException  passthrough exception from the File class 
   */
  public FileIO(String filepath, Boolean readonly) throws NullPointerException {
    this.file = new File(filepath);
    this.readonly = readonly;
  }

  /** reading ops */

  /**
   * Starts a scanner instance for file reading 
   * @throws  FileIOException  if file could not be opened with FileInputStream 
   */
  private void startReader() throws FileIOException {
    try {
      this.reader = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException | SecurityException e) {
      throw new FileIOException(String.format("Could not open %s for reading", file));
    }
  }

  /**
   * Checks if scanner instance has been started and starts one 
   * @throws  FileIOException  if file could not be opened with FileInputStream 
   */
  private void checkAndLoadReader() throws FileIOException {
    if (!(this.reader instanceof Scanner)) {
      this.startReader();
    }
  }

  /**
   * Checks whether a file exists 
   * @return  <code>True</code> if and only if the file or directory 
   *          denoted by this abstract pathname exists
   */
  public boolean exists() {
    return this.file.exists();
  }

  /**
   * Checks whether there is another token to be read from the file 
   * @return  whether there is another token to read from the file
   * @throws  FileIOException  if file could not be opened with FileInputStream 
   */
  public boolean canReadNext() throws FileIOException {
    this.checkAndLoadReader();
    return this.reader instanceof Scanner ? this.reader.hasNext() : false;
  }

  /**
   * Reads the next token from the file 
   * @return  next token read from the file as String
   * @throws  FileIOException  if file could not be opened with FileInputStream 
   */
  public String readNext() throws FileIOException {
    this.checkAndLoadReader();
    return this.canReadNext() ? this.reader.next() : "";
  }

  /**
   * Whether there is another line to be read from the file 
   * @return  whether this is another line to read from the file 
   * @throws  FileIOException  if file could not be opened with FileInputStream 
   */
  public boolean canReadNextLine() throws FileIOException {
    this.checkAndLoadReader();
    return this.reader.hasNextLine();
  }

  /**
   * Reads the next line from the file 
   * @return  the remainder of the current line as String 
   * @throws  FileIOException  if file could not be opened with FileInputStream 
   */
  public String readNextLine() throws FileIOException {
    this.checkAndLoadReader();
    return this.canReadNextLine() ? this.reader.nextLine() : "";
  }

  /**
   * Gets the entire file contents as an ArrayList of Strings 
   * @return  the entire file contents as an ArrayList of Strings 
   * @throws  FileIOException  if file could not be opened with FileInputStream 
   */
  public ArrayList<String> readContentsAsArray() throws FileIOException {
    this.resetReader();
    ArrayList<String> contents = new ArrayList<String>();
    while (this.canReadNextLine()) {
      contents.add(this.readNextLine());
    }
    return contents;
  }

  /**
   * Closes the Scanner instance for file reading 
   */
  public void closeReader() {
    if (this.reader instanceof Scanner) {
      this.reader.close();
    }
  }

  /**
   * Close and restarts the Scanner instance for file reading 
   * @throws  FileIOException  if file could not be opened with FileInputStream 
   */
  public void resetReader() throws FileIOException {
    this.closeReader();
    this.startReader();
  }

  /** writing ops */

  /**
   * Opens a file for writing
   * @param  append  append to the file rather than overwriting it 
   * @throws  FileIOException  if read only operation is specified 
   * @throws  FileIOException  if file could not be opened with FileOutputStream 
   */
  private PrintWriter openWritableFile(boolean append) throws FileIOException {
    if (this.readonly) {
      throw new FileIOException("File operation in readonly mode");
    }

    try {
      return new PrintWriter(new FileOutputStream(file, append));
    } catch (FileNotFoundException | SecurityException e) {
      throw new FileIOException("Could not open file for writing");
    }
  }

  /**
   * Enables file writing operations by setting readonly to <code>False</code>
   * @return  FileIO to chain methods 
   */
  public FileIO setWritable() {
    this.readonly = false;
    return this;
  }

  /**
   * Writes to the file by replacing its current contents 
   * @param  text  the string text to write to the file 
   * @throws  FileIOException  if file could not be opened with FileOutputStream 
   */
  public void overwrite(String text) throws FileIOException {
    PrintWriter file = this.openWritableFile(false);
    file.println(text);
    file.close();
  }

  /**
   * Appends one line of string text to the file 
   * @param  text  the string text to write to the file 
   * @throws  FileIOException  if file could not be opened with FileOutputStream 
   */
  public void writeLine(String text) throws FileIOException {
    PrintWriter file = this.openWritableFile(true);
    file.println(text);
    file.close();
  }

  /** object file ops */

  /**
   * Writes an object to file 
   * @param  obj  the object to write to file 
   * @throws  FileIOException  if an error occurs while writing the file 
   */
  public void writeObject(Object obj) throws FileIOException {
    try {
      ObjectOutputStream outputStream = 
        new ObjectOutputStream(new FileOutputStream(this.file));
      outputStream.writeObject(obj);
      outputStream.close();
    } catch (Exception e) {
      throw new FileIOException("Could not write object to file.");
    }
  }

  /**
   * Reads an object from file 
   * @return  an object read from file 
   * @throws  FileIOException  if an error occurs while reading the file 
   */
  public Object readObject() throws FileIOException {
    try {
      ObjectInputStream inputStream = 
        new ObjectInputStream(new FileInputStream(this.file));
      Object readObject = inputStream.readObject();
      inputStream.close();
      return readObject;
    } catch (Exception e) {
      throw new FileIOException("Could not read object from file");
    }
  }

  /** other file ops */

  /**
   * Clean up operations 
   */
  public void close() {
    this.closeReader();
  }

  /**
   * Delete the file if required 
   * @return  <code>True</code> if file is successfull deleted 
   * @throws  SecurityException  file could not be accessed due to security settings 
   */
  public boolean delete() throws SecurityException {
    return this.file.delete();
  }
  
}
