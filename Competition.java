/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Competition {

  private String name; //competition name
  private int id; //competition identifier
  private boolean active;

  /**
   * @param  id  the competition id
   * @param  name  the competition name
   */
  public Competition(int id, String name) {
    this.id = id;
    this.name = name;
    this.active = true;
  }

  public int getId() {
    return this.id;
  }

  public boolean isActive() {
    return this.active;
  }

  public abstract void addEntries();

  public abstract void drawWinners();

  public void report() {
  }

  /**
   * @param  type  invoke using this.getClass().getSimpleName() 
   */
  public String toString(String type) {
    return String.format(OutputFormat.COMPETITION_CREATED, 
      this.id, this.name, type
    );
  }
}
