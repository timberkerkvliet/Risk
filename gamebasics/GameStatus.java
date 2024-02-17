package gamebasics;

import java.util.*;

public class GameStatus {
	
	public String gameName;
	public int noOfPlayers;
	public String[] nickNames;
	public String inputTurn;
	public String gameSetup;
	public String gameData;
	
	public GameStatus(String gameName, String hostName) {
		this.gameName = gameName;
		noOfPlayers = 1;
		gameSetup = "noSetup";
		gameData = "initial";
		nickNames = new String[1];
		nickNames[0] = hostName;
		inputTurn = hostName;
	}
	
	public GameStatus(String s) {
		Scanner input = new Scanner(s);
		gameName = input.next();
		gameSetup = input.next();
		gameData = input.next();
		noOfPlayers = input.nextInt();
		inputTurn = input.next();
		nickNames = new String[noOfPlayers];
		for (int j=0; j<noOfPlayers; j++) {
			nickNames[j]=input.next();	
		}
	}
	
	public String toString() {
		String result = gameName + " " + gameSetup + " " + gameData + " " + noOfPlayers + " " + inputTurn;
		for (int j=0; j<nickNames.length; j++) {
			result += " "+ nickNames[j];
		}
		return result;
	}
	
	public boolean isPlayer(String s) {
		for (int j=0; j< noOfPlayers; j++) {
			if (nickNames[j].equals(s)) {
				return true;	
			}
		}
		return false;
	}
	
}
