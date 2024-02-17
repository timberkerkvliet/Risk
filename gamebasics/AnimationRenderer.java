package gamebasics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;

public class AnimationRenderer extends Thread {
	
	private ArrayList list;
	private GPanel mother;
	
	
	public AnimationRenderer(GPanel mother) {
		super();
		setMother(mother);
		list = new ArrayList();
	}
	
	public void addAnimation(GAnimation gAnim) {
		if (!list.contains(gAnim)) {
			list.add(gAnim);
		}
	}
	
	public void removeAnimation(GAnimation gAnim) {
		int index = list.indexOf(gAnim);
		if (index>=0) {
			list.remove(index);
		}
	}
	
	public void setMother(GPanel gp) {
		mother = gp;	
	}
	
	public GPanel getMother() {
		return mother;	
	}
	
	public void requestRepaint() {
		if (getMother()!=null) {
			getMother().repaint();	
		}
	}
	
	public void run() {
		long startRender=System.currentTimeMillis();
		long t;
		long n = 0;
		while (!list.isEmpty()) {
			t = System.currentTimeMillis()-startRender;
			long m = t/40 + 1;
			
			// set ready for next frame
			for (int k=0; k<(m-n); k++) {
				for (int j=0; j<list.size(); j++) {
					GAnimation gAnim = (GAnimation) list.get(j);	
					gAnim.goToNextFrame();
					gAnim.upFrame();
				}
			}
			
			t = System.currentTimeMillis()-startRender;
		
			try {
				sleep(Math.max(0,m*40 - t));;
			} catch(InterruptedException ie){ }
			
			requestRepaint();
			n=m;
			
		}
	}



}
