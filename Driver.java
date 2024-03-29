/**
 * This class creates the GameBoard object, setting the
 * window size and the start of the Board Game (Bomb Sweeper).
 * 
 * @author Will Holbrook
 */
public class Driver
{
	public static final int BOARD_WIDTH = 12;
	public static final int BOARD_HEIGHT = 12;
	public static boolean gameFinished = false;

	public static void main(String[] Args)
	{
		GameBoard b = new GameBoard("BombSweeper", BOARD_WIDTH,BOARD_HEIGHT);
	}
}
