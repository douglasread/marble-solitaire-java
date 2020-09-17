import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the getScore() and isGameOver() methods on the Triangle Marble Solitaire Model.
 */
public class TriangleModelTestGetScoreAndIsGameOver {

  public TriangleModelTestGetScoreAndIsGameOver() {
    // public constructor for testing class
  }

  MarbleSolitaireModel normalGame;
  MarbleSolitaireModel differentEmptyAndThickness;

  @Before
  public void initializeExamples() {
    normalGame = new TriangleSolitaireModelImpl();
    differentEmptyAndThickness = new TriangleSolitaireModelImpl(14, 12, 5);
  }

  @Test
  public void testGetScore() {
    assertEquals(14, normalGame.getScore());
    assertEquals(104, differentEmptyAndThickness.getScore());
    normalGame.move(2, 0, 0, 0); // decrease by 1
    differentEmptyAndThickness.move(10, 5, 12, 5); // decrease by 1
    assertEquals(13, normalGame.getScore());
    assertEquals(103, differentEmptyAndThickness.getScore());
  }

  @Test
  public void testGetScoreGameEnd() {
    assertEquals(14, normalGame.getScore());
    assertFalse(normalGame.isGameOver());
    normalGame.move(2, 0, 0, 0);
    assertEquals(13, normalGame.getScore());
    assertFalse(normalGame.isGameOver());
    normalGame.move(2, 2, 2, 0);
    assertEquals(12, normalGame.getScore());
    assertFalse(normalGame.isGameOver());
    normalGame.move(0, 0, 2, 2);
    assertEquals(11, normalGame.getScore());
    assertFalse(normalGame.isGameOver());
    normalGame.move(4, 1, 2, 1);
    assertEquals(10, normalGame.getScore());
    assertFalse(normalGame.isGameOver());
    normalGame.move(3, 3, 3, 1);
    assertEquals(9, normalGame.getScore());
    assertFalse(normalGame.isGameOver());
    normalGame.move(3, 0, 1, 0);
    assertEquals(8, normalGame.getScore());
    assertFalse(normalGame.isGameOver());
    normalGame.move(1, 0, 3, 2);
    assertEquals(7, normalGame.getScore());
    assertFalse(normalGame.isGameOver());
    normalGame.move(3, 2, 3, 0);
    assertEquals(6, normalGame.getScore());
    assertFalse(normalGame.isGameOver());
    normalGame.move(4, 0, 2, 0);
    assertEquals(5, normalGame.getScore());
    assertFalse(normalGame.isGameOver());
    normalGame.move(4, 3, 4, 1);
    assertEquals(4, normalGame.getScore());
    assertEquals(
        "    _\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " _ _ _ _\n"
            + "_ O _ _ O", normalGame.getGameState()); // no moves left, 4 pieces left
    assertTrue(normalGame.isGameOver());
  }
}
