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

  private static final int MEANING_OF_LIFE = 42;

  public AutoNumbersEntry(int entryId, String billId, String memberId) {
    super(entryId, billId, memberId);
  }
  
  /**
   * @param  seed  In the testing mode, you should use the competition 
   *                identifier as seed for generating the lucky entry and 
   *                the number of entries in the currently active competition 
   *                to generate automated customers' entries.
   */
  public void create(int seed) throws InvalidEntryException {
    ArrayList<Integer> validList = new ArrayList<Integer>();
    for (int i = 1; i <= this.MAX_NUMBER; i++) {
      validList.add(i);
    }
    Collections.shuffle(validList, new Random(seed));
    for (int i = 0; i < this.NUMBER_COUNT; i++) {
      super.stage(validList.get(i).intValue());
    }

    super.create();
  }

  /**
   * In normal mode, create an entry by randoming selecting a seed based on 
   * the MEANING_OF_LIFE 
   */
  @Override
  public void create() throws InvalidEntryException {
    Random random = new Random();
    int seed = random.nextInt(MEANING_OF_LIFE);
    this.create(seed);
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
