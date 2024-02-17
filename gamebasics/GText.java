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

public class GText extends GObject {
	
	private Font font;
	private Color fontColor;
	private ArrayList text;
	private String inputText;
	private FontMetrics fm;
	
	public GText(GRectangle rect, int layer, Font font, Color fontColor) {
		super(rect,layer);
		this.font = font;
		this.fontColor = fontColor;
		fm = null;
		text = null;
	}
	
	public void setText(String s) {
		inputText = s;
		if (fm==null) {
			return;
		}
		int lineHeight = fm.getMaxAscent() + 2;
		int noOfLines = getHeight()/lineHeight;
		text = new ArrayList();
		int charCount = 0;
		int lineCount = 0;
		String line = "";
		chLoop: while(charCount<inputText.length()) {
			boolean breakline = false;
			char c = inputText.charAt(charCount);
			charCount++;
			if (charCount==inputText.length()) {
				breakline = true;	
			}
			if (c=='\n') {
				breakline = true;
				
			} else {
				if (fm.stringWidth(line+c)<getWidth()) {
					line += c;	
				} else {
					breakline = true;
				}
			}
			if (breakline) {
				text.add(line);
				lineCount++;
				if (lineCount==noOfLines) {
					break chLoop;	
				}
				line = ""+c;	
			}
		}
	}
	
	public void paintIn(Graphics g, GRectangle frame) {
		g.setFont(font);
		if (fm==null) { 
			fm = g.getFontMetrics();
			setText(inputText);
		}
		g.setColor(fontColor);
		int lineHeight = fm.getMaxAscent() + 2;
		for (int j=0; j<text.size(); j++) {
			g.drawString((String) text.get(j), 
			frame.getX()+getX(), frame.getY()+getY()+(j+1)*lineHeight);		
		}
		
		
		
	}
	
}
