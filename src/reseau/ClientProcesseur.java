package reseau;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientProcesseur implements Runnable{

	private Socket sock;
	   private PrintWriter writer = null;
	   private BufferedInputStream reader = null;
	   
	   public ClientProcesseur(Socket pSock){
	      sock = pSock;
	   }
	   
	   //Le traitement est lanc� dans un thread s�par�
	   public void run(){
	      boolean closeConnexion = false;
	      //tant que la connexion est active, on traite les demandes
	      while(!sock.isClosed()){
	         
	         try {
	            writer = new PrintWriter(sock.getOutputStream());
	            reader = new BufferedInputStream(sock.getInputStream());
	            
	            //On attend la demande du client            
	            String response = read();
	            
	            String debug = "";
	            debug = "Thread : " + Thread.currentThread().getName();
	            debug += " -> " + response + "\n";
	            System.err.println("\n" + debug);
	            
	            //On traite la demande du client en fonction de la commande envoy�e
	            String toSend = "";
	            
	            switch(response.toUpperCase()){
	               case "TEST_ENTREE":
	                  toSend = "TEST_SORTIE";
	                  break;
	               case "CLOSE":
	                  closeConnexion = true;
	                  break;
	               default : 
	                  toSend = "Commande inconnu !";                     
	                  break;
	            }
	            
	            //On envoie la r�ponse au client
	            writer.write(toSend);
	            //Il faut utiliser flush() pour transmettre les donn�es au client
	            writer.flush();
	            
	            if(closeConnexion){
	               writer = null;
	               reader = null;
	               sock.close();
	               break;
	            }
	         }catch(SocketException e){
	            System.err.println("La connexion a �t� interrompue ! ");
	            break;
	         } catch (IOException e) {
	            e.printStackTrace();
	         }         
	      }
	   }
	   
	   //La m�thode que nous utilisons pour lire les r�ponses
	   private String read() throws IOException{      
	      String response = "";
	      int stream;
	      byte[] b = new byte[4096];
	      stream = reader.read(b);
	      response = new String(b, 0, stream);
	      return response;
	   }
}
