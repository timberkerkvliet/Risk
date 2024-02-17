import gamebasics.*;
import java.util.*;
import java.net.*;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.net.*;

class RiskUI implements UserInterface {
	
	private URL BACK = RiskGame.class.getResource("data/back.jpg");
	private URL MAP1 = RiskGame.class.getResource("data/map1.gif");
	private URL MAP2 = RiskGame.class.getResource("data/map2.gif");
	
	private GWindow win;
	
	private GImage back;
	private GImage map;
	
	private Font font;
		
	
	RiskUI() {
		win = new GWindow("Online Risk VI", 1110, 678);
		
		font = null;
		try {
		     URL a = RiskGame.class.getResource("data/VeraSe.ttf");
		     font = Font.createFont(Font.TRUETYPE_FONT, a.openStream());
		     font = font.deriveFont(12f);
		} catch (IOException ioe) { } catch (FontFormatException ffe) { }
		if (font==null) {
			System.out.println("font=null");	
		}
		
		back = null;
		map = null;
		setBack(true);
	}
	
	private void setBack(boolean middleEast) {
		if (back==null) {
			back = new GImage(new GRectangle(0,0,0,0),0,BACK);
			win.getDiv().addObject(back);
		}
		if (map!=null) {
			win.getDiv().removeObject(map);	
		}
		if (middleEast) {
			map = new GImage(new GRectangle(0,0,0,0),1,MAP1);
		} else {
			map = new GImage(new GRectangle(0,0,0,0),1,MAP2);
		}
		win.getDiv().addObject(map);
		win.repaint();
	}
	
	public String getNick() {
		String s = "Welcome to Online Risk VI. What is your nickname?";
		DialogInput d = new DialogInput(10, s, font);
		win.getDiv().addObject(d);
		d.start();
		win.repaint();
		
		while (d.getInput()==null) {
			Lib.wait(100);	
		}
		d.stop();
		win.getDiv().removeObject(d);
		return d.getInput();
	}
		
	public void showNickError() {
		String s = "You didn't enter an allowed nickname. Make sure\n"
			+ "that all characters of your nickname are alphanumeric \n"
			+ "and your nickname doesn't contain whitespaces.";
		DialogMessage d = new DialogMessage(10, s, font);
		win.getDiv().addObject(d);
		win.repaint();
		
		while (!d.isClicked()) {
			Lib.wait(100);	
		}
		win.getDiv().removeObject(d);
	}
		
	public int askRole() {
		DialogStart d = new DialogStart(10);
		win.getDiv().addObject(d);
		win.repaint();
		boolean a = true;
		while(a) {
			Lib.wait(100);	
		}
		return 0;
	}

	public int getPort() {
		return 9999;
	}
	
	public void showServerSetupError() {
		
	}
	
	public GameSetup getGameSetup() {
		return null;
	}
	
	public void roamingUpdateHost(GameStatus s) {
		
	}
	
	public void roamingUpdate(GameStatus s) {
		
	}
	
	public void setAllowedToStart() {
		
	}
	
	public boolean getGameStart() {
		return false;	
	}
	
	// String van nicknames s1 waar we op wachten, en strin van nicknames s2
	// van spectators waar we op wachten die gedropd mogen worden.
	// retouneert of de spectators gedropped moeten worden
	public boolean showWaitingFor(String[] s1, String[] s2) {
		return false;
	}
	
	public String getServerIp() {
		return null;	
	}
	
	public int getServerPort() {
		return 9999;
	}
	
	public void showConnectError() {
		System.out.println("Fout: kan niet verbinden met de server");
	}
	
	public void showNickExists() {
		System.out.println("Fout: nickname al aanwezig op deze server");
	}
	
	public void showUnknownError() {
		System.out.println("Onbekende fout!");
	}
	
	public void showMaxPlayersError() {
		System.out.println("Fout: het maximaal aantal spelers is bereikt");
	}
	
	public void showWrongServer() {
		System.out.println("Fout: dit is geen geschikte server");
	}
	
	public void showAlreadyStarted() {
		System.out.println("Fout: het spel is al begonnen");
	}
	
	public void serverDisconnected() {
		System.out.println("Connectie met server (tijdelijk) verloren...");	
	}
	
}
