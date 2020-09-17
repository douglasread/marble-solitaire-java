package cs3500.marblesolitaire.controller;

import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.controller.FailingAppendable;
import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import java.io.InputStreamReader;
import java.io.StringReader;
import org.junit.Test;

/**
 * Tests for exceptions that should be thrown by the ControllerImpl class and its methods.
 */
public class ControllerTestExceptions {

  public ControllerTestExceptions() {
    // public constructor for testing class
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructorInput1() {
    new MarbleSolitaireControllerImpl(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructorInput2() {
    new MarbleSolitaireControllerImpl(new InputStreamReader(System.in), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructorInput3() {
    new MarbleSolitaireControllerImpl(null, System.out);
  }

  @Test(expected = IllegalStateException.class)
  public void testNoInputs() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringReader input = new StringReader("");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);
    msc.playGame(m);
  }

  @Test
  public void testNoInputsMessage() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringReader input = new StringReader("");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    String toCompare = null;
    try {
      msc.playGame(m);
    } catch (IllegalStateException ise) {
      toCompare = ise.getMessage();
    }
    assertEquals("No inputs detected. Game state is stale.", toCompare);
  }

  @Test(expected = IllegalStateException.class)
  public void inputsStop() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringReader input = new StringReader("4 6 4 4");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);
    msc.playGame(m);
  }

  @Test
  public void inputsStopMessage() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringReader input = new StringReader("4 6 4 4");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    String toCompare = null;
    try {
      msc.playGame(m);
    } catch (IllegalStateException ise) {
      toCompare = ise.getMessage();
    }
    assertEquals("No inputs detected. Game state is stale.", toCompare);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkExceptionIfModelNull() {
    StringReader input = new StringReader("4 6 4 4 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);
    msc.playGame(null);
  }

  @Test(expected = IllegalStateException.class)
  public void unableToTransmitOutput() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringReader input = new StringReader("4 6 4 4 q");
    Appendable output = new FailingAppendable();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, output);
    msc.playGame(m);
  }
}
