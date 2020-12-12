package abstraction.game;

import abstraction.board.Board;
import abstraction.exceptions.BushiHasMovedException;
import abstraction.exceptions.IllegalMoveException;
import abstraction.exceptions.InvalidPositionException;
import abstraction.exceptions.NoBushiException;
import abstraction.exceptions.OpposingBushiMoveException;
/**
 *	Validateur de saisie
 */
public class TypingValidator {

	/**
	 * Vérifie la sasie des coordonnées du pièce choisie
	 * 
	 * @param game le jeu
	 * @param row le numéro de ligne
	 * @param col le numéro de colonne
	 * @throws InvalidPositionException
	 * @throws NoBushiException
	 * @throws BushiHasMovedException
	 * @throws OpposingBushiMoveException
	 */
	public static void checkBushiChoice(Game game, int row, int col)
			throws InvalidPositionException, NoBushiException, BushiHasMovedException, OpposingBushiMoveException {
		Board board = game.getBoard();
		if(!board.isInside(row,col))
			throw new InvalidPositionException();
		if(board.getSquare(row, col).isVoid())
			throw new NoBushiException();
		
		if(board.isAdditionalTurn() && board.getSquare(row, col).getBushi().isHasMoved())
			throw new BushiHasMovedException();
		
		if(game.isNotTurnPlayer(board.getSquare(row,col).getBushi().getPlayer()))
			throw new OpposingBushiMoveException();
	}

	/**
	 * Vérifie la saisie des coordonnées de la case d'arrivée d'un déplacement
	 * @param board le plateau de jeu
	 * @param row le numéro de ligne
	 * @param col le numéro de colonne
	 * @throws InvalidPositionException
	 * @throws IllegalMoveException
	 */
	public static void checkMoveChoice(Board board, int row, int col)
			throws InvalidPositionException, IllegalMoveException {
		if(board.isInside(row,col) == false)
			throw new InvalidPositionException();
		if(board.notLegalMove(row, col))
			throw new IllegalMoveException();
	}	
}

