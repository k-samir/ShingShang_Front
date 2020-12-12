package abstraction.pawn;

import java.util.List;

import abstraction.board.Board;
import abstraction.game.Player;
import abstraction.move.Jump;
import abstraction.move.Move;

public class Dragon extends Bushi{
	/* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
	/**
	 * Initialise le dragon
	 * @param p le joueur propri�taire
	 */
	public Dragon(Player p) {
		super(3,p);
	}
	
	/**
	 *  Initialise le dragon
	 *  
	 * @param b le plateau de jeu
	 * @param p le joueur propri�taire
	 * @param r le num�ro de ligne
	 * @param c le num�ro de colonne
	 */
	public Dragon(Board b, Player p, int r, int c) {
		super(b,3,p,r,c);
	}
	
	/* * * * * * * * * * *
	 *	M�thodes 
	 * * * * * * * * * * */
	
	public String toString() {
		return "D" + this.player.getNumber();
	}
	/**
	 * D�termine tous les d�placements possibles
	 */
	public void calculateLegalMoves() {
		calculateLegalJump();
	}
}

