package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.ASquareMarbleSolitaire;

/**
 * Representing the implementation of the Marble Solitaire Game Model. Implements the functionality
 * described in {@link MarbleSolitaireModel} by extending the functionality of {@link
 * ASquareMarbleSolitaire}. Represents the english version of the game.
 */
public final class MarbleSolitaireModelImpl extends ASquareMarbleSolitaire {

  /**
   * Constructs a standard Marble Solitaire Game with a default thickness of 3 and an empty space at
   * the center (3, 3).
   */
  public MarbleSolitaireModelImpl() {
    super(3);
    GamePiece[][] tempGameBoard = initializeGameBoard();
    this.gameBoard = setEmpty(tempGameBoard, 3, 3);
  }

  /**
   * Constructs a standard Marble Solitaire Game with a default thickness of 3 and the empty space
   * at the specified row and column.
   *
   * @param sRow    represents the row of the empty space
   * @param sColumn represents the column of the empty space
   * @throws IllegalArgumentException if the row or column is invalid
   */
  public MarbleSolitaireModelImpl(int sRow, int sColumn) {
    super(3);
    GamePiece[][] tempGameBoard = initializeGameBoard();
    this.gameBoard = setEmpty(tempGameBoard, sRow, sColumn);
  }

  /**
   * Constructs a standard Marble Solitaire Game with thee thickness with the empty space in the
   * center.
   *
   * @param thickness represents the thickness of the board (must be positive and odd)
   * @throws IllegalArgumentException if the given thickness is invalid (negative or even)
   */
  public MarbleSolitaireModelImpl(int thickness) {
    super(thickness);
    GamePiece[][] tempGameBoard = initializeGameBoard();
    int center = getCenter();
    this.gameBoard = setEmpty(tempGameBoard, center, center);
  }

  /**
   * Constructs a standard Marble Solitaire Game of the given thickness with the empty space at the
   * given location.
   *
   * @param thickness represents the thickness of the board (must be positive and odd)
   * @param sRow      represents the row of the empty space
   * @param sColumn   represents the column of the empty space
   * @throws IllegalArgumentException if the given thickness is negative or even.
   * @throws IllegalArgumentException if the row or column is invalid
   */
  public MarbleSolitaireModelImpl(int thickness, int sRow, int sColumn) {
    super(thickness);
    GamePiece[][] tempGameBoard = initializeGameBoard();
    this.gameBoard = setEmpty(tempGameBoard, sRow, sColumn);
  }

  /**
   * Override to correctly mutate the rows in the plus shape of the English Solitaire Model.
   *
   * @param arr                represents the array to mutate by filling with dummy game pieces and
   *                           marbles
   * @param distanceFromCenter represents the distance of this row from the center of the game
   */
  @Override
  protected void createOutsideRow(GamePiece[] arr, int distanceFromCenter) {
    int j = 0;
    for (; j < size - 1; j++) {
      arr[j] = GamePiece.Dummy;
    }
    for (; j < 2 * size - 1; j++) {
      arr[j] = GamePiece.Marble;
    }
    for (; j < (size * 3 - 2); j++) {
      arr[j] = GamePiece.Dummy;
    }
  }
}
