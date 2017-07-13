// Exercise 8.17: TicTacToeGame.java
// This program is a two player Tic-Tac-Toe game using class TicTacToe

public class TicTacToeGame
{
  public static void main(String[] args)
  {
    TicTacToe game = new TicTacToe();

    game.startGame();
    int playerNumber = 1;

    while (true)
    {
      game.evaluateGame();
      playerNumber = game.move(playerNumber);
      game.displayBoard();
    }
  }
}
