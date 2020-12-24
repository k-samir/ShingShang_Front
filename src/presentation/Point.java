package presentation;

import java.awt.MouseInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Point {

	private MainScene mainScene;
	private Circle circle;
	
	private Rectangle r;
	private StackPane stackP;
	private Line north;
	private Line east;
	private Line south;
	private Line west;
	
	
	
	private String type;
	private Boolean already_clicked = false;
	

	private Connection N;
	// , S, E, W, NE, NW, SE, SW;

	public Point(String typ, MainScene mainScen) {
		this.mainScene = mainScen;
		this.type = typ;

		stackP = new StackPane();
		
		// Drawing a Circle
		circle = new Circle();
		// Setting the properties of the circle
	//	circle.setCenterX(300.0f);
	//	circle.setCenterY(135.0f);
		circle.setRadius(30);
		// Setting other properties
		circle.setFill(Color.WHITE);
		circle.setStrokeWidth(5.0);
		circle.setStroke(Color.BLACK);
		
	
		
		r = new Rectangle();
		r.setWidth(75);
		r.setHeight(75);
		r.setFill(Color.WHITE);
		r.setStroke(Color.WHITE);
		
	
		north = new Line(0,70,0,0);

		north.setStrokeWidth(5);
		
		east = new Line(70,0,0,0);

		east.setStrokeWidth(5);
		south = new Line(70,70,0,0);

		south.setStrokeWidth(5);
		
		west = new Line(0,70,70,0);

		west.setStrokeWidth(5);
		

		 stackP.getChildren().addAll(r,north,south,east,west,circle);;
		

		if (typ.equals("PORTAL")) {
			Image im;
			try {
				im = new Image(new FileInputStream("image" + File.separator + "red_portal.jpg"));
				circle.setFill(new ImagePattern(im));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		N = new Connection(mainScen, this);

		circle.setOnMouseClicked(e -> {
			if(!this.already_clicked) {
				already_clicked = true;
				mainScene.showBack(Point.this);
			}
			else { 
			already_clicked = false;
			mainScene.show(Point.this);
			}

		});

	}

	public void actionPerformed(java.awt.event.ActionEvent e) {
		showBack();

	}

	public void showBack() {
		//this.north.setStroke(Color.GOLD);
			this.circle.setStroke(Color.GREEN);
	}
	
	public void show() {
		this.circle.setStroke(Color.BLACK);
	}

	public StackPane getStackPane() {
		return stackP;
	}
	
	public Circle getCircle() {
		return circle;
	}

	public Circle getNothing() {
		circle.setStroke(Color.WHITE);
		return circle;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String typ) {

		this.type = typ;
	}

	public Connection getN() {
		return N;
	}

}
