package control;

import abstraction.game.Game;

public class ControlPlayerName {

	String player1;
	String player2;
	Game game;
	public ControlPlayerName(String p1, String p2,Game game) {
		this.player1 = p1;
		this.player2 = p2;
		this.game = game;
		game.setName(p1, p2);
	}
	
	
	
}
