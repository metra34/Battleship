import java.util.*;

public class Ship {
	private boolean isSunk;
	private int shipSize;
	private int hitCount;
	private String name;
	
	public Ship(int s, String n){
		isSunk = false;
		shipSize = s;
		hitCount = 0;
		name = n;
	}
	
	public void addHit(){
		hitCount++;
		if (hitCount == shipSize){
			isSunk = true;
		}
	}
	
	public boolean checkSunk(){
		return isSunk;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return "   x";
	}
}
