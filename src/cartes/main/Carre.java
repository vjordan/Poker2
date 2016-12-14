package cartes.main;

import cartes.Main;
import cartes.ValeurCarte;

/**
 * @version 1.0
 * @author Léo
 * Cette méthode gère les mains de type "Carré", c'est à dire quatre cartes de la même valeur
 */
public class Carre extends Main {
	/**la valeur des quatres cartes de même valeur*/
	private ValeurCarte carre;
	/**la valeur de la dernière carte*/
	private ValeurCarte other;
	
	/**
	 * Constructeur de la main de type "Carré"
	 * @param vc la valeur de carte des cartes du carré ("as", "roi", "deux"...)
	 * @param valeurCarte la valeur de carte de la dernière carte
	 */
	public Carre(ValeurCarte vc, ValeurCarte valeurCarte){
		this.carre = vc;
		this.other = valeurCarte;
	}

	/**
	 * Cette méthode permet de comparer ce carré à une autre main
	 * Elle retourne 1 si cette main est supérieure à celle passée en paramètre et
	 * -1 si elle est inférieure (et 0 si elle est égale, ce qui est impossible dans le cas d'un carré)
	 */
	public int compareTo(Main m) {
		if(m instanceof QuinteFlushRoyale || m instanceof QuinteFlush)
			return -1;
		else if(m instanceof Carre){
			if(this.carre.getValue() > ((Carre)m).carre.getValue())
				return 1;
			else if(this.carre.getValue() < ((Carre)m).carre.getValue())
				return -1;
			else{
				if(this.other.getValue() > ((Carre)m).other.getValue())
					return 1;
				else if(this.other.getValue() < ((Carre)m).other.getValue())
					return -1;
				else
					return 0;
			}
		}else{
			return 1;
		}
	}
	
	/**
	 * Cette méthode retourne une chaine de caractères correspondant à cette main
	 */
	public String toString(){
		return "Carré de " + carre + " complete par " + other;
	}

}
