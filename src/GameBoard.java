import java.util.*;

public class GameBoard {
	private int height, width, numShips;
	private HashMap<String, Ship> gameBoard;
	private int shipSize1, shipSize2, shipSize3;
	
	public GameBoard(int w, int h, int n){
		width = w;
		height = h;
		numShips = n;
		gameBoard = new HashMap<String, Ship>();
		// shipSize variables can be modified
		shipSize1 = 3;
		shipSize2 = 3;
		shipSize3 = 3;
	}
	
	public boolean initializaBoard(){
		int fails = 0;
		int count = 0;
		//String alphabet = "ABCDEFG";
		// Vertical values first, using int (0 - 6) representation of char 'a to g' 
		// Assign random value
		while (count < numShips || fails < 30){
			char first = (char) ((int) Math.floor(Math.random() * height) + 10); //adding 10 to convert to a letter
			first = Character.toUpperCase(first);
			int second = (int)Math.floor(Math.random() * width) + 1; //adding 1 to start at 1
			String result = first + second + "";
			
			// if position taken, add to fails and try again
			if (gameBoard.containsKey(result)){
				
			}
		}
		if (fails >= 30){
			return false;
		}
		return true;
	}

}
