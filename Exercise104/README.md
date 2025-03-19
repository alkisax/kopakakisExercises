# Tic-Tac-Toe Game - Java Implementation

This project is a simple console-based implementation of the Tic-Tac-Toe (Three-in-a-Row) game in Java. It allows two players to take turns playing with "X" and "Y" on a 3x3 grid. The game ends when one player wins by aligning three of their marks in a row, column, or diagonal, or when the board is full with no winner (draw).

## Project Structure

### Main Class: `Exercise104`

The main class controls the flow of the game, alternating turns between the two players and handling input and output. It contains the following methods:

### Methods

- **`main(String[] args)`**  
  Starts the game loop. It initializes the game board, handles player turns, checks for game over conditions, and asks if the players want to play again after a game ends.

- **`gridCreator()`**  
  Creates and returns a 3x3 board initialized with empty spaces (" "). It represents the Tic-Tac-Toe grid where the players will make their moves.

- **`gameplay(String[][] board, Scanner in)`**  
  Handles the game loop where players take turns. It alternates between player "X" and player "Y". After each turn, it checks if the game has ended (draw, "X" wins, or "Y" wins).

- **`xRound(String[][] board, Scanner in)`**  
  Manages player "X"'s turn. It clears the screen, displays the current game board, and prompts player "X" to make a move. It validates the move and updates the board.

- **`yRound(String[][] board, Scanner in)`**  
  Similar to `xRound`, this method manages player "Y"'s turn. It performs the same operations, allowing "Y" to place their mark on the board.

- **`showGrid(String[][] board)`**  
  Displays the current state of the board on the console. It prints the grid with the "X" and "Y" marks and the dividers between rows.

- **`moveChecker(String[][] board, int[] move)`**  
  Checks if the player's move is valid. It ensures that the chosen row and column are within the valid range and that the selected spot is not already taken.

- **`clearScreen()`**  
  Clears the console screen by printing 50 newlines. This is used to refresh the view after each player's move.

- **`isGameOver(String[][] board)`**  
  Checks the game status after each move. It calls other methods (`drawChecker`, `xWinChecker`, `yWinChecker`) to determine if the game has ended in a draw, or if either player has won.

- **`drawChecker(String[][] board)`**  
  Checks if the game has ended in a draw. It returns `true` if there are no empty spots left on the board.

- **`xWinChecker(String[][] board)`**  
  Checks if player "X" has won the game. It checks all possible winning combinations (rows, columns, diagonals).

- **`yWinChecker(String[][] board)`**  
  Similar to `xWinChecker`, but checks for a win by player "Y".

- **`winChecker(String c, String[][] board)`**  
  A helper method that checks if a given player (either "X" or "Y") has won by aligning three of their marks in a row, column, or diagonal.

## How to Play

1. The game starts with an empty 3x3 grid.
2. Player "X" and Player "Y" take turns to enter their moves.
3. To make a move, a player must input the column (0, 1, or 2) and the row (0, 1, or 2) where they want to place their mark.
4. The game will announce the result after every game (win or draw).
5. Players can choose to play again after a game ends.

## Additional Notes

- This implementation uses a simple console interface. To restart the game, players will be asked if they want to play again after each game.
- The screen is cleared between turns to give a fresh view of the board after each move (note that this feature is implemented via printing 50 newlines).
- Input validation ensures players can only choose valid positions on the grid.