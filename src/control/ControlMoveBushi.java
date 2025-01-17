package control;

import java.util.ArrayList;

import abstraction.game.Console;
import abstraction.game.Game;
import abstraction.move.Move;
import abstraction.pawn.Bushi;

public class ControlMoveBushi {
	int row;
	int col;
	Game game;
	
	public ControlMoveBushi(int row,int col,Game game) {
		this.row = row;
		this.col = col;
		this.game = game;
	
	}
	
	public void nextTurn() {
		game.nextTurn();
	}
	public void play() {
		game.setSelectedMove1(Console.askChoseMove(game.getBoard(), row, col));
		game.playTurn();
		
	}
	
	public boolean checkAdditionalMove() {
		return game.getBoard().isAdditionalTurn();

	}
	public void playAdditionalTurn() {
		game.setSelectedMove1(Console.askChoseMove(game.getBoard(), row, col));
		game.playAdditionnalTurn();
	}
	
	
	public boolean checkShingshang() {
		boolean ret = false;
		if (game.getSelectedMove1().isShingShang()) {
			
			ret = true;
		}
		return ret;
	}
	public boolean checkMove() {
		boolean ret = false;
		ArrayList<Move> legal = game.getBoard().getLegalMoves();
		
		for(Move m : legal) {
			int x = m.getDestination().getRow();
			int y = m.getDestination().getCol();
			
			if(x == row && y == col) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	public Bushi getMove() {
		return game.getSelectedMove1().getMovedBushi();
	}
	
	

	
	
}
