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

  // todo 
  public void addEntries(UserConsole console, DataProvider data) {
    int entries = this.validateBill(console, data);
    // follow through line break from validateBill
    System.out.println();
    
    // return to main menu if bill is not eligible for any entries 
    if (entries < 1) { return; }
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
