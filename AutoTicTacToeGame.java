// Exercise 8.17: AutoTicTacToeGame.java
// This program modifies TicTacToeGame.java to play a game between one player
// and the computer

public class AutoTicTacToeGame
{
  public static void main(String[] args)
  {
    AutoTicTacToe game = new AutoTicTacToe();

    int playerNumber = game.startGame();
    int computerNumber = 0;

    if(playerNumber == 1)
    {
      computerNumber = 2;
    }
    else
    {
      computerNumber = 1;
      playerNumber = game.moveComputer(1);
    }

    /*int newPlayerNumber = game.move(playerNumber);
    playerNumber = game.moveComputer(newPlayerNumber);
    newPlayerNumber = game.move(playerNumber);
    playerNumber = game.moveComputer(newPlayerNumber);
    game.displayBoard(); */

    while (true)
    {
      game.evaluateGame();
      int newPlayerNumber = game.move(playerNumber);
      if(newPlayerNumber == playerNumber)
      {
        newPlayerNumber = game.move(newPlayerNumber);
      }
      else
      {
        game.displayBoard();
      }

      game.evaluateGame();
      playerNumber = game.moveComputer(newPlayerNumber);
      game.displayBoard();
    } 
  }
}
