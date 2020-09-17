import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the get game state method on the marble solitaire triangle model.
 */
public class TriangleModelTestGetGameState {

  public TriangleModelTestGetGameState() {
    // public constructor for testing class
  }

  MarbleSolitaireModel normalGame;
  MarbleSolitaireModel differentEmpty;
  MarbleSolitaireModel differentBaseSize;
  MarbleSolitaireModel differentEmptyAndSize;

  @Before
  public void init() {
    normalGame = new TriangleSolitaireModelImpl();
    differentEmpty = new TriangleSolitaireModelImpl(3, 1);
    differentBaseSize = new TriangleSolitaireModelImpl(12);
    differentEmptyAndSize = new TriangleSolitaireModelImpl(8, 6, 1);
  }

  @Test
  public void testDifferentSizedBoards() {
    assertEquals(
        "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", normalGame.getGameState());
    assertEquals(
        "    O\n"
            + "   O O\n"
            + "  O O O\n"
            + " O _ O O\n"
            + "O O O O O", differentEmpty.getGameState());
    assertEquals(
        "           _\n"
            + "          O O\n"
            + "         O O O\n"
            + "        O O O O\n"
            + "       O O O O O\n"
            + "      O O O O O O\n"
            + "     O O O O O O O\n"
            + "    O O O O O O O O\n"
            + "   O O O O O O O O O\n"
            + "  O O O O O O O O O O\n"
            + " O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O", differentBaseSize.getGameState());
    assertEquals(
        "       O\n"
            + "      O O\n"
            + "     O O O\n"
            + "    O O O O\n"
            + "   O O O O O\n"
            + "  O O O O O O\n"
            + " O _ O O O O O\n"
            + "O O O O O O O O", differentEmptyAndSize.getGameState());
  }

  @Test
  public void testDrawMiddleOfGame() {
    normalGame.move(2, 0, 0, 0);
    normalGame.move(2, 2, 2, 0);
    normalGame.move(0, 0, 2, 2);
    normalGame.move(4, 1, 2, 1);
    normalGame.move(3, 3, 3, 1);
    assertEquals(
        "    _\n"
            + "   _ _\n"
            + "  O O O\n"
            + " O O _ _\n"
            + "O _ O O O", normalGame.getGameState());
  }

  @Test
  public void testDrawEndOfGame() {
    testDrawMiddleOfGame();
    normalGame.move(3, 0, 1, 0);
    normalGame.move(1, 0, 3, 2);
    normalGame.move(3, 2, 3, 0);
    normalGame.move(4, 0, 2, 0);
    normalGame.move(4, 3, 4, 1);
    assertEquals(
        "    _\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " _ _ _ _\n"
            + "_ O _ _ O", normalGame.getGameState()); // no moves left
  }
}
