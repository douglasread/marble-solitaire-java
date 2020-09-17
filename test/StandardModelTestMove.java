import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the move method.
 */
public class StandardModelTestMove {

  public StandardModelTestMove() {
    // public constructor for testing class
  }

  MarbleSolitaireModel normalGame;
  MarbleSolitaireModel differentEmptyAndThickness;


  @Before
  public void initializeExamples() {
    normalGame = new MarbleSolitaireModelImpl();
    differentEmptyAndThickness = new MarbleSolitaireModelImpl(5, 12, 5);
  }

  @Test
  public void testBasicMoves() {
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O",
        normalGame.getGameState()); // check starting conditions
    normalGame.move(3, 1, 3, 3); // jump left to right
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O",
        normalGame.getGameState());
    normalGame.move(1, 2, 3, 2); // jump up to down
    assertEquals(
        "    O O O\n"
            + "    _ O O\n"
            + "O O _ O O O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O",
        normalGame.getGameState());
    normalGame.move(4, 2, 2, 2); // jump down to up
    assertEquals(
        "    O O O\n"
            + "    _ O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O _ O O O O\n"
            + "    O O O\n"
            + "    O O O",
        normalGame.getGameState());
    normalGame.move(1, 4, 1, 2); // jump right to left
    assertEquals(
        "    O O O\n"
            + "    O _ _\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O _ O O O O\n"
            + "    O O O\n"
            + "    O O O",
        normalGame.getGameState());
  }

  @Test
  public void testBasicMovesLarger() {
    assertEquals(
        "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O _ O O O",
        differentEmptyAndThickness.getGameState()); // starting conditions
    differentEmptyAndThickness.move(10, 5, 12, 5);
    assertEquals(
        "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O _ O O O\n"
            + "        O _ O O O\n"
            + "        O O O O O",
        differentEmptyAndThickness.getGameState());
  }

  @Test
  public void endGame() {
    normalGame.move(3, 5, 3, 3);
    normalGame.move(5, 4, 3, 4);
    normalGame.move(4, 6, 4, 4);
    normalGame.move(4, 3, 4, 5);
    normalGame.move(4, 1, 4, 3);
    normalGame.move(6, 2, 4, 2);
    normalGame.move(3, 2, 5, 2);
    normalGame.move(6, 4, 6, 2);
    normalGame.move(6, 2, 4, 2);
    normalGame.move(2, 4, 4, 4);
    normalGame.move(0, 4, 2, 4);
    normalGame.move(1, 2, 1, 4);
    normalGame.move(3, 3, 1, 3);
    normalGame.move(0, 2, 0, 4);
    normalGame.move(2, 5, 2, 3);
    normalGame.move(0, 4, 2, 4);
    normalGame.move(1, 3, 3, 3);
    normalGame.move(3, 0, 3, 2);
    normalGame.move(4, 3, 4, 1);
    normalGame.move(4, 0, 4, 2);
    normalGame.move(3, 2, 5, 2);
    normalGame.move(5, 2, 5, 4);
    normalGame.move(4, 5, 4, 3);
    normalGame.move(3, 3, 5, 3);
    normalGame.move(5, 4, 5, 2);
    normalGame.move(2, 6, 4, 6);
    normalGame.move(2, 1, 2, 3);
    assertEquals(
        "    _ _ _\n"
            + "    _ _ _\n"
            + "O _ _ O O _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    O _ _\n"
            + "    _ _ _",
        normalGame.getGameState()); // one before game over
    normalGame.move(2, 4, 2, 2);
    assertEquals(
        "    _ _ _\n"
            + "    _ _ _\n"
            + "O _ O _ _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    O _ _\n"
            + "    _ _ _",
        normalGame.getGameState()); // no moves left
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveGameOver() {
    this.endGame();
    normalGame.move(5, 2, 5, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMarbleInSpaceMovingTo() {
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O",
        normalGame.getGameState()); // check there is a tile in space (4, 3)
    normalGame.move(6, 3, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDiagonalMoveNotAllowed() {
    setUpDiagonalTest();
    normalGame.move(2, 5, 4, 3);
  }

  @Test
  public void setUpDiagonalTest() {
    normalGame.move(5, 3, 3, 3);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O",
        normalGame.getGameState());
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
    intermediateGame();
    normalGame.move(4, 4, 4, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoPieceToMove() {
    intermediateGame();
    normalGame.move(5, 2, 3, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void jumpMoreThanTwo() {
    normalGame.move(6, 3, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testJumpOne() {
    normalGame.move(4, 3, 3, 3);
  }

  @Test
  public void intermediateGame() {
    normalGame.move(3, 5, 3, 3);
    normalGame.move(5, 4, 3, 4);
    normalGame.move(4, 6, 4, 4);
    normalGame.move(4, 3, 4, 5);
    normalGame.move(4, 1, 4, 3);
    normalGame.move(6, 2, 4, 2);
    normalGame.move(3, 2, 5, 2);
    normalGame.move(6, 4, 6, 2);
    normalGame.move(6, 2, 4, 2);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O _ O O _ O\n"
            + "O _ O O _ O _\n"
            + "    _ O _\n"
            + "    _ _ _",
        normalGame.getGameState());
  }
}
