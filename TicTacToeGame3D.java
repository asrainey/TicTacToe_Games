// Exercise 8.17: TicTacToeGame3D.java (second part)
// This program is a two player Tic-Tac-Toe game using class TicTacToe

public class TicTacToeGame3D
{
  public static void main(String[] args)
  {
    TicTacToe3D game = new TicTacToe3D();

    //game.displayBoard();
    //game.displaySquareNumbers();
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
