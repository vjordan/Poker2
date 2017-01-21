package reseau;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import cartes.Carte;
import partie.Joueur;
import partie.Table;

public class Serveur {
	
	protected HashMap<Joueur, ClientProcesseur> map = new HashMap<Joueur, ClientProcesseur>();

	//On initialise des valeurs par défaut
	   private int port = 1234;
	   private String host = "127.0.0.1";
	   private ServerSocket server = null;
	   private boolean isRunning = true;
	   
	   private Table table = new Table();
	   
	   public Serveur(String pHost, int pPort){
	      host = pHost;
	      port = pPort;
	      try {
	         server = new ServerSocket(port, 10, InetAddress.getByName(host));
	      } catch (UnknownHostException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	   
	   
	   //On lance notre serveur
	   public void open(){
	      
	      //Toujours dans un thread à part vu qu'il est dans une boucle infinie
	      Thread t = new Thread(new Runnable(){
	         public void run(){
	            while(isRunning == true){ 
	               try {
	                  //On attend une connexion d'un client
	                  Socket client = server.accept();
	                  if (table.getJoueurs().size() > 0)
	                	  Serveur.this.server.setSoTimeout(10000);
	                  //Une fois reçue, on la traite dans un thread séparé                 
	                  Thread t = new Thread(new ClientProcesseur(client,table,Serveur.this));
	                  t.start();                  
	               } catch (SocketTimeoutException e) {
	                  break;
	               } catch (IOException  e) {
	            	   e.printStackTrace();
	               }
	               
	            }
	            try {
					partie();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            try {
	               server.close();
	            } catch (IOException e) {
	               e.printStackTrace();
	               server = null;
	            }
	         }
	      });      
	      t.start();
	   }
	   
	   public void partie() throws InterruptedException, IOException{
		   String res = table.start();
		   envoi(res);
		   while(!isFinished()){
				jouerTour();
			}
	   }
	   
	   public boolean isFinished(){
			return !(table.getJoueurs().size() >= 2);
		}
	   
	   public void jouerTour() throws InterruptedException, IOException{
		   String res;
		   
			res = "debut::----------------debut du tour----------------";
			envoi(res);
			
			for(Joueur j : table.getJoueurs())
				j.resetCards();
			
			table.getPaquet();
			
			res = table.updateDealer();
			envoi(res);
			
			table.melanger();
			
			res = table.blinds();
			envoi(res);
			
			distribuer();

			mises(true);

			res = table.flop();
			envoi(res);

			mises(false);

			res = table.turn();
			envoi(res);

			mises(false);

			res = table.river();
			envoi(res);

			mises(false);

			finirTour();
			
			res = "fin::----------------fin du tour----------------";
			envoi(res);
			
			res = table.toString();
			envoi(res);
		}
	   
	   public void distribuer() throws InterruptedException{
		   ClientProcesseur cp;
		   
			for(int i = 0; i < 2; i++){
				int indexDealer = table.joueurs.indexOf(table.currentDealer);
				int indexJoueurAdistribuer;
				for(int j = 0; j < table.joueurs.size(); j++){
					indexJoueurAdistribuer = indexDealer  + j;
					if(indexJoueurAdistribuer >= table.joueurs.size())
						indexJoueurAdistribuer = indexJoueurAdistribuer - table.joueurs.size();
					
					Joueur receveur = table.joueurs.get(indexJoueurAdistribuer);
					
					Carte aDistribuer = table.prendreCarte();
					receveur.recevoirCarte(aDistribuer);
				}
			}
			
			for(Joueur j : table.joueurs){
				StringBuilder sb = new StringBuilder("");
				sb.append("distribution::Après distribution : " + j.afficherCartes());
				cp = map.get(j);
				cp.send(sb.toString());
			}
		}
	   
	   public void finirTour(){
			for(Joueur j : table.joueurs){
				j.setMain(table.flop, table.turn, table.river);
			}
			
			ArrayList<Joueur> vainqueurs = new ArrayList<Joueur>();
			for(Joueur j : table.joueurs){
				if(!j.isFolded()){
					if(vainqueurs.size() == 0)
						vainqueurs.add(j);
					if(j.getMain().compareTo(vainqueurs.get(0).getMain()) == 1){
						vainqueurs = new ArrayList<Joueur>(); //au cas ou il y deux joueurs a egalite pour le moment
						vainqueurs.add(j);
					}else if(j.getMain().compareTo(vainqueurs.get(0).getMain()) == 0 && !vainqueurs.get(0).equals(j)){
						vainqueurs.add(j);
					}
				}
			}
			
			int cagnotte = table.pot / vainqueurs.size();
			for(Joueur j : vainqueurs){
				StringBuilder sb = new StringBuilder("");
				j.gagner(cagnotte);
				sb.append("vainqueur::vainqueur tour : " + j.getName() + " avec la main " + j.getMain());
				sb.append("\n" + j.getName() + " remporte une cagnotte de " + cagnotte + " jetons");
				envoi(sb.toString());
			}
			table.pot = 0;
			
			for(Joueur j : table.joueurs){
				j.resetMise();
				j.setFolded(false);
			}
			
			ArrayList<Joueur> joueursToRemove = new ArrayList<Joueur>();
			for(Joueur j : table.joueurs){
				if(j.getNbrJetons() == 0){
					StringBuilder sb = new StringBuilder("");
					joueursToRemove.add(j);
					table.currentBlind = table.currentBlind*2;
					sb.append("éliminé::" + j.getName() + " a perdu, il est viré de la table");
					sb.append("\nEn conséquence, le montant de la blind est doublé");
					envoi(sb.toString());
				}
			}
			table.joueurs.removeAll(joueursToRemove);
		}
	   
	   public void mises(boolean isPreflop) throws IOException{
		   String res;
		   
			boolean miseOk = false;
			table.currentMise = 0;
			int currentMiseur;
			int indexDealer = table.joueurs.indexOf(table.currentDealer);
			if(isPreflop){
				table.currentMise = table.currentBlind*2;
				int petiteBlind = indexDealer + 1;
				if(petiteBlind >= table.joueurs.size())
					petiteBlind = 0;
				int grosseBlind = petiteBlind + 1;
				if (grosseBlind >= table.joueurs.size())
					grosseBlind = 0;
				currentMiseur = grosseBlind + 1;
			}else{
				currentMiseur = indexDealer +1;
			}
			if (currentMiseur >= table.joueurs.size())
				currentMiseur = 0;	
			int nbrJoueursQuiOntMise = 0;
			while(!miseOk){
				nbrJoueursQuiOntMise++;		
				Joueur j = table.joueurs.get(currentMiseur);
				String saisie = "";
				if(!j.isFolded()){
					res = "joueurActuel::\nAu tour de " + j.getName() + " de miser, la mise actuelle est de " + table.currentMise + ". " + j.getName() + " a déjà misé " + j.getMise() + " et il lui reste " + j.getNbrJetons();
					envoi(res);
					res = "choix::Veuillez saisir 'F' pour folder, 'A' pour all in, 'S' pour suivre ou 'Rx' avec x un nombre pour relancer la mise de x";
					map.get(j).send(res);
					while(map.get(j).action == null) {
					}
					saisie = map.get(j).action;
					map.get(j).setAction(null);
				}else{
					saisie = "F";
				}
				if(saisie.equals("F")){
					j.setFolded(true);
				}else if(saisie.equals("S")){
					int miseJoueur = j.getMise();
					if(miseJoueur < table.currentMise){
						int miseAAjouter = table.currentMise - miseJoueur;
						int jetonsRestantsJoueur = j.getNbrJetons();
						if(jetonsRestantsJoueur >= miseAAjouter){
							j.miser(miseAAjouter);
							table.pot += miseAAjouter;
						}else{
							j.miser(jetonsRestantsJoueur);
							table.pot += jetonsRestantsJoueur;
						}
					}
				}else if(saisie.equals("A")){
					int jetonsRestantsJoueur = j.getNbrJetons();
					j.miser(jetonsRestantsJoueur);
					table.pot += jetonsRestantsJoueur;
					if(j.getMise() > table.currentMise)
						table.currentMise = j.getMise();
				}else if(saisie.matches("R\\d+")){
					String relanceS = saisie.substring(1);
					int relance = Integer.parseInt(relanceS);
					int miseSouhaitee = table.currentMise + relance;
					int miseActuelleJoueur = j.getMise();
					int resteAMiser = miseSouhaitee - miseActuelleJoueur;
					int jetonsRestants = j.getNbrJetons();
					if(jetonsRestants >= resteAMiser){
						j.miser(resteAMiser);
						table.pot += resteAMiser;
					}else{
						j.miser(jetonsRestants);
						table.pot += jetonsRestants;
					}	
					if(j.getMise() > table.currentMise)
						table.currentMise = j.getMise();
				}else{
					throw new IllegalArgumentException("Paramètre invalide!");
				}		
				miseOk = miseOk(table.currentMise);
				if(nbrJoueursQuiOntMise < table.joueurs.size())
					miseOk = false;
				currentMiseur++;
				if(currentMiseur >= table.joueurs.size())
					currentMiseur = 0;
			}
			for(Joueur j : table.joueurs){
				j.resetMise();
			}
		}
	   
	   public boolean miseOk(int currentMise){
			for(Joueur j : table.joueurs){
				if(!(j.isFolded() || j.getMise() == currentMise || j.getNbrJetons() == 0))
					return false;
			}
			return true;
		}
	   
	   public void close(){
	      isRunning = false;
	   }
	   
	   public void envoi(String str) {
		   for(ClientProcesseur cp : map.values()){
			   cp.send(str);
		   }
	   }
	   
	   public static void main(String[] args){
		   String host = "127.0.0.1";
		   int port = 1234;
		    
		   Serveur ts = new Serveur(host, port);
		   ts.open();
	   }
}
