/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/

public class RandomPickCompetition extends Competition {
  private final int FIRST_PRIZE = 50000;
  private final int SECOND_PRIZE = 5000;
  private final int THIRD_PRIZE = 1000;
  private final int[] prizes = {FIRST_PRIZE, SECOND_PRIZE, THIRD_PRIZE};

  private final int MAX_WINNING_ENTRIES = 3;

  /**
   * @param  id  the competition id
   * @param  name  the competition name
   * @param  testMode  whether in test mode 
   */
  public RandomPickCompetition(int id, String name, boolean testMode) {
    super(id, name, testMode);
    this.announceCreated(this.getClass().getSimpleName());
  }

  /** Competition */

  /**
   * Add entries to this competition 
   * @param  console  UserConsole to input bill id
   * @param  dasta  DataProvider to provide bill and member information 
   */
  public void addEntries(UserConsole console, DataProvider data) {
    Bill bill = this.selectBill(console, data);
    String billId = bill.getId();
    String memberId = bill.getMemberId();
    double billAmount = bill.getAmount();
    int entryQuantity = this.calculateEligibleEntries(billAmount);
    
    // return to main menu if bill is not eligible for any entries 
    if (entryQuantity < 1) { 
      // follow through line break from validateBill
      System.out.println();
      return; 
    }

    // generate entries
    for (int i = 1; i <= entryQuantity; i++) {
      int entryId = this.entrySize() + 1;
      Entry newEntry = new Entry(entryId, billId, memberId);
      this.addEntry(newEntry);
      bill.markUsed();
    }

    // print confirmation for all entries added 
    System.out.println(OutputPrompts.ENTRIES_GENERATED);
    for (Entry entry : this.getEntries()) {
      if (entry.getBillId().equals(billId)) {
        System.out.println(entry.summary());
      }
    }

    // whether to continue adding more entries? 
    System.out.println(OutputPrompts.ADD_MORE_ENTRIES);
    console.clearBuffer();
    String addMore = console.readBufferedNext();
    console.clearBuffer();
    switch (addMore) {
      case "y":
        this.addEntries(console, data);
        return;

      case "n":
      default:
        return;
    }
  }

  // todo 
  public void drawWinners() {
    // Random randomGenerator = null;
    // if (this.getIsTestingMode()) {
    //     randomGenerator = new Random(this.getId());
    // } else {
    //     randomGenerator = new Random();
    // }

    // int winningEntryCount = 0;
    // while (winningEntryCount < MAX_WINNING_ENTRIES) {
    //   int winningEntryIndex = randomGenerator.nextInt(entries.size());

    //   Entry winningEntry = entries.get(winningEntryIndex);
  
    //   /*
    //   * Ensure that once an entry has been selected,
    //   * it will not be selected again.
    //   */
    //   if (winningEntry.getPrize() == 0) {
    //     int currentPrize = prizes[winningEntryCount];
    //     winningEntry.setPrize(currentPrize);
    //     winningEntryCount++;
    //   }
    // }

    /*
    * Note that the above piece of code does not ensure that
    * one customer gets at most one winning entry. Add your code
    * to complete the logic.
    */
  }
}
