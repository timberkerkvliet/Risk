package gamebasics;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class GWindow extends JFrame implements KeyListener {
	
	private GPanel p;
	private int insetWidth;
	private int insetHeight;
	
	public GWindow(String title, int width, int height) {
		super(title);
		p = new GPanel(width,height);
		add(p);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		addKeyListener(this);
		Lib.wait(100);		
		Insets insets = getInsets();
		insetWidth = insets.left + insets.right;
		insetHeight = insets.top + insets.bottom;
		setSize(width + insetWidth,height + insetHeight);
	}
	
	public GDiv getDiv() {
		return p.getDiv();	
	}
	
	public void keyTyped(KeyEvent e) {
		getDiv().keyType(e);
        }

	public void keyPressed(KeyEvent e) {
		getDiv().keyPress(e);
	}
	
	public void keyReleased(KeyEvent e) {
		getDiv().keyRelease(e);
	}
	
	public void repaint() {
		p.repaint();	
	}
	
}
