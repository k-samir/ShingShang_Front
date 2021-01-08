package control;

import java.util.ArrayList;

import abstraction.game.Console;
import abstraction.game.Game;
import abstraction.move.Move;
import abstraction.pawn.Bushi;

public class ControlChooseBushi {
	int row;
	int col;
	Game game;
	
	public ControlChooseBushi(int row,int col,Game game) {
		
		this.row = row;
		this.col = col;
		this.game = game;
		
		Bushi b = Console.askChoseBushi(game, row, col);
			
		// Set selected Bushi in Game
		if(b != null) {
			game.setSelectedBushi1(b);
			game.getSelectedBushi1().calculateLegalMoves();
		}
		
		
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList<Tuple> getLegalMoves(){
		
		// Search and update legalMoves in Game
		ArrayList<Tuple> legal = new ArrayList<Tuple>();
		for(Move m: game.getBoard().getLegalMoves()) {
			legal.add(new Tuple(m.getDestination().getRow(),m.getDestination().getCol()));
		}
		
		return legal;
		
	}
	
}
