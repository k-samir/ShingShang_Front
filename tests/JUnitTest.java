import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import abstraction.board.Board;
import abstraction.game.Game;
import abstraction.game.Player;
import abstraction.move.Jump;
import abstraction.move.Move;
import abstraction.pawn.Bushi;
import abstraction.pawn.Dragon;
import abstraction.pawn.Monkey;

public class JUnitTest {
	
	private static Game game;
	private static Board board;
	private static Player player1;
	private static Player player2;
	private static Bushi bushi;
	private static Move move;
	
	@BeforeClass
	public static void setup(){
		game = new Game();
		player1 = new Player(1, "Joueur 1");
		player2 = new Player(2, "Joueur 2");
		board = new Board(player1,player2);
		game.setBoard(board);
		game.setTurnPlayer(player1);
	}
	
	@After
	public void tearDown(){
		game = new Game();
		player1 = new Player(1, "Joueur 1");
		player2 = new Player(2, "Joueur 2");
		board = new Board(player1,player2);
		game.setBoard(board);
		game.setTurnPlayer(player1);
	}
	
	@Test
	public void executeMoveTest() {
		boolean result = false;
		Bushi bushi = board.getSquare(1, 1).getBushi();
		move = new Move(bushi,board.getSquare(2,2),false);
		board.executeMove(move);
		result = board.getSquare(2,2).getBushi().equals(bushi) && board.getSquare(1,1).isVoid();
		Assert.assertTrue("devrait être vrai.", result);
	}
	
	@Test
	public void removeBushiTest() {
		boolean result = false;
		removeBushiSetup();
		board.executeMove(move);
		result = board.getSquare(2, 2).isVoid();
		Assert.assertTrue("devrait être vrai.", result);
	}
	
	public void removeBushiSetup() {
		board.getSquare(2,2).setBushi(new Monkey(board, player2, 2, 2));
		Bushi caughtBushi = board.getSquare(2,2).getBushi();
		bushi = board.getSquare(1,1).getBushi();
		move = new Jump(bushi, board.getSquare(3,3),false,caughtBushi);
	}
	
	@Test 
	public void isOverTest() {
		boolean result = false;
		isOverSetup();
		result = game.isOver();
		
		Assert.assertTrue("devrait être vrai.", result);
		
		tearDown();
		
		catchPlayerDragons();
		result=game.isOver();
		Assert.assertTrue("devrait être vrai.", result);
	}
	
	public void isOverSetup() {
		game.getBoard().getSquare(1,4).setBushi(new Dragon(board, player2, 1, 4));
		game.getBoard().getSquare(1,5).setBushi(new Dragon(board, player2, 1, 5));
	}
	
	public void catchPlayerDragons() {
		game.getBoard().getPlayer2().setLostDragons(2);
	}
	
	@Test 
	public void isOccupiedTest() {
		
	}
	
	@Test 
	public void nextTurn() {
		
	}
	
}
