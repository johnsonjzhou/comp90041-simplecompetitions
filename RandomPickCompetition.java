/*
* Student name: Johnson Zhou
* Student ID: 1302442
* LMS username: zhoujj
*/
import java.util.Random;
import java.util.Collections;

/**
 * A type of RandomPick competition 
 */
public class RandomPickCompetition extends Competition {
  /** value awarded for the first prize */
  private final int FIRST_PRIZE = 50000;

  /** value awarded for the second prize */
  private final int SECOND_PRIZE = 5000;

  /** value awarded for the third prize */
  private final int THIRD_PRIZE = 1000;

  /** allocation of prize values in sorted order */
  private final int[] prizes = {FIRST_PRIZE, SECOND_PRIZE, THIRD_PRIZE};

  /** maximum amount of winning entries */
  private final int MAX_WINNING_ENTRIES = 3;

  /**
   * Constructor for creating a new RandomPickCompetition 
   * @param  id  the competition id
   * @param  name  the competition name
   */
  public RandomPickCompetition(int id, String name) {
    super(id, name);
    this.announceCreated();
    this.announceName();
  }

  /**
   * Prints the competition winners to string 
   * @param  data  the DataProvider to provide information 
   */
  private void announceWinners(DataProvider data) {
    // announce competition name and type 
    this.announceName();

    // announce the winning entries
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
   * @param  data  DataProvider to provide bill and member information 
   */
  public void addEntries(UserConsole console, DataProvider data) {
    Bill bill = this.selectBill(console, data);
    String billId = bill.getId();
    String memberId = bill.getMemberId();
    double billAmount = bill.getAmount();
    int entryQuantity = this.calculateEligibleEntries(billAmount);

    // follow through line break from validateBill
    System.out.println();
    
    // return to main menu if bill is not eligible for any entries 
    if (entryQuantity < 1) { 
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
    if (SimpleCompetitions.userSelectYes(console, OutputPrompts.ADD_MORE_ENTRIES)) {
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
    while (winningEntryCount < MAX_WINNING_ENTRIES) {
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
