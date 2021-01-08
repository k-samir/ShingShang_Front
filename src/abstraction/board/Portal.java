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
	 * @param x le numéro de ligne
	 * @param y le numéro de colonne
	 */
	public Portal(int x, int y){
		super(x,y);
	}
	
	/* * * * * * * * * * *
	 *	Getters & Setters
	 * * * * * * * * * * */

	/**
	 * 
	 * @param p le joueur propriétaire du portail
	 */
	public void setOwner(Player p) {
		owner = p;
	}
	
	/**
	 *  
	 * @return le joueur propriétaire du portail
	 */
	public Player getOwner() {
		return owner;
	}
	
	/* * * * * * * * * * *
	 *	Méthodes
	 * * * * * * * * * * */
	/**
	 * Retourne true si le portail est occupé par un ennemi
	 * @return true si le portail est occupé par une pièce ennemie
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
