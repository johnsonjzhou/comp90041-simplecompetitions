/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Competition {
    private String name; //competition name
    private int id; //competition identifier

    public abstract void addEntries();

    public abstract void drawWinners();

    public void report() {
    }
}
