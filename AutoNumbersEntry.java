/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Use for auto entry generation in <b>LuckyNumbers</b> competition. 
 */
public class AutoNumbersEntry extends NumbersEntry {

  public AutoNumbersEntry(int entryId, String billId, String memberId) {
    super(entryId, billId, memberId);
  }
  
  /**
   * @param  seed  In the testing mode, you should use the competition 
   *                identifier as seed for generating the lucky entry and 
   *                the number of entries in the currently active competition 
   *                to generate automated customers' entries.
   */
  public void createNumbers (int seed) throws InvalidEntryException {
    ArrayList<Integer> validList = new ArrayList<Integer>();
    // int[] tempNumbers = new int[this.NUMBER_COUNT];
    for (int i = 1; i <= this.MAX_NUMBER; i++) {
      validList.add(i);
    }
    Collections.shuffle(validList, new Random(seed));
    for (int i = 0; i < this.NUMBER_COUNT; i++) {
      // tempNumbers[i] = validList.get(i);
      this.stage(validList.get(i).intValue());
    }
    // Arrays.sort(tempNumbers);
    // return tempNumbers;
    this.create();

    // seed = 1, Numbers:  3  4 17 18 24 29 30 [Auto]
    // seed = 2, Numbers:  2  7 14 18 22 25 35 [Auto]
    // seed = 3, Numbers:  1  5  6  8 31 32 35 [Auto]
    // seed = 4, Numbers:  2  5 11 12 23 24 34 [Auto]
    // seed = 5, Numbers:  1  2 13 24 25 31 34 [Auto]
    // seed = 6, Numbers:  2  3 15 18 26 27 31 [Auto]
    // seed = 7, Numbers:  4  6 14 16 17 18 20 [Auto]
  }

  /**
   * @return  summary of entry id and numbers generated if valid 
   */
  @Override
  public String summary() {
    String summary = super.summary(); 
    
    if (this.isValid()) {
      summary += " [Auto]";
    }
    
    return summary;
  }
}
