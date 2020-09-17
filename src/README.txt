This code was adapted from a previous design that had only one type
of Marble Solitaire Model.

It is meant to show MVC design patterns by having a segregated controller and models.

DETAIL CHANGES FROM OLD IMPLEMENTATION:

Most code from the earlier assignment was unchanged, but nearly all of it was moved to a different
location. The first change was changing the enum to represent game pieces to a protected inner class
of the Base Abstract class. This is because there no reason for such detail about my specific
implementation to be its own public class. All logic for moving, checking if the game was over,
and getting the score was moved to the base abstract classes. The only changes to this code was that
move now delegates to implementations of this abstract class to decide whether to allow diagonal
moves. From this abstract class code, a second abstract class for square-based models was created.
Since the original implementation shares most of its logic with making game boards with the added
European model, code to make the game board was placed in this class. The only code remaining in the
class is the protected method to correctly construct the upper and bottom rows of the game board.
In doing so, the only code that was changed was setting the empty space after the game board instead
of while making it. This design choice was changed to more easily abstract the code.

