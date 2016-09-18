import java.util.*;

public class Ship {
	private boolean isSunk;
	private int shipSize;
	private int hitCount;
	private String name;
	private ArrayList<String> coordinates;
	
	public Ship(int s, String n){
		isSunk = false;
		shipSize = s;
		hitCount = 0;
		name = n;
		coordinates = new ArrayList<String>();
	}
	
	public void addHit(){
		hitCount++;
		if (hitCount == shipSize){
			isSunk = true;
		}
	}
	
	public void addCoor(String c){
		coordinates.add(c);
	}
	
	public void removeCoor(String c){
		coordinates.remove(c);
	}
	
	public boolean checkSunk(){
		return isSunk;
	}
	
	public String toString(){
		return "You sunk " + name;
	}
}
