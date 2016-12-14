package cartes;

/**
 * @version 1.0
 * @author L�o
 * Cette �num�ration comporte les quatre "couleurs" pouvant �tre prises par une carte
 */
public enum CouleurCarte {
	Coeur ("Coeur"),
	Pique ("Pique"),
	Trefle ("Trefle"),
	Carreau ("Carreau");
	
	private String name = "";

	/**
	 * Constructeur
	 * @param name le nom de la couleur de la carte
	 */
	CouleurCarte(String name){
		this.name = name;
	}

	/**
	 * retourne la couleur sous forme de chaine de caractere
	 */
	public String toString(){
		return name;
	}
}
