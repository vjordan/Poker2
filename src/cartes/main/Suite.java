package cartes.main;

import java.util.ArrayList;

import cartes.Carte;
import cartes.Main;
import cartes.ValeurCarte;

/**
 * @version 1.0
 * @author L�o
 * Cette classe permet de g�rer les mains de type "Suite"
 */
public class Suite extends Main{

	/**La valeur de la plus haute carte de la suite*/
	private ValeurCarte highestValue;
	
	/**
	 * Constructeur
	 * @param cartes la lsite de toutes la cartes qui constituent la main
	 * @param highestValue la plus haute valeur de cette main
	 */
	public Suite(ArrayList<Carte> cartes, ValeurCarte highestValue){
		this.cartes = cartes;
		this.highestValue = highestValue;
	}

	/**
	 * Cette m�thode permet de comparer cette main � une autre main pass�e en param�tres
	 * Elle retourne 1 si elle est sup�rieure, 0 si elle est �gale et -1 si elle est inf�rieure
	 */
	public int compareTo(Main m){
		if(m instanceof QuinteFlushRoyale || m instanceof QuinteFlush || m instanceof Carre || m instanceof Full || m instanceof Couleur)
			return -1;
		else if(m instanceof Suite){
			if(this.highestValue.getValue() > ((Suite)m).highestValue.getValue())
				return 1;
			if(this.highestValue.getValue() == ((Suite)m).highestValue.getValue())
				return 0;
			else
				return -1;
		}else
			return 1;
	}
	
	/**
	 * Cette m�thode retourne une chaine de caract�res correspondant � la suite en question.
	 */
	public String toString(){
		return "Suite dont la plus haute valeure est " + highestValue + " : " + cartes;
	}
}
