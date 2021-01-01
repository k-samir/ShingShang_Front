package control;

import abstraction.game.Console;
import abstraction.game.Game;

public class ControlMoveBushi {
	int row;
	int col;
	Game game;
	
	public ControlMoveBushi(int row,int col,Game game) {
		this.row = row;
		this.col = col;
		this.game = game;
		game.setSelectedMove1(Console.askChoseMove(game.getBoard(), row, col));
		game.playTurn();
	}
	

	
	
}
