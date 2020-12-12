package abstraction.exceptions;

public class BushiHasMovedException extends Exception {

	public BushiHasMovedException(){
		super("La pièce a déjà été utilisée.");
	}
}
