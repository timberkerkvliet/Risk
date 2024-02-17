package gamebasics;

import java.io.*;
import java.net.*;
import java.util.*;

public interface UserInterface {
	
	String getNick();
	
	void showNickError();
	
	int askRole();

	int getPort();
	
	void showServerSetupError();
	
	GameSetup getGameSetup();
	
	void roamingUpdateHost(GameStatus s);
	
	void roamingUpdate(GameStatus s);
	
	void setAllowedToStart();
	
	boolean getGameStart();
	
	// String van nicknames s1 waar we op wachten, en strin van nicknames s2
	// van spectators waar we op wachten die gedropd mogen worden.
	// retouneert of de spectators gedropped moeten worden
	boolean showWaitingFor(String[] s1, String[] s2);
	
	String getServerIp();
	
	int getServerPort();
	
	void showConnectError();
	
	void showNickExists();
	
	void showUnknownError();
	
	void showMaxPlayersError();
	
	void showWrongServer();
	
	void showAlreadyStarted();
	
	void serverDisconnected();
}
