package abstraction.pawn;

import abstraction.board.Board;
import abstraction.game.Player;
import abstraction.move.Jump;
/**
 * 
 * La pièce
 *
 */
public abstract class Bushi {
	/* * * * * * * * * * *
	 *	Attributs 
	 * * * * * * * * * * */

	protected Board board;
	protected final Player player; 
	protected final int size;
	protected int row;
	protected int col;
	protected boolean hasMoved;
	
	/* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
	/**
	 * Initialise la pièce
	 * @param size la taille de la pièce
	 * @param p le joueur propriétaire
	 */
	public Bushi(int size, Player p){
		this.player = p;
		this.size = size;
	}
	
	/**
	 * Initialise la pièce 
	 * 
	 * @param b le plateau de jeu
	 * @param size la taille de la pièce
	 * @param p le joueur propriétaire
	 * @param r le numéro de ligne
	 * @param c le numéro de colonne
	 */
	public Bushi(Board b, int size, Player p, int r, int c){
		board = b;
		this.player = p;
		this.size = size;
		this.row = r;
		this.col = c;
		this.hasMoved = false;
	}
	
	/* * * * * * * * * * *
	 *	Getters & Setters 
	 * * * * * * * * * * */
	
	/**
	 * 
	 * @return le plateau de jeu
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * 
	 * @return le joueur propriétaire
	 */
	public Player getPlayer() {
		return player;
	}
		
	/**
	 * 
	 * @return la taille de la pièce
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @return le numéro de ligne
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * 
	 * @param row le numéro de ligne
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 *  
	 * @return le numéro de colonne
	 */
	public int getCol() {
		return col;
	}

	/**
	 * 
	 * @param col le numéro de colonne
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * 
	 * @return true si la pièce a été déplacée
	 */
	public boolean isHasMoved() {
		return hasMoved;
	}

	/**
	 * 
	 * @param hasMoved la valeur de l'attribut hasMoved, vrai si la pièce a été déplacée
	 */
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	/* * * * * * * * * * *
	 *	Méthodes abstraites 
	 * * * * * * * * * * */
	/**
	 * Détermine les déplacements possibles
	 */
	public abstract void calculateLegalMoves();
	
	/* * * * * * * * * * *
	 *	Méthodes  
	 * * * * * * * * * * */
	
	/**
	 * Détermine tous les sauts possibles
	 */
	public void calculateLegalJump() {
		int newRow = row, newCol = col;
		
		for(int i = -1 ; i <= 1 ; i++) {
			for(int j = -1 ; j <= 1 ; j++) {
				newRow = row + i;
				newCol = col + j;
				
				if(board.isInside(newRow, newCol) && board.getSquare(newRow, newCol).isEnabled() && !board.getSquare(newRow, newCol).isVoid()) {
					calculateJump(i,j);	
				}
			}
		}
	}
	
	/**
	 * Détermine le saut possible en fonction d'une direction (i,j)
	 * 
	 * Détermine le saut possible en fonction d'une direction (i,j) et l'ajoute à la liste des déplacements possibles du Board
	 * @param i décalage horizontal
	 * @param j décalage vertical
	 */
	public void calculateJump(int i, int j) {
		
		int newRow = row + i*2;
		int newCol = col + j*2;
		if(this.isBiggerThan(neighbour(i,j))) { 
			if(board.isInside(newRow, newCol) && board.getSquare(newRow, newCol).isEnabled() && board.getSquare(newRow, newCol).isVoid()) {
				if(neighbour(i,j).isAlly(this)) {
					board.getLegalMoves().add(new Jump(this, board.getSquare(newRow,newCol),true));
				} else {
					board.getLegalMoves().add(new Jump(this, board.getSquare(newRow,newCol),false,neighbour(i,j)));
				}
				board.getSquare(newRow, newCol).setAccessible(true); 
			}
		}
	}

	/**
	 * Retourne vrai si la pièce est plus grande qu'une autre
	 * @param other une autre pièce
	 * @return true si la pièce est plus grande
	 */
	public boolean isBiggerThan(Bushi other) {
		return size >= other.getSize();
	}
	
	/**
	 * Retourne une pièce voisine à la place
	 * @param i le décalage horizontal
	 * @param j le décalage vertical
	 * @return la pièce voisine
	 */
	public Bushi neighbour(int i, int j) {
		return board.getSquare(row + i, col + j).getBushi();
	}
	
	/**
	 * Retourne vrai si les pièces sont alliées
	 * @param otherBushi une autre pièce
	 * @return true si la pièce est allié
	 */
	public boolean isAlly(Bushi otherBushi) {
		return this.player.equals(otherBushi.player);
	}
}
