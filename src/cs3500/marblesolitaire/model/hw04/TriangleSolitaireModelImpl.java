package cs3500.marblesolitaire.model.hw04;

import java.util.StringJoiner;

/**
 * Represents the implementation of the Triangle Marble Solitaire model.
 */
public final class TriangleSolitaireModelImpl extends ABaseMarbleModel {

  private final int baseSize;

  /**
   * Constructs a Triangle Marble Solitaire Model with default base size of 5 and the empty space at
   * (0,0).
   */
  public TriangleSolitaireModelImpl() {
    this.baseSize = 5;
    GamePiece[][] tempBoard = initializeGameBoard();
    this.gameBoard = setEmpty(tempBoard, 0, 0);
  }

  /**
   * Constructs a Triangle Marble Solitaire Model with the given base size and the empty space at
   * (0,0).
   *
   * @param size represents the base size of the model (must be positive)
   * @throws IllegalArgumentException if the given base size is negative.
   */
  public TriangleSolitaireModelImpl(int size) {
    this.baseSize = ensureSizePositive(size);
    GamePiece[][] tempBoard = initializeGameBoard();
    this.gameBoard = setEmpty(tempBoard, 0, 0);
  }

  /**
   * Constructs a Triangle Marble Solitaire Model with base size of 5 and the empty space at the
   * given row and column.
   *
   * @param row represents the row to place the empty piece
   * @param col represents the column to place the empty piece
   * @throws IllegalArgumentException if the row or column is invalid (negative or invalid for a
   *                                  board of this size)
   */
  public TriangleSolitaireModelImpl(int row, int col) {
    this.baseSize = 5;
    GamePiece[][] tempBoard = initializeGameBoard();
    this.gameBoard = setEmpty(tempBoard, row, col);
  }

  /**
   * Constructs a Triangle Marble Solitaire Model with the given base size and the empty space at
   * the given row and column.
   *
   * @param size represents the base size of the triangle board
   * @param row  represents the row to place the empty piece
   * @param col  represents the column to place the empty piece
   * @throws IllegalArgumentException if the given base size is negative.
   * @throws IllegalArgumentException if the row or column is invalid (negative or invalid for a
   *                                  board of this size)
   */
  public TriangleSolitaireModelImpl(int size, int row, int col) {
    this.baseSize = ensureSizePositive(size);
    GamePiece[][] tempBoard = initializeGameBoard();
    this.gameBoard = setEmpty(tempBoard, row, col);
  }

  /**
   * Ensures that the given size is positive and returns it if it is.
   *
   * @param size represents the size to test if it is positive
   * @return the valid size
   * @throws IllegalArgumentException if the given {@code size} isn't positive
   */
  private static int ensureSizePositive(int size) {
    if (size > 0) {
      return size;
    } else {
      throw new IllegalArgumentException("Given size for triangle must be positive.");
    }
  }

  /**
   * Creates the game board for a triangle game with marbles in all valid positions.
   *
   * @return the 2-D array representing a game board
   */
  @Override
  protected GamePiece[][] initializeGameBoard() {
    GamePiece[][] returnArray = new GamePiece[baseSize][baseSize];

    for (int i = 0; i < baseSize; i++) {
      int numberOfMarbles = i + 1;
      for (int j = 0; j < baseSize; j++) {
        if (numberOfMarbles > 0) {
          returnArray[i][j] = GamePiece.Marble;
          numberOfMarbles--;
        } else {
          returnArray[i][j] = GamePiece.Dummy;
        }
      }
    }
    return returnArray;
  }

  @Override
  protected void checkRowAndColumnInBounds(int row, int column, String message) {
    if (row < 0 || column < 0 || row > baseSize - 1 || column > baseSize - 1) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Overridden to account for diagonal moves in this implementation.
   *
   * @param r represents the row starting at
   * @param c represents the column starting at
   * @return true if there is a valid move from this position
   */
  @Override
  protected boolean isValidMoveFromPosn(int r, int c) {
    if (super.isValidMoveFromPosn(r, c)) {
      return true;
    }
    try {
      checkMove(r, c, r + 2, c + 2); // right 2 down 2
      return true;
    } catch (IllegalArgumentException ignored) {
    }
    try {
      checkMove(r, c, r - 2, c - 2); // up 2 left 2
      return true;
    } catch (IllegalArgumentException ignored) {
    }
    return false;
  }

  /**
   * Returns the position of the jumped piece (as a 1-D array) if the diagonal move was valid
   * (either up to the left, or down to the right). This implementation assumes the move was both 2
   * horizontally and vertically already and did not involve invalid positions.
   *
   * @param fromRow represents the row of the piece to move (0-based)
   * @param fromCol represents the column of the piece to move (0-based)
   * @param toRow   represents the row moving to (0-based)
   * @param toCol   represents the column moving to (0-based)
   * @return the position of the jumped piece
   * @throws IllegalArgumentException if the attempted diagonal move was up to right or down to the
   *                                  left
   */
  @Override
  protected int[] allowDiagonal(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow - toRow > 0) { // moving up
      if (toCol - fromCol > 0) { // moving right
        throw new IllegalArgumentException("Cannot move 2 up and 2 to the right.");
      }
    } else { // moving down
      if (fromCol - toCol > 0) { // moving left
        throw new IllegalArgumentException("Cannot move 2 down and 2 to the left.");
      }
    }
    return new int[]{(fromRow + toRow) / 2, (fromCol + toCol) / 2};
  }

  @Override
  public String getGameState() {
    StringJoiner joiner = new StringJoiner("\n");

    int currRow = 0;
    for (int i = baseSize; i > 0; i--, currRow++) {
      StringBuilder blankSpace = new StringBuilder();
      int numberOfSpaces = i - 1;
      for (; numberOfSpaces > 0; numberOfSpaces--) {
        blankSpace.append(" ");
      }
      String rowStr = blankSpace.toString() + getRow(this.gameBoard[currRow]);
      joiner.add(rowStr);
    }
    return joiner.toString();
  }
}
