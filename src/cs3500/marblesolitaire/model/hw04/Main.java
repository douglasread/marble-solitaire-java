package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import java.io.InputStreamReader;

/**
 * Represents an Interactive way to run the {@link MarbleSolitaireModel}.
 */
public class Main {

  /**
   * Runs a Marble Solitaire game. Must give input of type of game ("english", "european",
   * "triangular") to be played. May also input the desired game board size with -size followed by
   * an int or specify the starting hole location with -hole followed by an int to represent the row
   * and then an int to represent the column.
   *
   * @param args represent the arguments the user passes the main method in order to create the
   *             desired board
   * @throws IllegalStateException if the controller times out or has other invalid inputs.
   */
  public static void main(String[] args) {
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(
        new InputStreamReader(System.in),
        System.out);

    String boardType;
    try {
      boardType = args[0];
    } catch (ArrayIndexOutOfBoundsException ae) {
      System.out.println("Must input a board type (i.e. english, european, or triangular).");
      return;
    }

    MarbleSolitaireModel game;
    Integer size = getBoardSize(args);
    Integer[] holeLocation = getHole(args);
    if (boardType.equalsIgnoreCase("english")) {
      game = createEnglishBoard(size, holeLocation);
    } else if (boardType.equalsIgnoreCase("european")) {
      game = createEuropeanBoard(size, holeLocation);
    } else if (boardType.equalsIgnoreCase("triangular")) {
      game = createTriangularBoard(size, holeLocation);
    } else {
      System.out.println("Invalid board type. Must be one of: english, european, triangular.");
      return;
    }

    controller.playGame(game);
  }

  /**
   * Creates an instance of the english Marble Solitaire Model with the given parameters. If the
   * parameters are null, uses default size or hole location.
   *
   * @param size represents the size of the board to create (if null, default size is used)
   * @param loc  represents a 1-D array of hole location (if null, default location used)
   * @return a {@code MarbleSolitaireModelImpl}
   */
  private static MarbleSolitaireModel createEnglishBoard(Integer size, Integer[] loc) {
    if (size == null && loc == null) {
      return new MarbleSolitaireModelImpl();
    } else if (size == null) {
      return new MarbleSolitaireModelImpl(loc[0], loc[1]);
    } else if (loc == null) {
      return new MarbleSolitaireModelImpl(size);
    } else {
      return new MarbleSolitaireModelImpl(size, loc[0], loc[1]);
    }
  }

  /**
   * Creates an instance of the European Marble Solitaire Model with the given parameters. If the
   * parameters are null, uses default size or hole location.
   *
   * @param size represents the size of the board to create (if null, default size is used)
   * @param loc  represents a 1-D array of hole location (if null, default location used)
   * @return a {@code EuropeanSolitaireModelImpl}
   */
  private static MarbleSolitaireModel createEuropeanBoard(Integer size, Integer[] loc) {
    if (size == null && loc == null) {
      return new EuropeanSolitaireModelImpl();
    } else if (size == null) {
      return new EuropeanSolitaireModelImpl(loc[0], loc[1]);
    } else if (loc == null) {
      return new EuropeanSolitaireModelImpl(size);
    } else {
      return new EuropeanSolitaireModelImpl(size, loc[0], loc[1]);
    }
  }

  /**
   * Creates an instance of the Triangular Marble Solitaire Model with the given parameters. If the
   * parameters are null, uses default size or hole location.
   *
   * @param size represents the size of the board to create (if null, default size is used)
   * @param loc  represents a 1-D array of hole location (if null, default location used)
   * @return a {@code TriangularSolitaireModelImpl}
   */
  private static MarbleSolitaireModel createTriangularBoard(Integer size, Integer[] loc) {
    if (size == null && loc == null) {
      return new TriangleSolitaireModelImpl();
    } else if (size == null) {
      return new TriangleSolitaireModelImpl(loc[0], loc[1]);
    } else if (loc == null) {
      return new TriangleSolitaireModelImpl(size);
    } else {
      return new TriangleSolitaireModelImpl(size, loc[0], loc[1]);
    }
  }

  /**
   * Gets the desired board size from the user arguments.
   *
   * @param args represents the user inputs
   * @return the board size desired (null if none given).
   */
  private static Integer getBoardSize(String[] args) {
    Integer size = null;
    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("-size")) {
        try {
          size = Integer.parseInt(args[i + 1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
          System.out.println("Invalid size input, using default size");
        }
      }
    }
    return size;
  }

  /**
   * Gets the desired hole location as a 1-D array from the user arguments.
   *
   * @param args represents the user arguments
   * @return the hole location desired (row in position 0 and column in position 1). If no input is
   *         given or it is entered incorrectly returns null.
   */
  private static Integer[] getHole(String[] args) {
    Integer[] location = null;
    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("-hole")) {
        try {
          location = new Integer[]{Integer.parseInt(args[i + 1]), Integer.parseInt(args[i + 2])};
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
          System.out.println("Invalid hole input, using default hole location.");
        }
      }
    }
    return location;
  }
}


