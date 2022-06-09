/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */
import java.util.ArrayList;

public class LuckyNumbersCompetition extends Competition {

  private ArrayList<Entry> entries;

  /**
   * @param  id  the competition id
   * @param  name  the competition name
   */
  public LuckyNumbersCompetition(int id, String name) {
    super(id, name);
    this.entries = new ArrayList<Entry>();
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

    // ask the user how many manual entries are to be entered

    System.out.println(" " + OutputPrompts.MANUAL_ENTRIES);

    int manualEntries;
    manualEntryLoop : while(true) {
      try {
        manualEntries = console.readBufferedNextInt();
        console.clearBuffer();

        if (manualEntries > entryQuantity) {
          System.out.println(OutputErrors.ENTRY_LIMIT_EXCEEDED);
          continue manualEntryLoop;
        }

        break manualEntryLoop;
      } catch (NonNumberException e) {
        System.out.println(OutputErrors.NUMBER_EXPECTED);
        continue manualEntryLoop;
      }
    }

    // ask the user to input the specified number of manual entries 

    for (int i = 1; i <= manualEntries; i++) {
      int entryId = this.entries.size() + 1;
      NumbersEntry manualEntry = new NumbersEntry(entryId, billId, memberId);

      createEntry : while(true) {
        System.out.println(OutputPrompts.LUCKYNUMBER_ENTRY);
        console.clearBuffer();
        manualEntry.clearStaged();

        boolean adding = true;
        while(adding) {
          try {
            int number = console.readBufferedNextInt();
            manualEntry.stage(number);
          } catch (NonNumberException e) {
            System.out.println(OutputErrors.NUMBER_EXPECTED);
            continue createEntry;
          } catch (InvalidEntryException e) {
            System.out.println(e.getMessage());
            continue createEntry;
          }

          adding = console.hasBufferedNext();
        }

        try {
          manualEntry.create();
        } catch (InvalidEntryException e) {
          System.out.println(e.getMessage());
          continue createEntry;
        }
        
        if (manualEntry.isValid()) {
          break createEntry;
        }
        
        // should not reach here 
        System.out.println(OutputErrors.ENTRY_CREATE_ERROR);
      }

      this.entries.add(manualEntry);
    }

    // generate remaining entries automatically 

    for (int i = manualEntries + 1; i <= entryQuantity; i++) {
      int entryId = this.entries.size() + 1;
      AutoNumbersEntry autoEntry = new AutoNumbersEntry(entryId, billId, memberId);
      try {
        // testing mode: seed is the total number of entries
        if (this.isTestMode()) {
          autoEntry.create(this.entries.size());
        } else {
          autoEntry.create();
        }
        this.entries.add(autoEntry);
      } catch (Exception e) {
        // should not reach here in normal circumstances
        System.out.println(OutputErrors.ENTRY_GENERATE_ERROR);
      }
    }

    // print confirmation for all entries added 
    System.out.println(OutputPrompts.ENTRIES_ADDED);
    for (Entry entry : this.entries) {
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

  //todo
  public void drawWinners() {

  }
}
