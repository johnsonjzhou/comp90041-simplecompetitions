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

  /** Entries are based on multiples of this amount */
  private static final int MIN_ENTRY_AMOUNT = 50;

  /** competition name */
  private String name; 

  /** competition identifier */
  private int id; 

  /** the list of entries in the competition */
  private ArrayList<Entry> entries;

  /** the list of winning entries */
  private ArrayList<Entry> winningEntries;

  /** whether the competition is currently active */
  private boolean active;

  /** whether operations are in test mode */
  private boolean testMode = false;

  /**
   * Constructor for creating a new Competition 
   * @param  id  the competition id
   * @param  name  the competition name
   */
  public Competition(int id, String name) {
    this.id = id;
    this.name = name;
    this.entries = new ArrayList<Entry>();
    this.winningEntries = new ArrayList<Entry>();
    this.active = true;
  }

  /** private */

  /**
   * Checks the winning entries for a particular memberId
   * @param  memberId  the member id to check
   * @return  the first winning entry found 
   */
  private Entry hasWinningEntry(String memberId) {
    for (Entry entry : this.winningEntries) {
      if (entry.getMemberId().equals(memberId)) {
        return entry;
      }
    }
    return null;
  }

  /** public */

  /**
   * Gets the competition ID 
   * @return  the competition id 
   */
  public int getId() {
    return this.id;
  }

  /**
   * Checks whether the competition is currently active 
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
   * Sets the testMode state 
   * @param  testMode  set testMode flag to specified 
   */
  public void setTestMode(boolean testMode) {
    this.testMode = testMode;
  }

  /**
   * Gets the tetMode state 
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
   * Gets the list of entries in the competition 
   * @return  the entries list 
   */
  public ArrayList<Entry> getEntries() {
    return this.entries;
  }

  /**
   * Gets how many entries currently in the entries list 
   * @return  number of entries currently in the entries list 
   */
  public int entrySize() {
    return this.entries.size();
  }

  /**
   * Adds an entry to the winning entries list. 
   * If an entry belongs to a member already with a winning entry, the
   * winning amount will be compared and existing entry replaced if 
   * the new entry has a higher winning amount.  
   * @param  entry  the entry to add 
   */
  public void addWinningEntry(Entry entry) {
    // check winning entries list for existing winning entries for same member 
    String memberId = entry.getMemberId();
    Entry existingWinningEntry = this.hasWinningEntry(memberId);

    if (existingWinningEntry != null) {
      // as per competition policy 
      // if existing prize is greater or equal, don't add another winning entry
      if (existingWinningEntry.getPrize() >= entry.getPrize()) {
        return;
      }

      // if existing prize is less, remove it so it can be replaced by new 
      this.winningEntries.remove(existingWinningEntry);
    }

    this.winningEntries.add(entry);
  }

  /**
   * Gets the list of winning entries 
   * @return  the list of winning entries 
   */
  public ArrayList<Entry> getWinningEntries() {
    return this.winningEntries;
  }

  /**
   * Prints a summary of the competition
   */
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
   * Prints a statement to confirm competition has been created
   */
  public void announceCreated() {
    System.out.println(OutputPrompts.COMPETITION_CREATED);
  }

  /**
   * Prints the competition id, name and type 
   */
  public void announceName() {
    System.out.println(
      String.format(OutputFormat.COMPETITION_CREATED, 
        this.id, this.name, this.getClass().getSimpleName()
      )
    );
  }

  /**
   * Allows user to input bill number and validate against data. 
   * This method is *blocking* as the user must provide a valid bill Id.
   * @param  console  the user console for input
   * @param  data  a member of the data provider class 
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

      // 1) checking for valid bill id
      String billId;
      try {
        billId = console.readNextLinePattern(Bill.ID_FORMAT);
      } catch (UnsupportedInputException e) {
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
   * Calculates how many entries is eligible based on a bill amount 
   * @param  billAmount  the amount from the bill 
   * @return  number of entries the bill is eligible for based on MIN_ENTRY_AMOUNT 
   */
  public int calculateEligibleEntries(double billAmount) {
    return Math.floorDiv((int) billAmount, MIN_ENTRY_AMOUNT);
  }

  /** abstract */

  /**
   * Adds an entry to the competition
   * @param  console  user console for input  
   * @param  data  data provider for bill and member information 
   */
  public abstract void addEntries(UserConsole console, DataProvider data);

  /**
   * Draws winners
   * @param  data  data provider for bill and member information 
   */
  public abstract void drawWinners(DataProvider data);
}
