import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.Test;

/**
 * Tests for different game play scenarios of the {@code MarbleSolitaireControllerImpl}.
 */
public class ControllerTestGamePlay {

  public ControllerTestGamePlay() {
    // empty public constructor for testing class
  }

  @Test
  public void testQuitRightAwayAndWelcomeScreen() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringReader input = new StringReader("q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);
    String[] lines = gameLog.toString().split("\n");

    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));

    String welcomeScreen = String.join("\n",
        Arrays.copyOfRange(lines, 0, 8));

    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32", welcomeScreen);

    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32", lastMsg);
  }

  @Test
  public void testQuitSecondInput() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringReader input = new StringReader("1 Q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", gameLog.toString());
  }

  @Test
  public void testQuitThirdInput() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringReader input = new StringReader("4 6 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", gameLog.toString());
  }

  @Test
  public void testQuitFourthInput() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringReader input = new StringReader("4 6 4 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", gameLog.toString());
  }


  @Test
  public void testQuitAfterMoveInput() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringReader input = new StringReader("4 6 4 4 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    assertEquals(26, lines.length);
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));

    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }

  @Test
  public void testInvalidFromRowIgnored() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    // invalid string should be ignored
    StringReader input = new StringReader("4 6 4 4 hyb 6 5 4 5 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    // check the number of lines is correct
    assertEquals(35, lines.length);
    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 8, lines.length));

    // check the user was prompted after invalid input
    assertEquals("Your input must be a positive integer (or \"q\" to quit).", lines[16]);

    // check the game state is as expected at end with correct move
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O _ O\n"
        + "O O O O _ O O\n"
        + "    O O _\n"
        + "    O O O\n"
        + "Score: 30", endState);
  }

  @Test
  public void testInvalidFromColumnIgnored() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    // negative 1 should be ignored
    StringReader input = new StringReader("4 6 4 4 6 -1 5 4 5 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    // check the number of lines is correct
    assertEquals(35, lines.length);
    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 8, lines.length));

    // check the user was prompted after invalid input
    assertEquals("Your input must be a positive integer (or \"q\" to quit).", lines[16]);

    // check the game state is as expected at end with correct move
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O _ O\n"
        + "O O O O _ O O\n"
        + "    O O _\n"
        + "    O O O\n"
        + "Score: 30", endState);
  }

  @Test
  public void testInvalidToRowIgnored() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    // invalid string is ignored (even when containing a "q"
    StringReader input = new StringReader("4 2 qhsh 4 4 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    // check the number of lines is correct
    assertEquals(27, lines.length);

    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 8, lines.length));

    // check the user was prompted after invalid input
    assertEquals("Your input must be a positive integer (or \"q\" to quit).", lines[8]);

    // check the game state is as expected at end with correct move
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31", endState);
  }

  @Test
  public void testInvalidToColumnIgnored() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    // invalid characters and string are ignored
    StringReader input = new StringReader("4 2 4 ! @ & dbhbeh 4 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    // check the number of lines is correct, 3 more than last test because 3 re-prompts
    assertEquals(30, lines.length);
    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 8, lines.length));

    // check the user was prompted after invalid input
    assertEquals("Your input must be a positive integer (or \"q\" to quit).", lines[8]);
    assertEquals("Your input must be a positive integer (or \"q\" to quit).", lines[9]);
    assertEquals("Your input must be a positive integer (or \"q\" to quit).", lines[10]);

    // check the game state is as expected at end with correct move
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31", endState);
  }

  @Test
  public void testGameWon() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    // input that would end game
    StringReader input = new StringReader("4 6 4 4 6 5 4 5 5 7 5 5 5 4 5 6 5 2 5 4 7 3 5 3 4 3 6 "
        + "3 7 5 7 3 7 3 5 3 3 5 5 5 3 7 3 5 2 3 4 3 2 5 4 5 4 4 4 6 2 4 4 4 4 7 4 5 5 3 3 3 4 5 4 "
        + "3 4 2 4 4 3 2 3 4 5 4 7 4 5 6 5 4 4 4 6 4 7 4 5 4");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    // check this is the correct length
    assertEquals(201, lines.length);
    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 9, lines.length));

    // check correct ending screen
    assertEquals("Game over!\n"
        + "    O O O\n"
        + "    _ _ _\n"
        + "O _ _ O _ _ _\n"
        + "O _ _ _ _ _ _\n"
        + "O _ _ O _ _ _\n"
        + "    _ _ _\n"
        + "    _ _ _\n"
        + "Score: 8", endState);
  }

  @Test
  public void testManyInvalidInputsGameEnds() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    // same sequence of valid inputs as last test with many invalid characters
    StringReader input = new StringReader(
        "4 ! 6 -3 4 4 @@ 6 5 4 5 5 7 5 ^&@ 5 5 4 5 6 5 efhbgfueh 2 5 4 7 3 5 3 4 3 6 "
            + "3 7 5 7 3 7 dh 3 5 3 qqqq 3 5 qq 5 5 3 ! 7 3 5 2 3 4 s 3 2 5 4 5 w 4 4 ef "
            + "4 6 2 -10 4 4 4 -3 4 7 4 5 -4 5 3 3 -19 3 4 5 4 "
            + "3 4 dfd 2 4 sdfds 4 3 &T^& 2 hello 3 4 5 4 7 4 5 6 5 4 4 4 6 4 7 4 5 4");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    // check this is the correct length, 20 more than last time for 20 invalid inputs
    assertEquals(221, lines.length);
    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 9, lines.length));

    // test same ending state despite invalid entries
    assertEquals("Game over!\n"
        + "    O O O\n"
        + "    _ _ _\n"
        + "O _ _ O _ _ _\n"
        + "O _ _ _ _ _ _\n"
        + "O _ _ O _ _ _\n"
        + "    _ _ _\n"
        + "    _ _ _\n"
        + "Score: 8", endState);
  }

  @Test
  public void testPosIntegerNotOnGameBoard() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(2, 1);
    // this should be read as valid inputs (all positive numbers) but the controller should handle
    // the move method rejecting it as (1, 2) or the 0-based (0, 1) doesn't exist
    StringReader input = new StringReader("1 2 3 2 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    assertEquals(27, lines.length);

    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));

    // correct message with informative response on what went wrong.
    assertEquals("Invalid move. Play again. The zero-based position (0, 1) is invalid!", lines[8]);

    // check no move was executed
    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O _ O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32", endState);
  }

  @Test
  public void testInvalidPositions2() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(5, 4, 1);
    // this should be read as valid inputs by controller but be rejected by the model. The space
    // jumping from is valid, but the place jumping to is invalid. The position (3, 2) or (2, 1) in
    // a zero-based system is invalid. The controller must give an informative message and ignore
    // the move.
    StringReader input = new StringReader("5 4 5 2 5 2 3 2 q");

    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    assertEquals(59, lines.length);

    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 14, lines.length));

    // correct message with informative response on what went wrong.
    assertEquals("Invalid move. Play again. The zero-based position (2, 1) is invalid!", lines[28]);

    // check the second move is ignored
    assertEquals("        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O _ _ O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "Score: 103", endState);
  }

  @Test
  public void invalidMoveAllPositionsValid() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(5);
    // all inputs should be accepted by the model. This correctly lands in an empty spot, but
    // attempts to jump more than 2 spaces. An informative message should be made by the controller
    // to explain this issue
    StringReader input = new StringReader("4 7 7 7 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    assertEquals(45, lines.length);

    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 14, lines.length));

    // correct message with informative response on what went wrong.
    assertEquals(
        "Invalid move. Play again. May only move 2 spaces away!",
        lines[14]);

    // check the move is ignored
    assertEquals("        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "Score: 104", endState);
  }

  @Test
  public void invalidMoveNotLandingInEmpty() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(5);
    // this input should be ignored as it does not land in an empty spot
    StringReader input = new StringReader("4 6 6 6 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    assertEquals(45, lines.length);

    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 14, lines.length));

    // correct message with informative response on what went wrong.
    assertEquals(
        "Invalid move. Play again. May only move a marble to an empty space, by jumping a marble.",
        lines[14]);

    // check the move is ignored
    assertEquals("        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "Score: 104", endState);
  }

  @Test
  public void testInvalidMoveNotJumpingMarble() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(5);
    // the first move should be accepted and executed. The second, however, should be ignored.
    // (7,4) -> (7,6) lands in an empty space, but doesn't jump a marble.
    StringReader input = new StringReader("7 5 7 7 7 4 7 6 q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    assertEquals(59, lines.length);

    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 14, lines.length));

    // correct message with informative response on what went wrong.
    assertEquals(
        "Invalid move. Play again. May only move a marble to an empty space, by jumping a marble.",
        lines[28]);

    // check the second move is ignored
    assertEquals("        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O _ _ O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "Score: 103", endState);
  }

  @Test
  public void attemptDiagonalMove() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(5);
    // attempting a diagonal jump that should not be allowed.
    StringReader input = new StringReader("5 9 7 7 Q");
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(input, gameLog);

    msc.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    assertEquals(45, lines.length);

    String endState = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 14, lines.length));

    // correct message with informative response on what went wrong.
    assertEquals(
        "Invalid move. Play again. Diagonal moves are not allowed.",
        lines[14]);

    // check the move is ignored
    assertEquals("        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "Score: 104", endState);
  }
}
