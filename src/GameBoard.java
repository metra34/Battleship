import java.util.*;

public class GameBoard {
	private int height, width, numShips;
	private Ship[][] gameBoard;
	private int[][] moves;
	private ArrayList<Integer> shipSizes;
	private ArrayList<String> shipNames;
	private static final int maxFails = 50;
	
	// construct with int width, int height, int number of ships, int[] ship sizes, String[] ship names 
	public GameBoard(int w, int h, int n, int[] sSizes, String[] sNames){
		width = w;
		height = h;
		numShips = n;
		
		gameBoard = new Ship[height][width]; // height first because using letters on vertical axis
		moves = new int[height][width];
		shipSizes = new ArrayList<Integer>();
		shipNames = new ArrayList<String>();
		HashSet<String> shipNamesTemp = new HashSet<String>(Arrays.asList(sNames));
		//HashSet<String> shipNamesTemp = new HashSet<String>();
		// loop through given array of ship sizes and only keep the ones that can fit on the map
		for (int i=0; i<sSizes.length; i++){
			if (sSizes[i] < height && sSizes[i] < width && sSizes[i] > 0){
				shipSizes.add(sSizes[i]);
			}
		}
		// add default length of 3 to ship sizes, default Dot Com names to shipNames
		shipSizes.add(3);
		shipNamesTemp.remove(null);
		shipNamesTemp.add("Go2.com");
		shipNamesTemp.add("Pets.com");
		shipNamesTemp.add("AskMe.com");
		shipNames.addAll(shipNamesTemp);
		
	}
	
	// randomly place ships on the board
	public boolean initializeBoard(){
		int fails = 0;
		int count = 0;
		
		// Assign random value using 0 to height-1 as numeric representations of upper case letters
		while (count < numShips && fails < maxFails){
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
			
			// choose ship size randomly and choose alignment randomly
			int index = (int) Math.floor(Math.random() * shipSizes.size());
			List<Integer> shipCoors = new ArrayList<Integer>();
			int firstV = -1;
			int secondH = -1;
			if (Math.random() < 0.5){
				//horizontal ship creatin / placement
				firstV = first;
				shipCoors = setHorizontal(first, second, shipSizes.get(index));
			}else{
				//Vertical
				secondH = second;
				shipCoors = setVertical(first, second, shipSizes.get(index));
			}
			
			// make new ship or add to fail attempts
			if (shipCoors == null){
				fails++;
			}else{
				String tempName;
				if (count < shipNames.size()){
					tempName = shipNames.get(count);
				}else{
					tempName = "BoatyMcBoatFace.com";
				}
				
				Ship temp = new Ship(shipSizes.get(index), tempName);
				gameBoard[first][second] = temp;
				for (Integer c : shipCoors){
					if (secondH < 0){
						gameBoard[firstV][c] = temp;
					}else{
						gameBoard[c][secondH] = temp;
					}
				}
				count++;
			}
		}
		if (fails >= maxFails){
			return false;
		}
		return true;
	}
	
	private int[] numToInt(String arg){
		String[] args = arg.split(",");
		int[] result = {Integer.parseInt(args[0]), Integer.parseInt(args[1])};
		return result;
	}
	
	// return an array of ship coordinates for a new ship
	// take the starting vertical and horizontal coordinates and try neighbouring coordinates until enough space is found to match shipSize
	private List<Integer> setVertical(int sV, int sH, int sSize){
		int negative, positive;
		negative = sV -1;
		positive = sV +1;
		
		List<Integer> successV = new ArrayList<Integer>();
		
		while (negative >= 0 || positive < height && successV.size() < sSize -1){
			if (negative >= 0){
				if (gameBoard[negative][sH] == null && successV.size() < sSize -1){
					successV.add(negative);
					negative--;
				}else{
					negative = -1;
				}
			}
			
			if (positive < height){
				if (gameBoard[positive][sH] == null && successV.size() < sSize -1){
					successV.add(positive);
					positive++;
				}else{
					positive = height;
				}
			}
		}
		
		if (successV.size() == sSize -1){
			return successV;
		}
		
		return null;
	}
	
	private List<Integer> setHorizontal(int sV, int sH, int sSize){
		Ship[] reducedBoard = gameBoard[sV];
		int negative, positive;
		negative = sH -1;
		positive = sH +1;
		
		List<Integer> successH = new ArrayList<Integer>();
		
		while (negative >= 0 || positive < width && successH.size() < sSize -1){
			if (negative >= 0){
				if (reducedBoard[negative] == null && successH.size() < sSize -1){
					successH.add(negative);
					negative--;
				}else{
					negative = -1;
				}
			}
			
			if (positive < width){
				if (reducedBoard[positive] == null && successH.size() < sSize -1){
					successH.add(positive);
					positive++;
				}else{
					positive = width;
				}
			}
		}
		
		if (successH.size() == sSize -1){
			return successH;
		}
		return null;
	}
	
	public Ship[][] getBoard(){
		return this.gameBoard;
	}
	
	// make the move and return a string response of the result
	public String makeMove(int f, int s){
		String result = "";
		if (moves[f][s] != 0){
			result = "Already tried those coordinates.";
		}else if (moves[f][s] == 0){
			moves[f][s] = 1;
			Ship t = gameBoard[f][s];
			if (t != null && !t.checkSunk()){
				t.addHit();
				if (t.checkSunk()){
					result = "You sunk "+t.getName()+"!";
				}else{
					result = "Hit!";
				}
			}else{
				result = "Miss!";
			}
		}
		return result;
	}
}
