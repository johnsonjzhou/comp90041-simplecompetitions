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

  public void report() {
  }

  /**
   * @param  type  invoke using this.getClass().getSimpleName() 
   */
  public void announceCreated(String type) {
    System.out.println(
      String.format(OutputFormat.COMPETITION_CREATED, 
        this.id, this.name, type
      )
    );
  }

  /**
   * Allows user to input bill number and validate against data 
   * @return  number of valid entries permitted, range 0 to n 
   */
  public int validateBill(UserConsole console, DataProvider data) {
    // https://edstem.org/au/courses/7656/discussion/892222
    // The order is: 1) checking for valid bill id, 
    // 2) checking if the bill exists, 
    // 3) checking if the bill has been used for a competition, 
    // 4) checking if the bill has membership info, and 
    // 5) check if the bill amount is eligible.
    
    console.clearBuffer();
    int minEntry = 50;

    // ? Can the user "escape" from this? 
    billLoop : while(true) {
      System.out.println(OutputPrompts.BILL_ID);

      String billId = console.readBufferedNext();

      // 1) checking for valid bill id
      if (!billId.matches("^\\d{6}$")) {
        System.out.println(OutputErrors.BILL_NUMBER);
        continue billLoop;
      }

      // 2) checking if the bill exists
      Bill bill = data.getBill(billId);
      if (bill == null) {
        System.out.println(OutputErrors.BILL_NOT_EXIST);
        continue billLoop;
      }

      // 3) checking if the bill has been used for a competition
      if (bill.isUsed()) {
        System.out.println(OutputErrors.BILL_USED);
        continue billLoop;
      }

      // 4) checking if the bill has membership info 
      String memberId = bill.getMemberId();
      if (memberId.length() < 1 || !data.isValidMember(memberId)) {
        System.out.println(OutputErrors.BILL_NO_MEMBER);
        continue billLoop;
      }

      // 5) check if the bill amount is eligible
      double billAmount = bill.getAmount();
      int entryQuantity = Math.floorDiv((int) billAmount, minEntry);
      System.out.println(
        String.format(OutputFormat.BILL_ELIGIBLE, billAmount, entryQuantity)
      );
      
      return entryQuantity;
    }
  }

  /** abstract */

  public abstract void addEntries();

  public abstract void drawWinners();
}
