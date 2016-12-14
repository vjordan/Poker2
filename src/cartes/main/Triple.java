package cartes.main;

import java.util.ArrayList;
import java.util.Collections;

import cartes.Carte;
import cartes.Main;
import cartes.ValeurCarte;

/**
 * @version 1.0
 * @author L�o
 * Cette classe g�re les mains de type "Brelan"
 */
public class Triple extends Main{
	/**la valeur de carte qui compose le brelan*/
	private ValeurCarte triple;
	private ArrayList<ValeurCarte> others;
	
	/**
	 * Constructeur
	 * @param cartes la liste des cartes qui constituent cette main
	 * @param triple la valeur des cartes qui constituent ce brelan
	 * @param others la liste des autres cartes que le brelan qui constituent la main
	 */
	public Triple(ArrayList<Carte> cartes, ValeurCarte triple, ArrayList<ValeurCarte> others){
		this.triple = triple;
		this.cartes = cartes;
		this.others = others;
	}
	
	/**
	 * Cette m�thode permet d'effectuer une comparaison entre cette main et celle pass�e en param�tres
	 * Elle retourne 1 si c'est une meilleure main, 0 si la main en param�tres est �quivalente et -1 si elle est moins forte
	 */
	public int compareTo(Main m){
		if(m instanceof QuinteFlushRoyale || m instanceof QuinteFlush || m instanceof Carre || m instanceof Full || m instanceof Couleur || m instanceof Suite){
			return -1;
		}else if(m instanceof Triple){
			if(this.triple.getValue() > ((Triple)m).triple.getValue())
				return 1;
			else if(this.triple.getValue() < ((Triple)m).triple.getValue())
				return -1;
			else{
				Collections.sort(this.others);
				Collections.sort(((Triple)m).others);
				for(int i = others.size()-1; i >= 0; i--){
					if(this.others.get(i).getValue() > ((Triple)m).others.get(i).getValue())
						return 1;
					else if(this.others.get(i).getValue() < ((Triple)m).others.get(i).getValue())
						return -1;
				}
				return 0;
			}
		}
		return 1;
	}
	
	/**
	 * Cette m�thode retourne une chaine de caract�res correspondant au brelan en question
	 */
	public String toString(){
		return "Brelan de " + triple;
	}
}
