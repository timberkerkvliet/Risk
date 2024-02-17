package gamebasics;

import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class GPanel extends JPanel  implements MouseListener, MouseMotionListener {

	private int dimH, dimW;
	private VolatileImage mImage;
	private GDiv div;
	  
	private AnimationRenderer animRen;
	  
	public GPanel(int dimW, int dimH) {
		this.dimW = dimW;
		this.dimH = dimH;
		div = new GDiv(new GRectangle(0,0,dimW,dimH),1);
		div.setTopMother(this);
		animRen = null;
		setVisible(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		mImage = null;
	}
	  
	public void startAnimation(GAnimation gAnim) {
		if (animRen!=null && animRen.isAlive()) {
			animRen.addAnimation(gAnim);
		} else {
			animRen = new AnimationRenderer(this);
			animRen.addAnimation(gAnim);
			animRen.start();
		}
	}
	
	public void stopAnimation(GAnimation gAnim) {
		if (animRen!=null && animRen.isAlive()) {
			animRen.removeAnimation(gAnim);
		}	
	}
  
	public GDiv getDiv() {
		return div;	  
	}
  
	public void update(Graphics g) {
		 paint(g);
	}
	
	public void paint(Graphics g) {
		checkOffscreenImage();
		paintOffscreen();
		g.drawImage(mImage, 0, 0, null);
	}

	private void checkOffscreenImage() {
		Dimension d = getSize();
		if (mImage == null || mImage.getWidth(null) != dimW
		|| mImage.getHeight(null) != dimH) {
			mImage = createVolatileImage(dimW, dimH);
	      	}
	}
  
	public void paintOffscreen() {
		div.paintIn(mImage.getGraphics(), new GRectangle(0,0,dimW,dimH));
	}
  
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		getDiv().mousePress(x,y);
	}
	
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		getDiv().mouseRelease(x,y);
	}
	
	public void mouseEntered(MouseEvent e) {
	       
	}
	
	public void mouseExited(MouseEvent e) {
	       
	}
	
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		getDiv().mouseClick(x,y);
	}
	    
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		getDiv().mouseNow(x,y);
	}

	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		getDiv().mouseDrag(x,y);
	}
	
}

