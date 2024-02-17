package gamebasics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;

public abstract class GAnimation extends GObject {
	
	private int currentFrame;
	private boolean running;
	
	public GAnimation(GRectangle rect, int layer) {
		super(rect,layer);
		currentFrame=0;
		running=false;
	}
	
	public int getCurrentFrame() {
		return currentFrame;	
	}
	
	public void upFrame() {
		currentFrame++;	
	}
	
	public void start() {
		running = true;
		getMother().startAnimation(this);
	}
	
	public void stop() {
		running = false;
		getMother().stopAnimation(this);
	}
	
	public void init() {
		stop();
		currentFrame = 0;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public abstract void goToNextFrame();
		
}
