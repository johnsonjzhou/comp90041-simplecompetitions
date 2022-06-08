/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */
import java.util.ArrayList;
import java.util.Arrays;

public class NumbersEntry extends Entry {

  private int[] numbers; 
  private ArrayList<Integer> stagedNumbers; 
  public final int NUMBER_COUNT = 7;
  public final int MAX_NUMBER = 35;

  public NumbersEntry (int entryId, String billId, String memberId) {
    super(entryId, billId, memberId);
    this.stagedNumbers = new ArrayList<Integer>();
  }

  /** 
   * Stages a new number intended to be added to the entry 
   * @param  number  the new number to add 
   * @throws  InvalidEntryException  if number is not within range of 1 to 35 
   * @throws  InvalidEntryException  if number already exist in the entry 
   */
  public void stage(int number) throws InvalidEntryException {
    // validate range [1..35]
    if (number < 1 || number > 35) {
      throw new InvalidEntryException(OutputErrors.ENTRY_NOT_IN_RANGE);
    }

    Integer newNumber = Integer.valueOf(number);

    // validate no duplicates 
    for (Integer num : this.stagedNumbers) {
      if (num.compareTo(newNumber) == 0) {
        throw new InvalidEntryException(OutputErrors.ENTRY_NO_DUPLICATES);
      }
    }

    this.stagedNumbers.add(newNumber);
  }

  /**
   * Validates the entry by checking the quantity of numbers that have been added
   * @throws  InvalidEntryException  if numbers are less than NUMBER_COUNT
   * @throws  InvalidEntryException  if numbers are more than NUMBER_COUNT
   */
  public void create() throws InvalidEntryException {
    if (this.stagedNumbers.size() < this.NUMBER_COUNT) {
      throw new InvalidEntryException(
        String.format(OutputErrors.ENTRY_LESSTHAN, this.NUMBER_COUNT)
      ); 
    }

    if (this.stagedNumbers.size() > this.NUMBER_COUNT) {
      throw new InvalidEntryException(
        String.format(OutputErrors.ENTRY_GREATERTHAN, this.NUMBER_COUNT)
      ); 
    }

    this.numbers = new int[this.NUMBER_COUNT];
    for (int i = 0; i < this.NUMBER_COUNT; i++) {
      this.numbers[i] = this.stagedNumbers.get(i);
    }

    Arrays.sort(this.numbers);

    this.setValid();
  }

  /**
   * @return  summary of entry id and numbers if numbers have been created 
   */
  @Override
  public String summary() {
    String summary = super.summary();

    if (this.isValid()) {
      String numbers = String.format(OutputFormat.ENTRY_NUMBERS, 
        this.numbers[0], 
        this.numbers[1], 
        this.numbers[2], 
        this.numbers[3], 
        this.numbers[4], 
        this.numbers[5], 
        this.numbers[6]
      );
      summary += " " + numbers;
    }
    
    return summary;
  }
}
