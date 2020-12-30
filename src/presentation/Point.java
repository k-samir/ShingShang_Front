package presentation;

import java.awt.MouseInfo;
import javafx.scene.Node;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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
	private Line north,north_w,north_e;
	private Line east;
	private Line south,south_w,south_e;
	private Line west;
	
	private String border;
	
	
	
	private String type;
	private Boolean already_clicked = false;
	

	@SuppressWarnings("static-access")
	public Point(String border,String typ, MainScene mainScen) {
		this.mainScene = mainScen;
		this.type = typ;
		this.border = border;

		stackP = new StackPane();
		
		// Drawing a Circle
		circle = new Circle();
	
		circle.setRadius(30);
		// Setting other properties
		circle.setFill(Color.WHITE);
		circle.setStrokeWidth(5.0);
		circle.setStroke(Color.BLACK);
		
	
		
		r = new Rectangle();
		r.setWidth(75);
		r.setHeight(75);
		r.setFill(Color.GREY);
		r.setStroke(Color.GREY);
		
	if(border.equals("NOTBORDER")) {
		north_w = new Line(0,0,35,35);
		north_w.setStrokeWidth(5);
		
		north_e = new Line(-35,35,0,0);
		north_e.setStrokeWidth(5);
		
		
		south_e = new Line(35,35,0,0);
		south_e.setStrokeWidth(5);
		
		south_w = new Line(0,70,35,35);
		south_w.setStrokeWidth(5);
		
		north = new Line(35,0,35,35);
		north.setStrokeWidth(5);
		
		east = new Line(0,0,35,0);
		east.setStrokeWidth(5);
		
		south = new Line(0,0,0,35);
		south.setStrokeWidth(5);
		
		west = new Line(0,0,35,0);
		west.setStrokeWidth(5);
		

		 stackP.getChildren().addAll(r,north,south,east,west,north_e,north_w,south_e,south_w,circle);
		 
		 stackP.setAlignment(Pos.TOP_LEFT);
		 

		 stackP.setAlignment(north,Pos.TOP_CENTER);
		 stackP.setAlignment(east,Pos.CENTER_RIGHT);
		 
		 stackP.setAlignment(south,Pos.BOTTOM_CENTER);
		 
		 stackP.setAlignment(west,Pos.CENTER_LEFT);
		 
		 
		 stackP.setAlignment(north_e,Pos.TOP_RIGHT);
		 stackP.setAlignment(south_e,Pos.BOTTOM_RIGHT);
		 stackP.setAlignment(south_w,Pos.BOTTOM_LEFT);
		 
		 stackP.setAlignment(circle,Pos.CENTER);
		 
	}
	else { 
	
		stackP.getChildren().addAll(r,circle); 
		stackP.setAlignment(Pos.TOP_LEFT);
		stackP.setAlignment(circle,Pos.CENTER);
	 
	 }

		if (typ.equals("PORTAL1")) {
			Image im;
			try {
				im = new Image(new FileInputStream("image" + File.separator + "red_portal.jpg"));
				circle.setFill(new ImagePattern(im));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if(typ.equals("PORTAL2")) {
			Image im;
			try {
				im = new Image(new FileInputStream("image" + File.separator + "blue_portal.jpg"));
				circle.setFill(new ImagePattern(im));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	
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



	public String isBorder() {
		return border;
	}
	
	public void setBorder(String bool) {
		this.border = bool;
	}
	
	public void setExternW(int pos) {
		this.type = "STANDARD";
		this.border = "BORDER";

			ObservableList<Node> childs = stackP.getChildren();
			 
	        if (childs.size() > 1) {
	            
	            stackP.getChildren().remove(childs.size()-1);
	            
			
				north_e = new Line(-35,35,0,0);
				north_e.setStrokeWidth(5);
				
				
				south_e = new Line(35,35,0,0);
				south_e.setStrokeWidth(5);
				
				
				east = new Line(0,0,35,0);
				east.setStrokeWidth(5);
				
				south = new Line(0,0,0,35);
				south.setStrokeWidth(5);
				

				north = new Line(35,0,35,35);
				north.setStrokeWidth(5);
				
				if(pos == 0) {
					
					stackP.getChildren().addAll(south,east,north_e,south_e,circle);
					 stackP.setAlignment(south,Pos.BOTTOM_CENTER);
				}
				else if(pos == 1) {
					stackP.getChildren().addAll(north,east,north_e,south_e,circle);
					 stackP.setAlignment(north,Pos.TOP_CENTER);
				}
				 
				 stackP.setAlignment(Pos.TOP_LEFT);
				 

				 stackP.setAlignment(east,Pos.CENTER_RIGHT);
				 
				
				
				 
				 stackP.setAlignment(north_e,Pos.TOP_RIGHT);
				 stackP.setAlignment(south_e,Pos.BOTTOM_RIGHT);
				 
				 stackP.setAlignment(circle,Pos.CENTER);
	        }
	        	 
		}
		
		
	
	public void setExternE(int pos) {
		this.type = "STANDARD";
		this.border = "BORDER";
		
		ObservableList<Node> childs = stackP.getChildren();
		 
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);
            
            north_w = new Line(0,0,35,35);
    		north_w.setStrokeWidth(5);
    		
    		
    		
    		
    		south_w = new Line(0,70,35,35);
    		south_w.setStrokeWidth(5);
    		
    		north = new Line(35,0,35,35);
    		north.setStrokeWidth(5);
    		
    		
    		south = new Line(0,0,0,35);
    		south.setStrokeWidth(5);
    		
    		west = new Line(0,0,35,0);
    		west.setStrokeWidth(5);
    		
			
			if(pos == 0) {
				
				stackP.getChildren().addAll(south,west,north_w,south_w,circle);
				 stackP.setAlignment(south,Pos.BOTTOM_CENTER);
			}
			else if(pos == 1) {
				stackP.getChildren().addAll(north,west,north_w,south_w,circle);
				 stackP.setAlignment(north,Pos.TOP_CENTER);
			}
			 
			 stackP.setAlignment(Pos.TOP_LEFT);
			 
			 stackP.setAlignment(north,Pos.TOP_CENTER);
			  stackP.setAlignment(south,Pos.BOTTOM_CENTER);
			 
			 stackP.setAlignment(west,Pos.CENTER_LEFT);
			 	 
		stackP.setAlignment(south_w,Pos.BOTTOM_LEFT);
			 
			
			 
			 stackP.setAlignment(circle,Pos.CENTER);
        }
	}
}
