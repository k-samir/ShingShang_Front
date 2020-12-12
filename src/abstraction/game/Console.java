package abstraction.game;

import java.util.InputMismatchException;
import java.util.Scanner;

import abstraction.board.Board;
import abstraction.exceptions.BushiHasMovedException;
import abstraction.exceptions.IllegalMoveException;
import abstraction.exceptions.InvalidPositionException;
import abstraction.exceptions.NoBushiException;
import abstraction.exceptions.OpposingBushiMoveException;
import abstraction.move.Move;
import abstraction.pawn.Bushi;

public class Console {
	static Scanner keyboard = new Scanner(System.in);
	
	/**
	 * 	Affiche dans la console, demande au joueur de choisir une pi�ce � d�placer 
	 *  puis g�re la r�ponse du joueur
	 * 
	 * @param game le jeu
	 */
	public static Bushi askChoseBushi(Game game){
		Board board = game.getBoard();
		int row = 0, col = 0;
		boolean ok = false;
		boolean isInt;
		
		while(!ok) {
			System.out.println("Choisissez une pi�ce.");
			do {
				isInt = true;
				System.out.println("num�ro de ligne : ");
				try {
				row = keyboard.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Erreur - La valeur saisie doit �tre un entier.");
					keyboard.nextLine();
					isInt = false;
				}				
			} while(isInt == false);
			
			do {
				isInt = true;
				System.out.println("num�ro de colonne : ");
				try {
				col = keyboard.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Erreur - La valeur saisie doit �tre un entier.");
					keyboard.nextLine();
					isInt = false;
				}				
			} while(isInt == false);
		
			try {
				TypingValidator.checkBushiChoice(game, row, col);
				ok = true;
				
			} catch (InvalidPositionException e) {
				System.out.println(e.getMessage() + " (row = " + row + ", col = " + col + ")"  );
			} catch (NoBushiException e) {
				System.out.println(e.getMessage() + " (row = " + row + ", col = " + col + ")" );
			} catch (BushiHasMovedException e) {
				System.out.println(e.getMessage() + " : " + board.getSquare(row, col).getBushi());
			} catch (OpposingBushiMoveException e) {
				System.out.println(e.getMessage()  + " : " + board.getSquare(row, col).getBushi());
			}
		

		}
		return  board.getSquare(row, col).getBushi();
	}
	
	/**
	 * 	Affiche dans la console, demande au joueur de choisir un d�placement
	 *  puis g�re la r�ponse du joueur
	 *  
	 * @param board un plateau de jeu
	 */
	public static Move askChoseMove(Board board) {
		int row = 0, col = 0;
		boolean ok = false;
		boolean isInt;
		
		while(!ok) {
			System.out.println("Choisissez une case d'arriv�e. ");
			do {
				isInt = true;
				System.out.println("num�ro de ligne : ");
				try {
				row = keyboard.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Erreur - La valeur saisie doit �tre un entier.");
					keyboard.nextLine();
					isInt = false;
				}				
			} while(isInt == false);
			
			do {
				isInt = true;
				System.out.println("num�ro de colonne : ");
				try {
				col = keyboard.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Erreur - La valeur saisie doit �tre un entier.");
					keyboard.nextLine();
					isInt = false;
				}				
			} while(isInt == false);

			try {
				
			TypingValidator.checkMoveChoice(board, row, col);	
			ok = true;
			
			} catch (InvalidPositionException e) {
				System.out.println(e.getMessage() + " (row = " + row + ", col = " + col + ")"  );
			} catch (IllegalMoveException e) {
				System.out.println(e.getMessage() + " (row = " + row + ", col = " + col + ")"  );
			}
		}
		
		return  board.findMove(row, col);
	}

	
	
	/**
	 * 	Affiche dans la console, demande au joueur s'il veut continuer
	 *  
	 * @return true si le joueur r�pond o (oui)
	 */
	
	public static boolean askContinue() {
		String c;
		System.out.println("Voulez-vous continuez ? (o/n)");
		keyboard.nextLine();
		c = keyboard.nextLine();
		
		while(!c.toLowerCase().equals("o") && !c.toLowerCase().equals("n")) {
			System.out.println("Erreur - r�ponse diff�rente de o ou n." );
			System.out.println("Voulez-vous continuez ? (o/n)");
			c = keyboard.nextLine();
		}
		
		return c.equals("o");
	}
	/**
	 * Demande � l'utilisateur s'il souhaite afficher les d�placements possibles
	 * @return true si l'utilisateur a r�pondu o (oui)
	 */
	public static boolean askShowLegalMoves() {
		String c;
		System.out.println("Voulez-vous afficher les d�placements possibles ? (o/n)");
		keyboard.nextLine();
		c = keyboard.nextLine();
		
		while(!c.toLowerCase().equals("o") && !c.toLowerCase().equals("n")) {
			System.out.println("Erreur - r�ponse diff�rente de o ou n." );
			System.out.println("Voulez-vous afficher les d�placements possibles ? (o/n)");
			c = keyboard.nextLine();
		}
		
		return c.equals("o");
	}
	
	/**
	 * 	Affiche dans la console, demande au joueur s'il veut continuer le shing shang
	 *  et g�re la r�ponse du joueur
	 *  
	 * @param board Board, un plateau de jeu
	 * @param bushi la derni�re pi�ce d�plac�e 
	 */
	public static void askShingShangChoice(Board board, Bushi bushi) {
		boolean keepGoing = askContinue();
		
		if(keepGoing)
			board.executeShingShang(bushi);
	}

	/**
	 * Demande au joueur son nom
	 * @param i num�ro du joueur
	 * @return le nom du joueur
	 */
	public static String askName(int i) {
		String s;
		String res;
		switch(i) {
			case 1 : 
				s = "Joueur 1";
				break;
			case 2 :
				s = "Joueur 2";
				break;
			default :
				s = "Joueur";
		}	
		System.out.println(s + ", entrez un pseudo :");
		res = keyboard.nextLine(); 
		if(res == "") {
			return s;
		}
		return res;
	}

}
