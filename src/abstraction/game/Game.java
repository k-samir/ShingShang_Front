package abstraction.game;

import abstraction.board.Board;
import abstraction.move.Move;
import abstraction.pawn.Bushi;

/**
 * Le jeu Shing Shang
 */
public class Game {
	/*
	 * * * * * * * * * * * Attributs * * * * * * * * *
	 */

	private Player turnPlayer;
	private int turnNumber;
	private Board board;
	private String name1,name2;
	private Bushi selectedBushi1;

	private Move selectedMove1;

	/*
	 * * * * * * * * * * * Constructeurs * * * * * * * * *
	 */

	public Bushi getSelectedBushi1() {
		return selectedBushi1;
	}

	public void setSelectedBushi1(Bushi selectedBushi1) {
		this.selectedBushi1 = selectedBushi1;
	}

	public Move getSelectedMove1() {
		return selectedMove1;
	}

	public void setSelectedMove1(Move selectedMove1) {
		this.selectedMove1 = selectedMove1;
	}

	public Game() {
		turnNumber = 1;
	}

	/*
	 * * * * * * * * * * * Getters & Setters * * * * * * * * *
	 */

	/**
	 * 
	 * @return le joueur du tour en cours
	 */
	public Player getTurnPlayer() {
		return turnPlayer;
	}

	/**
	 * 
	 * @param turnPlayer le joueur du tour en cours
	 */
	public void setTurnPlayer(Player turnPlayer) {
		this.turnPlayer = turnPlayer;
	}

	/**
	 * 
	 * @return le nombre de tour
	 */
	public int getTurnNumber() {
		return turnNumber;
	}

	/**
	 * 
	 * @param turnNumber le nombre de tour
	 */
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	/**
	 * 
	 * @return le plateau de jeu
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * 
	 * @param board le plateau de jeu
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/*
	 * * * * * * * * * * * Methodes * * * * * * * * *
	 */

	/**
	 * Creer un plateau et l'affecte e l'attribut board du jeu
	 */
	public void createBoard() {
		/* PREVIOUS CODE
		String name1 = Console.askName(1);
		String name2 = Console.askName(2);
		*/
		String name1 = this.name1;
		String name2 = this.name2;

		Player p1 = new Player(1, name1);
		Player p2 = new Player(2, name2);

		board = new Board(p1, p2);
		turnPlayer = p1;
		
	
		
	}

	/**
	 * Demarre le jeu et gere le deroulement de jeu
	 */
	public void start() {
		//showName();
	//	createBoard();
		//while (notOver()) {
		//	playTurn();
	//	}
	//	end();
	}

	/**
	 * Indique si le jeu n'est pas termine
	 * 
	 * @return true si le jeu est toujours en cours
	 */
	private boolean notOver() {
		return !isOver();
	}

	/**
	 * Indique si le jeu est termine en recherchant le nom du gagnant
	 * 
	 * @return true s'il existe un gagnant
	 */
	public boolean isOver() { // retirer le static
		return (board.winner() != null);
	}

	/**
	 * Gere le deroulement d'un tour
	 */
	

	public void playTurn() {
		System.out.println("PLAY TURN");
		Bushi selectedBushi;
		Move selectedMove;
		//boolean yesShowLegalMoves;

		//board.show();
		//System.out.println(turnPlayer.getName() + ", e toi de jouer ! :)");
		selectedBushi = this.selectedBushi1;
	
		System.out.println("Piece selectionnee : " + selectedBushi.toString());
		
		//selectedBushi.calculateLegalMoves();
		
		//yesShowLegalMoves = true;
		//if (yesShowLegalMoves)
		//board.showLegalMoves();

		//	selectedMove = Console.askChoseMove(board);
		selectedMove = selectedMove1;
		
		
		board.executeMove(selectedMove);
		
		//CHECK IF SHINGSHANG
		
		
		if (selectedMove.isShingShang()) {
			//Console.askShingShangChoice(board, selectedMove.getMovedBushi());
			System.out.println("SHINGSHANG");
			board.executeShingShang(selectedMove.getMovedBushi());
		}
		
		
		/**
		if (board.isAdditionalTurn()) {
			playAdditionnalTurn();
		}*/

		//nextTurn();
	}

	/**
	 * Gere le deroulement d'un tour supplementaire lors d'un shing shang
	 */
	public void playAdditionnalTurn() {

		Bushi selectedBushi;
		Move selectedMove;
		boolean playerWantsToContinue = Console.askContinue();
		boolean yesShowLegalMoves;

		while (playerWantsToContinue && board.isAdditionalTurn()) {
			board.show();
			System.out.println(turnPlayer.getName() + ", tu peux choisir une autre piece :)");
			
			selectedBushi = selectedBushi1;
			
			System.out.println("Piece selectionnee : " + selectedBushi.toString());
			selectedBushi.calculateLegalMoves();

			yesShowLegalMoves = Console.askShowLegalMoves();
			if (yesShowLegalMoves)
				board.showLegalMoves();

			/*selectedMove = Console.askChoseMove(board);
			board.executeMove(selectedMove);

			if (selectedMove.isShingShang()) {
				Console.askShingShangChoice(board, selectedMove.getMovedBushi());
			}

			if (board.isAdditionalTurn()) {
				playerWantsToContinue = Console.askContinue();
			}*/

		}
		board.resetMovedBushis();
	}

	/**
	 * Passe au tour suivant et determine le joueur du tour
	 */
	public void nextTurn() {
		
		turnNumber++;
		if (turnNumber % 2 == 1)
			turnPlayer = board.getPlayer1();
		else
			turnPlayer = board.getPlayer2();
		System.out.println("Tour à : " + turnPlayer.getNumber());
		this.board.resetLegalModal();
	}

	/**
	 * Indique si ce n'est pas le tour du joueur
	 * 
	 * @param p un joueur
	 * @return true si le joueur du tour est different du joueur du tour
	 */
	public boolean isNotTurnPlayer(Player p) {
		return !turnPlayer.equals(p);
	}

	/**
	 * Indique le jeu est fini et affiche le nom du joueur
	 */
	private void end() {
		System.out.println("-- Fin de la partie --\n Le gagnant est " + board.winner().getName() + ".");
	}

	
	
	 /* public static void main(String args[]) {
	  
	  Game shingshang = new Game();
	  shingshang.start();
	  
	  }*/
	
	public void setName(String n1, String n2) {
		this.name1 = n1;
		this.name2 = n2;
	}
	

}