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
			
		if(b != null) {
			game.setSelectedBushi1(b);
			game.getSelectedBushi1().calculateLegalMoves();
		}
		
		//game.playTurn();
		
		
	}
	public ArrayList<Tuple> getLegalMoves(){
		
		ArrayList<Tuple> legal = new ArrayList<Tuple>();
		for(Move m: game.getBoard().getLegalMoves()) {
			legal.add(new Tuple(m.getDestination().getRow(),m.getDestination().getCol()));
		}
		
		return legal;
		
	}
	
}
