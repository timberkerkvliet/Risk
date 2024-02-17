package gamebasics;

public class Lib {

	public static void wait(int ms) {
		try{
		  Thread.currentThread().sleep(ms);
		}
		catch(InterruptedException ie){ }
	}
	
	public static int si(double d) {
		return (int) Math.round(d);
	}
	
}
