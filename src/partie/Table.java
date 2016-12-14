package partie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import cartes.Carte;
import cartes.CouleurCarte;
import cartes.ValeurCarte;

/**
 * @version 1.0
 * @author L�o
 * Cette classe g�re la table autour de laquelle les joueurs disputent la partie
 */
public class Table {

	/**Cet attribut indique si la partie a commenc� ou non*/
	protected boolean isStarted;
	/**Cet attribut contient la listedes joueurs encore pr�sents autour de la table*/
	protected ArrayList<Joueur> joueurs;
	/**Cet attribut contient le paquet de cartes*/
	protected ArrayList<Carte> cartes;
	/**Cet attribut contient le dealer actuel pour cette table*/
	protected Joueur currentDealer;
	/**Cet attribut contient le montant de la mise actuelle (pas la pot)*/
	protected int currentMise;
	/**Cet attribut contient le montant de la blind*/
	protected int currentBlind;
	/**Cet attribut contient le montant du pot qui sera revers� au gagnant*/
	protected int pot;
	
	/**Cet attribut contient la carte retourn�e au turn*/
	private Carte turn;
	/**Cet attribut contient la carte retourn�e au river*/
	private Carte river;
	/**Cet attribut contient les trois cartes retourn�es au flop*/
	private Carte[] flop;
	
	/**
	 * Constructeur
	 */
	public Table(){
		isStarted = false;
		joueurs = new ArrayList<Joueur>();
		flop = new Carte[3];
		currentBlind = 10;
	}
	
	/**
	 * Cette m�thode r�initialise le paquet avec toutes les cartes, non m�lang�es
	 */
	public void getPaquet(){
		cartes = new ArrayList<Carte>();
		for(CouleurCarte couleur : CouleurCarte.values()){
			for(ValeurCarte valeur : ValeurCarte.values()){
				cartes.add(new Carte(couleur, valeur));
			}
		}
	}
	
	/**
	 * Cette m�thode permet de passer la table au statut "partie commence".
	 * On ne peut plus ajouter de joueurs � la table et un dealer est d�sign� pour commencer
	 */
	public void start(){
		StringBuilder presentation = new StringBuilder("Les joueurs ");
		for(Joueur j : joueurs)
			presentation.append(j.getName() + ", ");
		presentation.append("sont autour de la table");
		System.out.println(presentation);
		
		isStarted = true;
	}
	
	/**
	 * Cette m�thode effectue un m�lange des cartes
	 */
	protected void melanger(){
		Collections.shuffle(cartes);
	}
	
	/**
	 * Cette m�thode r�initilise le pot de la table
	 */
	public void restePot(){
		this.pot = 0;
	}
	
	/**
	 * Cette m�thode retourne la liste des joueurs
	 * @return la liste des joueurs
	 */
	public ArrayList<Joueur> getJoueurs(){
		return this.joueurs;
	}
	
	/**
	 * Cette m�thode permet d'ajouter un joueur � la table
	 * @param j le joueur � ajouter � la table
	 * @throws RuntimeException si la table contient d�j� 10 joueurs ou si la partie est commenc�e
	 */
	public void addPlayer(Joueur j){
		if(!isStarted){
			if(joueurs.size() < 10){
				joueurs.add(j);
				System.out.println("La table comporte d�sormais " + joueurs.size() + " joueurs");
			}else{
				System.out.println("Impossible d'avoir plus de 10 joueurs autour de la table!");
			}
		}else{
			throw new RuntimeException("Impossible d'ajouter des joueurs en cours de partie");
		}
	}
	
	/**
	 * Cette m�thode change le dealer (dealer suivant, m�thode � appeler en fin de tour)
	 */
	protected void updateDealer(){
		if(currentDealer == null){
			int dealerPos = (int)Math.random()*joueurs.size();
			currentDealer = joueurs.get(dealerPos);
			currentDealer.setDealer(true);
			
			System.out.println(currentDealer.getName() + " commence en tant que dealer");
		}else{
			int indexOldDealer = joueurs.indexOf(currentDealer);
			int indexNewDealer = indexOldDealer + 1;
			if(indexNewDealer >= joueurs.size())
				indexNewDealer = 0;
			
			currentDealer.setDealer(false);
			currentDealer = joueurs.get(indexNewDealer);
			currentDealer.setDealer(true);
		}
	}
	
	/**
	 * Cette m�thode effectue les blinds
	 */
	protected void blinds(){
		int indexDealer = joueurs.indexOf(currentDealer);

		int petiteBlind = indexDealer + 1;
		if(petiteBlind >= joueurs.size())
			petiteBlind = 0;
		
		int grosseBlind = petiteBlind + 1;
		if (grosseBlind >= joueurs.size())
			grosseBlind = 0;
		
		pot += joueurs.get(petiteBlind).miser(currentBlind);
		pot += joueurs.get(grosseBlind).miser(currentBlind*2);

		System.out.println(joueurs.get(petiteBlind).getName() + " d�pose la petite blind d'un montant de " + currentBlind);
		System.out.println(joueurs.get(grosseBlind).getName() + " d�pose la grosse blind d'un montant de " + currentBlind*2);
	}
	
	/**
	 * Cette m�thode distribue les cartes (2 par joueurs)
	 */
	protected void distribuer(){
		for(int i = 0; i < 2; i++){
			int indexDealer = joueurs.indexOf(currentDealer);
			int indexJoueurAdistribuer;
			for(int j = 0; j < joueurs.size(); j++){
				indexJoueurAdistribuer = indexDealer  + j;
				if(indexJoueurAdistribuer >= joueurs.size())
					indexJoueurAdistribuer = indexJoueurAdistribuer - joueurs.size();
				
				Joueur receveur = joueurs.get(indexJoueurAdistribuer);
				
				Carte aDistribuer = this.prendreCarte();
				receveur.recevoirCarte(aDistribuer);
			}
		}
		
		System.out.println("Apr�s distribution :");
		for(Joueur j : joueurs){
			System.out.println(j.afficherCartes());
		}
		
		for(Joueur j : joueurs){
			j.afficherCartes();
		}
	}
	
	/**
	 * Cette m�thode g�re le flop
	 */
	protected void flop(){
		cartes.remove(0); // on brule 1 carte
		for(int i = 0; i < flop.length; i++){
			flop[i] = prendreCarte();
		}
		System.out.println("\nLe flop vient de d�couvrir les cartes " + flop[0] + " ," + flop[1] + ", " + flop[2] + "\n");
	}
	
	/**
	 * Cette m�thode g�re le turn
	 */
	protected void turn(){
		cartes.remove(0); // brule 1 carte
		
		this.turn = cartes.remove(0);
		System.out.println("\nLe turn vient de d�couvrir la carte " + turn + "\n");
	}
	
	/**
	 * Cette m�thode g�re le river
	 */
	protected void river(){
		cartes.remove(0); // brule 1 carte
		
		this.river = cartes.remove(0);
		
		System.out.println("Le river vient de d�couvrir la carte " + river + "\n");
	}
	
	/**
	 * Cette retourne une carte du paquet
	 * @return
	 */
	protected Carte prendreCarte(){
		return this.cartes.remove(0);
	}
	
	/**
	 * Cette m�thode affiche l'�tat de la table sous forme de chaine de caract�res.
	 * Utile essentiellement � des fins de d�boggage
	 */
	public String toString(){
		String s = "\n//////////////////////////Etat de la table///////////////////////////\n";
		for(Joueur j : joueurs){
			s+= j.toString();
		}
		if(flop != null)
			s+= "\nflop : " + flop[0] + "/" + flop[1] + "/" + flop[2];
		if(turn != null)
			s+= "\nturn : " + turn;
		if(river != null)
			s += "\nriver : " + river;
		
		s+= "\npot actuel : " + pot;
		return s + "\n///////////////////////////////////////////////\n";
	}
	
	/**
	 * Cette m�thode g�re les mises � chaque tour de mises
	 */
	protected void mises(boolean isPreflop){
			
		boolean miseOk = false;
		currentMise = 0;
		int currentMiseur;
		int indexDealer = joueurs.indexOf(currentDealer);
		if(isPreflop){ //au preflop on se base sur la grosse blind sinon non
			currentMise = currentBlind*2;
		
			//on redetermine la petite blind
			int petiteBlind = indexDealer + 1;
			if(petiteBlind >= joueurs.size())
				petiteBlind = 0;
			
			//idem grosse blind
			int grosseBlind = petiteBlind + 1;
			if (grosseBlind >= joueurs.size())
				grosseBlind = 0;
			
			//puis le premier a miser
			currentMiseur = grosseBlind + 1;
		}else{
			currentMiseur = indexDealer +1;
		}
		if (currentMiseur >= joueurs.size())
			currentMiseur = 0;
		
		int nbrJoueursQuiOntMise = 0;
		while(!miseOk){
			nbrJoueursQuiOntMise++;
			
			Joueur j = joueurs.get(currentMiseur);
			String saisie = "";
			while(!saisieCorrecte(saisie, j)){
				if(!j.isFolded()){
					System.out.println("\nAu tour de " + j.getName() + " de miser, la mise actuelle est de " + currentMise + ". " + j.getName() + " a d�j� mis� " + j.getMise() + " et il lui reste " + j.getNbrJetons());
					System.out.println("Veuillez saisir 'F' pour folder, 'A' pour all in, 'S' pour suivre ou 'Rx' avec x un nombre pour relancer la mise de x");
					saisie = new Scanner(System.in).nextLine();
				}else{
					saisie = "F";
				}
			}
			if(saisie.equals("F")){
				j.setFolded(true);
			}else if(saisie.equals("S")){
				int miseJoueur = j.getMise();
				if(miseJoueur < currentMise){
					int miseAAjouter = currentMise - miseJoueur;
					int jetonsRestantsJoueur = j.getNbrJetons();
					if(jetonsRestantsJoueur >= miseAAjouter){
						j.miser(miseAAjouter);
						pot += miseAAjouter;
					}else{
						j.miser(jetonsRestantsJoueur);
						pot += jetonsRestantsJoueur;
					}
				}
			}else if(saisie.equals("A")){
				int jetonsRestantsJoueur = j.getNbrJetons();
				j.miser(jetonsRestantsJoueur);
				pot += jetonsRestantsJoueur;
				if(j.getMise() > currentMise)
					currentMise = j.getMise();
			}else if(saisie.matches("R\\d+")){
				String relanceS = saisie.substring(1);
				int relance = Integer.parseInt(relanceS);
				int miseSouhaitee = currentMise + relance;
				int miseActuelleJoueur = j.getMise();
				int resteAMiser = miseSouhaitee - miseActuelleJoueur;
				int jetonsRestants = j.getNbrJetons();
				if(jetonsRestants >= resteAMiser){
					j.miser(resteAMiser);
					pot += resteAMiser;
				}else{
					j.miser(jetonsRestants);
					pot += jetonsRestants;
				}
				
				if(j.getMise() > currentMise)
					currentMise = j.getMise();
			}else{
				throw new IllegalArgumentException("Param�tre invalide!");
			}
			
			miseOk = miseOk(currentMise);
			if(nbrJoueursQuiOntMise < joueurs.size()) //on s'assure que tous les joueurs ont bien eu le temps de miser une fois
				miseOk = false;
			
			currentMiseur++;
			if(currentMiseur >= joueurs.size())
				currentMiseur = 0;
		}
		
		for(Joueur j : joueurs){
			j.resetMise();
		}
	}
	
	/**
	 * Cette m�thode indique si on est ok au niveau des mises
	 * @param currentMise la mise � atteindre pour �tre ok (� moins d'�tre fold ou tapis)
	 * @return true si tout le monde est � jour sur la mise (sauf cas �nonc�s pr�c�dement), false sinon
	 */
	private boolean miseOk(int currentMise){
		for(Joueur j : joueurs){
			if(!(j.isFolded() || j.getMise() == currentMise || j.getNbrJetons() == 0))
				return false;
		}
		return true;
	}
	
	/**
	 * Cette m�thode indique si la saisie d'un joueur est correcte ou non
	 * @param saisie la saisie de l'utilsateur
	 * @return true si la saise correspond � un �l�ment de r�ponse attendu, false sinon
	 */
	private boolean saisieCorrecte(String saisie, Joueur j){
		if(!( saisie.equals("S") || saisie.equals("F") || saisie.equals("A") || saisie.matches("R\\d+")))
			return false;
		
		if(saisie.equals("S")){
			int miseJoueur = j.getMise();
			if(miseJoueur < currentMise){
				int miseAAjouter = currentMise - miseJoueur;
				int jetonsRestantsJoueur = j.getNbrJetons();
				if(jetonsRestantsJoueur >= miseAAjouter){
					return true;
				}else{
					System.out.println("Vous n'avez pas assez pour suivre. Vous pouvez fold (F) ou faire tapis (A)");
					return false;
				}
			}else if(miseJoueur == currentMise){
				return true;
			}
		}else if(saisie.equals("A")){
			return true;
		}else if(saisie.matches("R\\d+")){
			String relanceS = saisie.substring(1);
			int relance = Integer.parseInt(relanceS);
			int miseSouhaitee = currentMise + relance;
			if(miseSouhaitee < 2*currentMise){
				System.out.println("Le montant de la mise apr�s relance doit �tre au moins le double de la mise actuelle");
				return false;
			}else{
				int miseActuelleJoueur = j.getMise();
				int resteAMiser = miseSouhaitee - miseActuelleJoueur;
				int jetonsRestants = j.getNbrJetons();
				if(jetonsRestants >= resteAMiser){
					return true;
				}else{
					System.out.println("Vous n'avez pas assez de jetons pour effectuer cette relance. Vous pouvez vous coucher (F) ou faire all-in (A)");
					return false;
				}
			}
		}else if(saisie.equals("F"))
			return true;

		return false;
	}
	
	/**
	 * Cette m�thode g�re la fin d'un tour : attribution du pot au(x) gagnant(s)
	 */
	protected void finirTour(){
		for(Joueur j : joueurs){
			j.setMain(flop, turn, river);
		}
		
		ArrayList<Joueur> vainqueurs = new ArrayList<Joueur>();
		for(Joueur j : joueurs){
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
		
		int cagnotte = this.pot / vainqueurs.size();
		for(Joueur j : vainqueurs){
			System.out.println("vainqueur tour : " + j.getName() + " avec la main " + j.getMain());
			j.gagner(cagnotte);
			System.out.println(j.getName() + " remporte une cagnotte de " + cagnotte + " jetons");
		}
		this.pot = 0;
		
		for(Joueur j : joueurs){
			j.resetMise();
			j.setFolded(false);
		}
		
		ArrayList<Joueur> joueursToRemove = new ArrayList<Joueur>();
		for(Joueur j : joueurs){
			if(j.getNbrJetons() == 0){
				joueursToRemove.add(j);
				System.out.println(j.getName() + " a perdu, il est vir� de la table");
				currentBlind = currentBlind*2;
				System.out.println("En cons�quence, le montant de la blind est doubl�");
			}
		}
		joueurs.removeAll(joueursToRemove);
	}
}
