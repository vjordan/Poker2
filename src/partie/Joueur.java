package partie;
import java.util.ArrayList;

import cartes.Carte;
import cartes.Main;

/**
 * @version 1.0
 * @author Léo
 * Cette classe gère un joueur de poker
 */
public class Joueur {

	/**cet attribut définit si le joueur est le dealer*/
	protected boolean isDealer;
	/**cet attribut définit si le joueur est couché ou non*/
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
	 * Cette méthode retourne le nom du joueur
	 * @return le nom du joueur
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Cette méthode sert a modifier l'état dealer ou non du joueur
	 * @param deal true pour passer ce joueur dealer, false sinon
	 */
	public void setDealer(boolean deal){
		this.isDealer = deal;
	}
	
	/**
	 * Cette méthode réinitialise les cartes du joueur ainsi que sa main
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
	 * Cette méthode retourne la (meilleure) main du joueur
	 * @return la main du joueur
	 */
	public Main getMain(){
		return this.main;
	}
	
	/**
	 * Cette méthode retourne le nombre de jetons que possède le joueur
	 * @return l'entier correspondant au nombre de jetons du joueur
	 */
	public int getNbrJetons(){
		return nbrJetons;
	}
	
	/**
	 * Cette méthode retourne la mise actuelle en cours du joueur
	 * @return la mise du joueur en cours
	 */
	public int getMise(){
		return this.currentMise;
	}
	
	/**
	 * Cette méthode permet à un joueur de miser
	 * @param mise le montant à miser
	 * @return un entier correspondant à la somme misée
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
	 * Cette méthode réinitialise le montant de la mise en cours pour ce joueur
	 */
	public void resetMise(){
		this.currentMise = 0;
	}
	
	/**
	 * Cette méthode permet à un joueur de recevoir des gains lorsque sa main est la meilleure de la table
	 * @param gain le montant des gains à ajouter au nombre de jetons du joueur
	 */
	public void gagner(int gain){
		this.nbrJetons += gain;
	}
	
	/**
	 * Cette méthode permet de changr l'état d'un joueur pour qu'il puisse se coucher
	 * @param folded true pour folder le joueur, false sinon
	 */
	public void setFolded(boolean folded){
		this.isFolded = folded;
	}
	
	/**
	 * Cette méthode retourne l'état fold ou non du joueur
	 * @return true si le joueur s'est couché, false sinon
	 */
	public boolean isFolded(){
		return this.isFolded;
	}
	
	/**
	 * Cette méthode affiche en francais les deux cartes qui ont été distribuées au joueur
	 * @return une chaine correspondant aux deux cartes privées de ce joueur
	 */
	public String afficherCartes(){
		return this.name + " posséde les cartes " + cartes[0] + " et " + cartes[1];
	}
	
	/**
	 * Cette méthode retourne une chaine correspondant aux données principales sur ce joueur, utile notamment à des fins de déboggage
	 */
	public String toString(){
		return name + " : " + cartes[0] + " / " + cartes[1] + " /// nbr jetons : " + nbrJetons + " /// dealer : " + isDealer +" ||| " + main + "\n";
	}
}