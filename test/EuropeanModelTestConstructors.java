import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import org.junit.Test;

/**
 * Class to test constructors in @Tag EuropeanStandardModelImpl and the exceptions they throw.
 */
public class EuropeanModelTestConstructors {

  public EuropeanModelTestConstructors() {
    // public constructor for testing class
  }

  MarbleSolitaireModel normalGame = new EuropeanSolitaireModelImpl();
  MarbleSolitaireModel differentEmpty = new EuropeanSolitaireModelImpl(0, 3);
  MarbleSolitaireModel differentThickness = new EuropeanSolitaireModelImpl(5);
  MarbleSolitaireModel differentEmptyAndThickness = new EuropeanSolitaireModelImpl(5, 12, 5);

  @Test
  public void drawExpectedConstruction() {
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
        "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", differentThickness.getGameState());
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
            + "        O _ O O O", differentEmptyAndThickness.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInputRowTooLarge() {
    new EuropeanSolitaireModelImpl(7, 2); // max row input should be 6
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInputRowTooLarge1() {
    new EuropeanSolitaireModelImpl(5, 13, 2); // 12 is max (13 given!!)
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInputColumnTooLarge() {
    new EuropeanSolitaireModelImpl(2, 7); // max is 6
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInputColumnTooLarge2() {
    new EuropeanSolitaireModelImpl(5, 2, 13); // max is 12
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColumn() {
    new EuropeanSolitaireModelImpl(5, 5, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPosnTopLeftCorner() {
    new EuropeanSolitaireModelImpl(5, 1, 0); // no tile exists at this position
  }

  // testing message from previous test
  @Test
  public void testMessage() {
    try {
      new EuropeanSolitaireModelImpl(5, 1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,0)", e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPosnTopLeft() {
    new EuropeanSolitaireModelImpl(0, 0); // in top left of plus shape where there are no slots
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPosnBottomLeft() {
    new EuropeanSolitaireModelImpl(5, 10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPosnBottomLeft2() {
    new EuropeanSolitaireModelImpl(0, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPosnTopRight() {
    new EuropeanSolitaireModelImpl(5, 0, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPosnTopRight2() {
    new EuropeanSolitaireModelImpl(7, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPosnBottomRight() {
    new EuropeanSolitaireModelImpl(5, 9, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPosnBottomRight2() {
    new EuropeanSolitaireModelImpl(7, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEvenThickness() {
    new EuropeanSolitaireModelImpl(8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeThickness() {
    new EuropeanSolitaireModelImpl(-1, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBounds() {
    new EuropeanSolitaireModelImpl(0, 10);
  }
}