package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents a Controller for the {@code MarbleSolitaireModel}: handles user inputs, and executes
 * moves using the model. Conveys state/outcomes to the user in some form.
 */
public interface MarbleSolitaireController {

  /**
   * Executes a single game of Marble Solitaire given a {@code MarbleSolitaireModel} as input. When
   * the game has ended, the method ends.
   *
   * @param model represents a non-null Marble Solitaire Model
   * @throws IllegalArgumentException if the provided model is null
   * @throws IllegalStateException    if the controller is unable to receive input or transmit
   *                                  output.
   */
  void playGame(MarbleSolitaireModel model);
}
