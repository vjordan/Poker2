package cartes;

import java.util.ArrayList;
import java.util.Collections;

import cartes.main.Carre;
import cartes.main.Couleur;
import cartes.main.Full;
import cartes.main.HighCard;
import cartes.main.Paire;
import cartes.main.QuinteFlush;
import cartes.main.QuinteFlushRoyale;
import cartes.main.Suite;
import cartes.main.Triple;
import cartes.main.TwoPairs;

/**
 * @version 1.0
 * @author Léo
 * Cette classe constitue une main
 */
public abstract class Main implements Comparable<Main>{

	protected ArrayList<Carte> cartes;
	
	/**
	 * Cette méthode détermine une main en fonction des cartes passées en paramètre
	 * @param listeCartes la liste des cartes qui constituent la main à tester
	 * @return un objet de type Main corespondant aux cartes passées en paramètres
	 */
	public static Main getMain(ArrayList<Carte> listeCartes){
		ArrayList<ValeurCarte> valCartes = new ArrayList<ValeurCarte>();
		for(Carte c : listeCartes){
			valCartes.add(c.getValeur());
		}
		Collections.sort(valCartes);
		if(isSuite(valCartes) && isColour(listeCartes) && valCartes.get(4).getValue() == 14)
			return new QuinteFlushRoyale(listeCartes);
		else if(isSuite(valCartes) && isColour(listeCartes))
			return new QuinteFlush(listeCartes, valCartes.get(4));
		else if(isFour(valCartes)){
			for(int i = 2; i < 15; i++){
				ValeurCarte vc = ValeurCarte.getName(i);
				if(Collections.frequency(valCartes, vc) == 4){
					ArrayList<ValeurCarte> toRemove = new ArrayList<ValeurCarte>();
					toRemove.add(vc);
					valCartes.removeAll(toRemove);
					return new Carre(vc, valCartes.get(0));
				}
			}
		}else if(isFull(valCartes)){
			for(int i = 2; i < 15; i++){
				for(int j = 3; j < 15; j++){
					if(i != j){
						ValeurCarte vc = ValeurCarte.getName(i);
						ValeurCarte vc2 = ValeurCarte.getName(j);
						if(Collections.frequency(valCartes, vc) == 2 && Collections.frequency(valCartes, vc2) == 3){
							return new Full(listeCartes, vc2, vc);
						}else if(Collections.frequency(valCartes, vc) == 3 && Collections.frequency(valCartes, vc2) == 2){
							return new Full(listeCartes, vc, vc2);
						}
					}
				}
			}
		}else if(isColour(listeCartes)){
			return new Couleur(listeCartes, valCartes);
		}else if(isSuite(valCartes)){
			if(valCartes.contains(ValeurCarte.As) && valCartes.contains(ValeurCarte.Cinq))
				return new Suite(listeCartes, ValeurCarte.Cinq);
			else
				return new Suite(listeCartes, valCartes.get(4));
		}else if(isTriple(valCartes)){
			for(int i = 2; i < 15; i++){
				ValeurCarte vc = ValeurCarte.getName(i);
				if(Collections.frequency(valCartes, vc) == 3){
					ArrayList<ValeurCarte> toRemove = new ArrayList<ValeurCarte>();
					toRemove.add(vc);
					valCartes.removeAll(toRemove);
					return new Triple(listeCartes, vc, valCartes);
				}
			}
		}else if(isDeuxDeux(valCartes)){
			for(int i = 2; i < 15; i++){
				for(int j = 3; j < 15; j++){
					if(i != j){
						ValeurCarte vc1 = ValeurCarte.getName(i);
						ValeurCarte vc2 = ValeurCarte.getName(j);
						if(Collections.frequency(valCartes, vc1) == 2 && Collections.frequency(valCartes, vc2) == 2){
							ArrayList<ValeurCarte> toRemove = new ArrayList<ValeurCarte>();
							toRemove.add(vc1);
							toRemove.add(vc2);
							valCartes.removeAll(toRemove);
							if(vc1.getValue() > vc2.getValue())
								return new TwoPairs(listeCartes, vc1, vc2, valCartes.get(0));
							else if(vc1.getValue() < vc2.getValue())
								return new TwoPairs(listeCartes, vc2, vc1, valCartes.get(0));
						}
					}
				}
			}
		}else if(isPaire(valCartes)){
			for(int i = 2; i < 15; i++){
				ValeurCarte vc = ValeurCarte.getName(i);
				if(Collections.frequency(valCartes, vc) == 2){
					ArrayList<ValeurCarte> toRemove = new ArrayList<ValeurCarte>();
					toRemove.add(vc);
					valCartes.removeAll(toRemove);
					return new Paire(listeCartes, vc, valCartes);
				}
			}
		}
		return new HighCard(listeCartes, valCartes);
	}
	
	
	/**
	 * Cette classe détermine si les cartes passées en paramètres constituent une couleur
	 * @param cartes la liste des cartes à tester
	 * @return true si les cartes passées en paramètres correspondent à une couleur
	 */
	public static boolean isColour(ArrayList<Carte> cartes){
		for(int i = 0; i < cartes.size()-1; i++){
			if(!(cartes.get(i).getCouleur().equals(cartes.get(i+1).getCouleur())))
				return false;
		}
		return true;
	}
	
	/**
	 * Cette classe détermine si les cartes passées en paramètres constituent une suite
	 * @param toTest la liste des cartes à tester
	 * @return true si les cartes passées en paramètres correspondent à une suite
	 */
	public static boolean isSuite(ArrayList<ValeurCarte> toTest){
		boolean suite = true;
		for(int i = 0; i < toTest.size()-1; i++){
			if(!(toTest.get(i).getValue() == toTest.get(i+1).getValue()-1))
				suite = false;
		}
		
		//cas particulier
		if(toTest.contains(ValeurCarte.As)
				&& toTest.contains(ValeurCarte.Deux)
				&& toTest.contains(ValeurCarte.Trois)
				&& toTest.contains(ValeurCarte.Quatre)
				&& toTest.contains(ValeurCarte.Cinq))
			suite = true;
		
		return suite;
	}
	
	/**
	 * Cette classe détermine si les cartes passées en paramètres constituent un brelan
	 * @param toTest la liste des cartes à tester
	 * @return true si les cartes passées en paramètres correspondent à un brelan
	 */
	public static boolean isTriple(ArrayList<ValeurCarte> toTest){
		boolean triple = false;
		
		for(int i = 2; i < 15; i++){
			
			if(Collections.frequency(toTest, ValeurCarte.getName(i)) == 3)
				triple = true;
		}
		
		return triple;
	}
	
	/**
	 * Cette classe détermine si les cartes passées en paramètres constituent une paire
	 * @param toTest la liste des cartes à tester
	 * @return true si les cartes passées en paramètres correspondent à une paire
	 */
	public static boolean isPaire(ArrayList<ValeurCarte> toTest){		
		for(int i = 2; i < 15; i++){
			if(Collections.frequency(toTest, ValeurCarte.getName(i)) == 2){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Cette classe détermine si les cartes passées en paramètres constituent un carré
	 * @param toTest la liste des cartes à tester
	 * @return true si les cartes passées en paramètres correspondent à un carré
	 */
	public static boolean isFour(ArrayList<ValeurCarte> toTest){
		boolean four = false;
		
		for(int i = 2; i < 15; i++){
			
			if(Collections.frequency(toTest, ValeurCarte.getName(i)) == 4)
				four = true;
		}
		
		return four;
	}
	
	/**
	 * Cette classe détermine si les cartes passées en paramètres constituent un full
	 * @param toTest la liste des cartes à tester
	 * @return true si les cartes passées en paramètres correspondent à un full
	 */
	public static boolean isFull(ArrayList<ValeurCarte> toTest){
		boolean full = false;
		
		for(int i = 2; i < 15; i++){
			for(int j = 3; j < 15; j++){
				if(i != j){
					if((Collections.frequency(toTest, ValeurCarte.getName(i)) == 2 
							&& Collections.frequency(toTest, ValeurCarte.getName(j)) == 3)
						|| (Collections.frequency(toTest, ValeurCarte.getName(i)) == 3 
							&& Collections.frequency(toTest, ValeurCarte.getName(j)) == 2))
						full = true;
				}
			}
		}
		
		return full;
	}
	
	/**
	 * Cette classe détermine si les cartes passées en paramètres constituent deux paires
	 * @param toTest la liste des cartes à tester
	 * @return true si les cartes passées en paramètres correspondent à deux paires
	 */
	public static boolean isDeuxDeux(ArrayList<ValeurCarte> toTest){
		boolean deuxPaires = false;
		
		for(int i = 2; i < 15; i++){
			for(int j = 3; j < 15; j++){
				if(i != j){
					if(Collections.frequency(toTest, ValeurCarte.getName(i)) == 2 && Collections.frequency(toTest, ValeurCarte.getName(j)) == 2)
						deuxPaires = true;
				}
			}
		}
		return deuxPaires;
	}	
}
