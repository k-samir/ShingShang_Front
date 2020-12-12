package abstraction.move;

import abstraction.board.Square;
import abstraction.pawn.Bushi;
/** 
 * Un déplacement
 */
public class Move {
	/* * * * * * * * * * *
	 *	Attributs 
	 * * * * * * * * * * */
	protected Bushi movedBushi;
	protected Square destination;
	protected boolean shingshang; // true si ce déplacement engendre un shingshang 
	
	/* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
	/**
	 * Initialise le déplacement 
	 * @param movedBushi la pièce à déplacer
	 * @param destination la case d'arrivée
	 * @param ss vrai si le déplacement entraine un shing shang
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
	 * @return la pièce à déplacer
	 */
	public Bushi getMovedBushi() {
		return movedBushi;
	}
	
	/**
	 * 
	 * @param movedBushi la pièce à déplacer
	 */
	public void setMovedBushi(Bushi movedBushi) {
		this.movedBushi = movedBushi;
	}
	
	/**
	 * 
	 * @return la case d'arrivée
	 */
	public Square getDestination() {
		return destination;
	}
	
	/**
	 * 
	 * @param destination la case d'arrivée
	 */
	public void setDestination(Square destination) {
		this.destination = destination;
	}
}
