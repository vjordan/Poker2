package cartes.main;

import cartes.Main;
import cartes.ValeurCarte;

/**
 * @version 1.0
 * @author L�o
 * Cette m�thode g�re les mains de type "Carr�", c'est � dire quatre cartes de la m�me valeur
 */
public class Carre extends Main {
	/**la valeur des quatres cartes de m�me valeur*/
	private ValeurCarte carre;
	/**la valeur de la derni�re carte*/
	private ValeurCarte other;
	
	/**
	 * Constructeur de la main de type "Carr�"
	 * @param vc la valeur de carte des cartes du carr� ("as", "roi", "deux"...)
	 * @param valeurCarte la valeur de carte de la derni�re carte
	 */
	public Carre(ValeurCarte vc, ValeurCarte valeurCarte){
		this.carre = vc;
		this.other = valeurCarte;
	}

	/**
	 * Cette m�thode permet de comparer ce carr� � une autre main
	 * Elle retourne 1 si cette main est sup�rieure � celle pass�e en param�tre et
	 * -1 si elle est inf�rieure (et 0 si elle est �gale, ce qui est impossible dans le cas d'un carr�)
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
	 * Cette m�thode retourne une chaine de caract�res correspondant � cette main
	 */
	public String toString(){
		return "Carr� de " + carre + " complete par " + other;
	}

}
