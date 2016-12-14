package cartes;

/**
 * @version 1.0
 * @author Léo
 * Cette classe gère la représentation d'une carte, avec sa valeur et sa couleur
 */
public class Carte implements Comparable<Carte>{
	protected CouleurCarte couleur;
	protected ValeurCarte valeur;
	
	/**
	 * Constructeur
	 * @param couleur la couleur de la carte à créer (coeur, pique, trèfle, carreau)
	 * @param valeur la valeur de la carte à créer (deux, trois, [...], rois, as)
	 */
	public Carte(CouleurCarte couleur, ValeurCarte valeur){
		this.valeur = valeur;
		this.couleur = couleur;
	}
	
	/**
	 * Cette méthode retourne la couleur d'une carte
	 * @return la couleur de la carte
	 */
	public CouleurCarte getCouleur(){
		return this.couleur;
	}
	
	/**
	 * Cette méthode rtourne la valeur de la carte
	 * @return la valeur de la carte
	 */
	public ValeurCarte getValeur(){
		return this.valeur;
	}
	
	/**
	 * Cette methode retourne une chaine de caractère correspondant à la représentation de cette carte en français
	 */
	public String toString(){
		return this.valeur.name() + " " + this.couleur;
	}

	/**
	 * Cette methode permet de comparer deux cartes entres elles
	 * @param carteToCompare la carte a comparer
	 * @return 1 si cette carte a une valeur supérieure à celle de la carte passée en paramètre, 0 si elle est égale
	 * et -1 dans le cas où sa valeur est inférieure
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
