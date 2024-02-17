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

public class GSimpleButton extends GObject {
	
	private boolean mouseOver;
	private boolean clicked;
	
	private Font font;
	private Color fontColor;
	private Color bgColor;
	private Color borderColor;
	private Color fontColor2;
	private Color bgColor2;
	private Color borderColor2;
	private FontMetrics fm;
	private int textWidth;
	private int textHeight;
	
	private String text;
	
	public GSimpleButton(GRectangle rect, int layer, Font font, Color fontColor, Color bgColor, Color borderColor,
				Color fontColor2, Color bgColor2, Color borderColor2, String text) {
		super(rect,layer);
		this.font = font;
		this.fontColor = fontColor;
		this.borderColor = borderColor;
		this.bgColor = bgColor;
		this.fontColor2 = fontColor2;
		this.borderColor2 = borderColor2;
		this.bgColor2 = bgColor2;
		this.text = text;
		mouseOver = false;
		clicked = false;
		fm = null;
		textWidth = 0;
		textHeight = 0;
	}
	
	public void setClicked(boolean b) {
		clicked = b;	
	}
	
	public boolean isClicked() {
		return clicked;	
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
		Color fontC = null;
		Color bgC = null;
		Color borderC = null;
		if (mouseOver) {
			fontC = fontColor2;
			bgC = bgColor2;
			borderC = borderColor2;
		} else {
			fontC = fontColor;
			bgC = bgColor;
			borderC = borderColor;
		}
		
		g.setColor(bgC);
		g.fillRect( frame.getX()+getX(), frame.getY()+getY(), getWidth(), getHeight() );
		g.setColor(borderC);
		g.drawRect( frame.getX()+getX(), frame.getY()+getY(), getWidth(), getHeight() );
		g.setColor(fontC);
		
		g.setFont(font);
		if (fm==null) { 
			fm = g.getFontMetrics();
			textHeight = fm.getMaxAscent();
			textWidth = fm.stringWidth(text);
		}
		
		int x = Math.max((getWidth()-textWidth)/2, 0);
		int y = Math.max((getHeight()-textHeight)/2, 0);
		g.drawString(text, 
			x+frame.getX()+getX(), frame.getY()+getY()+y+textHeight);
		
		
	
	}
	
}
