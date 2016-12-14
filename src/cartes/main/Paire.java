package cartes.main;

import java.util.ArrayList;
import java.util.Collections;

import cartes.Carte;
import cartes.Main;
import cartes.ValeurCarte;

/**
 * @version 1.0
 * @author L�o
 * Cette classe g�re les mains de type "Paire" c'est � dire deux cartes identiques
 */
public class Paire extends Main {
	
	/**la valeur de cartes de la paire*/
	private ValeurCarte paire;
	/**la liste des valeurs des autres cartes de la main*/
	private ArrayList<ValeurCarte> others;
	
	/**
	 * Constructeur
	 * @param cartes la liste des cartes de cette main
	 * @param paire la valeur de carte de la paire
	 * @param others la liste des valeurs de carte des autres cartes que la paire
	 */
	public Paire(ArrayList<Carte> cartes, ValeurCarte paire, ArrayList<ValeurCarte> others){
		this.cartes = cartes;
		this.paire = paire;
		this.others = others;
	}

	/**
	 * Cette m�thode permet de comparer cette main � une autre pass�e en param�tres.
	 * Elle retourne 1 si cette main est sup�rieure� celle pass�e en param�tres, 0 si elle est
	 * identique et -1 si elle est inf�rieure
	 */
	public int compareTo(Main m) {
		if(!(m instanceof HighCard || m instanceof Paire)){
			return -1;
		}else if(m instanceof Paire){
			if(this.paire.getValue() > ((Paire)m).paire.getValue())
				return 1;
			else if(this.paire.getValue() < ((Paire)m).paire.getValue())
				return -1;
			else{
				Collections.sort(this.others);
				Collections.sort(((Paire)m).others);
				for(int i = others.size()-1; i >= 0; i--){
					if(this.others.get(i).getValue() > ((Paire)m).others.get(i).getValue())
						return 1;
					else if(this.others.get(i).getValue() < ((Paire)m).others.get(i).getValue())
						return -1;
				}
				return 0;
			}
		}else{
			return 1;
		}
	}
	
	/**
	 * Cette m�thode retourne une chaine de caract�res correspondant � la paire
	 */
	public String toString(){
		return "Paire de " + paire + " complete par " + others;
	}

}
