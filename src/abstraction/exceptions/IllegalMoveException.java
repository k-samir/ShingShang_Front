package abstraction.exceptions;

public class IllegalMoveException extends Exception {
	public IllegalMoveException() {
		super("Erreur - d�placement non autoris�.");
	}
}
