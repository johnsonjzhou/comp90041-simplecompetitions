/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Implements Serializable so it can be saved to file 
 */
public abstract class Competition implements Serializable {

  private static final int MIN_ENTRY_AMOUNT = 50;
  private String name; //competition name
  private int id; //competition identifier
  private ArrayList<Entry> entries;
  private ArrayList<Entry> winningEntries;
  private boolean active;
  private boolean testMode = false;

  /**
   * @param  id  the competition id
   * @param  name  the competition name
   * @param  testMode  whether in test mode 
   */
  public Competition(int id, String name) {
    this.id = id;
    this.name = name;
    this.entries = new ArrayList<Entry>();
    this.winningEntries = new ArrayList<Entry>();
    this.active = true;
  }

  /**
   * @return  the competition id 
   */
  public int getId() {
    return this.id;
  }

  /**
   * @return  whether the competition is active 
   */
  public boolean isActive() {
    return this.active;
  }

  /**
   * Marks the competition as compelte by setting the active flag to false 
   */
  public void setComplete() {
    this.active = false;
  }

  /**
   * @param  testMode  set testMode flag to specified 
   */
  public void setTestMode(boolean testMode) {
    this.testMode = testMode;
  }

  /**
   * @return  the state of testMode flag 
   */
  public boolean isTestMode() {
    return this.testMode;
  }

  /**
   * Adds an entry to the entries list 
   * @param  entry  the entry to add 
   */
  public void addEntry(Entry entry) {
    this.entries.add(entry);
  }

  /**
   * @return  the entries list 
   */
  public ArrayList<Entry> getEntries() {
    return this.entries;
  }

  /**
   * @return  number of entries currently in the entries list 
   */
  public int entrySize() {
    return this.entries.size();
  }

  /**
   * Adds an entry to the winning entries list
   * @param  entry  the entry to add 
   */
  public void addWinningEntry(Entry entry) {
    this.winningEntries.add(entry);
  }

  /**
   * Checks the winning entries for a particular memberId
   * @param  memberId  the member id to check
   * @return  <code>True</code> if member already holds a winning entry
   */
  public boolean isWinner(String memberId) {
    for (Entry entry : this.winningEntries) {
      if (entry.getMemberId().equals(memberId)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return  the list of winning entries 
   */
  public ArrayList<Entry> getWinningEntries() {
    return this.winningEntries;
  }

  public void report() {
    // competition summary 
    System.out.println(String.format(OutputFormat.SUMMARY_COMPETITION, 
      this.id, this.name, this.active ? "yes" : "no"
    ));

    // number of entries 
    System.out.println(String.format(OutputFormat.SUMMARY_ENTRIES, 
      this.entries.size()
    ));

    // only if competition is complete 
    if (this.isActive()) {
      return;
    }

    // number of winning entries 
    System.out.println(String.format(OutputFormat.SUMMARY_WINNING_ENTRIES, 
      this.winningEntries.size()
    ));

    // total prize
    int totalPrize = 0;
    for (Entry winningEntry : this.winningEntries) {
      totalPrize += winningEntry.getPrize();
    }
    System.out.println(String.format(OutputFormat.SUMMARY_PRIZES, totalPrize));
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
   * Allows user to input bill number and validate against data. 
   * This method is *blocking* as the user must provide a valid bill Id.
   * @return  the bill if it passes all of the validation criteria 
   */
  public Bill selectBill(UserConsole console, DataProvider data) {
    // https://edstem.org/au/courses/7656/discussion/892222
    // The order is: 1) checking for valid bill id, 
    // 2) checking if the bill exists, 
    // 3) checking if the bill has been used for a competition, 
    // 4) checking if the bill has membership info, and 
    // 5) check if the bill amount is eligible.
    
    console.clearBuffer();

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
      int entryQuantity = this.calculateEligibleEntries(billAmount);
      System.out.printf(OutputFormat.BILL_ELIGIBLE, billAmount, entryQuantity);
      //! -- no line break here 
      
      return bill;
    }
  }

  /**
   * @param  billAmount  the amount from the bill 
   * @return  number of entries the bill is eligible for based on MIN_ENTRY_AMOUNT 
   */
  public int calculateEligibleEntries(double billAmount) {
    return Math.floorDiv((int) billAmount, MIN_ENTRY_AMOUNT);
  }

  /** abstract */

  public abstract void addEntries(UserConsole console, DataProvider data);

  public abstract void drawWinners(DataProvider data);
}
