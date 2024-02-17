package gamebasics;

import java.io.*;
import java.net.*;

public class Client {
	
	private String ip;
	private int port;
	
	private int n;
	
	private String nick;
	
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
		nick = null;
		n=-1;
	}
	
	public void setNumberOfAttempts(int n) {
		this.n = n;	
	}
	
	private String request(String req) throws UnknownHostException, IOException {
		Socket s = new Socket(ip,port);
		PrintStream ps = new PrintStream(s.getOutputStream());
            	ps.println(req);
            	BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream() ));
            	return in.readLine();
	}
	
	private String doRequest(String req, int n) {
		String resp = null;
		try {
			resp = request(req);
		} catch(UnknownHostException e) { }
		catch(IOException e) { }
		if (resp==null) {
			if (n>0) {
				Lib.wait(500);
				doRequest(req,n-1);
			}
			if (n<0) {
				Lib.wait(500);
				doRequest(req,n);	
			}
		}
		return resp;
	}
	
	public boolean serverTest() {
		String resp = null;
		try {
			resp = request("echo");
		} catch (UnknownHostException e) { } catch(IOException e) { }
		return (resp!=null && resp.equals("echo"));
	}
	
	public String join(String nick) {
		this.nick = nick;
		return doRequest("join"+nick,n);
	}	
	
	public String unJoin() {
		return doRequest("unjo"+nick,n);	
	}
	
	public String setStatus(String s) {
		return doRequest("ssta"+s,n);	
	}
	
	public String setStatusWithoutCheck(String s) {
		return doRequest("sswc"+s,n);	
	}
	
	public String waitForUpdate() {
		return doRequest("wsta"+nick,n);	
	}
	
	public String getStatus() {
		return doRequest("gsta"+nick,n);
	}
	
	public String waitForUpToDate() {
		return doRequest("wait",n);	
	}
	
	public String sayHello() {
		return doRequest("hllo",n);	
	}
	
}
