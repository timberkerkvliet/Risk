package gamebasics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class GDiv extends GObject {
	
	private Lijst<GObject> container;
	
	private GPanel topMother;
	
	public GDiv(GRectangle rect, int layer) {
		super(rect,layer);
		container = new Lijst<GObject>();
		topMother = null;
	}
	
	public void setTopMother(GPanel panel) {
		topMother = panel;	
	}
	
	public void addObject(GObject gObj) {
		container.insert(gObj);
		gObj.setMother(this);
		if (gObj.getAutoPaint()) {
			requestRepaint();
		}
	}
	
	public void removeObject(GObject gObj) {
		if (container.find(gObj)) {
			container.remove();
			if (gObj.getAutoPaint()) {
				requestRepaint();
			}
		}
	}
	
	public void mouseNow(int x, int y) {
		if (container.setFirst()) {
			do {	
				GObject obj = container.retrieve();
				if (obj.isActive()) {
					obj.mouseNow(x-getX(), y-getY() );
				}
			} while (container.getNext());
		}
	}
	
	public void mouseClick(int x, int y) {
		if (container.setFirst()) {
			do {	
				GObject obj = container.retrieve();
				if (obj.isActive()) {
					obj.mouseClick(x-getX(), y-getY() );
				}
			} while (container.getNext());
		}
	}
	
	public void mousePress(int x, int y) {
		if (container.setFirst()) {
			do {	
				GObject obj = container.retrieve();
				if (obj.isActive()) {
					obj.mousePress(x-getX(), y-getY() );
				}
			} while (container.getNext());
		}
	}
	
	public void mouseDrag(int x, int y) {
		if (container.setFirst()) {
			do {	
				GObject obj = container.retrieve();
				if (obj.isActive()) {
					obj.mouseDrag(x-getX(), y-getY() );
				}
			} while (container.getNext());
		}
	}
	
	public void mouseRelease(int x, int y) {
		if (container.setFirst()) {
			do {	
				GObject obj = container.retrieve();
				if (obj.isActive()) {
					obj.mouseRelease(x-getX(), y-getY() );
				}
			} while (container.getNext());
		}
	}
	
	public void keyPress(KeyEvent e) {
		if (container.setFirst()) {
			do {	
				GObject obj = container.retrieve();
				if (obj.isActive()) {
					obj.keyPress(e);
				}
			} while (container.getNext());
		}
	}
	
	public void keyRelease(KeyEvent e) {
		if (container.setFirst()) {
			do {	
				GObject obj = container.retrieve();
				if (obj.isActive()) {
					obj.keyRelease(e);
				}
			} while (container.getNext());
		}
	}
	
	public void keyType(KeyEvent e) {
		if (container.setFirst()) {
			do {	
				GObject obj = container.retrieve();
				if (obj.isActive()) {
					obj.keyType(e);
				}
			} while (container.getNext());
		}
	}
	
	public void requestRepaint() {
		if (topMother!=null) {
			topMother.repaint();	
		} else {
			if (getMother()!=null) {
				getMother().requestRepaint();	
			}
		}
	}
	
	public void startAnimation(GAnimation gAnim) {
		if (topMother!=null) {
			topMother.startAnimation(gAnim);	
		} else {
			if (getMother()!=null) {
				getMother().startAnimation(gAnim);	
			}
		}
	}
	
	public void stopAnimation(GAnimation gAnim) {
		if (topMother!=null) {
			topMother.stopAnimation(gAnim);	
		} else {
			if (getMother()!=null) {
				getMother().stopAnimation(gAnim);	
			}
		}
	}
	
	public void paintIn(Graphics g, GRectangle frame) {
		if (container.setFirst() && frame.intersects(getRect())) {
			GRectangle newRect = frame.intersection(getRect());
			do {	
				container.retrieve().paintIn(g, newRect);
			} while (container.getNext());
		}
	}
	
}
