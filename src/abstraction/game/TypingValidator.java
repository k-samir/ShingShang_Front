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
	 * V�rifie la sasie des coordonn�es du pi�ce choisie
	 * 
	 * @param game le jeu
	 * @param row le num�ro de ligne
	 * @param col le num�ro de colonne
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
	 * V�rifie la saisie des coordonn�es de la case d'arriv�e d'un d�placement
	 * @param board le plateau de jeu
	 * @param row le num�ro de ligne
	 * @param col le num�ro de colonne
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

