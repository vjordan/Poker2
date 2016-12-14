package cartes.main;

import java.util.ArrayList;

import cartes.Carte;
import cartes.Main;
import cartes.ValeurCarte;

/**
 * @version 1.0
 * @author L�o
 * Cette classe g�re les mains de type quinte flush
 */
public class QuinteFlush extends Main{
	/**la valeur de la plus forte carte*/
	private ValeurCarte highestValue;
	
	/**
	 * Constructeur
	 * @param cartes la liste de toutes les cartes qui constituent cette main
	 * @param highestValue la plus grosse carte de cette suite
	 */
	public QuinteFlush(ArrayList<Carte> cartes, ValeurCarte highestValue){
		this.highestValue = highestValue;
		this.cartes = cartes;
	}

	/**
	 * Cettem�thode permet d'effectuer une comparaison entre cette main et une autre pass�e en param�tres
	 * Elle retourne 1 si la main pass�e en param�tres est moins forte, 0 si elle est identique et
	 * -1 si cette main est inf�rieure
	 */
	public int compareTo(Main m){
		if(m instanceof QuinteFlushRoyale)
			return -1;
		else if(m instanceof QuinteFlush){
			if(this.highestValue.getValue() > ((QuinteFlush)m).highestValue.getValue())
				return 1;
			if(this.highestValue == ((QuinteFlush)m).highestValue)
				return 0;
			else
				return -1;
		}else
			return -1;
	}
	
	/**
	 * Cette m�thode retoune une chaine de cract�re correspondant � cette quinte flush
	 */
	public String toString(){
		return "Suite de couleur (quinteflush) dont la plus haute valeur est " + highestValue + " : " + cartes;
	}
}
