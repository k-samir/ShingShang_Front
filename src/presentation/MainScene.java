package presentation;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainScene extends Scene {
	private Point[][] points;
	BorderPane root;
	GridPane board;

	public MainScene(Main main) throws Exception {
		
		
		super(new BorderPane());
		
		root = (BorderPane) (this.getRoot());
		board = new GridPane();
		
		StackPane stackPan = new StackPane();
	//	Rectangle rBleu = new Rectangle(770,70, Color.GREY);
		
		Label label1 = new Label("TEXT INFO");
	
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
		reset();
	}

	
	public void reset() {
		this.points = new Point[10][10];

		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {

				if ((lig == 1 && (col == 4 || col == 5) || (lig == 8 && (col == 4 || col == 5)))) {
					points[lig][col] = new Point("NOTBORDER","PORTAL", this);
				}

				else if (col == 0 || col == 9) {
					points[lig][col] = new Point("BORDER","NOTHING", this);

				} else {
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

		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {
				if (points[lig][col].getType().equals("STANDARD") || points[lig][col].getType().equals("PORTAL")) {
					
				
					board.add(points[lig][col].getStackPane(), col, lig);

				}
				else {
					Rectangle r = new Rectangle();
					r.setWidth(75);
					r.setHeight(75);
					r.setFill(Color.WHITE);
					r.setStroke(Color.WHITE);
					board.add(r,col,lig);
				}

			}
		}

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

}
