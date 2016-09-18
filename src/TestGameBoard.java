import java.util.*;

public class TestGameBoard {
	
	public static void main(String[] args){
		Ship s = new Ship(3, "ship1");
		for (int i=0;i<3;i++){
			s.addHit();
			System.out.println(i);
			if (s.checkSunk()){
				System.out.println(s.toString());
			}
		}
		
		GameBoard g = new GameBoard(7, 7, 3, new int[1], new String[1]);
		g.initializeBoard();
		for (int i=0; i<7; i++){
			for (int j=0; j<7; j++){
				System.out.print(g.getBoard()[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Any Key");
		String input = scan.nextLine();
		
		while (input != ""){
			System.out.println("first");
			int f = scan.nextInt();
			System.out.println("second");
			int z = scan.nextInt();
			System.out.println(g.makeMove(f, z));
			System.out.println("");
			System.out.println("Any Key");
			input = scan.next();
		}
		
	}

}
