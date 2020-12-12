package abstraction.game;


import abstraction.board.Board;
import abstraction.move.Move;
import abstraction.pawn.Bushi;
/** 
 *Le jeu Shing Shang
 */
public class Game {
	/* * * * * * * * * * *
	 *	Attributs 
	 * * * * * * * * * * */
	
	private Player turnPlayer;
	private int turnNumber;
	private Board board;
	
	/* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
	
	public Game() {
		turnNumber = 1;
	}
	
	/* * * * * * * * * * *
	 *	Getters & Setters
	 * * * * * * * * * * */
	
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
	
	/* * * * * * * * * * *
	 *	Méthodes
	 * * * * * * * * * * */

	/**
	 * Créer un plateau et l'affecte à l'attribut board du jeu
	 */
	private void createBoard() {
		String name1 = Console.askName(1);
		String name2 = Console.askName(2);
		
		Player p1 = new Player(1, name1);
		Player p2 = new Player(2, name2);
		
		board = new Board(p1,p2);
		turnPlayer = p1;
	}
	
	/**
	 * Démarre le jeu et gère le déroulement de jeu
	 */
	public void start() {
		showName();
		createBoard();
		while(notOver()) {
			playTurn();
		}
		end();
	}
	
	/**
	 * Indique si le jeu n'est pas terminé
	 * 
	 * @return true si le jeu est toujours en cours
	 */
	private boolean notOver() {
		return !isOver();
	}
	
	/**
	 * Indique si le jeu est terminé en recherchant le nom du gagnant
	 * 
	 * @return true s'il existe un gagnant
	 */
	public boolean isOver() { // retirer le static 
		return (board.winner() != null);
	}
	
	/**
	 * Gère le déroulement d'un tour
	 */
	public void playTurn() {
		Bushi selectedBushi;
		Move selectedMove;
		boolean yesShowLegalMoves;
		
		board.show();
		System.out.println(turnPlayer.getName() + ", à toi de jouer ! :)");
		selectedBushi = Console.askChoseBushi(this);
		System.out.println("Pièce séléctionnée : " + selectedBushi.toString());
		selectedBushi.calculateLegalMoves();
		
		yesShowLegalMoves = Console.askShowLegalMoves();
		if(yesShowLegalMoves)
			board.showLegalMoves();

		selectedMove = Console.askChoseMove(board);
		board.executeMove(selectedMove);
		if(selectedMove.isShingShang()) {
			Console.askShingShangChoice(board,selectedMove.getMovedBushi());
		}
		if(board.isAdditionalTurn()) {
			playAdditionnalTurn();
		}
		
		nextTurn();
	}
	
	/**
	 * Gère le déroulement d'un tour supplémentaire lors d'un shing shang
	 */
	public void playAdditionnalTurn() {
		
		Bushi selectedBushi;
		Move selectedMove;
		boolean playerWantsToContinue = Console.askContinue();
		boolean yesShowLegalMoves;
		
		while(playerWantsToContinue && board.isAdditionalTurn()) {
			board.show();
			System.out.println(turnPlayer.getName() + ", tu peux choisir une autre pièce :)");
			selectedBushi = Console.askChoseBushi(this);
			System.out.println("Pièce séléctionnée : " + selectedBushi.toString());			
			selectedBushi.calculateLegalMoves();
			
			yesShowLegalMoves = Console.askShowLegalMoves();
			if(yesShowLegalMoves)
				board.showLegalMoves();
			
			selectedMove = Console.askChoseMove(board);
			board.executeMove(selectedMove);
			
			if(selectedMove.isShingShang()) {
				Console.askShingShangChoice(board,selectedMove.getMovedBushi());
			}
			
			if(board.isAdditionalTurn()) {
				playerWantsToContinue = Console.askContinue();
			}

		}
		board.resetMovedBushis();
	}
	
	/**
	 * Passe au tour suivant et détermine le joueur du tour
	 */
	public void nextTurn() {
		turnNumber++;
		if(turnNumber % 2 == 1) 
			turnPlayer = board.getPlayer1();
		else 
			turnPlayer = board.getPlayer2();
	}
	
	/**
	 * Indique si ce n'est pas le tour du joueur
	 * @param p un joueur
	 * @return true si le joueur du tour est différent du joueur du tour
	 */
	public boolean isNotTurnPlayer(Player p) {
		return !turnPlayer.equals(p);
	}
	
	/**
	 * Indique le jeu est fini et affiche le nom du joueur
	 */
	private void end() {
		System.out.println("-- Fin de la partie --\n Le gagnant est " +  board.winner().getName() +".");
	}
	
	/**
	 * Affiche le nom du jeu
	 */
	public static void showName() {
		System.out.println("_______________________________________________________________________________________ ");
		System.out.println(" d88888b  dP       oo                               dP");                                  
		System.out.println("88.    \"' 88                                        88");                                  
		System.out.println("`Y88888b. 88d888b. dP 88d888b. .d8888b.    .d8888b. 88d888b. .d8888b. 88d888b. .d8888b."); 
		System.out.println("      `8b 88'  `88 88 88'  `88 88'  `88    Y8ooooo. 88'  `88 88'  `88 88'  `88 88'  `88"); 
		System.out.println("d8'   .8P 88    88 88 88    88 88.  .88          88 88    88 88.  .88 88    88 88.  .88 ");
		System.out.println(" Y88888P  dP    dP dP dP    dP `8888P88    `88888P' dP    dP `88888P8 dP    dP `8888P88"); 
		System.out.println("                                   .88                                             .88");
		System.out.println("                                d8888P                                          d8888P");
		System.out.println("_______________________________________________________________________________________ ");
	}
	

	public static void main(String args[]) {
		
		Game shingshang = new Game();
		shingshang.start();
		
	}

	

}