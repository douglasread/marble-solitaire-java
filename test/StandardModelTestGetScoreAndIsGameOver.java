import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the the getScore() and isGameOver() method.
 */
public class StandardModelTestGetScoreAndIsGameOver {

  public StandardModelTestGetScoreAndIsGameOver() {
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
  public void testGetScore() {
    assertEquals(32, normalGame.getScore());
    assertEquals(104, differentEmptyAndThickness.getScore());
    normalGame.move(3, 1, 3, 3); // decrease by 1
    differentEmptyAndThickness.move(10, 5, 12, 5); // decrease by 1
    assertEquals(31, normalGame.getScore());
    assertEquals(103, differentEmptyAndThickness.getScore());
  }

  @Test
  public void testGetScoreGameEnd() {
    normalGame.move(3, 5, 3, 3);
    assertFalse(normalGame.isGameOver());
    assertEquals(31, normalGame.getScore());
    normalGame.move(5, 4, 3, 4);
    assertFalse(normalGame.isGameOver());
    assertEquals(30, normalGame.getScore());
    normalGame.move(4, 6, 4, 4);
    assertFalse(normalGame.isGameOver());
    assertEquals(29, normalGame.getScore());
    normalGame.move(4, 3, 4, 5);
    assertFalse(normalGame.isGameOver());
    assertEquals(28, normalGame.getScore());
    normalGame.move(4, 1, 4, 3);
    assertFalse(normalGame.isGameOver());
    assertEquals(27, normalGame.getScore());
    normalGame.move(6, 2, 4, 2);
    assertFalse(normalGame.isGameOver());
    assertEquals(26, normalGame.getScore());
    normalGame.move(3, 2, 5, 2);
    assertFalse(normalGame.isGameOver());
    assertEquals(25, normalGame.getScore());
    normalGame.move(6, 4, 6, 2);
    assertFalse(normalGame.isGameOver());
    assertEquals(24, normalGame.getScore());
    normalGame.move(6, 2, 4, 2);
    assertFalse(normalGame.isGameOver());
    assertEquals(23, normalGame.getScore());
    normalGame.move(2, 4, 4, 4);
    assertFalse(normalGame.isGameOver());
    assertEquals(22, normalGame.getScore());
    normalGame.move(0, 4, 2, 4);
    assertFalse(normalGame.isGameOver());
    assertEquals(21, normalGame.getScore());
    normalGame.move(1, 2, 1, 4);
    assertFalse(normalGame.isGameOver());
    assertEquals(20, normalGame.getScore());
    normalGame.move(3, 3, 1, 3);
    assertFalse(normalGame.isGameOver());
    assertEquals(19, normalGame.getScore());
    normalGame.move(0, 2, 0, 4);
    assertFalse(normalGame.isGameOver());
    assertEquals(18, normalGame.getScore());
    normalGame.move(2, 5, 2, 3);
    assertFalse(normalGame.isGameOver());
    assertEquals(17, normalGame.getScore());
    normalGame.move(0, 4, 2, 4);
    assertFalse(normalGame.isGameOver());
    assertEquals(16, normalGame.getScore());
    normalGame.move(1, 3, 3, 3);
    assertFalse(normalGame.isGameOver());
    assertEquals(15, normalGame.getScore());
    normalGame.move(3, 0, 3, 2);
    assertFalse(normalGame.isGameOver());
    assertEquals(14, normalGame.getScore());
    normalGame.move(4, 3, 4, 1);
    assertFalse(normalGame.isGameOver());
    assertEquals(13, normalGame.getScore());
    normalGame.move(4, 0, 4, 2);
    assertFalse(normalGame.isGameOver());
    assertEquals(12, normalGame.getScore());
    normalGame.move(3, 2, 5, 2);
    assertFalse(normalGame.isGameOver());
    assertEquals(11, normalGame.getScore());
    normalGame.move(5, 2, 5, 4);
    assertFalse(normalGame.isGameOver());
    assertEquals(10, normalGame.getScore());
    normalGame.move(4, 5, 4, 3);
    assertFalse(normalGame.isGameOver());
    assertEquals(9, normalGame.getScore());
    normalGame.move(3, 3, 5, 3);
    assertFalse(normalGame.isGameOver());
    assertEquals(8, normalGame.getScore());
    normalGame.move(5, 4, 5, 2);
    assertFalse(normalGame.isGameOver());
    assertEquals(7, normalGame.getScore());
    normalGame.move(2, 6, 4, 6);
    assertFalse(normalGame.isGameOver());
    assertEquals(6, normalGame.getScore());
    normalGame.move(2, 1, 2, 3);
    assertFalse(normalGame.isGameOver());
    assertEquals(5, normalGame.getScore());
    normalGame.move(2, 4, 2, 2);
    assertEquals(
        "    _ _ _\n"
            + "    _ _ _\n"
            + "O _ O _ _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    O _ _\n"
            + "    _ _ _",
        normalGame.getGameState());
    assertEquals(4, normalGame.getScore());
    assertTrue(normalGame.isGameOver());
  }

  @Test
  public void testIsGameOverFilledRow() {
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
    normalGame.move(2, 6, 2, 4);
    normalGame.move(1, 2, 3, 2);
    normalGame.move(1, 4, 3, 4);
    normalGame.move(3, 3, 3, 5);
    normalGame.move(1, 3, 3, 3);
    normalGame.move(3, 6, 3, 4);
    normalGame.move(4, 2, 2, 2);
    normalGame.move(3, 4, 3, 2);
    normalGame.move(3, 1, 3, 3);
    normalGame.move(2, 1, 2, 3);
    normalGame.move(4, 3, 6, 3);
    normalGame.move(4, 5, 4, 3);
    normalGame.move(3, 3, 5, 3);
    normalGame.move(6, 3, 4, 3);
    assertEquals(
        "    O O O\n"
            + "    _ _ _\n"
            + "O _ _ O _ _ _\n"
            + "O _ _ _ _ _ _\n"
            + "O _ _ O _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _",
        normalGame
            .getGameState()); // a row and column are completely filled, but game is still over
    assertTrue(normalGame.isGameOver());
  }
}
