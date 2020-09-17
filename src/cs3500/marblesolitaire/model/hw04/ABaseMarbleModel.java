package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import java.util.StringJoiner;

/**
 * Base Abstract class of the interface {@link MarbleSolitaireModel}. Implements shared
 * functionality of all Marble Solitaire Models including a way to store the game board, the methods
 * to move, check if the game is over, get score, etc,
 */
abstract class ABaseMarbleModel implements MarbleSolitaireModel {

  protected GamePiece[][] gameBoard;

  /**
   * Representing an {@code enum} to create the game board with. {@code Marble} represents a space
   * with a marble in it, {@code Empty} represents an empty (but valid) space, and {@code Dummy}
   * represents an invalid space.
   */
  protected enum GamePiece {
    Marble(1), Empty(0), Dummy(-1);
    private final int value;

    GamePiece(int value) {
      this.value = value;
    }

    @Override
    public String toString() {
      switch (value) {
        case 1:
          return "O";
        case 0:
          return "_";
        case -1:
          return "";
        default:
          throw new IllegalArgumentException(
              "Some weird error occurred. Check how Game Piece was initialized");
      }
    }
  }

  /**
   * Sets the empty game piece in the array and returns the initialized game board. This method
   * should only be called from the constructor after initializing the board with {@code
   * initializeGameBoard()} as it assumes a board with {@code GamePiece.Marble} in valid positions.
   *
   * @param gb     represents the initialized game board with marbles in all valid positions.
   * @param row    represents the row to put the empty piece
   * @param column represents the column to put the empty piece
   * @return the 2-D array of game pieces representing the game board with marbles in all positions
   *         except the given one.
   * @throws IllegalArgumentException if the given row and column are invalid (out of bounds or
   *                                  containing a dummy piece).
   */
  protected GamePiece[][] setEmpty(GamePiece[][] gb, int row, int column) {
    String message = String.format("Invalid empty cell position (%d,%d)", row, column);
    checkRowAndColumnInBounds(row, column, message);

    if (gb[row][column] == GamePiece.Dummy) {
      throw new IllegalArgumentException(message);
    } else {
      gb[row][column] = GamePiece.Empty;
    }
    return gb;
  }

  /**
   * Creates a game-board of the desired shape with marbles in all valid positions.
   *
   * @return a 2-D game board correctly implementing the desired shape with marbles in all valid
   *         positions.
   */
  protected abstract GamePiece[][] initializeGameBoard();

  /**
   * Ensures that the given row and column are in bounds (positive and not outside the array index)
   * for this game board. Implementations of different boards must override this and throw an {@code
   * IllegalArgumentException} if the position is out of bounds of the game board array.
   *
   * @param row     represents the row to ensure is in bounds
   * @param column  represents the column to ensure is in bounds
   * @param message represents the message to return if an exception is thrown.
   * @throws IllegalArgumentException if the given row or column is either negative or too large for
   *                                  the game board array.
   */
  protected abstract void checkRowAndColumnInBounds(int row, int column, String message);


  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    int[] jumpedPosn = checkMove(fromRow, fromCol, toRow, toCol);

    this.gameBoard[fromRow][fromCol] = GamePiece.Empty;
    this.gameBoard[jumpedPosn[0]][jumpedPosn[1]] = GamePiece.Empty;
    this.gameBoard[toRow][toCol] = GamePiece.Marble;
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.gameBoard.length; i++) {
      for (int j = 0; j < this.gameBoard.length; j++) {

        if (isValidMoveFromPosn(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Implementations of this abstract class must override this. It should return true if there is a
   * valid move in some direction from this position.
   *
   * @param r represents the row starting at
   * @param c represents the column starting at
   * @return a boolean, true if there is at least one valid move from this position
   */
  protected boolean isValidMoveFromPosn(int r, int c) {
    try {
      checkMove(r, c, r + 2, c); // try right two
      return true;
    } catch (IllegalArgumentException ignored) {
    }
    try {
      checkMove(r, c, r - 2, c); // try left two
      return true;
    } catch (IllegalArgumentException ignored) {
    }
    try {
      checkMove(r, c, r, c + 2); // try down two
      return true;
    } catch (IllegalArgumentException ignored) {
    }
    try {
      checkMove(r, c, r, c - 2); // try up 2
      return true;
    } catch (IllegalArgumentException ignored) {
    }
    return false;
  }

  /**
   * Ensures all of the conditions for making a move from the given position to the other position
   * are valid and returns the indices of the piece jumped by the valid move.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @return a 1-D array representing the index of the piece that was jumped
   * @throws IllegalArgumentException if the given fromRow and fromCol represent an invalid position
   *                                  (negative or invalid for a game board of this size)
   * @throws IllegalArgumentException if the given toRow and toCol represent an invalid position
   *                                  (negative or invalid for a game board of this size)
   * @throws IllegalArgumentException if the move is not from a space occupied by a marble, jumping
   *                                  over another marble, to an empty space that is 2 away either
   *                                  horizontally or vertically or diagonally if the specific
   *                                  implementation allows that
   */
  protected int[] checkMove(int fromRow, int fromCol, int toRow, int toCol) {
    checkRowAndColumnInBounds(fromRow, fromCol,
        String.format("The zero-based position (%d, %d) is invalid!", fromRow, fromCol));
    checkRowAndColumnInBounds(toRow, toCol,
        String.format("The zero-based position (%d, %d) is invalid!", toRow, toCol));

    if (gameBoard[fromRow][fromCol] == GamePiece.Dummy) {
      throw new IllegalArgumentException(
          String.format("The zero-based position (%d, %d) is invalid!", fromRow, fromCol));
    }
    if (gameBoard[toRow][toCol] == GamePiece.Dummy) {
      throw new IllegalArgumentException(
          String.format("The zero-based position (%d, %d) is invalid!", toRow, toCol));
    }

    int[] jumped;

    int rowDifference = Math.abs(fromRow - toRow);
    int colDifference = Math.abs(fromCol - toCol);
    if (rowDifference == 2 && colDifference == 0) {
      jumped = new int[]{(fromRow + toRow) / 2, fromCol};
    } else if (rowDifference == 0 && colDifference == 2) {
      jumped = new int[]{fromRow, (fromCol + toCol) / 2};
    } else if (rowDifference == 2 && colDifference == 2) {
      jumped = allowDiagonal(fromRow, fromCol, toRow, toCol);
    } else {
      throw new IllegalArgumentException(
          "May only move 2 spaces away!");
    }

    if (this.gameBoard[fromRow][fromCol] != GamePiece.Marble
        || this.gameBoard[toRow][toCol] != GamePiece.Empty
        || this.gameBoard[jumped[0]][jumped[1]] != GamePiece.Marble) {
      throw new IllegalArgumentException(
          "May only move a marble to an empty space, by jumping a marble.");
    }
    return jumped;
  }

  /**
   * Delegates to concrete implementations on whether or not to allow diagonal moves. Returns the
   * position of the jumped piece if a diagonal move is permitted.
   *
   * @param fromRow represents the row of the piece to move (0-based)
   * @param fromCol represents the column of the piece to move (0-based)
   * @param toRow   represents the row moving to (0-based)
   * @param toCol   represents the column moving to (0-based)
   * @return the position of the piece that was jumped as a 1-D Array
   * @throws IllegalArgumentException if the diagonal move is not permitted
   */
  protected abstract int[] allowDiagonal(int fromRow, int fromCol, int toRow, int toCol);

  @Override
  public int getScore() {
    int marbles = 0;

    for (GamePiece[] row : this.gameBoard) {
      for (GamePiece gp : row) {
        if (gp == GamePiece.Marble) {
          marbles++;
        }
      }
    }
    return marbles;
  }

  /**
   * Returns a formatted String represents a row in the game board. Each slot on the game board is a
   * single character (O, _ or space for a marble, empty and invalid position respectively). Slots
   * should be separated by a space. Each row has no space before the first slot and after the last
   * slot.
   *
   * @param arr represents the row array to turn into a string
   * @return a formatted string to represent the row
   */
  protected static String getRow(GamePiece[] arr) {
    StringJoiner joiner = new StringJoiner(" ");
    for (GamePiece gp : arr) {
      if (gp == GamePiece.Marble || gp == GamePiece.Empty) {
        String piece = gp.toString();
        joiner.add(piece);
      }
    }
    return joiner.toString();
  }
}
