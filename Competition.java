/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

public abstract class Competition {

  private static final int MIN_ENTRY_AMOUNT = 50;
  private String name; //competition name
  private int id; //competition identifier
  private boolean active;
  private boolean testMode;

  /**
   * @param  id  the competition id
   * @param  name  the competition name
   * @param  testMode  whether in test mode 
   */
  public Competition(int id, String name, boolean testMode) {
    this.id = id;
    this.name = name;
    this.testMode = testMode;
    this.active = true;
  }

  public int getId() {
    return this.id;
  }

  public boolean isActive() {
    return this.active;
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

  public void report() {
    //todo
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

  public abstract void drawWinners();
}
