package cartes;

/**
 * @version 1.0
 * @author Léo
 * Correspond à la valeur d'une carte
 */
public enum ValeurCarte {

	Deux (2),
	Trois (3),
	Quatre (4),
	Cinq (5),
	Six (6),
	Sept(7),
	Huit (8),
	Neuf (9),
	Dix (10),
	Valet (11),
	Dame (12),
	Roi (13),
	As(14);
	
	private int name;

	/**
	 * Constructeur
	 * @param name le nom de la valeur de la carte
	 */
	ValeurCarte(int name){
		this.name = name;
	}
	
	/**
	 * Cette méthode retourne le nom d'une valeur (as, roi, deux, dix...) en fonction d'une valeur
	 * passée en paramètres qui correspond à la valeur numérique de la carte
	 * @param value la valeur de la carte que l'on cherche
	 * @return le nom "français" pour cette valeur de carte dans l'enumeration (2 pour deux, 14 pour as)
	 */
	public static ValeurCarte getName(int value){
		for(ValeurCarte vc : ValeurCarte.values()){
			if(vc.getValue() == value)
				return vc;
		}
		return null; //impossible
	}
	
	/**
	 * Cette méthode renvoie la valeur de la carte sous forme entière (utile pour le tri par valeur)
	 * @return la valeur de la carte
	 */
	public int getValue(){
		return this.name;
	}

	/**
	 * retourne le nom de la valeur de la carte (as, roi, deux...)
	 */
	public String toString(){
		return this.name();
	}
	
	/**
	 * Cette méthode permet d'effectuer une comparaison entre deux cartes en fonction de leur valeur
	 * @param v un objet de type valeur carte correspondant à la valeur de la carte que l'on souhaite comparer
	 * @return 1 si la valeur de cette carte est supérieure à clle passée en paramètre, 0 si elle est égale
	 * et -1 si elle est inférieure.
	 */
	public int CompareTo(ValeurCarte v){
		if(this.name > v.name)
			return 1;
		else if(this.name < v.name)
			return -1;
		else
			return 0;
	}
}
