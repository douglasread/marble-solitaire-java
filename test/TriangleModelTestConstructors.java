import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import org.junit.Test;

/**
 * Tests for the constructors of the Triangle Marble Solitaire Model.
 */
public class TriangleModelTestConstructors {

  public TriangleModelTestConstructors() {
    // public constructor for testing class
  }

  MarbleSolitaireModel normalGame = new TriangleSolitaireModelImpl();
  MarbleSolitaireModel differentEmpty = new TriangleSolitaireModelImpl(3, 2);
  MarbleSolitaireModel differentBaseSize = new TriangleSolitaireModelImpl(6);
  MarbleSolitaireModel weirdGameOnly1 = new TriangleSolitaireModelImpl(1);
  MarbleSolitaireModel differentEmptyAndBaseSize = new TriangleSolitaireModelImpl(8, 7, 2);

  @Test
  public void drawExpectedConstruction() {
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
            + " O O _ O\n"
            + "O O O O O", differentEmpty.getGameState());
    assertEquals(
        "     _\n"
            + "    O O\n"
            + "   O O O\n"
            + "  O O O O\n"
            + " O O O O O\n"
            + "O O O O O O", differentBaseSize.getGameState());
    assertEquals(
        "       O\n"
            + "      O O\n"
            + "     O O O\n"
            + "    O O O O\n"
            + "   O O O O O\n"
            + "  O O O O O O\n"
            + " O O O O O O O\n"
            + "O O _ O O O O O", differentEmptyAndBaseSize.getGameState());
    assertEquals("_", weirdGameOnly1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInputRowTooLarge() {
    new TriangleSolitaireModelImpl(7, 2); // max row input should be 5
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInputRowTooLarge1() {
    new TriangleSolitaireModelImpl(12, 13, 2); // 12 is max (13 given!!)
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInputColumnTooLarge() {
    new TriangleSolitaireModelImpl(2, 7); // max is 5
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInputColumnTooLarge2() {
    new TriangleSolitaireModelImpl(12, 2, 13); // max is 12
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColumn() {
    new TriangleSolitaireModelImpl(5, 4, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPosnTopRightCorner() {
    new TriangleSolitaireModelImpl(8, 1, 3); // no tile exists at this position
  }

  // testing message from previous test
  @Test
  public void testMessage() {
    try {
      new TriangleSolitaireModelImpl(8, 1, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,3)", e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPosnTopRight() {
    new TriangleSolitaireModelImpl(0, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBounds() {
    new TriangleSolitaireModelImpl(0, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPosnMiddle() {
    new TriangleSolitaireModelImpl(10, 5, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPosnMiddle1() {
    new TriangleSolitaireModelImpl(40, 20, 25);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPosnBottom() {
    new TriangleSolitaireModelImpl(3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeBaseSize() {
    new TriangleSolitaireModelImpl(-1, 3, 3);
  }

}
