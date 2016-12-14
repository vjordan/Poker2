package cartes.main;

import java.util.ArrayList;

import cartes.Carte;
import cartes.Main;

public class QuinteFlushRoyale extends Main{
	
	/**
	 * Constructeur
	 * @param cartes la liste des cartes constituant la quinte flush royale
	 */
	public QuinteFlushRoyale(ArrayList<Carte> cartes){
		this.cartes = cartes;
	}
	
	/**
	 * Cette methode permet de comparer cette main à d'autres mains
	 * Elle retourne 0 si la main en face est identique (impossible...), 1 si elle est supérieure
	 */
	public int compareTo(Main m){
		if(m instanceof QuinteFlushRoyale)
			return 0;
		else
			return 1;
	}
	
	/**
	 * Cette méthode correspond à la description d'une quinte flush royale
	 */
	public String toString(){
		return "Quinte flush royale mageule " + cartes;
	}
}
