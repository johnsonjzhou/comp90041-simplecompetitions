/*
 * Student name: Johnson Zhou
 * Student ID: 1302442
 * LMS username: zhoujj
 */
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The program state that can be saved and loaded by implementing the 
 * Serializable interface 
 */
public class State implements Serializable {
  
  /** a list of Competitions */
  private ArrayList<Competition> competitions;

  /** whether running mode is in test mode, default is False */
  private boolean testMode = false;

  /**
   * General constructor for creating a new state 
   */
  public State() {
    this.competitions = new ArrayList<Competition>();
    this.testMode = false;
  }

  /**
   * Create a state from an exsiting state object 
   * @param  state  a state object to consume 
   */
  public State(State state) {
    this.competitions = state.competitions;
    this.testMode = state.testMode;
  }

  /** setters */

  /**
   * @param  testMode  sets the testMode state 
   */
  public void setTestMode(boolean testMode) {
    this.testMode = testMode;
  }

  /**
   * @param  competition  adds a Competition to the state 
   */
  public void addCompetition(Competition competition) {
    competition.setTestMode(this.testMode);
    this.competitions.add(competition);
  }

  /** getters */

  /**
   * @return  all the competitions currently held in state 
   */
  public ArrayList<Competition> getCompetitions() {
    return this.competitions;
  }

  /**
   * @return  the testMode state 
   */
  public boolean getTestMode() {
    return this.testMode;
  }
}
