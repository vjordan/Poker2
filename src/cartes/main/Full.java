package cartes.main;

import java.util.ArrayList;

import cartes.Carte;
import cartes.Main;
import cartes.ValeurCarte;

/**
 * @version 1.0
 * @author L�o
 * Cette classe repr�sente une main de type "Full"
 */
public class Full extends Main {
	
	/**Cet attribut correspond � la paire du full*/
	private ValeurCarte paire;
	/**Cet attribut correspond � la triplette du brelan de ce full*/
	private ValeurCarte triple;
	
	/**
	 * Constructeur
	 * @param cartes la liste des cartes qui constituent cette main
	 * @param triple la valeur de carte qui constitue ce brelan
	 * @param paire la valeur de cartes qui constitue cette paire
	 */
	public Full(ArrayList<Carte> cartes, ValeurCarte triple, ValeurCarte paire){
		this.triple = triple;
		this.paire = paire;
	}

	/**
	 * Cette m�thode permet de comparer cette main de type full � d'autres mains.
	 * Retourne 1 si cette main est sup�rieure, 0 si elle est �gale et -1 si elle est inf�rieure � la main pass�e en param�tres
	 */
	public int compareTo(Main m) {
		if(m instanceof QuinteFlushRoyale || m instanceof QuinteFlush || m instanceof Carre){
			return -1;
		}else if(m instanceof Full){
			if(this.triple.getValue() > ((Full)m).triple.getValue())
				return 1;
			else if(this.triple.getValue() < ((Full)m).triple.getValue())
				return -1;
			else{
				if(this.paire.getValue() > ((Full)m).paire.getValue())
					return 1;
				else if(this.paire.getValue() < ((Full)m).paire.getValue())
					return -1;
				else
					return 0;
			}
		}
		return 1;
	}

	/**
	 * Cette m�thode retourne une chaine de caract�res correspindant � cette main de type "Full"
	 */
	public String toString(){
		return "Full avec 3 " + triple + " et deux " + paire;
	}
}
