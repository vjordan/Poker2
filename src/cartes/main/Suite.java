package cartes.main;

import java.util.ArrayList;

import cartes.Carte;
import cartes.Main;
import cartes.ValeurCarte;

/**
 * @version 1.0
 * @author Léo
 * Cette classe permet de gérer les mains de type "Suite"
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
	 * Cette méthode permet de comparer cette main à une autre main passée en paramètres
	 * Elle retourne 1 si elle est supérieure, 0 si elle est égale et -1 si elle est inférieure
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
	 * Cette méthode retourne une chaine de caractères correspondant à la suite en question.
	 */
	public String toString(){
		return "Suite dont la plus haute valeure est " + highestValue + " : " + cartes;
	}
}
