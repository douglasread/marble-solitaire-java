package cs3500.marblesolitaire.controller;

import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MockMarbleSolitaire;
import java.io.StringReader;
import org.junit.Test;

/**
 * Class to test that user inputs are correctly passed to the model.
 */
public class ControllerTestMockModel {

  public ControllerTestMockModel() {
    // public constructor for the test class
  }

  @Test
  public void testInputsSentToModel() {
    StringBuilder modelLog = new StringBuilder();
    MarbleSolitaireModel m = new MockMarbleSolitaire(modelLog);
    // all positive integers, should be consumed by the constructor and passed to the model as their
    // values minus 1. The model should get 4, 8, 6, 6
    StringReader input = new StringReader("5 9 7 7 Q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    assertEquals("fromRow = 4, fromColumn = 8, toRow = 6, toColumn = 6 ", m.getGameState());
  }

  @Test
  public void testOnlyValidSent() {
    StringBuilder modelLog = new StringBuilder();
    MarbleSolitaireModel m = new MockMarbleSolitaire(modelLog);
    // test that the model only receives the 4 positive integers, with their values decreased by 1.
    // It should receive 6, 8, 6, 6
    StringReader input = new StringReader("dh -1  0 7 dasd 9 sjs 7 ! 7 &&93 Q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    assertEquals("fromRow = 6, fromColumn = 8, toRow = 6, toColumn = 6 ", m.getGameState());
  }

  @Test
  public void testTwoMoves() {
    StringBuilder modelLog = new StringBuilder();
    MarbleSolitaireModel m = new MockMarbleSolitaire(modelLog);
    // test that the model only receives both moves correctly when surrounded by invalid inputs.
    // The model should receive, 6, 6, 4, 6, 2, 6, 4, 6
    StringReader input = new StringReader("7 ghr 7 % 5 tjn 7 3 && 7 5 7 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    assertEquals(
        "fromRow = 6, fromColumn = 6, toRow = 4, toColumn = 6 fromRow = 2, "
            + "fromColumn = 6, toRow = 4, toColumn = 6 ",
        m.getGameState());
  }

  @Test
  public void testNothingSentToModel() {
    StringBuilder modelLog = new StringBuilder();
    MarbleSolitaireModel m = new MockMarbleSolitaire(modelLog);
    // test that nothing is sent to model as no fully valid move is inputted before quitting
    StringReader input = new StringReader("-1 -2 -3 $$ 6 7 8 dhbhjsdb q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    assertEquals("", m.getGameState());
  }
}
