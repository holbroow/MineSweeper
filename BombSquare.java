import java.util.*;

import javax.swing.JOptionPane;

public class BombSquare extends GameSquare
{
	private boolean thisSquareHasBomb = false;
	public static final int MINE_PROBABILITY = 6; // 1 = 100% chance for a bombSquare to be a bomb
	private int nearbyBombs = 0;
	private boolean visible = false;
	private BombSquare[] surroundingSquares = new BombSquare[8];

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png", board);

		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
	}

	public void clicked()
	{
		if (!visible)
		{
			visible = true;
			if(!thisSquareHasBomb)
			{
				surroundingSquares[0] = (BombSquare) board.getSquareAt(xLocation-1, yLocation-1);
				surroundingSquares[1] = (BombSquare) board.getSquareAt(xLocation, yLocation-1);
				surroundingSquares[2] = (BombSquare) board.getSquareAt(xLocation+1, yLocation-1);
				surroundingSquares[3] = (BombSquare) board.getSquareAt(xLocation-1, yLocation);
				surroundingSquares[4] = (BombSquare) board.getSquareAt(xLocation+1, yLocation);
				surroundingSquares[5] = (BombSquare) board.getSquareAt(xLocation-1, yLocation+1);
				surroundingSquares[6] = (BombSquare) board.getSquareAt(xLocation, yLocation+1);
				surroundingSquares[7] = (BombSquare) board.getSquareAt(xLocation+1, yLocation+1);

				for (BombSquare obj : surroundingSquares)
				{
					if (obj != null)
					{
						if (obj.getBombStatus())
						{
							nearbyBombs++;
						}
					}
				}

				if (nearbyBombs > 0)
				{
					setImage("images/" + nearbyBombs + ".png");
				}
				else 
				{
					setImage("images/0.png");

					for (BombSquare obj : surroundingSquares)
					{
						if (obj != null)
						{
							obj.clicked();
						}
					}
				}
			}
			else
			{
				setImage("images/bomb.png");
				uncoverBoard();
				if (!Driver.gameFinished)
				{
					JOptionPane.showMessageDialog(this,
						"Oops! You hit a mine. Try again next time!",
						"Game Over!",
						JOptionPane.ERROR_MESSAGE);

						Driver.gameFinished = true;
						System.exit(0);
				}
			}
		}
	}

	public void uncoverBoard()
	{
		for (int i = 0; i < Driver.BOARD_WIDTH; i++)
		{
			for (int j = 0; j < Driver.BOARD_HEIGHT; j++)
			{
				BombSquare bs = (BombSquare) board.getSquareAt(i, j);
				if (bs != null && !bs.getVisibility())
				{
					bs.clicked();
				}
			}
		}
	}

	public boolean getBombStatus()
	{
		return thisSquareHasBomb;
	}

	public boolean getVisibility()
	{
		return visible;
	}
}
