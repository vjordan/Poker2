package reseau.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientProcessor implements Runnable {

	   private Socket socket;   
	   private PrintWriter writer = null;
	   private BufferedReader reader = null;
	   		
	   public ClientProcessor(Socket s){
			socket = s;
	   }
		
	   public void run() {
		      System.out.println("Starting Client Connection....");

		      try {
		    	  writer = new PrintWriter(socket.getOutputStream());
		    	  reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		       
		          String msg;
		      
		          while ((msg=reader.readLine())!=null) {       
		                InetSocketAddress remote = (InetSocketAddress)socket.getRemoteSocketAddress();
			            System.out.println("Msg received from: " + socket + " from " + remote.getAddress().getHostAddress());
			            System.out.println(msg);
			            
			            //On envoie la réponse au client
			            writer.println(msg.toUpperCase());
			            
			            //Il FAUT IMPERATIVEMENT UTILISER flush()
			            //Sinon les données ne seront pas transmises au client
			            //et il attendra indéfiniment
			            writer.flush(); 
		          } 
		      } 
		      catch (IOException  e) {
		          e.printStackTrace();
		      } 
	   }
}
