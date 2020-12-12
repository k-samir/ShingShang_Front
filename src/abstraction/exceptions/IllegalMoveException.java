package abstraction.exceptions;

public class IllegalMoveException extends Exception {
	public IllegalMoveException() {
		super("Erreur - déplacement non autorisé.");
	}
}
