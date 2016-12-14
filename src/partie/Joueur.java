package partie;
import java.util.ArrayList;

import cartes.Carte;
import cartes.Main;

/**
 * @version 1.0
 * @author L�o
 * Cette classe g�re un joueur de poker
 */
public class Joueur {

	/**cet attribut d�finit si le joueur est le dealer*/
	protected boolean isDealer;
	/**cet attribut d�finit si le joueur est couch� ou non*/
	protected boolean isFolded;
	/**Cet attribut contient le nom du joueur*/
	protected String name;
	/**Cet attribut contient le nombre de jetons du joueur*/
	protected int nbrJetons;
	/**Cet attibut contient les deux cartes privative du joueur*/
	protected Carte[] cartes;
	/**Cet attribut contient la meilleure main que le joueur peut obtenir avec ses cartes et celles disponibles sur la table*/
	protected Main main;
	/**cet attribut contient la mise actuelle du joueur*/
	protected int currentMise;
	
	/**
	 * Constructeur
	 * @param name le nom du joueur
	 */
	public Joueur(String name){
		this.name = name;
		this.nbrJetons = 200;
		this.isDealer = false;
		//this.cartes = new Carte[2];
	}
	
	/**
	 * Cette m�thode retourne le nom du joueur
	 * @return le nom du joueur
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Cette m�thode sert a modifier l'�tat dealer ou non du joueur
	 * @param deal true pour passer ce joueur dealer, false sinon
	 */
	public void setDealer(boolean deal){
		this.isDealer = deal;
	}
	
	/**
	 * Cette m�thode r�initialise les cartes du joueur ainsi que sa main
	 */
	public void resetCards(){
		this.cartes = new Carte[2];
		this.main = null;
	}
	
	/**
	 * Permet au joueur de recevoir une carte on ne donne pas les deux d'un coup car on fait deux tours d'une carte)
	 * @param c la carte a recevoir
	 */
	public void recevoirCarte(Carte c){
		if(cartes[0] == null)
			cartes[0] = c;
		else
			cartes[1] = c;
	}
	
	/**
	 * Cette methode permet d'initialiser la main de ce joueur avec les meilleures cartes possibles
	 * @param flop les 3 cartes du flop
	 * @param turn la carte du turn
	 * @param river la carte du river
	 */
	public void setMain(Carte[] flop, Carte turn, Carte river){
		ArrayList<Carte> possibilites = new ArrayList<Carte>();
		possibilites.add(this.cartes[0]);
		possibilites.add(this.cartes[1]);
		possibilites.add(flop[0]);
		possibilites.add(flop[1]);
		possibilites.add(flop[2]);
		possibilites.add(turn);
		possibilites.add(river);
		for(int i = 0; i < 6; i++){
			for(int j = i; j < 7; j++){
				if(i != j){
					@SuppressWarnings("unchecked")
					ArrayList<Carte> test = (ArrayList<Carte>) possibilites.clone();
					test.remove(j);
					test.remove(i);
					@SuppressWarnings("unchecked")
					Main m = Main.getMain((ArrayList<Carte>) test.clone());
					if(this.main == null)
						this.main = m;
					else
						if(this.main.compareTo(m) == -1){
							//System.out.println(main + " <<<<<<<<< " + m);
							this.main = m;
						}
				}
			}
		}
	}
	
	/**
	 * Cette m�thode retourne la (meilleure) main du joueur
	 * @return la main du joueur
	 */
	public Main getMain(){
		return this.main;
	}
	
	/**
	 * Cette m�thode retourne le nombre de jetons que poss�de le joueur
	 * @return l'entier correspondant au nombre de jetons du joueur
	 */
	public int getNbrJetons(){
		return nbrJetons;
	}
	
	/**
	 * Cette m�thode retourne la mise actuelle en cours du joueur
	 * @return la mise du joueur en cours
	 */
	public int getMise(){
		return this.currentMise;
	}
	
	/**
	 * Cette m�thode permet � un joueur de miser
	 * @param mise le montant � miser
	 * @return un entier correspondant � la somme mis�e
	 */
	public int miser(int mise){
		if(nbrJetons >= mise){
			this.currentMise = currentMise + mise;
			nbrJetons = nbrJetons - mise;
			return mise;
		}else{
			this.currentMise += nbrJetons;
			nbrJetons = 0;
			return currentMise;
		}
	}
	
	/**
	 * Cette m�thode r�initialise le montant de la mise en cours pour ce joueur
	 */
	public void resetMise(){
		this.currentMise = 0;
	}
	
	/**
	 * Cette m�thode permet � un joueur de recevoir des gains lorsque sa main est la meilleure de la table
	 * @param gain le montant des gains � ajouter au nombre de jetons du joueur
	 */
	public void gagner(int gain){
		this.nbrJetons += gain;
	}
	
	/**
	 * Cette m�thode permet de changr l'�tat d'un joueur pour qu'il puisse se coucher
	 * @param folded true pour folder le joueur, false sinon
	 */
	public void setFolded(boolean folded){
		this.isFolded = folded;
	}
	
	/**
	 * Cette m�thode retourne l'�tat fold ou non du joueur
	 * @return true si le joueur s'est couch�, false sinon
	 */
	public boolean isFolded(){
		return this.isFolded;
	}
	
	/**
	 * Cette m�thode affiche en francais les deux cartes qui ont �t� distribu�es au joueur
	 * @return une chaine correspondant aux deux cartes priv�es de ce joueur
	 */
	public String afficherCartes(){
		return this.name + " poss�de les cartes " + cartes[0] + " et " + cartes[1];
	}
	
	/**
	 * Cette m�thode retourne une chaine correspondant aux donn�es principales sur ce joueur, utile notamment � des fins de d�boggage
	 */
	public String toString(){
		return name + " : " + cartes[0] + " / " + cartes[1] + " /// nbr jetons : " + nbrJetons + " /// dealer : " + isDealer +" ||| " + main + "\n";
	}
}