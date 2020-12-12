package abstraction.move;

import abstraction.board.Square;
import abstraction.pawn.Bushi;
/**
 * Le saut
 */
public class Jump extends Move {
	/* * * * * * * * * * *
	 *	Attributs 
	 * * * * * * * * * * */
	private Bushi caughtBushi;

	/* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
	/**
	 * Initilise un saut 
	 * 
	 * @param movedBushi la pièce à déplacer
	 * @param destination la case d'arrivée
	 * @param ss vrai si le déplacement entraine un shing shang
	 * @param cb la pièce à attraper
	 */
	public Jump(Bushi movedBushi, Square destination, boolean ss, Bushi cb) {
		super(movedBushi, destination, ss);
		caughtBushi = cb;
	}

	/**
	 * Initialise un saut 
	 * 
	 * @param movedBushi la pièce à déplacer
	 * @param destination la case d'arrivée
	 * @param ss vrai si le déplacement entraine un shing shang
	 */
	
	public Jump(Bushi movedBushi, Square destination, boolean ss) {
		super(movedBushi, destination, ss);
		caughtBushi = null;
	}
	
	/* * * * * * * * * * *
	 *	Getters & Setters
	 * * * * * * * * * * */
	/**
	 * 
	 * @return la pièce à attraper
	 */
	public Bushi getCaughtBushi() {
		return caughtBushi;
	}
	/**
	 * 
	 * @param caughtBushi la pièce à attraper
	 */
	public void setCaughtBushi(Bushi caughtBushi) {
		this.caughtBushi = caughtBushi;
	}
	
	/* * * * * * * * * * *
	 *	Méthodes
	 * * * * * * * * * * */
	/**
	 * Détermine si le saut est un saut par dessus une pièce ennemie
	 * en regardant si une pièce a été attrapée
	 * @return true s'il s'agit d'un saut par dessus un une pièce ennemie
	 */
	public boolean isJumpEnemy() {
		return (caughtBushi != null);
	}

}
