package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Class to test inputs to model by the controller.
 */
public class MockMarbleSolitaire implements MarbleSolitaireModel {

  public final StringBuilder log;

  /**
   * Sets the log to be built to confirm inputs from controller.
   *
   * @param log represents a log of inputs
   */
  public MockMarbleSolitaire(StringBuilder log) {
    this.log = log;
  }

  // appends onto the log for testing
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    log.append(String
        .format("fromRow = %d, fromColumn = %d, toRow = %d, toColumn = %d ",
            fromRow, fromCol, toRow, toCol));
  }

  // these methods are just stub methods, implementation not important
  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String getGameState() {
    return log.toString();
  }

  @Override
  public int getScore() {
    return 0;
  }
}
