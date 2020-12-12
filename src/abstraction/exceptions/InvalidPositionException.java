package abstraction.exceptions;

import abstraction.board.Board;

public class InvalidPositionException extends Exception {
	public InvalidPositionException() {
		super("Erreur - les coordonn�es doivent �tre comprises entre 0 et " + (Board.size-1));
	}
}
