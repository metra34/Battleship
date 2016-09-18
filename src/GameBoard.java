import java.util.*;

public class GameBoard {
	private int height, width, numShips;
	private Ship[][] gameBoard;
	private ArrayList<Integer> shipSizes;
	private HashSet<String> shipNames;
	
	// construct with int width, int height, int number of ships, int[] ship sizes, String[] ship names 
	public GameBoard(int w, int h, int n, int[] sSizes, String[] sNames){
		width = w;
		height = h;
		numShips = n;
		gameBoard = new Ship[height][width]; // height first because letters on vertical axis
		shipSizes = new ArrayList<Integer>();
		shipNames = new HashSet<String>();
		// loop through given array of ship sizes and only keep the ones that can fit on the map
		for (int i=0; i<sSizes.length; i++){
			if (sSizes[i] < height && sSizes[i] < width){
				shipSizes.add(sSizes[i]);
			}
		}
		// add default length of 3 to ship sizes, default Dot Com names to shipNames
		shipSizes.add(3);
		shipNames.add("Go2.com");
		shipNames.add("Pets.com");
		shipNames.add("AskMe.com");
		
	}
	
	// randomly place ships on the board
	public boolean initializeBoard(){
		int fails = 0;
		int count = 0;
		
		// Assign random value using 0 to height-1 as numeric representations of upper case letters
		while (count < numShips || fails < 100){
			int first = (int) Math.floor(Math.random() * height); // get number from 0 to height-1
			int second = (int)Math.floor(Math.random() * width); // get random horizontal position
			
			//char first = (char)(f + 64); //convert to upper case letter equivalent
			//String result = Character.toString(first) + second + "";
			//String result = f + "," + second;
			
			// if position taken, add to fails and try again
			while (gameBoard[first][second] != null){
				first = (int) Math.floor(Math.random() * height); // get number from 0 to height-1
				second = (int)Math.floor(Math.random() * width); // get random horizontal position 0 to width -1
				fails ++;
			}
			if (gameBoard[first][second] != null){
				
			}else{
				// choose ship size randomly and choose alignment randomly
				int index = (int) Math.floor(Math.random() * shipSizes.size());
				if (Math.random() < 0.5){
					//horizontal ship creatin / placement
					setHorizontal(first, second, shipSizes.get(index));
				}else{
					//Vertical
					setVertical(first, second, shipSizes.get(index));
				}
					
			}
		}
		if (fails >= 30){
			return false;
		}
		return true;
	}
	
	private int[] numToInt(String arg){
		String[] args = arg.split(",");
		int[] result = {Integer.parseInt(args[0]), Integer.parseInt(args[1])};
		return result;
	}
	
	//take the starting vertical and horizontal coordinates and try neighbouring coordinates until enough space is found to match shipSize
	private boolean setHorizontal(int sV, int sH, int sSize){
		int left = sH;
		int right = sH;
		int fails = 0;
		
		while ()
		
		return false;
	}
	
	private boolean setVertical(int sV, int sH, int sSize){
		
		return false;
	}

}
