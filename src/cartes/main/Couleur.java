package cartes.main;

import java.util.ArrayList;
import java.util.Collections;

import cartes.Carte;
import cartes.Main;
import cartes.ValeurCarte;

public class Couleur extends Main{
	/**la liste des valeurs de cartes pour cette main*/
	private ArrayList<ValeurCarte> valCartes;

	/**
	 * Constructeur de la main de type "Couleur"
	 * @param cartes la liste de toutes les cartes qui composent cette main
	 * @param valCartes la liste des valeurs de cartes pour cette main
	 */
	public Couleur(ArrayList<Carte> cartes, ArrayList<ValeurCarte> valCartes){
		this.valCartes = valCartes;
		this.cartes = cartes;
	}
	
	/**
	 * Cette méthode permet de comparer cette main de type "Couleur" à d'autres mains.
	 * Elle retourne 1 si cette main est supérieure à celle passée en paramètres, -1 si elle
	 * est inférieure et 0 si elle est égale.
	 */
	public int compareTo(Main m){
		if(m instanceof QuinteFlush || m instanceof QuinteFlushRoyale || m instanceof Carre || m instanceof Full){
			return -1;
		}else if(m instanceof Couleur){
			Collections.sort(this.valCartes);
			Collections.sort(((Couleur)m).valCartes);
			for(int i = valCartes.size()-1; i >= 0; i--){
				if(this.valCartes.get(i).getValue() > ((Couleur)m).valCartes.get(i).getValue())
					return 1;
				else if(this.valCartes.get(i).getValue() < ((Couleur)m).valCartes.get(i).getValue())
					return 1;
			}
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * Cette méthode retourne une chaine de caractères montrant le contenu de cette main de type "Couleur"
	 */
	public String toString(){
		return "couleur " + " dont les cartes sont " + cartes;
	}
}
