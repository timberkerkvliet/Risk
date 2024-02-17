package gamebasics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;

import java.awt.datatransfer.*;

public class GButton extends GObject {
	
	private Image img1;
	private Image img2;
	
	private boolean mouseOver;
	private boolean clicked;
	
	public GButton(GRectangle rect, int layer, URL url1, URL url2) {
		super(rect,layer);
		img1 = null;
		try {
		    img1 = ImageIO.read(url1);
		} catch (IOException e) { }
		img2 = null;
		try {
		    img2 = ImageIO.read(url2);
		} catch (IOException e) { }
		automaticallySetSize(1);
		mouseOver = false;
		clicked = false;
	}
	
	public void setClicked(boolean b) {
		clicked = b;	
	}
	
	public boolean isClicked() {
		return clicked;	
	}
	
	public void automaticallySetSize(int no) {
		if (no==1) setSize(img1.getWidth(null),img1.getHeight(null));
		if (no==2) setSize(img2.getWidth(null),img2.getHeight(null));
	}
	
	public void mouseNow(int x, int y) {
		if (getRect().contains(x,y) && !mouseOver) {
			mouseOver = true;
			requestRepaint();
		}
		if (!getRect().contains(x,y) && mouseOver) {
			mouseOver = false;
			requestRepaint();
		}
	}
	
	public void mouseClick(int x, int y) {
		if (getRect().contains(x,y)) {
			setClicked(true);
		}
	}
	
	public void paintIn(Graphics g, GRectangle frame) {
		Image img = null;
		if (mouseOver) {
			img = img2;	
		} else {
			img = img1;	
		}
		g.drawImage(img, 
			frame.getX()+getX(),frame.getY()+getY(),
			Math.min(frame.getX()+getX()+getWidth(),frame.getX()+frame.getWidth()),
			Math.min(frame.getY()+getY()+getHeight(),frame.getY()+frame.getHeight()),
			0,0,
			Math.min(frame.getWidth()-getX(),getWidth()),
			Math.min(frame.getHeight()-getY(),getHeight()),
			null);
	}
	
}
