// Exercise 8.17: TicTacToe.java
// Create a class TicTacToe that will enable you to write an application to
// play a game of Tic-Tac-Toe.
// The class contains a private 3x3 two-dimensional array
// Use an enum type to represent the value in each cell (X, O, EMPTY)
// The constructor should initialize the board elements to EMPTY
// Allow two human players. Wherever the first player moves place an X and
// wherever the second player moves place an O. Each move must be to an empty
// square and after each move determine whether the game has been won or if
// it is a draw.


// enum to string -> yourEnum.name();
// string to enum -> YourEnum.valueOf(yourString);

import java.util.Scanner;

public class TicTacToe
{
	// enum type with constants that represent the cell value
	private enum Value {X, O, EMPTY};

	private Value board[][] = {{Value.EMPTY, Value.EMPTY, Value.EMPTY},
			 {Value.EMPTY, Value.EMPTY, Value.EMPTY},
			 {Value.EMPTY, Value.EMPTY, Value.EMPTY}};

	private static Value player1 = Value.X;
	private static Value player2 = Value.O;


	public TicTacToe()
	{
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
	}

	public Value[][] getBoard()
	{
		return board;
	}

	public Value getPlayer1()
	{
		return player1;
	}

	public Value getPlayer2()
	{
		return player2;
	}

	public void startGame()
	{
		System.out.println("Welcome to Tic-Tac-Toe");
		System.out.println("Player 1 is X and Player 2 is O");
		System.out.println("The squares on the board are identified as follows -");
		System.out.printf("1 2 3\n4 5 6\n7 8 9\n");
	}

	public int move(int playerNumber)
	{
		Scanner input = new Scanner(System.in);
		TicTacToe myGame = new TicTacToe();

		System.out.printf("Player %d, please select a square (1-9): ",
			playerNumber);
		String answer = input.nextLine();
		int square = Integer.parseInt(answer);

		if (square < 0 || square > 9)
		{
			System.out.println("That is not a valid answer\n");
			return playerNumber;
		}

		int row = 0;
		int column = 0;

		switch(square)
		{
			case 1:
				row = 0;
				column = 0;
				break;
			case 2:
				row = 0;
				column = 1;
				break;
			case 3:
				row = 0;
				column = 2;
				break;
			case 4:
				row = 1;
				column = 0;
				break;
			case 5:
				row = 1;
				column = 1;
				break;
			case 6:
				row = 1;
				column = 2;
				break;
			case 7:
				row = 2;
				column = 0;
				break;
			case 8:
				row = 2;
				column = 1;
				break;
			case 9:
				row = 2;
				column = 2;
			break;
		}

		if (board[row][column] == player1 || board[row][column] == player2)
		{
			System.out.printf("That is not an empty square.\n");
			return playerNumber;
		}
		else
		{
			if(playerNumber == 1)
			{
				board[row][column] = player1;
				playerNumber = 2;
			}
			else
			{
				board[row][column] = player2;
				playerNumber = 1;
			}
		}
		return playerNumber;
	}

	// possible winning combos:
	// 1-3, 4-6, 7-9, 1-4-7, 2-5-8, 3-6-9, 1-5-9, 7-5-3
	public void evaluateGame()
	{
		TicTacToe myGame = new TicTacToe();
		int totalEmpty = 0;

		// check rows
		for(int k = 0; k < board.length; k++)
		{
			int totalPlayer1 = 0;
			int totalPlayer2 = 0;

			for(int m = 0; m < board.length; m++)
			{
				if (board[k][m] == player1)
				{
					totalPlayer1++;
				}
				else if(board[k][m] == player2)
				{
					totalPlayer2++;
				}
				else
				{
					totalEmpty++;
				}
			}
			myGame.checkForWin(totalPlayer1, totalPlayer2);
		}

		// check columns
		for(int k = 0; k < board.length; k++)
		{
			int totalPlayer1 = 0;
			int totalPlayer2 = 0;

			for(int m = 0; m < board.length; m++)
			{
				if (board[m][k] == player1)
				{
					totalPlayer1++;
				}
				else if(board[m][k] == player2)
				{
					totalPlayer2++;
				}
				else
				{
					totalEmpty++;
				}
			}
			myGame.checkForWin(totalPlayer1, totalPlayer2);
		}

		// check diagonals
		int totalPlayer1 = 0;
		int totalPlayer2 = 0;
		int m = 0;

		for(int k = 0; k < board.length; k++)
		{
			if (board[m][k] == player1)
			{
				totalPlayer1++;
			}
			else if(board[m][k] == player2)
			{
				totalPlayer2++;
			}
			else
			{
				totalEmpty++;
			}
			m++;
			myGame.checkForWin(totalPlayer1, totalPlayer2);
		}

		totalPlayer1 = 0;
		totalPlayer2 = 0;
		m = 2;

		for(int k = 0; k < board.length; k++)
		{
			if (board[m][k] == player1)
			{
				totalPlayer1++;
			}
			else if(board[m][k] == player2)
			{
				totalPlayer2++;
			}
			else
			{
				totalEmpty++;
			}
			m--;
			myGame.checkForWin(totalPlayer1, totalPlayer2);
		}
		checkForDraw(totalEmpty);
	}

	public void checkForWin(int totalPlayer1, int totalPlayer2)
	{
		if (totalPlayer1 == 3)
		{
			System.out.println("Player 1 wins!");
			System.exit(0);
		}
		else if(totalPlayer2 == 3)
		{
			System.out.println("Player 2 wins!");
			System.exit(0);
		}
	}

	public void checkForDraw(int totalEmpty)
	{
		if (totalEmpty == 0)
		{
			System.out.println("Game ends in a draw, there are no empty squares");
			System.exit(0);
		}
	}

	public void displayBoard()
	{
		for(int k = 0; k < board.length; k++)
		{
			for(int m = 0; m < board.length; m++)
			{
				Value currentValue = board[k][m];
				String enumString = currentValue.name();
				System.out.printf("%s ", enumString);
			}
			System.out.println();
		}
	}




}
