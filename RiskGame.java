import gamebasics.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.net.*;
import java.util.*;

class RiskGame extends Game {
	
	RiskUI ui;
	
	RiskGame() {
		ui = new RiskUI();
		init("RiskVI",ui);
	}
	
	public GameStatus initGame(GameStatus stat) {
		
		return stat;
	}
	
	public GameStatus doAction(GameStatus stat) {
		
		return stat;
	}
	
	public void updateStatus(GameStatus stat) {
		
	}
	
	public static void main(String[] args) {
		new RiskGame().startProgram();
	
	}


}
