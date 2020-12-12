package abstraction.pawn;

import java.util.List;

import abstraction.board.Board;
import abstraction.game.Player;
import abstraction.move.Jump;
import abstraction.move.Move;

public class Lion extends Bushi {
	/* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
	/**
	 * Initialise le lion
	 * 
	 * @param p le joueur propriétaire
	 */
	public Lion(Player p) {
		super(2,p);
	}
	
	/**
	 *  Initialise le lion
	 *  
	 * @param b le plateau de jeu
	 * @param p le joueur propriétaire
	 * @param r le numéro de ligne
	 * @param c le numéro de colonne
	 */
	public Lion(Board b,Player p, int r, int c) {
		super(b,2,p,r,c);
	}
	
	/* * * * * * * * * * *
	 *	Méthodes 
	 * * * * * * * * * * */
	public String toString() {
		return "L" + this.player.getNumber();
	}

	/**
	 * Détermine tous les déplacements possibles
	 */
	public void calculateLegalMoves() {
		int newRow = row, newCol = col;
		for(int i = -1 ; i <= 1 ; i++) {
			for(int j = -1 ; j <= 1 ; j++) {
				newRow = row + i;
				newCol = col + j;
				if(board.isInside(newRow, newCol) && board.getSquare(newRow, newCol).isEnabled()) {
					if(board.getSquare(newRow, newCol).isVoid()) {
						board.getLegalMoves().add(new Move(this, board.getSquare(newRow, newCol),false));
						board.getSquare(newRow, newCol).setAccessible(true); 
					} else {
						calculateJump(i,j);							
					}
				}
			}
		}
	}
}
