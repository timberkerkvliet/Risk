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

public class GTextInput extends GAnimation {
	
	/*
	warning: does not respect div size of the div it is in
	
	*/
	
	private Font font;
	private Color fontColor;
	private Color bgColor;
	private Color borderColor;
	private String text;
	private FontMetrics fm;
	
	private boolean cursorOn;
	private boolean ctrlOn;
	
	private int maxLength;
	
	public GTextInput(GRectangle rect, int layer, Font font, Color fontColor, Color bgColor, Color borderColor, int maxlength) {
		super(rect,layer);
		text="";
		this.font=font;
		this.fontColor=fontColor;
		this.bgColor=bgColor;
		this.borderColor=borderColor;
		this.maxLength=maxlength;
		fm=null;
		cursorOn=true;
		ctrlOn=false;
	}
	
	public String getText() {
		return text;	
	}
	
	public void goToNextFrame() {
		if (getCurrentFrame()%37==0) {
			if (cursorOn) {
				cursorOn=false;	
			} else {
				cursorOn=true;	
			}
			
		}
	}
	
	public void keyPress(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
			if (text.length()>0) {
				text = text.substring(0,text.length()-1);
			}
		} else if (e.getKeyCode()==KeyEvent.VK_CONTROL) {
			ctrlOn=true;	
		} else if (ctrlOn && e.getKeyCode()==KeyEvent.VK_V) {
			String p = getClipboardContents();
			for (int j=0; j< p.length(); j++) {
				char c = p.charAt(j);
				if (font.canDisplay(c) && text.length()<maxLength) {
					text+= (char) c;
				}		
			}
		} else {
			char c = e.getKeyChar();
			if (font.canDisplay(c) && text.length()<maxLength) {
				text+= (char) c;
			}
		}
		requestRepaint();
	}
	
	public void keyRelease(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_CONTROL) {
			ctrlOn=false;	
		}	
	}
	
	public String getClipboardContents() {
	    String result = "";
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    Transferable contents = clipboard.getContents(null);
	    boolean hasTransferableText =
	      (contents != null) &&
	      contents.isDataFlavorSupported(DataFlavor.stringFlavor);
	    if ( hasTransferableText ) {
	      try {
		result = (String)contents.getTransferData(DataFlavor.stringFlavor);
	      } catch (UnsupportedFlavorException ex){ }
	      catch (IOException ex) { }
	    }
	    return result;
	  }
	
	public void paintIn(Graphics g, GRectangle frame) {
		g.setFont(font);
		if (fm==null) { 
			fm = g.getFontMetrics();
			setSize(getWidth(),fm.getMaxAscent()+fm.getMaxDescent()+4 );
		}
		
		g.setColor(bgColor);
		g.fillRect( frame.getX()+getX()+0, frame.getY()+getY()+0, getWidth(), getHeight() );
		g.setColor(borderColor);
		g.drawRect( frame.getX()+getX()+0, frame.getY()+getY()+0, getWidth(), getHeight() );
		g.setColor(fontColor);
		
		String display = new String(text);
		while (fm.stringWidth(display)>getWidth()-11 ) {
			display = display.substring(1,display.length());	
		}
		
		if (cursorOn) {
			display += "|";
		}
		
		g.drawString(display, 
			5+frame.getX()+getX(), frame.getY()+getY()+fm.getMaxAscent()+2);
	}
	
}
