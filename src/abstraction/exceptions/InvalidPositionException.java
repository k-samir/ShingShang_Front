package abstraction.exceptions;

import abstraction.board.Board;

public class InvalidPositionException extends Exception {
	public InvalidPositionException() {
		super("Erreur - les coordonnées doivent être comprises entre 0 et " + (Board.size-1));
	}
}
