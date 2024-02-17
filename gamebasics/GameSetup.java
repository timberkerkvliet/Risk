package gamebasics;

import java.util.*;

public class GameSetup {
	
	public int minNoPlayers;
	public int maxNoPlayers;
	public String data;
	
	public GameSetup(int mi, int ma, String s) {
		minNoPlayers = mi;
		maxNoPlayers = ma;
		data = s;
	}
	
	public GameSetup(String s) {
		Scanner input = new Scanner(s);
		input.useDelimiter(",");
		minNoPlayers = input.nextInt();
		maxNoPlayers = input.nextInt();
		data = input.next();
	}
	
	public String toString() {
		return minNoPlayers + "," + maxNoPlayers + "," + data;	
	}
}
