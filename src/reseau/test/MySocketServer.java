package reseau.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocketServer {
	private int port = 2009;
	private String host = "127.0.0.1";
	private ServerSocket server = null;
  	private volatile Thread t;
	
    public MySocketServer() {
		try {
			server = new ServerSocket(port, 100, InetAddress.getByName(host));
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	// On lance notre serveur
	public void open() {

		// Toujours dans un thread à part vu qu'il est dans une boucle infinie
		t = new Thread(new Runnable() {			
			public void run() {
				while (true) {
					try {
						// On attend une connexion d'un client
						Socket client = server.accept();

						// Une fois reçue, on la traite dans un thread séparé
						System.out.println("Client connection received.");
						Thread t = new Thread(new ClientProcessor(client));
						t.start();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}		
		});

		t.start();	
	}

	public void close() {
		try {
			t.stop();
			server.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			server = null;
		}
	}

	public static void main(String[] args) {

		MySocketServer s = new MySocketServer();
		s.open();
		System.out.println("Server initialized.");
		try {
			System.out.println("Type any key to terminate it.");
			System.in.read();
			s.close();
			System.out.println("Server has terminated.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
