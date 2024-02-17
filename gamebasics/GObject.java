package gamebasics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;

public abstract class GObject implements Comparable {
	
	private GRectangle rect;
	private int layer;
	
	private GDiv mother;
	
	private boolean autoPaint;
	private boolean isActive;
	
	public GObject(GRectangle rect, int layer) {
		this.rect=rect;
		this.layer=layer;
		mother = null;
		autoPaint = false;
		isActive = true;
	}
	
	public void setActive(boolean b) {
		isActive = b;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setAutoPaint(boolean p) {
		autoPaint = p;
	}
	
	public boolean getAutoPaint() {
		return autoPaint;	
	}
	
	public void setMother(GDiv gp) {
		mother = gp;	
	}
	
	public GDiv getMother() {
		return mother;	
	}
	
	public GRectangle getRect() {
		return rect;	
	}
	
	public int getX() {
		return rect.getX();	
	}
	
	public int getY() {
		return rect.getY();	
	}
	
	public int getWidth() {
		return rect.getWidth();	
	}
	
	public int getHeight() {
		return rect.getHeight();	
	}
	
	public void setLocation(int x, int y) {
		rect.setLocation(x,y);
		requestRepaint();
	}
	
	public void setSize(int w, int h) {
		rect.setSize(w,h);
		requestRepaint();
	}
	
	public void move(int x, int y) {
		setLocation(getX()+x,getY()+y);	
	}
	
	public int compareTo(Object obj) throws ClassCastException {
		if (obj==null) {
			return 1;	
		}
		GObject gObj = (GObject) obj;
		return this.layer-gObj.layer;
	}
		
	public void requestRepaint() {
		if (getMother()!=null) {
			getMother().requestRepaint();	
		}
	}
	
	public abstract void paintIn(Graphics g, GRectangle rect);
	
	
	public void mouseNow(int x, int y) {
		// by default do nothing
	}
	
	public void mouseClick(int x, int y) {
		// by default do nothing
	}
	
	public void mousePress(int x, int y) {
		// by default do nothing
	}
	
	public void mouseDrag(int x, int y) {
		// by default do nothing
	}
	
	public void mouseRelease(int x, int y) {
		// by default do nothing
	}
	
	public void keyPress(KeyEvent e) {
		// by default do nothing
	}
	
	public void keyRelease(KeyEvent e) {
		// by default do nothing
	}
	
	public void keyType(KeyEvent e) {
		// by default do nothing
	}
	
}
