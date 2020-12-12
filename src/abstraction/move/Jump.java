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
	 * @param movedBushi la pi�ce � d�placer
	 * @param destination la case d'arriv�e
	 * @param ss vrai si le d�placement entraine un shing shang
	 * @param cb la pi�ce � attraper
	 */
	public Jump(Bushi movedBushi, Square destination, boolean ss, Bushi cb) {
		super(movedBushi, destination, ss);
		caughtBushi = cb;
	}

	/**
	 * Initialise un saut 
	 * 
	 * @param movedBushi la pi�ce � d�placer
	 * @param destination la case d'arriv�e
	 * @param ss vrai si le d�placement entraine un shing shang
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
	 * @return la pi�ce � attraper
	 */
	public Bushi getCaughtBushi() {
		return caughtBushi;
	}
	/**
	 * 
	 * @param caughtBushi la pi�ce � attraper
	 */
	public void setCaughtBushi(Bushi caughtBushi) {
		this.caughtBushi = caughtBushi;
	}
	
	/* * * * * * * * * * *
	 *	M�thodes
	 * * * * * * * * * * */
	/**
	 * D�termine si le saut est un saut par dessus une pi�ce ennemie
	 * en regardant si une pi�ce a �t� attrap�e
	 * @return true s'il s'agit d'un saut par dessus un une pi�ce ennemie
	 */
	public boolean isJumpEnemy() {
		return (caughtBushi != null);
	}

}
