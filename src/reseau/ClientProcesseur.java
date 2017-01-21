package reseau;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import partie.Joueur;
import partie.Table;

public class ClientProcesseur implements Runnable{

	   private Socket sock;
	   private PrintWriter writer = null;
	   private BufferedInputStream reader = null;
	   protected String action;
	   
	   private Table table;
	   private Serveur serveur;
	   
	   public ClientProcesseur(Socket pSock, Table table, Serveur serveur){
	      sock = pSock;
	      this.table = table;
	      this.serveur = serveur;
	   }
	   
	   //Le traitement est lancé dans un thread séparé
	   public void run(){
	      boolean closeConnexion = false;
	      //tant que la connexion est active, on traite les demandes
	      	while(true) {        
		         try {
		            writer = new PrintWriter(sock.getOutputStream());
		            reader = new BufferedInputStream(sock.getInputStream());
		            
		            //On attend la demande du client            
		            String response = read();         
		            //On traite la demande du client en fonction de la commande envoyée
		            String toSend = "";
		            String res = "";
		            
		            if (response.equals("connexion")) {
		                toSend = "nom";
		            }
		            else if (response.startsWith("prénom::")){
		            	res = response.split("::")[1];
		            	Joueur j = new Joueur(res);
		            	table.addPlayer(j);
		            	serveur.map.put(j, this);
		            }
		            else if (response.startsWith("debut::")) {
		            	  toSend = response;
		            }
		            else if (response.startsWith("joueurs::")) {
		            	  toSend = response;
		            }
		            else if (response.startsWith("dealer::")) {
		            	toSend = response;
		            }
		            else if (response.startsWith("blinds::")) {
		            	toSend = response;
		            }
		            else if (response.startsWith("distribution::")) {
		            	toSend = response;
		            }
		            else if (response.startsWith("flop::")) {
		            	toSend = response;
		            }
		            else if (response.startsWith("turn::")) {
		            	toSend = response;
		            }
		            else if (response.startsWith("river::")) {
		            	toSend = response;
		            }
		            else if (response.startsWith("vainqueur::")) {
		            	toSend = response;
		            }
		            else if (response.startsWith("éliminé::")) {
		            	toSend = response;
		            }
		            else if (response.startsWith("fin::")) {
		            	toSend = response;
		            }
		            else if (response.startsWith("état::")) {
		            	toSend = response;
		            }
		            
		            else if (response.startsWith("joueurActuel::")) {
		            	toSend = response;
		            }
		            else if (response.startsWith("choix::")) {
		            	toSend = response;
		            }
		            else if (response.startsWith("choisi::")) {
		            	res = response.split("::")[1];
		            	action = res;
		            }
		            
		            send(toSend);
		            
		            if(closeConnexion){
		               writer = null;
		               reader = null;
		               sock.close();
		               break;
		            }
		         }catch(SocketException e){
		            break;
		         } catch (IOException e) {
		            e.printStackTrace();
		         }         
		      }
	      }
	   
	   public void setAction(String action) {
		this.action = action;
	}

	//La méthode que nous utilisons pour lire les réponses
	   protected String read() throws IOException{      
	      String response = "";
	      int stream;
	      byte[] b = new byte[4096];
	      stream = reader.read(b);
	      response = new String(b, 0, stream);
	      return response;
	   }
	   
	   public void send(String s){
		 //On envoie la réponse au client
           writer.write("#" + s);
           //Il faut utiliser flush() pour transmettre les données au client
           writer.flush();
	   }
}
