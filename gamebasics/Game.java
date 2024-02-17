package gamebasics;

import java.io.*;
import java.net.*;
import java.util.*;

public abstract class Game {
	
	public static final int HOST = 1;
	public static final int JOIN = 2;
	public static final int SPECT = 3;
	
	private String name;
	private UserInterface ui;
	
	private String nick;
	private Server server;
	private Client client;
	
	public Game() {
		name = null;
		ui = null;
		nick = null;
		server = null;
		client = null;
	}
	
	public void init(String name, UserInterface ui) {
		this.name = name;
		this.ui = ui;
	}
	
	private void startHost() {
		// setup server
		do {
			int port = ui.getPort();
			try {
				server = new Server(port);
			} catch(IOException e) {
				server = null;
				ui.showServerSetupError();	
			}
		} while(server==null);
		// setup game
		GameSetup gSetup = ui.getGameSetup();
		GameStatus gStatus = new GameStatus(name, nick);
		gStatus.gameSetup = gSetup.toString();
		server.setStatus(gStatus.toString());
		// start server
		server.start();
		boolean gameStart = false;
		while (!gameStart) {
			gStatus = new GameStatus(server.readStatus());
			ui.roamingUpdateHost(gStatus);
			if (gStatus.noOfPlayers>=gSetup.minNoPlayers) {
				ui.setAllowedToStart();
			} 
			gameStart = ui.getGameStart();
			Lib.wait(100);
		}
		
		gStatus = initGame(gStatus);
		server.setStatus(gStatus.toString());
		
		// start game loop
		while (true) {
			
			// Lib.wait for new status
			String s = null;
			do {
				s = server.waitForUpdate();
				// check if nothing has come
				if (s==null) {
					String[] sarr = new String[1];
					sarr[0] = gStatus.inputTurn;
					ui.showWaitingFor(sarr, null);
					server.sayHello();
				} 
			} while (s==null);
			gStatus = new GameStatus(s);
			
			// check if everyone has this new status and show
			// who does not and possibly drop spectators
			while (!server.waitForUpToDate()) {
				String[] all = server.notUpToDate();
				ArrayList players = new ArrayList();
				ArrayList spectators = new ArrayList();
				for (int j=0; j<all.length; j++) {
					if(gStatus.isPlayer(all[j])) {
						players.add(all[j]);	
					} else {
						spectators.add(all[j]);	
					}
				}
				String[] plArray = (String[]) players.toArray();
				String[] spArray = (String[]) spectators.toArray();
				if (ui.showWaitingFor(plArray,spArray)) {
					for (int j=0; j< spArray.length; j++) {
						server.removeRemote(spArray[j]);	
					}
				}
			}
			
			
			// do something with current status
			updateStatus(gStatus);
			if (gStatus.inputTurn.equals(nick)) {
				gStatus = doAction(gStatus);
				server.setStatus(gStatus.toString());
			}
		}
	}
	
	private void startJoin(boolean onlySpect) {
		// connect to server
		GameStatus gStatus = null;
		GameSetup gSetup = null;
		boolean serverTest=false;
		testLoop: do {
			String ip = ui.getServerIp();
			int port = ui.getServerPort();
			client = new Client(ip,port);
			serverTest = client.serverTest();
			if (!serverTest) {
				ui.showConnectError();
				continue testLoop;
			}
			
			String resp = client.join(nick);
			serverTest = !resp.equals("joinNotOk");
			if (!serverTest) {
				ui.showNickExists();
				continue testLoop;
			}
			serverTest = resp.equals("joinOk");
			if (!serverTest) {
				ui.showUnknownError();
				continue testLoop;
			}
			
			gStatus = new GameStatus(client.getStatus());
			gSetup = new GameSetup(gStatus.gameSetup);
			
			serverTest = gStatus.gameName.equals(name);
			if (!serverTest) {
				client.unJoin();
				ui.showWrongServer();
				continue testLoop;
			}
			if (!onlySpect) {
				serverTest = gStatus.noOfPlayers < gSetup.maxNoPlayers;
				if (!serverTest) {
					client.unJoin();
					ui.showMaxPlayersError();
					continue testLoop;
				}
				serverTest = gStatus.gameData.equals("initial");
				if (!serverTest) {
					client.unJoin();
					ui.showAlreadyStarted();
					continue testLoop;
				}
			}
		} while(!serverTest);
		
		// join
		if (!onlySpect) {
			String[] nickNames = new String[gStatus.noOfPlayers+1];
			for (int j=0; j<gStatus.noOfPlayers; j++) {
				nickNames[j] = gStatus.nickNames[j];
			}
			nickNames[gStatus.noOfPlayers]=nick;
			gStatus.noOfPlayers++;
			gStatus.nickNames = nickNames;
			String r = null;
			do {
				r = client.setStatusWithoutCheck(gStatus.toString());
			} while(!r.equals("statusOk"));
		}
		
		// Lib.wait for start
		client.setNumberOfAttempts(25);
		while(gStatus.gameData.equals("initial")) {
			ui.roamingUpdate(gStatus);
			String res = client.waitForUpdate();
			if (res==null) {
				ui.serverDisconnected();
			} else {
				gStatus = new GameStatus(res);
			}
		}
		
		// start game loop
		while (true) {
			// do something with current status
			updateStatus(gStatus);
			if (gStatus.inputTurn.equals(nick)) {
				HelloLoop loop = new HelloLoop(client);
				gStatus = doAction(gStatus);
				loop.stop();
				String res = null;
				do {
					res = client.waitForUpToDate();
					if (res==null) {
						ui.serverDisconnected();	
					}
				} while (res==null);
				res = null;
				do {
					res = client.setStatus(gStatus.toString());
					if (res==null || !res.equals("statusOk")) {
						ui.serverDisconnected();	
					}
				} while (res==null);
				
			} 
			// get new status
			String res = null;
			do {
				res = client.waitForUpdate();
				if (res==null) {
					ui.serverDisconnected();	
				}
			} while (res==null);
			
			gStatus = new GameStatus(res);
		}
		
	}
	
	
	public String getNick() {
		return nick;	
	}
	
	private boolean isGoodNick(String s) {
		if (s==null || s.length()==0 || s.length()>12) return false;
		for (int j=0; j<s.length(); j++) {
			if (!Character.isLetterOrDigit(s.charAt(j))) {
				return false;	
			}
		}
		return true;
	}
	
	public void startProgram() {	
		nick = ui.getNick();
		while (!isGoodNick(nick)) {
			ui.showNickError();
			nick = ui.getNick();
		}
		int role = ui.askRole();
		
		if (role==Game.HOST) {
			startHost();	
		} if (role==Game.JOIN) {
			startJoin(false);	
		} else {
			startJoin(true);	
		}
	}
	
	// alter the status before entering the game loop  (something done only by host)
	public abstract GameStatus initGame(GameStatus stat);
	
	// alter the status by doing your turn
	public abstract GameStatus doAction(GameStatus stat);
	
	// process update of status
	public abstract void updateStatus(GameStatus stat);
}
