package gamebasics;

import java.io.*;
import java.net.*;

public class ServerRemote {
	
	private String nick;
	
	private int lastNo;
	
	public ServerRemote(String nick) {
		this.nick = nick;
		lastNo = 0;
	}
	
	public String getNick() {
		return nick;	
	}
	
	public boolean hasReceived(int n) {
		return n<=lastNo;
	}
	
	public int getLastNo() {
		return lastNo;
	}
	
	public void setLastNo(int n) {
		lastNo = n;
	}

}
