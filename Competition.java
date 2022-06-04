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

  /**
   * @param  id  the competition id
   * @param  name  the competition name
   */
  public Competition(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public abstract void addEntries();

  public abstract void drawWinners();

  public void report() {
  }
}
