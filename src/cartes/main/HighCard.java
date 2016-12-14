package cartes.main;

import java.util.ArrayList;
import java.util.Collections;

import cartes.Carte;
import cartes.Main;
import cartes.ValeurCarte;

/**
 * @version 1.0
 * @author L�o
 * Cette classe g�re une main avec aucune combinaison particuli�re, on se base sur la plus forte carte : "HighCard"
 */
public class HighCard extends Main{
	
	/**la liste des cartes constituant la main*/
	private ArrayList<ValeurCarte> others;
	
	/**
	 * Constructeur
	 * @param cartes la liste des cartes de la main
	 * @param others la liste des valeurs de carte de la main (plus pratique pour effectuer des comparaisons)
	 */
	public HighCard(ArrayList<Carte> cartes, ArrayList<ValeurCarte> others){
		this.cartes = cartes;
		this.others = others;
	}

	/**
	 * Cette m�thode permet de comparer cette main � d'autres mains.
	 * Elle retourne 1 si cette main est plus forte que celle pass�e en param�tres, 0 si elle est
	 * identique et -1 si elle est moins forte
	 */
	public int compareTo(Main m) {
		if(m instanceof HighCard){
			Collections.sort(this.others);
			Collections.sort(((HighCard)m).others);
			for(int i = others.size()-1; i >= 0; i--){
				if(this.others.get(i).getValue() > ((HighCard)m).others.get(i).getValue())
					return 1;
				else if(this.others.get(i).getValue() < ((HighCard)m).others.get(i).getValue())
					return -1;
			}
			return 0;
		}else
			return -1;
	}
	
	/**
	 * Cette methode retourne une chaine de caract�res correspondant � la main poss�d�e
	 */
	public String toString(){
		return "rien de particulier : " + others;
	}
}
