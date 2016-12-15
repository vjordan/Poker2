package reseau.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MySocketClient {

	public static void main(String[] args) {
		Socket socket;
		PrintWriter writer = null;
		BufferedReader reader = null;
		String response;
	    
		try {
			socket = new Socket("localhost", 2009);

			writer = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			writer.println("teste");
			writer.flush();
					
			if ((response = reader.readLine()) != null) {
				System.out.println("Reveived: " + response);
			}

			writer.close();
			socket.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
