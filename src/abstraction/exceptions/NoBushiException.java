package abstraction.exceptions;

public class NoBushiException extends Exception {
	public NoBushiException(){
		super("Erreur - La case est vide.");
	}
}
