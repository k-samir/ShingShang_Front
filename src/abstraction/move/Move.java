package abstraction.move;

import abstraction.board.Square;
import abstraction.pawn.Bushi;
/** 
 * Un d�placement
 */
public class Move {
	/* * * * * * * * * * *
	 *	Attributs 
	 * * * * * * * * * * */
	protected Bushi movedBushi;
	protected Square destination;
	protected boolean shingshang; // true si ce d�placement engendre un shingshang 
	
	/* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
	/**
	 * Initialise le d�placement 
	 * @param movedBushi la pi�ce � d�placer
	 * @param destination la case d'arriv�e
	 * @param ss vrai si le d�placement entraine un shing shang
	 */
	public Move(Bushi movedBushi, Square destination, boolean ss){
		this.movedBushi = movedBushi;
		this.destination = destination;
		this.shingshang = ss;
	}
	/* * * * * * * * * * *
	 *	Getters & Setters
	 * * * * * * * * * * */
	
	public boolean isShingShang() {
		return shingshang;
	}
	
	/**
	 * 
	 * @return la pi�ce � d�placer
	 */
	public Bushi getMovedBushi() {
		return movedBushi;
	}
	
	/**
	 * 
	 * @param movedBushi la pi�ce � d�placer
	 */
	public void setMovedBushi(Bushi movedBushi) {
		this.movedBushi = movedBushi;
	}
	
	/**
	 * 
	 * @return la case d'arriv�e
	 */
	public Square getDestination() {
		return destination;
	}
	
	/**
	 * 
	 * @param destination la case d'arriv�e
	 */
	public void setDestination(Square destination) {
		this.destination = destination;
	}
}
