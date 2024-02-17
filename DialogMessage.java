import gamebasics.*;
import java.util.*;
import java.net.*;
import java.awt.*;

class DialogMessage extends GDiv {
	
	private URL BACK = RiskGame.class.getResource("data/dialog2.gif");
	
	private GImage back;
	
	private Font font;
	
	private GText text;
	private GSimpleButton but;
	
	private String input;
	private boolean end;
	
	DialogMessage(int layer, String s, Font font) {
		super(new GRectangle(352,277,405,231), layer);
		back = new GImage(new GRectangle(0,0,0,0), 0, BACK);
		addObject(back);
		
		this.font = font;
		
		text = new GText(new GRectangle(20,20,370,100), 1, font, Color.BLACK);
		text.setText(s);
		addObject(text);
		
		but = new GSimpleButton(new GRectangle(20,90,130,20), 1, font, Color.BLACK, new Color(197,210,228), Color.BLACK,
			Color.BLACK, Color.WHITE, Color.BLACK, "OK");
		addObject(but);
		
		input = "";
		end = false;
	}
		
	public boolean isClicked() {
		return but.isClicked();	
	}
	
}
