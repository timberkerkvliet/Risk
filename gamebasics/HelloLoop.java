package gamebasics;

public class HelloLoop extends Thread {

	Client client;
	
	HelloLoop(Client client) {
		this.client = client;
		start();
	}
	
	public void run() {
		while (true) {
			client.sayHello();
			Lib.wait(3000);
		}
	}

}
