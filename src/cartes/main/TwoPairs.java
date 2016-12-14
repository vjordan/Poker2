package cartes.main;

import java.util.ArrayList;

import cartes.Carte;
import cartes.Main;
import cartes.ValeurCarte;

/**
 * @version 1.0
 * @author Léo
 * Cette classe gère les mains de deux paires
 */
public class TwoPairs extends Main {
	
	/**la plus forte paire de la main*/
	private ValeurCarte highestPair;
	/**la moins forte paire de la main*/
	private ValeurCarte lowestPair;
	/**l'autre carte qui constitue la main*/
	private ValeurCarte otherCard;
	
	/**
	 * Constructeur
	 * @param cartes la liste des cartes qui constituent la main
	 * @param highestPair la plus forte paire
	 * @param lowestPair la plus petite paire
	 * @param otherCard l'autre carte
	 */
	public TwoPairs(ArrayList<Carte> cartes, ValeurCarte highestPair, ValeurCarte lowestPair, ValeurCarte otherCard){
		this.highestPair = highestPair;
		this.lowestPair = lowestPair;
		this.otherCard = otherCard;
		this.cartes = cartes;
	}

	/**
	 * Cette méthode effectue une comparaison entre cette main et cele passée en paramètres
	 * Elle retourne 1 si elle est supérieure, 0 si elle est égale et -1 si elle est inférieure
	 */
	public int compareTo(Main m) {
		if(m instanceof QuinteFlushRoyale || m instanceof QuinteFlush || m instanceof Carre || m instanceof Full || m instanceof Couleur || m instanceof Suite || m instanceof Triple){
			return -1;
		}else if(m instanceof TwoPairs){
			if(this.highestPair.getValue() > ((TwoPairs)m).highestPair.getValue()){
				return 1;
			}else if(this.highestPair.getValue() < ((TwoPairs)m).highestPair.getValue()){
				return -1;
			}else{
				if(this.lowestPair.getValue() > ((TwoPairs)m).lowestPair.getValue()){
					return 1;
				}else if(this.lowestPair.getValue() < ((TwoPairs)m).lowestPair.getValue()){
					return -1;
				}else{
					if(this.otherCard.getValue() > ((TwoPairs)m).otherCard.getValue()){
						return 1;
					}else if(this.otherCard.getValue() < ((TwoPairs)m).otherCard.getValue()){
						return -1;
					}else{
						return 0;
					}
				}
			}
		}else{
			return 1;
		}
	}
	
	/**
	 * Cette méthode retourne une chaine qui décrit cette double paire en francais
	 */
	public String toString(){
		return "Deux paires dont la plus forte est : " + highestPair + " et la moins forte est " + lowestPair + ", complete par " + otherCard;
	}

}
