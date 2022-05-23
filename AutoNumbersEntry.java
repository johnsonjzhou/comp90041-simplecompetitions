/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Use for auto entry generation in <b>LuckyNumbers</b> competition. 
 */
public class AutoNumbersEntry extends NumbersEntry {
    private final int NUMBER_COUNT = 7;
    private final int MAX_NUMBER = 35;
  
    /**
     * @param  seed  In the testing mode, you should use the competition 
     *                identifier as seed for generating the lucky entry and 
     *                the number of entries in the currently active competition 
     *                to generate automated customers' entries.
     */
    public int[] createNumbers (int seed) {
        ArrayList<Integer> validList = new ArrayList<Integer>();
        int[] tempNumbers = new int[NUMBER_COUNT];
        for (int i = 1; i <= MAX_NUMBER; i++) {
          validList.add(i);
        }
        Collections.shuffle(validList, new Random(seed));
        for (int i = 0; i < NUMBER_COUNT; i++) {
          tempNumbers[i] = validList.get(i);
        }
        Arrays.sort(tempNumbers);
        return tempNumbers;
    }
}
