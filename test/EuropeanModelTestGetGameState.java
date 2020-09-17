import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test the getGameState() method for the European Model.
 */
public class EuropeanModelTestGetGameState {

  public EuropeanModelTestGetGameState() {
    // public constructor for testing class
  }

  MarbleSolitaireModel normalGame;
  MarbleSolitaireModel differentEmpty;
  MarbleSolitaireModel differentSideLength;
  MarbleSolitaireModel differentEmptyAndSize;

  @Before
  public void init() {
    normalGame = new EuropeanSolitaireModelImpl();
    differentEmpty = new EuropeanSolitaireModelImpl(0, 3);
    differentSideLength = new EuropeanSolitaireModelImpl(11);
    differentEmptyAndSize = new EuropeanSolitaireModelImpl(5, 12, 5);
  }

  @Test
  public void testDifferentSizedBoards() {
    assertEquals(
        "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", normalGame.getGameState());
    assertEquals(
        "    O _ O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", differentEmpty.getGameState());
    assertEquals(
        "                    O O O O O O O O O O O\n"
            + "                  O O O O O O O O O O O O O\n"
            + "                O O O O O O O O O O O O O O O\n"
            + "              O O O O O O O O O O O O O O O O O\n"
            + "            O O O O O O O O O O O O O O O O O O O\n"
            + "          O O O O O O O O O O O O O O O O O O O O O\n"
            + "        O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "      O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "    O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O _ O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "    O O O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "      O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "        O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "          O O O O O O O O O O O O O O O O O O O O O\n"
            + "            O O O O O O O O O O O O O O O O O O O\n"
            + "              O O O O O O O O O O O O O O O O O\n"
            + "                O O O O O O O O O O O O O O O\n"
            + "                  O O O O O O O O O O O O O\n"
            + "                    O O O O O O O O O O O", differentSideLength.getGameState());
    assertEquals(
        "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O _ O O O", differentEmptyAndSize.getGameState());
  }

  @Test
  public void testDrawMiddleOfGame() {
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
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O _ O O _ O\n"
            + "O _ O O _ O _\n"
            + "  O _ O _ O\n"
            + "    _ _ _",
        normalGame.getGameState());
  }

  @Test
  public void testDrawEndOfGame() {
    testDrawMiddleOfGame();
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
    normalGame.move(2, 4, 2, 2);
    normalGame.move(5, 1, 5, 3);
    assertEquals(
        "    _ _ _\n"
            + "  O _ _ _ O\n"
            + "O _ O _ _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "  _ _ O _ O\n"
            + "    _ _ _",
        normalGame.getGameState()); // no moves left
  }
}