// Exercise 8.17: TicTacToe3D.java (second part)
// Create a class TicTacToe3D that will enable you to write an application to
// play a three dimensional game of Tic-Tac-Toe.
// The class contains a private 4x4 three-dimensional array
// Use an enum type to represent the value in each cell (X, O, EMPTY)
// The constructor should initialize the board elements to EMPTY
// Allow two human players. Wherever the first player moves place an X and
// wherever the second player moves place an O. Each move must be to an empty
// square and after each move determine whether the game has been won or if
// it is a draw.

import java.util.Scanner;

public class TicTacToe3D
{
	// enum type with constants that represent the cell value
	private enum Value {X, O, EMPTY};

	private Value board[][][] = new Value[4][4][4];

	private static Value player1 = Value.X;
	private static Value player2 = Value.O;


	public TicTacToe3D()
	{
		this.board = board;

		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board.length; c++)
			{
				for(int h = 0; h < board.length; h++)
				{
					board[r][c][h] = Value.EMPTY;
				}
			}
		}

		this.player1 = player1;
		this.player2 = player2;
	}

	public Value[][][] getBoard()
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
		System.out.println("Welcome to 3D Tic-Tac-Toe");
		System.out.println("Player 1 is X and Player 2 is O");
		System.out.println("The playing area consists of four 4x4 boards, ");
		System.out.println("stacked one on top of the other.");
		TicTacToe3D.displaySquareNumbers();
	}

	public static void displaySquareNumbers()
	{
		System.out.println("The squares on the board are identified as follows -");
		System.out.printf(" 1  2  3  4\n 5  6  7  8\n 9 10 11 12\n13 14 15 16\n");
		System.out.println();
		System.out.printf("17 18 19 20\n21 22 23 24\n25 26 27 28\n29 30 31 32\n");
		System.out.println();
		System.out.printf("33 34 35 36\n37 38 39 40\n41 42 43 44\n45 46 47 48\n");
		System.out.println();
		System.out.printf("49 50 51 52\n53 54 55 56\n57 58 59 60\n61 62 63 64\n");
		System.out.println();
		System.out.println("Square 17 is above Square 1, Square 33 is above " +
			"Square 17, and Square 49 is above Square 33.");

	}

	public int move(int playerNumber)
	{
		Scanner input = new Scanner(System.in);
		TicTacToe3D myGame = new TicTacToe3D();

		System.out.printf("Player %d, please select a square (1-64),", playerNumber);
		System.out.printf(" or enter zero to see the square numbers on the board: ");
		String answer = input.nextLine();
		int square = Integer.parseInt(answer);

		if (square < 0 || square > 64)
		{
			System.out.println("That is not a valid answer\n");
			return playerNumber;
		}

		if (square == 0)
		{
			TicTacToe3D.displaySquareNumbers();
			return playerNumber;
		}

		int row = 0;
		int column = 0;
		int height = 0;

		// determine row value
		if((square + 16) % 16 > 0 && (square + 16) % 16 < 5)
		{
			row = 0;
		}
		if((square + 16) % 16 > 4 && (square + 16) % 16 < 9)
		{
			row = 1;
		}
		if((square + 16) % 16 > 8 && (square + 16) % 16 < 13)
		{
			row = 2;
		}
		if((square + 16) % 16 > 12 || (square + 16) % 16 == 0)
		{
			row = 3;
		}

		// determine column value
		if(square % 4 == 0)
		{
			column = 3;
		}
		else
		{
			column = ((square + 4) % 4) - 1;
		}

		// determine height value
		if ((square + 16) % 16 == 0)
		{
			height = (square / 16) - 1;
		}
		else
		{
			height = square / 16;
		}


		if (board[height][row][column] == player1 ||
			board[height][row][column] == player2)
		{
			System.out.printf("That is not an empty square.\n");
			return playerNumber;
		}
		else
		{
			if(playerNumber == 1)
			{
				board[height][row][column] = player1;
				playerNumber = 2;
			}
			else
			{
				board[height][row][column] = player2;
				playerNumber = 1;
			}
		}
		return playerNumber;
	}

	// possible winning combos:
	// For each board (add 16 for levels 2-4)*
	// *Rows: 1-4, 5-8, 9-12, 13-16
	// *Columns: 1-5-9-13, 2-6-10-14, 3-7-11-15, 4-8-12-16

	public void evaluateGame()
	{
		TicTacToe3D myGame = new TicTacToe3D();
		int totalEmpty = 0;

		// check rows
		for(int h = 0; h < board.length; h++)
		{
			for(int c = 0; c < board.length; c++)
			{
				int totalPlayer1 = 0;
				int totalPlayer2 = 0;

				for(int r = 0; r < board.length; r++)
				{
					if (board[h][r][c] == player1)
					{
						totalPlayer1++;
					}
					else if(board[h][r][c] == player2)
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
		}
		// check columns
		for(int h = 0; h < board.length; h++)
		{
			for(int r = 0; r < board.length; r++)
			{
				int totalPlayer1 = 0;
				int totalPlayer2 = 0;

				for(int c = 0; c < board.length; c++)
				{
					if (board[h][r][c] == player1)
					{
						totalPlayer1++;
					}
					else if(board[h][r][c] == player2)
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
		}

		// check height
		for(int c = 0; c < board.length; c++)
		{
			for(int r = 0; r < board.length; r++)
			{
				int totalPlayer1 = 0;
				int totalPlayer2 = 0;

				for(int h = 0; h < board.length; h++)
				{
					if (board[h][r][c] == player1)
					{
						totalPlayer1++;
					}
					else if(board[h][r][c] == player2)
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
		}

		// check diagonals
		// Diagonals : 1-6-11-16, 13-10-7-4 (+ 16 for rows 2-4)

		for(int h = 0; h < board.length; h++)
		{
			int totalPlayer1 = 0;
			int totalPlayer2 = 0;
			int c = 0;

			for(int r = 0; r < board.length; r++)
			{
				if (board[h][r][c] == player1)
				{
					totalPlayer1++;
				}
				else if(board[h][r][c] == player2)
				{
					totalPlayer2++;
				}
				else
				{
					totalEmpty++;
				}
				c++;
				myGame.checkForWin(totalPlayer1, totalPlayer2);
			}
		}

		for(int h = 0; h < board.length; h++)
		{
			int totalPlayer1 = 0;
			int totalPlayer2 = 0;
			int c = 3;

			for(int r = 0; r < board.length; r++)
			{
				if (board[h][r][c] == player1)
				{
					totalPlayer1++;
				}
				else if(board[h][r][c] == player2)
				{
					totalPlayer2++;
				}
				else
				{
					totalEmpty++;
				}
				c--;
				myGame.checkForWin(totalPlayer1, totalPlayer2);
			}
		}

		// 3D Diagonal: 1-22-43-64
			int totalPlayer1 = 0;
			int totalPlayer2 = 0;
			int c = 0;
			int h = 0;

			for(int r = 0; r < board.length; r++)
			{
				if (board[h][r][c] == player1)
				{
					totalPlayer1++;
				}
				else if(board[h][r][c] == player2)
				{
					totalPlayer2++;
				}
				else
				{
					totalEmpty++;
				}
				c++;
				h++;
				myGame.checkForWin(totalPlayer1, totalPlayer2);
			}

		// 3D Diagonals: 13-26-39-52
		totalPlayer1 = 0;
		totalPlayer2 = 0;
		c = 0;
		int r = 3;

		for(h = 0; h < board.length; h++)
		{
			if (board[h][r][c] == player1)
			{
				totalPlayer1++;
			}
			else if(board[h][r][c] == player2)
			{
				totalPlayer2++;
			}
			else
			{
				totalEmpty++;
			}
			c++;
			r--;
			myGame.checkForWin(totalPlayer1, totalPlayer2);
		}

		// 16-27-38-49
		totalPlayer1 = 0;
		totalPlayer2 = 0;
		c = 0;
		h = 3;

		for(r = 0; r < board.length; r++)
		{
			if (board[h][r][c] == player1)
			{
				totalPlayer1++;
			}
			else if(board[h][r][c] == player2)
			{
				totalPlayer2++;
			}
			else
			{
				totalEmpty++;
			}
			c++;
			h--;
			myGame.checkForWin(totalPlayer1, totalPlayer2);
		}

		// 4-23-42-61
		totalPlayer1 = 0;
		totalPlayer2 = 0;
		c = 3;
		h = 0;

		for(r = 0; r < board.length; r++)
		{
			if (board[h][r][c] == player1)
			{
				totalPlayer1++;
			}
			else if(board[h][r][c] == player2)
			{
				totalPlayer2++;
			}
			else
			{
				totalEmpty++;
			}
			c--;
			h++;
			myGame.checkForWin(totalPlayer1, totalPlayer2);
		}

		checkForDraw(totalEmpty);
	}

	public void checkForWin(int totalPlayer1, int totalPlayer2)
	{
		if (totalPlayer1 == 4)
		{
			System.out.println("Player 1 wins!");
			System.exit(0);
		}
		else if(totalPlayer2 == 4)
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
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board.length; c++)
			{
				for(int h = 0; h < board.length; h++)
				{
					Value currentValue = board[r][c][h];
					String enumString = currentValue.name();
					System.out.printf("%s ", enumString);
				}
				System.out.println();
			}
			System.out.println();
		}
	}




}
