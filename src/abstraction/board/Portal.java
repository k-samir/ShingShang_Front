package abstraction.board;

import abstraction.game.Player;
import abstraction.pawn.Dragon;

/** 
 * Le portail
 */
public class Portal extends Square{
	/* * * * * * * * * * *
	 *	Attributs 
	 * * * * * * * * * * */

	private Player owner;
	
	/* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
	/**
	 * Initialise le portail
	 * @param x le num�ro de ligne
	 * @param y le num�ro de colonne
	 */
	public Portal(int x, int y){
		super(x,y);
	}
	
	/* * * * * * * * * * *
	 *	Getters & Setters
	 * * * * * * * * * * */

	/**
	 * 
	 * @param p le joueur propri�taire du portail
	 */
	public void setOwner(Player p) {
		owner = p;
	}
	
	/**
	 *  
	 * @return le joueur propri�taire du portail
	 */
	public Player getOwner() {
		return owner;
	}
	
	/* * * * * * * * * * *
	 *	M�thodes
	 * * * * * * * * * * */
	/**
	 * Retourne true si le portail est occup� par un ennemi
	 * @return true si le portail est occup� par une pi�ce ennemie
	 */
	public boolean isOccupiedByEnemy() {
		if(!isVoid()) {
			if(!(bushi.getPlayer().equals(owner))) {
				if (bushi instanceof Dragon) {
					return true;
				}
			}
				
			
			}
		
		return false;
	}		
	
	@Override
	public String toString() {
		if(isVoid()) {
			return "P" + owner.getNumber();
		} else {
			return bushi.toString();
		}
	}	


}
