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
					// above left
					if (xLocation > 0 && yLocation > 0)
					{
						BombSquare bs = (BombSquare) board.getSquareAt(xLocation-1, yLocation-1);
						bs.clicked();
					}
					// above
					if (yLocation > 0)
					{
						BombSquare bs = (BombSquare) board.getSquareAt(xLocation, yLocation-1);
						bs.clicked();
					}
					// above right
					if (xLocation < arrayWidth && yLocation > 0 )
					{
						BombSquare bs = (BombSquare) board.getSquareAt(xLocation+1, yLocation-1);
						System.out.printf("Above Right: %d, %d", xLocation+1, yLocation-1);
						bs.clicked();
					}
					// left
					if (xLocation > 0)
					{
						BombSquare bs = (BombSquare) board.getSquareAt(xLocation-1, yLocation);
						bs.clicked();
					}
					// right
					// board.getWidth()-1
					if (xLocation < arrayWidth)
					{
						BombSquare bs = (BombSquare) board.getSquareAt(xLocation+1, yLocation);
						bs.clicked();
					}
					// below left
					if (xLocation > 0 && yLocation < arrayHeight)
					{
						BombSquare bs = (BombSquare) board.getSquareAt(xLocation-1, yLocation+1);
						bs.clicked();
					}
					// below
					if (yLocation < arrayHeight)
					{
						BombSquare bs = (BombSquare) board.getSquareAt(xLocation, yLocation+1);
						bs.clicked();
					}
					// below right
					if (xLocation < arrayWidth && yLocation < arrayHeight)
					{
						BombSquare bs = (BombSquare) board.getSquareAt(xLocation+1, yLocation+1);
						bs.clicked();
					}
				}
			}
			else
			{
				setImage("images/bomb.png");
			}
			//visible = true;
		}
	}

    public void countNearbyBombs(int x, int y)
    {
        // above left
        if (x > 0 && y > 0)
        {
            if (board.getSquareAt(x-1, y-1) instanceof BombSquare)
            {
                BombSquare bs = (BombSquare) board.getSquareAt(xLocation-1, yLocation-1);
                if(bs.getBombStatus() == true)
                {
                    nearbyBombs++;
                }
            }
        }
        // above
        if (y > 0)
        {
            if (board.getSquareAt(xLocation, yLocation-1) instanceof BombSquare)
			{
				BombSquare bs = (BombSquare) board.getSquareAt(xLocation, yLocation-1);
				if(bs.getBombStatus() == true)
				{
					nearbyBombs++;
				}
			}   
        }
        // above right
        if (x < arrayWidth && y > 0)
        {
            if (board.getSquareAt(xLocation+1, yLocation-1) instanceof BombSquare)
			{
				BombSquare bs = (BombSquare) board.getSquareAt(xLocation+1, yLocation-1);
				if(bs.getBombStatus() == true)
				{
					nearbyBombs++;
				}
			}
        }
        // left
        if (x > 0)
        {
            if (board.getSquareAt(xLocation-1, yLocation) instanceof BombSquare)
			{
				BombSquare bs = (BombSquare) board.getSquareAt(xLocation-1, yLocation);
				if(bs.getBombStatus() == true)
				{
					nearbyBombs++;
				}
			}
        }
        // right
        if (x < arrayWidth)
        {
            if (board.getSquareAt(xLocation+1, yLocation) instanceof BombSquare)
                {
                    BombSquare bs = (BombSquare) board.getSquareAt(xLocation+1, yLocation);
                    if(bs.getBombStatus() == true)
                    {
                        nearbyBombs++;
                    }
                }
        }
        // below left
        if (x > 0 && y < arrayHeight)
        {
            if (board.getSquareAt(xLocation-1, yLocation+1) instanceof BombSquare)
			{
				BombSquare bs = (BombSquare) board.getSquareAt(xLocation-1, yLocation+1);
				if(bs.getBombStatus() == true)
				{
					nearbyBombs++;
				}
			}
        }
        // below
        if (y < arrayHeight)
        {
            if (board.getSquareAt(xLocation, yLocation+1) instanceof BombSquare)
			{
				BombSquare bs = (BombSquare) board.getSquareAt(xLocation, yLocation+1);
				if(bs.getBombStatus() == true)
				{
					nearbyBombs++;
				}
			}
        }
        // below right
        if ( x < arrayWidth && y < arrayHeight)
        {
            if (board.getSquareAt(xLocation+1, yLocation+1) instanceof BombSquare)
			{
				BombSquare bs = (BombSquare) board.getSquareAt(xLocation+1, yLocation+1);
				if(bs.getBombStatus() == true)
				{
					nearbyBombs++;
				}
			}	
		}
    }

	public boolean getBombStatus()
	{
		return thisSquareHasBomb;
	}
}
