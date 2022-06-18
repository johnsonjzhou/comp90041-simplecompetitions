/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */
import java.util.Collections;

public class LuckyNumbersCompetition extends Competition {

  private final int[] prizeAllocation = { 0, 0, 50, 100, 500, 1000, 5000, 50000 };

  /**
   * @param  id  the competition id
   * @param  name  the competition name
   * @param  testMode  whether in test mode 
   */
  public LuckyNumbersCompetition(int id, String name) {
    super(id, name);
    this.announceCreated();
    this.announceName(this.getClass().getSimpleName());
  }

  private void announceWinners(DataProvider data) {
    System.out.println(OutputPrompts.WINNING_ENTRIES);
    for (Entry entry : this.getWinningEntries()) {
      String memberId = entry.getMemberId();
      String memberName = data.getMemberName(memberId);
      String numbersSummary = "";

      if (entry instanceof NumbersEntry) {
        numbersSummary = ((NumbersEntry) entry).numbersSummary();
      }

      if (entry instanceof AutoNumbersEntry) {
        numbersSummary = ((AutoNumbersEntry) entry).numbersSummary();
      }

      System.out.println(String.format(OutputFormat.WINNING_ENTRY_LUCKYNUMBERS, 
        memberId, memberName, entry.getPrize()
      ));

      System.out.println(String.format(OutputFormat.WINNING_ENTRY_DETAILS, 
        entry.getId(), numbersSummary
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

    // ask the user how many manual entries are to be entered

    System.out.println(" " + OutputPrompts.MANUAL_ENTRIES);

    int manualEntries;
    manualEntryLoop : while(true) {
      try {
        manualEntries = console.readBufferedNextInt();
        console.clearBuffer();

        if (manualEntries > entryQuantity) {
          System.out.println(String.format(OutputErrors.ENTRY_LIMIT_EXCEEDED, 
            0, entryQuantity
          ));
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
      int entryId = this.entrySize() + 1;
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

      this.addEntry(manualEntry);
      bill.markUsed();
    }

    // generate remaining entries automatically 

    for (int i = manualEntries + 1; i <= entryQuantity; i++) {
      int entryId = this.entrySize() + 1;
      AutoNumbersEntry autoEntry = new AutoNumbersEntry(entryId, billId, memberId);
      try {
        // testing mode: seed is the total number of entries
        if (this.isTestMode()) {
          autoEntry.create(this.entrySize());
        } else {
          autoEntry.create();
        }
        this.addEntry(autoEntry);
      } catch (Exception e) {
        // should not reach here in normal circumstances
        System.out.println(OutputErrors.ENTRY_GENERATE_ERROR);
      }
      bill.markUsed();
    }

    // print confirmation for all entries added 
    System.out.println(OutputPrompts.ENTRIES_ADDED);
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
   * Draws winners and sets the competition to complete
   * @param  data  data provider 
   */
  public void drawWinners(DataProvider data) {
    // if there are no entries 
    if (this.entrySize() < 1) {
      System.out.println(OutputErrors.COMPETITION_NO_ENTRIES);
      return;
    }
    
    // announce competition name and type 
    this.announceName(this.getClass().getSimpleName());

    // auto generate a winning entry 
    AutoNumbersEntry winningEntry = new AutoNumbersEntry(0, "0", "0");
    try {
      if (this.isTestMode()) {
        // in test mode, use the competition ID as the seed 
        winningEntry.create(this.getId());
      } else {
        winningEntry.create();
      }
    } catch (InvalidEntryException e) {
      // should not reach here 
      System.out.println(OutputErrors.COMPETITION_DRAW_ERROR);
      return;
    }

    // announce the winning entry 
    System.out.println("Lucky " + winningEntry.numbersSummary());
    
    // compare all entries to generated winningEntry 
    checkEntries : for (Entry entry : this.getEntries()) {

      // skip if entry type is not valid 
      if (!(entry instanceof NumbersEntry)) {
        continue checkEntries;
      } 

      int matchingNumbers = ((NumbersEntry) entry).match(winningEntry);

      // skip if matchingNumbers don't conform to competition policy
      if (matchingNumbers < 2 || matchingNumbers > 7) {
        continue checkEntries;
      }

      entry.setPrize(this.prizeAllocation[matchingNumbers]);

      this.addWinningEntry(entry);
    }

    // mark competition as complete 
    this.setComplete();

    // if no winners are found, hopefully not!
    if (this.getWinningEntries().size() < 1) {
      System.out.println(OutputErrors.COMPETITION_NO_WINNERS);
      return;
    }

    // sort winners 
    Collections.sort(this.getWinningEntries());

    // list winning entries 
    this.announceWinners(data);
  }
}
