package partie;

import java.util.ArrayList;

import cartes.Carte;
import cartes.CouleurCarte;
import cartes.Main;
import cartes.ValeurCarte;

public class Test {

	public static void main(String[] args){
		
		showTest();
		
		//Partie p = new Partie();
		/*Table t = new Table();
		
		Joueur j1 = new Joueur("Leo");
		Joueur j2 = new Joueur("Thomas");
		Joueur j3 = new Joueur("Vincent");
		Joueur j4 = new Joueur("David");
	
		t.addPlayer(j1);
		t.addPlayer(j2);
		t.addPlayer(j3);
		t.addPlayer(j4);
		
		t.start();
		System.out.println(t.toString());*/
	}
	
	@SuppressWarnings("unchecked")
	public static void showTest(){
		ArrayList<Carte> cartes = new ArrayList<Carte>();
		
		//////////////////////////////////////////////////////////////////////////
		
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Dix));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Valet));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Dame));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Roi));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.As));
		
		Main quinteFlushRoyale = Main.getMain((ArrayList<Carte>)cartes.clone());
		
		//////////////////////////////////////////////////////////////////////////

		cartes.removeAll(cartes);
		
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Neuf));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Dix));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Valet));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Dame));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Roi));
		
		Main quinteFlush = Main.getMain((ArrayList<Carte>)cartes.clone());
		
		//////////////////////////////////////////////////////////////////////////

		cartes.removeAll(cartes);
		
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Pique, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Trefle, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Coeur, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Roi));
		
		Main carre = Main.getMain((ArrayList<Carte>)cartes.clone());
		
		//////////////////////////////////////////////////////////////////////////

		cartes.removeAll(cartes);
		
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Pique, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Trefle, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Coeur, ValeurCarte.Roi));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Roi));
		
		Main full = Main.getMain((ArrayList<Carte>)cartes.clone());
		
		//////////////////////////////////////////////////////////////////////////
		
		cartes.removeAll(cartes);
		
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Cinq));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Deux));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Valet));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Roi));
		
		Main couleur = Main.getMain((ArrayList<Carte>)cartes.clone());

		//////////////////////////////////////////////////////////////////////////
				
		cartes.removeAll(cartes);
		
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Pique, ValeurCarte.Cinq));
		cartes.add(new Carte(CouleurCarte.Trefle, ValeurCarte.Six));
		cartes.add(new Carte(CouleurCarte.Trefle, ValeurCarte.Sept));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Huit));
		
		Main suite = Main.getMain((ArrayList<Carte>)cartes.clone());

		cartes.removeAll(cartes);
		
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.As));
		cartes.add(new Carte(CouleurCarte.Pique, ValeurCarte.Deux));
		cartes.add(new Carte(CouleurCarte.Trefle, ValeurCarte.Trois));
		cartes.add(new Carte(CouleurCarte.Trefle, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Cinq));
		
		Main suiteAvecAs = Main.getMain((ArrayList<Carte>)cartes.clone());
		
		//////////////////////////////////////////////////////////////////////////
				
		cartes.removeAll(cartes);
		
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Pique, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Trefle, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Valet));
		cartes.add(new Carte(CouleurCarte.Coeur, ValeurCarte.Roi));
		
		Main triple = Main.getMain((ArrayList<Carte>)cartes.clone());
		
		//////////////////////////////////////////////////////////////////////////
		
		cartes.removeAll(cartes);
		
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Pique, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Trefle, ValeurCarte.Roi));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Valet));
		cartes.add(new Carte(CouleurCarte.Coeur, ValeurCarte.Roi));
		
		Main doublePaire = Main.getMain((ArrayList<Carte>)cartes.clone());
		
		//////////////////////////////////////////////////////////////////////////
		
		cartes.removeAll(cartes);
		
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Pique, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Trefle, ValeurCarte.Dame));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Valet));
		cartes.add(new Carte(CouleurCarte.Coeur, ValeurCarte.Roi));
		
		Main paire = Main.getMain((ArrayList<Carte>)cartes.clone());
		
		//////////////////////////////////////////////////////////////////////////
		
cartes.removeAll(cartes);
		
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Quatre));
		cartes.add(new Carte(CouleurCarte.Pique, ValeurCarte.Neuf));
		cartes.add(new Carte(CouleurCarte.Trefle, ValeurCarte.Deux));
		cartes.add(new Carte(CouleurCarte.Carreau, ValeurCarte.Six));
		cartes.add(new Carte(CouleurCarte.Coeur, ValeurCarte.Roi));
		
		Main rien = Main.getMain((ArrayList<Carte>)cartes.clone());
		
		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////
		
		System.out.println(quinteFlushRoyale);
		System.out.println(quinteFlush);
		System.out.println(carre);
		System.out.println(full);
		System.out.println(couleur);
		System.out.println(suite);
		System.out.println(suiteAvecAs);
		System.out.println(triple);
		System.out.println(doublePaire);
		System.out.println(paire);
		System.out.println(rien);
	}
	
}
