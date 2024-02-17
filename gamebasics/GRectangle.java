package gamebasics;

public class GRectangle {
	
	private java.awt.Rectangle awtRect;
	
	public GRectangle(java.awt.Rectangle r) {
		awtRect = r;	
	}
	
	public GRectangle(GRectangle r) {
		awtRect = new java.awt.Rectangle(r.awtRect);	
	}
	
	public GRectangle(int x, int y, int w, int h) {
		awtRect = new java.awt.Rectangle(x,y,w,h);
	}
	
	public GRectangle intersection(GRectangle r) {
		return new GRectangle(awtRect.intersection(r.awtRect));	
	}
	
	public void setLocation(int x, int y) {
		awtRect.setLocation(x,y);	
	}
	
	public void setSize(int w, int h) {
		awtRect.setSize(w,h);	
	}
	
	public boolean contains(int x, int y) {
		return awtRect.contains(x,y);	
	}
	
	public boolean intersects(GRectangle r) {
		return awtRect.intersects(r.awtRect);	
	}
	
	public int getWidth() {
		return (int) Math.round(awtRect.getWidth());	
	}
	
	public int getHeight() {
		return (int) Math.round(awtRect.getHeight());	
	}
	
	public int getX() {
		return (int) Math.round(awtRect.getX());	
	}
	
	public int getY() {
		return (int) Math.round(awtRect.getY());	
	}

}
