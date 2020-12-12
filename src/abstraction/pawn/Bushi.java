package abstraction.pawn;

import abstraction.board.Board;
import abstraction.game.Player;
import abstraction.move.Jump;
/**
 * 
 * La pi�ce
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
	 * Initialise la pi�ce
	 * @param size la taille de la pi�ce
	 * @param p le joueur propri�taire
	 */
	public Bushi(int size, Player p){
		this.player = p;
		this.size = size;
	}
	
	/**
	 * Initialise la pi�ce 
	 * 
	 * @param b le plateau de jeu
	 * @param size la taille de la pi�ce
	 * @param p le joueur propri�taire
	 * @param r le num�ro de ligne
	 * @param c le num�ro de colonne
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
	 * @return le joueur propri�taire
	 */
	public Player getPlayer() {
		return player;
	}
		
	/**
	 * 
	 * @return la taille de la pi�ce
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @return le num�ro de ligne
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * 
	 * @param row le num�ro de ligne
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 *  
	 * @return le num�ro de colonne
	 */
	public int getCol() {
		return col;
	}

	/**
	 * 
	 * @param col le num�ro de colonne
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * 
	 * @return true si la pi�ce a �t� d�plac�e
	 */
	public boolean isHasMoved() {
		return hasMoved;
	}

	/**
	 * 
	 * @param hasMoved la valeur de l'attribut hasMoved, vrai si la pi�ce a �t� d�plac�e
	 */
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	/* * * * * * * * * * *
	 *	M�thodes abstraites 
	 * * * * * * * * * * */
	/**
	 * D�termine les d�placements possibles
	 */
	public abstract void calculateLegalMoves();
	
	/* * * * * * * * * * *
	 *	M�thodes  
	 * * * * * * * * * * */
	
	/**
	 * D�termine tous les sauts possibles
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
	 * D�termine le saut possible en fonction d'une direction (i,j)
	 * 
	 * D�termine le saut possible en fonction d'une direction (i,j) et l'ajoute � la liste des d�placements possibles du Board
	 * @param i d�calage horizontal
	 * @param j d�calage vertical
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
	 * Retourne vrai si la pi�ce est plus grande qu'une autre
	 * @param other une autre pi�ce
	 * @return true si la pi�ce est plus grande
	 */
	public boolean isBiggerThan(Bushi other) {
		return size >= other.getSize();
	}
	
	/**
	 * Retourne une pi�ce voisine � la place
	 * @param i le d�calage horizontal
	 * @param j le d�calage vertical
	 * @return la pi�ce voisine
	 */
	public Bushi neighbour(int i, int j) {
		return board.getSquare(row + i, col + j).getBushi();
	}
	
	/**
	 * Retourne vrai si les pi�ces sont alli�es
	 * @param otherBushi une autre pi�ce
	 * @return true si la pi�ce est alli�
	 */
	public boolean isAlly(Bushi otherBushi) {
		return this.player.equals(otherBushi.player);
	}
}
