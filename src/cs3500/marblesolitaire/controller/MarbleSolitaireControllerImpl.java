package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents an implementation of {@code MarbleSolitaireController}. Handles user inputs and system
 * outputs to interact with the Marble Solitaire Model.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructs a Marble Solitaire Controller from {@code Readable} and {@code Appendable} objects.
   *
   * @param rd represents the readable being passed in (NOT NULL).
   * @param ap represents the appendable going out of the controller (NOT NULL).
   * @throws IllegalArgumentException if the given {@param in} or {@param out} is null.
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = ap;
    scan = new Scanner(rd);
  }

  @Override
  public void playGame(MarbleSolitaireModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The Input Model may not be null.");
    }

    while (!model.isGameOver()) {
      gameStateAndScore(model);
      int fromRow;
      int fromColumn;
      int toRow;
      int toColumn;

      fromRow = getInput();
      if (fromRow == -1) {
        gameQuit(model);
        return;
      }
      fromColumn = getInput();
      if (fromColumn == -1) {
        gameQuit(model);
        return;
      }
      toRow = getInput();
      if (toRow == -1) {
        gameQuit(model);
        return;
      }
      toColumn = getInput();
      if (toColumn == -1) {
        gameQuit(model);
        return;
      }

      tryMove(model, fromRow, fromColumn, toRow, toColumn);
    }
    output("Game over!\n");
    gameStateAndScore(model);
  }

  /**
   * Appends the appropriate output to scan when the game is quit.
   *
   * @param m represents the model that is being played
   * @throws IllegalStateException if the output fails
   */
  private void gameQuit(MarbleSolitaireModel m) {
    output("Game quit!\n");
    output("State of game when quit:\n");
    gameStateAndScore(m);
  }

  /**
   * Tries to move a piece in the given model with the given 0-based coordinates. If the move is
   * valid, executes it. If it is invalid and the model throws an error, the method catches the
   * error and outputs an informative message.
   *
   * @param model the model where the piece is to be moved
   * @param fromR the row of the piece to be moved (0-based)
   * @param fromC the column of the piece to be moved (0-based)
   * @param toR   the row that the piece should be moved to (0-based)
   * @param toC   the column that the piece should be moved to (0-based)
   */
  private void tryMove(MarbleSolitaireModel model, int fromR, int fromC, int toR, int toC) {
    try {
      model.move(fromR, fromC, toR, toC);
    } catch (IllegalArgumentException e) {
      output("Invalid move. Play again. " + e.getMessage() + "\n");
    }
  }

  /**
   * Gets the next valid input from the scanner. A valid input is a:
   * <ul>
   *   <li>Positive Integer, returned as itself</li>
   *   <li>The letter "q" representing the user wishes to quit, this returns -1.</li>
   * </ul>
   * <p>If the input is invalid, it re-prompts the user for correct input.
   * If there are no more inputs, the game is stale and an {@code IllegalStateException} is thrown.
   * </p>
   *
   * @return a positive integer representing the next valid input
   * @throws IllegalStateException if there are no more inputs
   */
  private int getInput() {
    while (scan.hasNext()) {
      if (scan.hasNextInt()) { // there is an integer to read
        int toReturn = scan.nextInt() - 1;
        if (toReturn < 0) { // screen for negative inputs
          output("Your input must be a positive integer (or \"q\" to quit).\n");
        } else {
          return toReturn;
        }
      } else if (scan.next().equalsIgnoreCase("q")) { // user wishes to quit
        return -1;
      } else { // input was invalid, re-prompt for correct input
        output("Your input must be a positive integer (or \"q\" to quit).\n");
      }
    }
    // scan has no next so there is no more input and the game is stale.
    throw new IllegalStateException("No inputs detected. Game state is stale.");
  }

  /**
   * Outputs the game state and current score to Appendable.
   *
   * @param model represents the Marble Solitaire Model to be printed.
   */
  private void gameStateAndScore(MarbleSolitaireModel model) {
    output(model.getGameState() + "\n");
    output(String.format("Score: %d\n", model.getScore()));
  }

  /**
   * Outputs the given String by appending it to Appendable. Catches IOException when output fails
   * and throws an {@code IllegalArgumentException} instead.
   *
   * @param outputStr represents the string to output
   * @throws IllegalStateException if the append fails and throws an {@code IOException}
   */
  private void output(String outputStr) {
    try {
      out.append(outputStr);
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }
}
