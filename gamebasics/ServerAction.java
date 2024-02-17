package gamebasics;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerAction extends Thread {
	
	private Socket s;
	private Server server;
	
	public ServerAction(Socket s, Server server) {
		this.s = s;
		this.server=server;
		start();
	}
	
	private String getRequest() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream() ));
		} catch(IOException ie) { }
		if (in==null) { return null; }
		String mes = null;
		
		try {
			mes =  in.readLine();
		} catch(IOException ie) { }
		
		return mes;
	}
	
	private boolean writeResponse(String line) {
		try {
			PrintStream os = new PrintStream(s.getOutputStream());
			os.println(line);
			return true;
		} catch(IOException e) { }
		return false;
	}
	
	private void join(String nick) {
		if (server.contains(nick)) {
			writeResponse("joinNotOk");	
		} else {
			server.addRemote(nick);
			writeResponse("joinOk");
		}
	}
	
	private void unjoin(String nick) {
		server.removeRemote(nick);
		writeResponse("unjoinOk");
	}
	
	private void getStatus(String nick) {
		ServerRemote r = server.findRemote(nick);
		int no = server.getStatusNo();
		if (writeResponse(server.getStatus())) {
			if (r!=null) {
				r.setLastNo(no);
			} 
		}
	}
	
	private void setStatus(String str) {
		if (!server.everyoneUpToDate()) {
			writeResponse("notAllowedNow");
			return;
		}
		server.setStatus(str);
		writeResponse("statusOk");
	}
	
	private void setStatusWithoutCheck(String str) {
		server.setStatus(str);
		writeResponse("statusOk");
	}
	
	private void waitForUpToDate() {
		while (!server.everyoneUpToDate()) {
			Lib.wait(100);	
		}
		writeResponse("everyoneUpToDate");
	}
	
	private void waitForUpdate(String nick) {
		ServerRemote r = server.findRemote(nick);
		if (r!=null) {
			int last = r.getLastNo();
			while (server.getStatusNo() == last) {
				Lib.wait(100);	
			}
			getStatus(nick);
		}
	}	
	
	public void run() {
		String mes = getRequest();
		if (mes==null || mes.length()<4) { return; }
		String mesA = mes.substring(0,4);
		String mesB = mes.substring(4);
		if (mesA.equals("join")) {
			join(mesB);	
		} else if (mesA.equals("gsta")) {
			getStatus(mesB);
		} else if (mesA.equals("wsta")) {
			waitForUpdate(mesB);	
		} else if (mesA.equals("ssta")) {
			setStatus(mesB);	
		} else if (mesA.equals("sswc")) {
			setStatusWithoutCheck(mesB);	
		} else if (mesA.equals("wait")) {
			waitForUpToDate();	
		} else if (mesA.equals("unjo")) {
			unjoin(mesB);	
		} else if (mesA.equals("echo")) {
			writeResponse("echo");	
		} else if (mesA.equals("hllo")) {
			server.sayHello();	
		}
		try { s.close(); } catch(IOException ie) { }
	}
	
}
