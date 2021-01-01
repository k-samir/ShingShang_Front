package presentation;


import java.util.ArrayList;

import application.Main;
import control.ControlChooseBushi;
import control.ControlMoveBushi;
import control.Tuple;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainScene extends Scene {
	private Point[][] points;
	BorderPane root;
	GridPane board;

	Boolean first_click = true;
	Point previous_point;
	Main main;
	Label label1;
	



	public MainScene(Main main) throws Exception {
		
		
		super(new BorderPane());
		this.main = main;
		root = (BorderPane) (this.getRoot());
		board = new GridPane();
		
		StackPane stackPan = new StackPane();
	//	Rectangle rBleu = new Rectangle(770,70, Color.GREY);
		
		label1 = new Label("TEXT INFO");
	
		HBox hb = new HBox();
		Button btn = new Button("BACK");
		btn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        try {
					main.start1();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		hb.getChildren().add(label1);
		hb.setSpacing(10);
		
		stackPan.getChildren().addAll(hb,btn);
		root.setTop(stackPan);
		root.setCenter(board);		
	//	root = (GridPane) (this.getRoot());
		// root.setHgap(10);
		// root.setVgap(10);
		init();
	}

	
	public void init() {
		this.points = new Point[10][10];

		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {

				
				//PORTAL1
			if (lig == 1 && (col == 4 || col == 5)){
					
					points[lig][col] = new Point("NOTBORDER","PORTAL1", this);
				}
				// PORTAL2
				else if (lig == 8 && (col == 4 || col == 5)){
					points[lig][col] = new Point("NOTBORDER","PORTAL2", this);
				}

				else if (col == 0 || col == 9) {
					points[lig][col] = new Point("BORDER","NOTHING", this);

				}
				
				// CORNER
				else if(lig == 0 && col == 1) {
					points[lig][col] = new Point("CORNERUL","STANDARD", this);
				}
				else if(lig == 0 && col == 8) {
					points[lig][col] = new Point("CORNERUR","STANDARD", this);			
				}
				else if(lig == 9 && col == 1) {
					points[lig][col] = new Point("CORNERDL","STANDARD", this);
				}
				else if(lig == 9 && col == 8) {
					points[lig][col] = new Point("CORNERDR","STANDARD", this);
				}
			
				else if((lig == 0 && col != 0) && (lig == 0 && col != 9)){
				points[lig][col] = new Point("UP","STANDARD", this);
			}
				else if((col == 1 && lig != 0) && (col == 1 && lig != 9)){
					points[lig][col] = new Point("LEFT","STANDARD", this);
				}
				else if((lig == 9 && col != 0) && (lig == 9 && col != 9)){
					points[lig][col] = new Point("DOWN","STANDARD", this);
				}
				else if((col == 8 && lig != 0) && (col == 8 && lig != 9)){
					points[lig][col] = new Point("RIGHT","STANDARD", this);
				}
				
				else {
					if(lig == 0 || col == 8 || lig == 9 || col == 1) {
						points[lig][col] = new Point("BORDER","STANDARD", this);
					}
					else {
						points[lig][col] = new Point("NOTBORDER","STANDARD", this);
					}
					

				}
			}
		}
		//Set extern point of the boardgame east side
		points[4][0].setExternW(0);
		points[5][0].setExternW(1);
		// set extern point of the boardgame west side
		points[4][9].setExternE(0);
		points[5][9].setExternE(1);
		
		points[3][1].setConnectionDL();
		points[4][1].setConnectionL1();
		points[5][1].setConnectionL2();
		points[6][1].setConnectionUL();
		
		points[3][8].setConnectionDR();
		points[4][8].setConnectionR1();
		points[5][8].setConnectionR2();
		points[6][8].setConnectionUR();
		
		// PIECE
		// Player 1
		points[0][1].setBushi("DRAGON1");
		points[0][2].setBushi("LION1");
		points[0][3].setBushi("MONKEY1");
		points[0][6].setBushi("MONKEY1");
		points[0][7].setBushi("LION1");
		points[0][8].setBushi("DRAGON1");
				
		points[1][1].setBushi("LION1");
		points[1][2].setBushi("MONKEY1");
		points[1][7].setBushi("MONKEY1");
		points[1][8].setBushi("LION1");
				
		points[2][1].setBushi("MONKEY1");
		points[2][8].setBushi("MONKEY1");

				// Player 2
				
		points[7][1].setBushi("MONKEY2");
		points[7][8].setBushi("MONKEY2");
				
		points[8][1].setBushi("LION2");
		points[8][2].setBushi("MONKEY2");
		points[8][7].setBushi("MONKEY2");
		points[8][8].setBushi("LION2");
				
		points[9][1].setBushi("DRAGON2");
		points[9][2].setBushi("LION2");
		points[9][3].setBushi("MONKEY2");
		points[9][6].setBushi("MONKEY2");
		points[9][7].setBushi("LION2");
		points[9][8].setBushi("DRAGON2");
		

		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {
				if (points[lig][col].getType().equals("STANDARD") || points[lig][col].getType().equals("PORTAL1")
						|| points[lig][col].getType().equals("PORTAL2")) {
					
				
					board.add(points[lig][col].getStackPane(), col, lig);

				}
				else {
					Rectangle r = new Rectangle();
					r.setWidth(75);
					r.setHeight(75);
					r.setFill(Color.rgb(192,194,181));
					r.setStroke(Color.rgb(192,194,181));
					board.add(r,col,lig);
				}

			}
		}

	}

	public void changeLabel(String text) {
		this.label1.setText(text);
	}
	public void showBack(Point point) {
		int x = 0, y = 0;
		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {

				if (points[lig][col] == point) {
					x = lig;
					y = col;
					break;
				}

			}
		}
		try {
			System.out.print("[" + x + " , " + y + "]");
			points[x + 1][y].showBack();
		} catch (Exception e) {
		}
		try {
			points[x - 1][y].showBack();
		} catch (Exception e) {
		}
		try {
			points[x][y + 1].showBack();
		} catch (Exception e) {
		}
		try {
			points[x][y - 1].showBack();
		} catch (Exception e) {
		}
		try {
		} catch (Exception e) {
		}

	}

	public void show(Point point) {
		int x = 0, y = 0;
		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {

				if (points[lig][col] == point) {
					x = lig;
					y = col;
					break;
				}

			}
		}
		try {
			System.out.print("[" + x + " , " + y + "]");
			points[x + 1][y].show();
		} catch (Exception e) {
		}
		try {
			points[x - 1][y].show();
		} catch (Exception e) {
		}
		try {
			points[x][y + 1].show();
		} catch (Exception e) {
		}
		try {
			points[x][y - 1].show();
		} catch (Exception e) {
		}
		try {
		} catch (Exception e) {
		}

	}

	public void available(presentation.Point square2) {
		// TODO Auto-generated method stub

	}


	public boolean getFirst_click() {
		return first_click;
	}
	


	public Point getPrevious_point() {
		return previous_point;
	}


	public void setPrevious_point(Point previous_text) {
		this.previous_point = previous_text;
	}


	public void setFirst_click(Boolean first_click) {
		this.first_click = first_click;
	}

	public void moveBushi(Point p) {
		int x = 0, y = 0;
		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {

				if (points[lig][col] == p) {
					x = lig;
					y = col;
					break;
				}

			}
		}
		
		ControlMoveBushi  controlMoveBushi = new ControlMoveBushi(x,y,main.getGame());
	}
	
	public void chooseBushi(Point p) {
		int x = 0, y = 0;
		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {

				if (points[lig][col] == p) {
					x = lig;
					y = col;
					break;
				}
			}
		}
		
		ControlChooseBushi  controlChooseBushi = new ControlChooseBushi(x,y,main.getGame());
		ArrayList<Tuple> legals = controlChooseBushi.getLegalMoves();
		
		for(Tuple m : legals) {
			try {
				points[m.getFirst()][m.getSecond()].showBack();
				
			} catch (Exception e) {
			}
		}

	}
	// Method to reset stroke of neigbours
	public void resetStroke(Point p) {
		int x = 0, y = 0;
		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {

				if (points[lig][col] == p) {
					x = lig;
					y = col;
					break;
				}
			}
		}
		
		for(int i = x-3;i<x+3;i++) {
			for(int j = y+3;j>y-3;j--) {
				points[i][j].show();
			}
		}
	}
	
	
	
	
	

}
