package gamebasics;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread {
	
	private String status;
	private int statusNo;
	
	private ArrayList remotes;
	private ServerSocket ss;
	
	private long lastHello;
	
	private int lastStatusNo;
	
	public Server(int port) throws IOException {
		ss = new ServerSocket(port);
		status = "initialStatus";
		statusNo = 0;
		lastStatusNo = 0;
		remotes = new ArrayList();
		lastHello = System.currentTimeMillis();
	}
	
	public String[] notUpToDate() {
		ArrayList list = new ArrayList();
		for (int j=0; j<remotes.size(); j++) {
			ServerRemote r = (ServerRemote) remotes.get(j);
			if (r.getLastNo()!=statusNo) {
				list.add(r.getNick());	
			}
		}
		return (String[]) list.toArray();
	}
	
	public void sayHello() {
		lastHello = System.currentTimeMillis();	
	}
	
	public long sinceLastHello() {
		long now = System.currentTimeMillis();
		return now-lastHello;
	}
	
	public String waitForUpdate() {
		while (lastStatusNo==statusNo) {
			if (sinceLastHello() > 10000) {
				return null;
			}
			Lib.wait(100);	
		}
		return readStatus();
	}
	
	public boolean waitForUpToDate() {
		int n=100;
		while (!everyoneUpToDate()) {
			Lib.wait(100);
			n--;
			if (n==0) {
				return false;	
			}
		}
		return true;
	}
	
	public boolean everyoneUpToDate() {
		if (lastStatusNo!=statusNo) { return false; }
		for (int j=0; j<remotes.size(); j++) {
			ServerRemote r = (ServerRemote) remotes.get(j);
			if (r.getLastNo()!=statusNo) {
				return false;	
			}
		}
		
		return true;
	}
	
	public ServerRemote findRemote(String nick) {
		for (int j=0; j<remotes.size(); j++) {
			ServerRemote r = (ServerRemote) remotes.get(j);
			if (r.getNick().equals(nick)) {
				return r;	
			}
		}
		return null;
	}
	
	public boolean addRemote(String nick) {
		ServerRemote n = new ServerRemote(nick);
		if (!contains(nick)) {
			remotes.add(n);
			return true;
		}
		return false;
	}
	
	public void removeRemote(String nick) {
		ServerRemote n = findRemote(nick);
		if (n!=null) {
			remotes.remove(n);	
		}
	}
	
	public boolean contains(String nick) {
		for (int j=0; j<remotes.size(); j++) {
			ServerRemote r = (ServerRemote) remotes.get(j);
			if (r.getNick().equals(nick)) {
				return true;	
			}
		}
		return false;
	}
	
	public int getStatusNo() {
		return statusNo;
	}
	
	public String readStatus() {
		lastStatusNo=statusNo;
		return status;
	}
	
	public String getStatus() {
		return status;		
	}
	
	public void setStatus(String s) {
		status = s;
		statusNo++;
	}
	
	public void run() {
		while (true) {
			Socket socket = null;
			try {
				socket = ss.accept();
			} catch(IOException e) { }
			if (socket!=null) {
				new ServerAction(socket,this);
			}
		}
	}
	
}
