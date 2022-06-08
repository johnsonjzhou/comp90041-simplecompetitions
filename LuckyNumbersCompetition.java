/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

public class LuckyNumbersCompetition extends Competition {

  /**
   * @param  id  the competition id
   * @param  name  the competition name
   */
  public LuckyNumbersCompetition(int id, String name) {
    super(id, name);
    this.announceCreated(this.getClass().getSimpleName());
  }

  /** Competition */

  // todo
  public void addEntries(UserConsole console, DataProvider data) {
    int entries = this.validateBill(console, data);
    
    // return to main menu if bill is not eligible for any entries 
    if (entries < 1) { 
      // follow through line break from validateBill
      System.out.println();
      return; 
    }

    System.out.println(" " + OutputPrompts.MANUAL_ENTRIES);
  }

  //todo
  public void drawWinners() {

  }
}
