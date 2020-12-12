package abstraction.board;

import abstraction.pawn.Bushi;
/**
 * La case
 */
public class Square {
	/* * * * * * * * * * *
	 *	Attributs 
	 * * * * * * * * * * */

	protected int row;
	protected int col;
	protected Bushi bushi;
	protected boolean enabled; 
	protected boolean accessible;
	
	/* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
	/**
	 * Initialise une case
	 * @param x le num�ro de ligne
	 * @param y le num�ro de colonne
	 */
	public Square(int x, int y) {
		row = x;
		col = y;
		bushi = null;
		enabled = true;
		accessible = false;
	}
	
	/**
	 * Initialise une case 
	 * @param x le num�ro de ligne
	 * @param y le num�ro de colonne
	 * @param b la pi�ce occupant la case
	 */
	public Square(int x, int y, Bushi b) {
		row = x;
		col = y;
		bushi = b;
		enabled = true;
		accessible = false;
	}

	/* * * * * * * * * * *
	 *	Getters & Setters
	 * * * * * * * * * * */

	/**
	 * 
	 * @return le num�ro de ligne
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 
	 * @param row le num�ro de ligne
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * 
	 * @return le num�ro de colonne
	 */
	public int getCol() {
		return col;
	}

	/**
	 * 
	 * @param col le num�ro de colonne
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * 
	 * @return la pi�ce
	 */
	public Bushi getBushi() {
		return bushi;
	}

	/**
	 * 
	 * @param bushi la pi�ce
	 */
	public void setBushi(Bushi bushi) {
		this.bushi = bushi;
	}

	/**
	 * 
	 * @return true si la case est activ�e
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * 
	 * @param enabled la valeur de l'attribut enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * 
	 * @return true si la case est vide
	 */
	public boolean isVoid() {
		return (bushi == null);
	}
	
	/**
	 * 
	 * @return true si la case est accessible
	 */
	public boolean isAccessible() {
		return accessible;
	}

	/**
	 * 
	 * @param accessible la valeur de l'attribut accessible
	 */
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	
	public String toString() {
		if(enabled) {
			
			if(bushi != null) 
				return bushi.toString();
			
			return "--";
		
		} else {
			return "  ";
		}
	}	
}
