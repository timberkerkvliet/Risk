import gamebasics.*;
import java.util.*;
import java.net.*;


class DialogStart extends GDiv {
	
	private URL BACK = RiskGame.class.getResource("data/dialog1.gif");
	
	private GImage back;
	
	DialogStart(int layer) {
		super(new GRectangle(100,100,405,231), layer);
		back = new GImage(new GRectangle(0,0,0,0), 0, BACK);
		addObject(back);
	}
		
	
	
	
	
}
