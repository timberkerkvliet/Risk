import gamebasics.*;
import java.util.*;
import java.net.*;
import java.awt.*;

class DialogInput extends GDiv {
	
	private URL BACK = RiskGame.class.getResource("data/dialog1.gif");
	
	private GImage back;
	
	private Font font;
	
	private GText text;
	private GTextInput tinput;
	private GSimpleButton but;
	
	private String input;
	private boolean end;
	
	DialogInput(int layer, String s, Font font) {
		super(new GRectangle(352,223,405,231), layer);
		back = new GImage(new GRectangle(0,0,0,0), 0, BACK);
		addObject(back);
		
		this.font = font;
		
		text = new GText(new GRectangle(20,20,370,100), 1, font, Color.BLACK);
		text.setText(s);
		addObject(text);
		
		tinput = new GTextInput(new GRectangle(20,70,200,25),1,
			font, Color.BLACK, Color.WHITE, Color.BLACK, 40);
		addObject(tinput);
		
		
		but = new GSimpleButton(new GRectangle(20,120,130,20), 1, font, Color.BLACK, new Color(197,210,228), Color.BLACK,
			Color.BLACK, Color.WHITE, Color.BLACK, "OK");
		addObject(but);
		
		input = "";
		end = false;
	}
		
	String getInput() {
		if (!but.isClicked()) {
			return null;
		} 
		return tinput.getText();
	}
	
	public void start() {
		tinput.start();	
	}
	
	public void stop() {
		tinput.stop();	
	}
}
