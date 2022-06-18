/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/
import java.util.Random;
import java.util.Collections;

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
  public RandomPickCompetition(int id, String name) {
    super(id, name);
    this.announceCreated(this.getClass().getSimpleName());
  }

  private void announceWinners(DataProvider data) {
    System.out.println(OutputPrompts.WINNING_ENTRIES);
    for (Entry entry : this.getWinningEntries()) {
      String memberId = entry.getMemberId();
      String memberName = data.getMemberName(memberId);

      System.out.println(String.format(OutputFormat.WINNING_ENTRY_RANDOMPICK, 
        memberId, memberName, entry.getId(), entry.getPrize()
      ));
    }
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
    if (SimpleCompetitions.userSelectYes(console)) {
      this.addEntries(console, data);
    }
  }

  /**
   * Draw winners based on the competition policy
   * @param  data  of the DataProvider class
   */
  public void drawWinners(DataProvider data) {
    // if there are no entries 
    if (this.entrySize() < 1) {
      System.out.println(OutputErrors.COMPETITION_NO_ENTRIES);
      return;
    }
    
    Random randomGenerator = null;
    if (this.isTestMode()) {
        randomGenerator = new Random(this.getId());
    } else {
        randomGenerator = new Random();
    }

    // handle if entry quantitiy is less than winning entry requirement
    if (this.entrySize() < MAX_WINNING_ENTRIES) {
      System.out.println(OutputErrors.COMPETITION_TOO_FEW_ENTRIES);
      return;
    }

    int winningEntryCount = 0;
    selectWinners : while (winningEntryCount < MAX_WINNING_ENTRIES) {
      int winningEntryIndex = randomGenerator.nextInt(this.entrySize());

      Entry winningEntry = this.getEntries().get(winningEntryIndex);
  
      /*
      * Ensure that once an entry has been selected,
      * it will not be selected again.
      */
      if (winningEntry.getPrize() == 0) {
        int currentPrize = prizes[winningEntryCount];
        winningEntry.setPrize(currentPrize);
        winningEntryCount++;

        // if member already has a winning entry, skip adding another 
        if (this.isWinner(winningEntry.getMemberId())) {
          continue selectWinners;
        }

        this.addWinningEntry(winningEntry);
      }
    }

    // sort winners 
    Collections.sort(this.getWinningEntries());

    // list winning entries 
    this.announceWinners(data);

    // mark competition as complete 
    this.setComplete();
  }
}
