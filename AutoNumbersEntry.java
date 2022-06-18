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

  /** seed for entry creation in normal mode */
  private static final int MEANING_OF_LIFE = 42;

  /**
   * @param  entryId  the entry id
   * @param  billId  associated bill ID
   * @param  memberId  associated member ID
   */
  public AutoNumbersEntry(int entryId, String billId, String memberId) {
    super(entryId, billId, memberId);
  }
  
  /**
   * @param  seed  In the testing mode, you should use the competition 
   *                identifier as seed for generating the lucky entry and 
   *                the number of entries in the currently active competition 
   *                to generate automated customers' entries.
   * @throws  InvalidEntryException  if an error occurs when creating the entry
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
   * @throws  InvalidEntryException  if an error occurs when creating the entry 
   */
  @Override
  public void create() throws InvalidEntryException {
    Random random = new Random();
    int seed = random.nextInt(MEANING_OF_LIFE);
    this.create(seed);
  }

  @Override
  public String numbersSummary() {
    return super.numbersSummary() + " [Auto]";
  }
}
