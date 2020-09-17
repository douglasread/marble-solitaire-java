package cs3500.marblesolitaire.model.hw04;

/**
 * Represents the implementation of the European-Style
 * {@link cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel} by extending the abstract class
 * for square-based marble solitaire models.
 */
public final class EuropeanSolitaireModelImpl extends ASquareMarbleSolitaire {

  /**
   * Constructs a standard European-Style Marble Solitaire Model with a side length of 3 and the
   * empty space in the middle.
   */
  public EuropeanSolitaireModelImpl() {
    super(3);
    GamePiece[][] tempGameBoard = initializeGameBoard();
    this.gameBoard = setEmpty(tempGameBoard, 3, 3);
  }

  /**
   * Constructs a European-Style Marble Solitaire Model with the given side length and empty space
   * in the middle.
   *
   * @param sideLength represents the side length of the board to construct
   * @throws IllegalArgumentException if the {@code sideLength} is negative or even
   */
  public EuropeanSolitaireModelImpl(int sideLength) {
    super(sideLength);
    int center = getCenter();
    GamePiece[][] tempGameBoard = initializeGameBoard();
    this.gameBoard = setEmpty(tempGameBoard, center, center);
  }

  /**
   * Constructs a European-Style Marble Solitaire Model with side length of 3 and an empty space in
   * the given row and column.
   *
   * @param row    represents the row of the empty space
   * @param column represents the column of the empty space
   * @throws IllegalArgumentException if the row or column is invalid for a game board with side
   *                                  length 3
   */
  public EuropeanSolitaireModelImpl(int row, int column) {
    super(3);
    GamePiece[][] tempGameBoard = initializeGameBoard();
    this.gameBoard = setEmpty(tempGameBoard, row, column);
  }

  /**
   * Constructs a European-Style Marble Solitaire Model with the given side length and an empty
   * space in the given row and column.
   *
   * @param sideLength represents the side length of the board to construct
   * @param row        represents the row of the empty space
   * @param column     represents the column of the empty space
   * @throws IllegalArgumentException if the {@code sideLength} is negative or even
   * @throws IllegalArgumentException if the row or column is invalid for a game board with the
   *                                  given side length
   */
  public EuropeanSolitaireModelImpl(int sideLength, int row, int column) {
    super(sideLength);
    GamePiece[][] tempGameBoard = initializeGameBoard();
    this.gameBoard = setEmpty(tempGameBoard, row, column);
  }

  @Override
  protected void createOutsideRow(GamePiece[] arr, int distanceFromCenter) {
    int additionalMarbles = (this.getCenter() - distanceFromCenter) * 2;
    int numberOfMarbles = size + additionalMarbles;
    int maxRowSize = size * 3 - 2;
    int dummySpaces = maxRowSize - numberOfMarbles;

    int i = 0;
    for (; i < dummySpaces / 2; i++) {
      arr[i] = GamePiece.Dummy;
    }
    for (; i < (dummySpaces / 2) + numberOfMarbles; i++) {
      arr[i] = GamePiece.Marble;
    }
    for (; i < maxRowSize; i++) {
      arr[i] = GamePiece.Dummy;
    }
  }
}
