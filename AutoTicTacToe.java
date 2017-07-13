// Exercise 8.17: AutoTicTacToe.java
// This modifies TicTacToe.java (Exercise 8.17) to have one person play
// against the computer. The player should decide whether to go first or second.

import java.util.Scanner;

public class AutoTicTacToe
{
	// enum type with constants that represent the cell value
	private enum Value {X, O, EMPTY};

	private static Value board[][] = {{Value.EMPTY, Value.EMPTY, Value.EMPTY},
			 {Value.EMPTY, Value.EMPTY, Value.EMPTY},
			 {Value.EMPTY, Value.EMPTY, Value.EMPTY}};

	private static Value player1 = Value.X;
	private static Value player2 = Value.O;
	private static Value emptySquare = Value.EMPTY;

	public AutoTicTacToe()
	{
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		this.emptySquare = emptySquare;
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

	public int startGame()
	{
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to Tic-Tac-Toe");
		System.out.println("The squares on the board are identified as follows -");
		System.out.printf("1 2 3\n4 5 6\n7 8 9\n");
		System.out.println();
		System.out.println("Player 1 is X and Player 2 is O");
		System.out.print("Would you like to be Player 1 or Player 2? (1/2): ");
		int playerNumber = input.nextInt();

		return playerNumber;
	}

	public int move(int playerNumber)
	{
		Scanner input = new Scanner(System.in);
		AutoTicTacToe myGame = new AutoTicTacToe();

		System.out.printf("Player %d, please select a square (1-9): ",
			playerNumber);
	 	int square = input.nextInt();

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
		AutoTicTacToe myGame = new AutoTicTacToe();
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

	public int moveComputer(int computerNumber)
	{
		AutoTicTacToe myGame = new AutoTicTacToe();

		int[] square = myGame.decideNextMove(computerNumber);

		int row = square[0];
		int column = square[1];
		int playerNumber = 0;

		if(computerNumber == 1)
		{
			board[row][column] = player1;
			playerNumber = 2;
		}
		else
		{
			board[row][column] = player2;
			playerNumber = 1;
		}
		return playerNumber;
	}

	public int[] decideNextMove(int computerNumber)
	{
		int row;
		int column;
		int[] square = {2, 2};
		Value computer = emptySquare;
		Value player = emptySquare;

		if(computerNumber == 1)
		{
			computer = player1;
			player = player2;
		}
		else
		{
			computer = player2;
			player = player1;
		}

		for(column = 0; column < board.length; column++)
		{
			for(row = 0; row < board.length; row++)
			{
				if (board[row][column] == emptySquare)
				{
					if(row == 0 && column != 0)
					{
						// new
						if(board[row + 1][column] == player && board[row + 2][column] == player)
						{
							board[row][column] = computer;
							square[0] = row;
							square[1] = column;
							return square;
						}
						// old, this was the only if
						if(board[row][column - 1] == computer)
						{
							board[row][column] = computer;
							square[0] = row;
							square[1] = column;
							return square;
						}
					}
					else if(column == 0 && row != 0)
					{
						//new
						if(board[row][column + 1] == player && board[row][column + 2] == player)
						{
							board[row][column] = computer;
							square[0] = row;
							square[1] = column;
							return square;
						}
						// old, this was the only if
						else if(board[row - 1][column] == computer)
						{
							board[row][column] = computer;
							square[0] = row;
							square[1] = column;
							return square;
						}
					}
					// new else if
					else if(row != 0 && column != 0 && board[row - 1][column - 1] == player)
					{
						board[row][column] = computer;
						square[0] = row;
						square[1] = column;
						return square;
					}
					// new else if
					else if(row != 2 && column != 2 && board[row + 1][column + 1] == player)
					{
						board[row][column] = computer;
						square[0] = row;
						square[1] = column;
						return square;
					}
					else if(row == 0 && column == 0)
					{
						board[0][0] = computer;
						square[0] = 0;
						square[1] = 0;
						return square;
					}
					else if(board[row][column - 1] == computer||
							board[row - 1][column - 1] == computer ||
							board[row - 1][column] == computer)
						{
							board[row][column] = computer;
							square[0] = row;
							square[1] = column;
							return square;
						}
					}
				}
			}
		return square;
	}
}
