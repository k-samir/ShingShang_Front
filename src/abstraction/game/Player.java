package abstraction.game;

/**
 * Le joueur
 */
public class Player {
	/* * * * * * * * * * *
	 *	Attributs 
	 * * * * * * * * * * */

	private String name;
	private int number;
	private int lostDragons;
	private String color;
	
	/* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
	/**
	 * Initialise le joueur
	 * @param number le numéro du joueur
	 * @param nom le nom du joueur
	 */
	public Player(int number,String nom) {
		
		this.number = number;
		this.name = nom;
		lostDragons = 0;
	}
	
	/* * * * * * * * * * *
	 *	Getters & Setters 
	 * * * * * * * * * * */

	/**
	 * 
	 * @return le nom du jouer
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return le numéro du joueur
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * 
	 * @return le nombre de dragons perdus
	 */
	public int getLostDragons() {
		return lostDragons;
	}
	
	/**
	 * 
	 * @param i le nombre de dragons perdus
	 */
	public void setLostDragons(int i) {
		lostDragons = i;
	}
	/* * * * * * * * * * *
	 *	Méthodes 
	 * * * * * * * * * * */

	/**
	 * Met à jour le nombre de dragons perdus par le joueur
	 */
	public void loseDragon() {
		lostDragons++;
		System.out.println("loose dragon");
	}

	/**
	 * Compare le joueur avec un autre joueur
	 * 
	 * @param other un autre joueur 
	 * @return true s'il s'agit du même joueur
	 */
	public boolean equals(Player other) {	
		if (lostDragons != other.lostDragons)
			return false;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (number != other.number)
			return false;
		
		return true;
	}

	
}
