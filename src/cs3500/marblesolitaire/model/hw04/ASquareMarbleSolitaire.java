package cs3500.marblesolitaire.model.hw04;

import java.util.StringJoiner;

/**
 * Represents an Abstract class for shared functionality of Square-based Marble-Solitaire Models.
 * Models like this include the English model in
 * {@link cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl} and the European model
 * in {@link EuropeanSolitaireModelImpl}.
 */
public abstract class ASquareMarbleSolitaire extends ABaseMarbleModel {

  // represents the size of a side the middle square the game is based from
  protected final int size;

  /**
   * Constructs an {@code AbstractSquareMarbleSolitaire} given the size of the game.
   *
   * @param size represents the size of the board (must be positive and odd).
   * @throws IllegalArgumentException if the given size is negative or even.
   */
  protected ASquareMarbleSolitaire(int size) {
    checkValidSize(size);
    this.size = size;
  }

  /**
   * Checks that the given size is both odd and positive.
   *
   * @param size represents the size of the game to construct
   * @throws IllegalArgumentException if the given size is negative or even
   */
  private static void checkValidSize(int size) {
    if (size < 0 || size % 2 == 0) { // number is even or negative
      throw new IllegalArgumentException("Invalid Size.");
    }
  }

  /**
   * Calculates the center of the game for a 0-based coordinate system.
   *
   * @return the center of the game board (0-based).
   */
  protected int getCenter() {
    return (size - 1) + (size / 2);
  }

  /**
   * Ensures that the row and column are in bounds for the given game (not-negative or larger than
   * the maximum possible index).
   *
   * @param row     represents the row index that
   * @param column  represents the column index
   * @param message represents the message to report in case of an invalid row or column
   * @throws IllegalArgumentException if the given row or column are out-of-bounds for the given
   *                                  board.
   */
  @Override
  protected void checkRowAndColumnInBounds(int row, int column, String message) {
    int maxIndex = size * 3 - 3;

    if (row > maxIndex || column > maxIndex || row < 0 || column < 0) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Initializes a game board with Marbles in all valid spaces and Dummy game pieces in invalid
   * ones.
   *
   * @return an initialized game board with Marbles in al valid spaces.
   */
  @Override
  protected GamePiece[][] initializeGameBoard() {
    int rowSize = size * 3 - 2;
    GamePiece[][] returnArray = new GamePiece[rowSize][rowSize];

    for (int i = 0; i < rowSize; i++) {
      int center = getCenter();
      int distanceFromCenter = (Math.abs(center - i));
      if (distanceFromCenter > (size / 2)) { // outside middle rows
        createOutsideRow(returnArray[i], distanceFromCenter);
      } else {
        for (int j = 0; j < size * 3 - 2; j++) {
          returnArray[i][j] = GamePiece.Marble;
        }
      }
    }
    return returnArray;
  }


  /**
   * Creates the top and bottom rows of the game board (the rows that are not completely filled with
   * valid game pieces).
   *
   * @param arr                represents the array to mutate by filling with dummy game pieces and
   *                           marbles
   * @param distanceFromCenter represents the distance of this row from the center of the game
   *                           board
   */
  protected abstract void createOutsideRow(GamePiece[] arr, int distanceFromCenter);


  /**
   * Throws an {@code IllegalArgumentException} as diagonal moves are not allowed in these square
   * game boards.
   *
   * @param fromRow represents the row of the piece to move (0-based)
   * @param fromCol represents the column of the piece to move (0-based)
   * @param toRow   represents the row moving to (0-based)
   * @param toCol   represents the column moving to (0-based)
   * @throws IllegalArgumentException if the move is not from a space occupied by a marble, jumping
   *                                  over another marble, to an empty space that is 2 away either
   *                                  horizontally or vertically
   */
  @Override
  protected int[] allowDiagonal(int fromRow, int fromCol, int toRow, int toCol) {
    throw new IllegalArgumentException(
        "Diagonal moves are not allowed.");
  }

  @Override
  public String getGameState() {
    int top = size - 1;
    int bottom = size * 2 - 2;
    StringJoiner joiner = new StringJoiner("\n");

    for (int i = 0; i < this.gameBoard.length; i++) {
      StringBuilder blankSpace = new StringBuilder();
      if (i < top || i > bottom) {
        for (int j = 0; j < size - 1; j++) {
          if (this.gameBoard[i][j] == GamePiece.Dummy) {
            blankSpace.append("  ");
          } else {
            break;
          }
        }
      }
      String rowStr = blankSpace.toString() + getRow(this.gameBoard[i]);
      joiner.add(rowStr);
    }
    return joiner.toString();
  }
}
