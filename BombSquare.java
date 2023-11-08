import java.util.*;

import javax.swing.Action;
import javax.swing.ImageIcon;

public class BombSquare extends GameSquare
{
	private boolean thisSquareHasBomb = false;
	public static final int MINE_PROBABILITY = 6; // 1 = 100% chance for a bombSquare to be a bomb
	private int nearbyBombs = 0;
	private boolean visible = false;
	private int arrayWidth = Driver.BOARD_WIDTH-1;
	private int arrayHeight = Driver.BOARD_HEIGHT-1;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png", board);

		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
	}

	public void clicked()
	{
		System.out.println(board.getX());
		if (!visible)
		{
			visible = true;
			if(!thisSquareHasBomb)
			{
				countNearbyBombs(xLocation, yLocation);
				if (nearbyBombs > 0)
				{
					setImage("images/" + nearbyBombs + ".png");
				}
				else 
				{
					System.out.printf("Current coordiates: %d, %d", xLocation, yLocation);
					setImage("images/0.png");

					BombSquare aboveLeft= (BombSquare) board.getSquareAt(xLocation-1, yLocation-1);
					BombSquare above = (BombSquare) board.getSquareAt(xLocation, yLocation-1);
					BombSquare aboveRight = (BombSquare) board.getSquareAt(xLocation+1, yLocation-1);
					BombSquare left = (BombSquare) board.getSquareAt(xLocation-1, yLocation);
					BombSquare right = (BombSquare) board.getSquareAt(xLocation+1, yLocation);
					BombSquare belowLeft = (BombSquare) board.getSquareAt(xLocation-1, yLocation+1);
					BombSquare below = (BombSquare) board.getSquareAt(xLocation, yLocation+1);
					BombSquare belowRight = (BombSquare) board.getSquareAt(xLocation+1, yLocation+1);

					if (aboveLeft != null)
					{
						aboveLeft.clicked();
					}
					if (above != null)
					{
						above.clicked();
					}
					if (aboveRight != null)
					{
						aboveRight.clicked();
					}
					if (left != null)
					{
						left.clicked();
					}
					if (right != null)
					{
						right.clicked();
					}
					if (belowLeft != null)
					{
						belowLeft.clicked();
					}
					if (below != null)
					{
						below.clicked();
					}
					if (belowRight != null)
					{
						belowRight.clicked();
					}
				}
			}
			else
			{
				setImage("images/bomb.png");
				uncoverBoard();
			}
		}
	}

    public void countNearbyBombs(int x, int y)
    {

		BombSquare aboveLeft= (BombSquare) board.getSquareAt(xLocation-1, yLocation-1);
		BombSquare above = (BombSquare) board.getSquareAt(xLocation, yLocation-1);
		BombSquare aboveRight = (BombSquare) board.getSquareAt(xLocation+1, yLocation-1);
		BombSquare left = (BombSquare) board.getSquareAt(xLocation-1, yLocation);
		BombSquare right = (BombSquare) board.getSquareAt(xLocation+1, yLocation);
		BombSquare belowLeft = (BombSquare) board.getSquareAt(xLocation-1, yLocation+1);
		BombSquare below = (BombSquare) board.getSquareAt(xLocation, yLocation+1);
		BombSquare belowRight = (BombSquare) board.getSquareAt(xLocation+1, yLocation+1);

		if (aboveLeft != null)
		{
			if (aboveLeft.getBombStatus())
			{
				nearbyBombs++;
			}
		}
		if (above != null)
		{
			if (above.getBombStatus()) 
			{
				nearbyBombs++;
			}
		}
		if (aboveRight != null)
		{
			if (aboveRight.getBombStatus())
			{
				nearbyBombs++;
			}
		}
		if (left != null)
		{
			if (left.getBombStatus())
			{
				nearbyBombs++;
			}
		}
		if (right != null)
		{
			if (right.getBombStatus())
			{
				nearbyBombs++;
			}
		}
		if (belowLeft != null)
		{
			if (belowLeft.getBombStatus())
			{
				nearbyBombs++;
			}
		}
		if (below != null)
		{
			if (below.getBombStatus())
			{
				nearbyBombs++;
			}
		}
		if (belowRight != null)
		{
			if (belowRight.getBombStatus())
			{
				nearbyBombs++;
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
