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
