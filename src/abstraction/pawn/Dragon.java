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
	 * @param p le joueur propriétaire
	 */
	public Dragon(Player p) {
		super(3,p);
	}
	
	/**
	 *  Initialise le dragon
	 *  
	 * @param b le plateau de jeu
	 * @param p le joueur propriétaire
	 * @param r le numéro de ligne
	 * @param c le numéro de colonne
	 */
	public Dragon(Board b, Player p, int r, int c) {
		super(b,3,p,r,c);
	}
	
	/* * * * * * * * * * *
	 *	Méthodes 
	 * * * * * * * * * * */
	
	public String toString() {
		return "D" + this.player.getNumber();
	}
	/**
	 * Détermine tous les déplacements possibles
	 */
	public void calculateLegalMoves() {
		calculateLegalJump();
	}
}

