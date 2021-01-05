package abstraction.board;

import java.util.ArrayList;
import java.util.List;

import abstraction.game.Player;
import abstraction.move.Jump;
import abstraction.move.Move;
import abstraction.pawn.Bushi;
import abstraction.pawn.Dragon;
import abstraction.pawn.Lion;
import abstraction.pawn.Monkey;
import control.Tuple;

/**
 * Le plateau de jeu
 */
public class Board {
	/* * * * * * * * * * *
	 *	Attributs 
	 * * * * * * * * * * */
	
	public static final int size = 10;
	
	private Square[][] array;
    private Player player1;
    private Player player2;
    private ArrayList<Move> legalMoves;
    private ArrayList<Bushi> movedBushis;
    private boolean additionalTurn;
    
    private ArrayList<Tuple<?,?>> caughtBushi = new ArrayList<Tuple<?,?>>(); 
  
	
    /* * * * * * * * * * *
	 *	Constructeurs 
	 * * * * * * * * * * */
    /**
     * Initialise un Board, un plateau de jeu
     * 
     * @param player1 le premier joueur
     * @param player2 le second joueur
     */
    
	public Board(Player player1, Player player2) {	
		this.player1 = player1;
		this.player2 = player2;
		legalMoves = new ArrayList<Move>();
		movedBushis = new ArrayList<Bushi>();
		additionalTurn = false;
		array = new Square[size][size];
		
		for(int line = 0 ; line < size ; line++) {
			for(int col = 0 ; col < size ; col++) {
				if(isPortail(line,col)) array[line][col] = new Portal(line,col);
				else array[line][col] = new Square(line,col);
			}
		}
		
		initialize();
	}

	/* * * * * * * * * * *
	 *	Getters & Setters
	 * * * * * * * * * * */
	
	/**
	 * @return array
	 */
	public Square[][] getArray() {
		return array;
	}
	
	/**
	 * @param array
	 */
	public void setArray(Square[][] array) {
		this.array = array;
	}
	
	/**
	 * @param i le num�ro de ligne
	 * @param j le num�ro de colonne
	 * @return la case ayant les coordonn�es (i,j)
	 */
	public Square getSquare(int i, int j) {
		return array[i][j];
	}
	
	/**
	 * @return le premier joueur
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * @return le second joueur
	 */
	public Player getPlayer2() {
		return player2;
	}
	
	/**
	 * @return la liste des d�placements possibles
	 */
	public ArrayList<Move> getLegalMoves() {
		return legalMoves;
	}

	/**
	 * @param legalMoves la liste des d�placements possibles
	 */
	public void setLegalMoves(ArrayList<Move> legalMoves) {
		this.legalMoves = legalMoves;
	}
	
	/**
	 * 
	 * @return la liste des pi�ces ayant d�j� �t� d�plac�es
	 */
	public List<Bushi> getMovedBushis() {
		return movedBushis;
	}

	/**
	 *  
	 * @param movedBushis la liste des pi�ces ayant d�j� �t� d�plac�es
	 */
	public void setMovedBushis(ArrayList<Bushi> movedBushis) {
		this.movedBushis = movedBushis;
	}
	
	/**
	 * @return true si le tour en cours est un tour supl�mentaire
	 */
	public boolean isAdditionalTurn() {
		return additionalTurn;
	}

	/**
	 * 
	 * @param additionalTurn 
	 */
	public void setAdditionalTurn(boolean additionalTurn) {
		this.additionalTurn = additionalTurn;
	}
	
	/**
	 * 
	 * @return la taille du plateau
	 */
	public int getSize() {
		return size;
	}
	
	/* * * * * * * * * * *
	 *	Methodes
	 * * * * * * * * * * */
	/**
	 * Initialise le plateau de jeu en positionner les pi�ces, les portails et en traitant l'activation des cases. 
	 */
	public void initialize(){
		
		// Disabled squares
		for (int i = 0; i < 4; i++) {
		    array[i][0].setEnabled(false);
		    array[i][9].setEnabled(false);
		    array[i + 6][0].setEnabled(false);
		    array[i + 6][9].setEnabled(false);
		}
	    
		//Creer des portails
		((Portal) array[1][4]).setOwner(player1);
		((Portal) array[1][5]).setOwner(player1);
		((Portal) array[8][4]).setOwner(player2);
		((Portal) array[8][5]).setOwner(player2);

		// Player 1
		array[0][1].setBushi(new Dragon(this,player1,0,1));
		array[0][2].setBushi(new Lion(this,player1,0,2));
		array[0][3].setBushi(new Monkey(this,player1,0,3));
		array[0][6].setBushi(new Monkey(this,player1,0,6));
		array[0][7].setBushi(new Lion(this,player1,0,7));
		array[0][8].setBushi(new Dragon(this,player1,0,8));
		
		array[1][1].setBushi(new Lion(this,player1,1,1));
		array[1][2].setBushi(new Monkey(this,player1,1,2));
		array[1][7].setBushi(new Monkey(this,player1,1,7));
		array[1][8].setBushi(new Lion(this,player1,1,8));
		
		array[2][1].setBushi(new Monkey(this,player1,2,1));
		array[2][8].setBushi(new Monkey(this,player1,2,8));

		// Player 2
		
		array[7][1].setBushi(new Monkey(this,player2,7,1));
		array[7][8].setBushi(new Monkey(this,player2,7,8));
		
		array[8][1].setBushi(new Lion(this,player2,8,1));
		array[8][2].setBushi(new Monkey(this,player2,8,2));
		array[8][7].setBushi(new Monkey(this,player2,8,7));
		array[8][8].setBushi(new Lion(this,player2,8,8));
		
		array[9][1].setBushi(new Dragon(this,player2,9,1));
		array[9][2].setBushi(new Lion(this,player2,9,2));
		array[9][3].setBushi(new Monkey(this,player2,9,3));
		array[9][6].setBushi(new Monkey(this,player2,9,6));
		array[9][7].setBushi(new Lion(this,player2,9,7));
		array[9][8].setBushi(new Dragon(this,player2,9,8));
		
	}
	
	/**
	 * Afficher dans la console l'�tat du plateau de jeu
	 */
	public void show() {
		System.out.println("\n");
		for(int i=0; i < size ; i++) {
			System.out.print("     " + i);
		}
		System.out.println();
		
		for(int line = 0 ; line < size ; line++) {
			System.out.print(line);
			for(int col = 0 ; col < size ; col++) {
				System.out.print( "    " + array[line][col].toString());
			}
			System.out.print( "    " + line);
			System.out.println();
			System.out.println();
		}
		
		for(int i=0; i < 10 ; i++) {
			System.out.print("     " + i);
		}
		System.out.println("\n");
	}
	
	/**
	 * Afficher dans la console l'�tat du plateau de jeu en montrant les d�placements possibles
	 */
	public void showLegalMoves() {
		System.out.println("\n");
		for(int i=0; i < 10 ; i++) {
			System.out.print("     " + i);
		}
		System.out.println();
		
		for(int line = 0 ; line < size ; line++) {
			System.out.print(line);
			for(int col = 0 ; col < size ; col++) {
				if(array[line][col].isAccessible()) {
					System.out.print( "    " + "[]");
					
				}
				else System.out.print( "    " + array[line][col].toString());
			}
			System.out.print( "    " + line);
			System.out.println();
			System.out.println();
		}
		
		for(int i=0; i < 10 ; i++) {
			System.out.print("     " + i);
		}
		System.out.println("\n");
	}

	/**
	 * D�termine si un d�placement est autoris� en le recherchant dans la liste legalMoves
	 *  
	 * @param row le num�ro de ligne de la case d'arriv�e
	 * @param col le num�ro de colonne de la case d'arriv�e
	 * @return true si le d�placement est autoris�, sinon false
	 */
	public boolean notLegalMove(int row, int col) {
		for(Move m : legalMoves) {
			if(m.getDestination().getRow() == row && m.getDestination().getCol() == col) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Obtient le d�placement � partir des coordonn�es de la case d'arriv�e
	 *  
	 * @param row le num�ro de ligne de la case d'arriv�e
	 * @param col le num�ro de colonne de la case d'arriv�e
	 * @return le d�placement trouv� de type Move
	 */
	public Move findMove(int row, int col) {
		for(Move m : legalMoves) {
			if(m.getDestination().getRow() == row && m.getDestination().getCol() == col) {
				return m;
			}
		}
		return null;
	}
	
	/**
	 * Execute un d�placement
	 *  
	 * @param move le d�placement � effectuer
	 */
	public void executeMove(Move move) {
		//rechercher si le move est bient
		int oldRow = move.getMovedBushi().getRow();
		int oldCol = move.getMovedBushi().getCol();
		int newRow = move.getDestination().getRow();
		int newCol = move.getDestination().getCol();
		
		//retirer la pi�ce de la case d'origine
		array[oldRow][oldCol].setBushi(null);
		
		//ajouter � la case de destination et mise � jour des coordonn�es de la pi�ce
		array[newRow][newCol].setBushi(move.getMovedBushi());
		move.getMovedBushi().setRow(newRow);
		move.getMovedBushi().setCol(newCol);
		
		//gestion du saut
		if (move instanceof Jump) {
			System.out.println("jump");
			Jump jump = ((Jump)move); 
			if(jump.isJumpEnemy()) {
				
				if(jump.getCaughtBushi() instanceof Dragon) 
					jump.getCaughtBushi().getPlayer().loseDragon();
				
				// AJOUT LISTE CAUGHT PIECE
				Tuple caught = new Tuple(jump.getCaughtBushi().getRow(),jump.getCaughtBushi().getCol());
				this.caughtBushi.add(caught);
				
				removeBushi(jump.getCaughtBushi());
				
				
				move.getMovedBushi().setHasMoved(true);
				movedBushis.add(move.getMovedBushi());
				setAdditionalTurn(true);
				System.out.println("EATING");
				
			}
		} else {
			setAdditionalTurn(false);
		}
		resetLegalModal();
		
		System.out.println("moved");
		
	}
	
	public ArrayList<Tuple<?, ?>> getCaughtBushi() {
		return caughtBushi;
	}

	public void setCaughtBushi(ArrayList<Tuple<?, ?>> caughtBushi) {
		this.caughtBushi = caughtBushi;
	}

	/**
	 * Execute un d�placement propre � un shing shang
	 *  
	 * @param bushi la pi�ce courante pouvant effectuer le shing shang
	 */
	public void executeShingShang(Bushi bushi,Move shingshang_move) {
	//	boolean yesShowLegalMoves;
		bushi.calculateLegalJump();
		
	//	yesShowLegalMoves = Console.askShowLegalMoves();
	//	if(yesShowLegalMoves)
	//		showLegalMoves();
		
		Move move = shingshang_move;
		executeMove(move);

		/**if(move.isShingShang())
		Console.askShingShangChoice(this, move.getMovedBushi());*/
	}
	
	
	 /* Vide la liste des d�placements possibles
	 */
	public void resetLegalModal() {
		for(Move m : legalMoves) {
			m.getDestination().setAccessible(false);
		}
		legalMoves.clear();
	}
	
	/**
	 * Vide la liste des pi�ces qui ont �t� d�plac�es durant le tour
	 */
	public void resetMovedBushis() {
		for(Bushi b : movedBushis) {
			b.setHasMoved(false);
		}
		movedBushis.clear();
	}

	/**
	 * Affiche dans la console la liste des pi�ces ayant �t� d�plac�es 
	 */
	public void printMovedBushis() {
		for(Bushi b : movedBushis) {
			System.out.print(b.toString()+ " | " );
		}
	}
	
	/**
	 * Affiche dans la console la liste des d�placements possibles
	 */
	public void printLegalMoves() {
		for(Move m : legalMoves) {
			System.out.println(m.getMovedBushi().getRow() + " | " + m.getMovedBushi().getCol() + " to " + m.getDestination().getRow() + " | " + m.getDestination().getCol());
		}
	}
	
	/**
	 * D�termine si une case est un portail en fonction des coordonn�es
	 * 
	 * @param x num�ro de ligne
	 * @param y num�ro de colonne
	 * @return true si les coordonn�es correspondent � ceux d'un portail
	 */
	public boolean isPortail(int x, int y) {
		return (x==1 || x==8) && (y==4 || y==5);
	}

	/**
	 * D�termine si toutes les portails d'un joueur sont occup�s
	 * 
	 * @param p le joueur propri�taire des portails
	 * 
	 * @return true si le nombre de portails occup�s est �gal � 2
	 */
	public boolean allPortalsOccupied(Player p) {
		return counterOccupiedPortals(p) == 2 ;
	}

	/**
	 * Compte le nombre de portails occup�s  appartenant � un joueur
	 * 
	 * @param p le joueur
	 * 
	 * @return le nombre de portails occup�s par une pi�ce ennemie
	 */
	public int counterOccupiedPortals(Player p) {
		Portal portal;
		int counter = 0;
		
		for(int i = 0 ; i < size ; i++) {
			for(int j = 0 ; j < size ; j++) {
				if(array[i][j] instanceof Portal) {
					portal = (Portal) array[i][j];
					if(portal.isOccupiedByEnemy()) {
						counter++;
					}
				}
			}
		}
		return counter;
	}
	
	/**
	 * D�termine s'il y a un gagnant
	 * 
	 * D�termine s'il y a un gagnant. Un joueur gagne s'il a captur� tous les dragons de son adverse
	 * ou s'il a occup� tous les portails de son adversaire
	 *  
	 * @return le joueur qui a gagn�
	 */
	
	public Player winner() {
		if(player2.getLostDragons() == 2 || allPortalsOccupied(player2))
			return player1;
		if(player1.getLostDragons() == 2 || allPortalsOccupied(player1))
			return player2;
		return null; 
	}
	/**
	 * Retourne vrai si la partie est finie
	 * 
	 * Retourne vrai si la partie est finie , c'est � dire s'il y a un gagnant
	 * @return true si la partie est finie
	 */
	public boolean isOver() {
		return (winner() != null);
	}
	
	/**
	 * Supprimer une pi�ce du plateau
	 *  
	 * @param b la pi�ce � supprimer
	 */
	public void removeBushi(Bushi b) {
		int x = b.getRow();
		int y = b.getCol();
		array[x][y].setBushi(null);
	}
	
	/**
	 * Retourne vrai si la position (row,col) est dans le plateau
	 * 
	 * @param row le num�ro de ligne
	 * @param col le num�ro de colonne
	 * @return true si les coordonn�es sont valides
	 */
	public boolean isInside(int row, int col) {
		return row >= 0 && row < Board.size && col >= 0 && col < Board.size;
	}

	

}
