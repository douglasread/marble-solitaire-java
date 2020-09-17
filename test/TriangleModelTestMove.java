import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Represents tests for the move method on the triangle model.
 */
public class TriangleModelTestMove {

  public TriangleModelTestMove() {
    // public constructor for testing class
  }

  MarbleSolitaireModel normalGame;
  MarbleSolitaireModel differentEmptyAndSize;


  @Before
  public void initializeExamples() {
    normalGame = new TriangleSolitaireModelImpl();
    differentEmptyAndSize = new TriangleSolitaireModelImpl(10, 3, 1);
  }

  @Test
  public void testBasicMoves() {
    assertEquals(
        "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O",
        normalGame.getGameState()); // check starting conditions
    normalGame.move(2, 0, 0, 0); // jump up 2
    assertEquals(
        "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O",
        normalGame.getGameState());
    normalGame.move(2, 2, 2, 0); // jump left 2
    assertEquals(
        "    O\n"
            + "   _ O\n"
            + "  O _ _\n"
            + " O O O O\n"
            + "O O O O O",
        normalGame.getGameState());
    normalGame.move(0, 0, 2, 2); // jump diagonal down right
    assertEquals(
        "    _\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " O O O O\n"
            + "O O O O O",
        normalGame.getGameState());
    normalGame.move(4, 3, 2, 1); // jump diagonal up left
    assertEquals(
        "    _\n"
            + "   _ _\n"
            + "  O O O\n"
            + " O O _ O\n"
            + "O O O _ O",
        normalGame.getGameState());
    normalGame.move(4, 1, 4, 3); // jump right
    assertEquals(
        "    _\n"
            + "   _ _\n"
            + "  O O O\n"
            + " O O _ O\n"
            + "O _ _ O O",
        normalGame.getGameState());
    normalGame.move(2, 1, 4, 1); // jump down
    assertEquals(
        "    _\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " O _ _ O\n"
            + "O O _ O O",
        normalGame.getGameState());
  }

  @Test
  public void testBasicMovesLarger() {
    assertEquals(
        "         O\n"
            + "        O O\n"
            + "       O O O\n"
            + "      O _ O O\n"
            + "     O O O O O\n"
            + "    O O O O O O\n"
            + "   O O O O O O O\n"
            + "  O O O O O O O O\n"
            + " O O O O O O O O O\n"
            + "O O O O O O O O O O",
        differentEmptyAndSize.getGameState()); // starting conditions
    differentEmptyAndSize.move(3, 3, 3, 1); // move left
    assertEquals(
        "         O\n"
            + "        O O\n"
            + "       O O O\n"
            + "      O O _ _\n"
            + "     O O O O O\n"
            + "    O O O O O O\n"
            + "   O O O O O O O\n"
            + "  O O O O O O O O\n"
            + " O O O O O O O O O\n"
            + "O O O O O O O O O O",
        differentEmptyAndSize.getGameState());
  }

  @Test
  public void endGame() {
    normalGame.move(2, 0, 0, 0);
    normalGame.move(2, 2, 2, 0);
    normalGame.move(0, 0, 2, 2);
    normalGame.move(4, 1, 2, 1);
    normalGame.move(3, 3, 3, 1);
    normalGame.move(3, 0, 1, 0);
    normalGame.move(1, 0, 3, 2);
    normalGame.move(3, 2, 3, 0);
    normalGame.move(4, 0, 2, 0);
    assertEquals(
        "    _\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " _ _ _ _\n"
            + "_ _ O O O",
        normalGame.getGameState()); // one before game over
    normalGame.move(4, 3, 4, 1);
    assertEquals(
        "    _\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " _ _ _ _\n"
            + "_ O _ _ O", normalGame.getGameState()); // no moves left
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveGameOver() {
    this.endGame();
    normalGame.move(4, 4, 4, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMarbleInSpaceMovingTo() {
    assertEquals(
        "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O",
        normalGame.getGameState()); // check there is a marble in space (4, 2)
    normalGame.move(2, 2, 4, 2);
  }

  @Test
  public void testDiagonalUpRightNotAllowed() {
    setUpDiagonalTest();
    try {
      differentEmptyAndSize.move(5, 1, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot move 2 up and 2 to the right.", e.getMessage());
    }
  }

  @Test
  public void testDiagonalDownLeftNotAllowed() {
    setUpDiagonalTest();
    differentEmptyAndSize.move(5, 2, 3, 2);
    differentEmptyAndSize.move(7, 2, 5, 2);
    assertEquals(
        "         O\n"
            + "        O O\n"
            + "       O O O\n"
            + "      O O O _\n"
            + "     O O _ O O\n"
            + "    O O O O O O\n"
            + "   O O _ O O O O\n"
            + "  O O _ O O O O O\n"
            + " O O O O O O O O O\n"
            + "O O O O O O O O O O",
        differentEmptyAndSize.getGameState());
    try {
      differentEmptyAndSize.move(5, 4, 7, 2);
      throw new IllegalStateException("should not have gotten here.");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot move 2 down and 2 to the left.", e.getMessage());
    }
  }

  @Test
  public void setUpDiagonalTest() {
    differentEmptyAndSize.move(3, 3, 3, 1);
    assertEquals(
        "         O\n"
            + "        O O\n"
            + "       O O O\n"
            + "      O O _ _\n"
            + "     O O O O O\n"
            + "    O O O O O O\n"
            + "   O O O O O O O\n"
            + "  O O O O O O O O\n"
            + " O O O O O O O O O\n"
            + "O O O O O O O O O O",
        differentEmptyAndSize.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalInputs() {
    normalGame.move(-1, -2, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalInputTop() {
    normalGame.move(0, 0, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoPieceToJump() {
    normalGame.move(2, 2, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoPieceToMove() {
    normalGame.move(0, 0, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void jumpMoreThanTwo() {
    normalGame.move(3, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testJumpOne() {
    normalGame.move(1, 0, 0, 0);
  }
}
