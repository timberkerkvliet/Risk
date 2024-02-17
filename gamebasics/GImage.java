package gamebasics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;

public class GImage extends GObject {
	
	private Image img;
	
	public GImage(GRectangle rect, int layer, Image img) {
		super(rect,layer);
		this.img=img;
		automaticallySetSize();
	}
	
	public GImage(GRectangle rect, int layer, String fileName) {
		super(rect,layer);
		img = null;
		try {
		    img = ImageIO.read(new File(fileName));
		} catch (IOException e) { }
		automaticallySetSize();
	}
	
	public GImage(GRectangle rect, int layer, URL url) {
		super(rect,layer);
		img = null;
		try {
		    img = ImageIO.read(url);
		} catch (IOException e) { }
		automaticallySetSize();
	}
	
	public void automaticallySetSize() {
		setSize(img.getWidth(null),img.getHeight(null));
	}
	
	public void paintIn(Graphics g, GRectangle frame) {
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
