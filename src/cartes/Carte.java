package cartes;

/**
 * @version 1.0
 * @author L�o
 * Cette classe g�re la repr�sentation d'une carte, avec sa valeur et sa couleur
 */
public class Carte implements Comparable<Carte>{
	protected CouleurCarte couleur;
	protected ValeurCarte valeur;
	
	/**
	 * Constructeur
	 * @param couleur la couleur de la carte � cr�er (coeur, pique, tr�fle, carreau)
	 * @param valeur la valeur de la carte � cr�er (deux, trois, [...], rois, as)
	 */
	public Carte(CouleurCarte couleur, ValeurCarte valeur){
		this.valeur = valeur;
		this.couleur = couleur;
	}
	
	/**
	 * Cette m�thode retourne la couleur d'une carte
	 * @return la couleur de la carte
	 */
	public CouleurCarte getCouleur(){
		return this.couleur;
	}
	
	/**
	 * Cette m�thode rtourne la valeur de la carte
	 * @return la valeur de la carte
	 */
	public ValeurCarte getValeur(){
		return this.valeur;
	}
	
	/**
	 * Cette methode retourne une chaine de caract�re correspondant � la repr�sentation de cette carte en fran�ais
	 */
	public String toString(){
		return this.valeur.name() + " " + this.couleur;
	}

	/**
	 * Cette methode permet de comparer deux cartes entres elles
	 * @param carteToCompare la carte a comparer
	 * @return 1 si cette carte a une valeur sup�rieure � celle de la carte pass�e en param�tre, 0 si elle est �gale
	 * et -1 dans le cas o� sa valeur est inf�rieure
	 */
	@Override
	public int compareTo(Carte toCompare) {
		if(this.valeur.getValue() > toCompare.valeur.getValue())
			return 1;
		else if(this.valeur.getValue() < toCompare.valeur.getValue())
			return -1;
		else
			return 0;
	}
}
