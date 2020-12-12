package abstraction.pawn;

import java.util.List;

import abstraction.board.Board;
import abstraction.game.Player;
import abstraction.move.Jump;
import abstraction.move.Move;

public class Monkey extends Bushi{
	/* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
	/**
	 *  Initialise le singe
	 *  
	 * @param p le joueur propriétaire
	 */
	public Monkey(Player p) {
		super(1,p);
	}
	
	/**
	 *  Initialise le singe
	 *  
	 * @param b le plateau de jeu
	 * @param p le joueur propriétaire
	 * @param r le numéro de ligne
	 * @param c le numéro de colonne
	 */
	public Monkey(Board b, Player p, int r, int c) {
		super(b,1,p,r,c);
	}
	
	/* * * * * * * * * * *
	 *	Méthodes 
	 * * * * * * * * * * */
	
	public String toString() {
		return "M" + this.player.getNumber();
	}

	/**
	 * Détermine tous les déplacements possibles
	 */
	@Override
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
						newRow += i;
						newCol += j;
						if(board.isInside(newRow, newCol) && board.getSquare(newRow, newCol).isEnabled() && board.getSquare(newRow, newCol).isVoid()) {
								board.getLegalMoves().add(new Move(this, board.getSquare(newRow, newCol),false));
								board.getSquare(newRow, newCol).setAccessible(true);
						}
					} else { 
						calculateJump(i,j);							
					}
				}
			}
		}
	}
}
