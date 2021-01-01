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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

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
	
	private Text text = null;
	private Color color = null;
	private Boolean used;
	
	
	
	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	private String type;
	private Boolean already_clicked = false;
	

	@SuppressWarnings("static-access")
	public Point(String border,String typ, MainScene mainScen) {
		this.mainScene = mainScen;
		this.type = typ;
		this.border = border;
		this.used = false;

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
		r.setFill(Color.rgb(192,194,181));
		r.setStroke(Color.rgb(192,194,181));
		
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
	else if(border.equals("CORNERUL")) {
		
		south_e = new Line(35,35,0,0);
		south_e.setStrokeWidth(5);
		
		
		east = new Line(0,0,35,0);
		east.setStrokeWidth(5);
		
		south = new Line(0,0,0,35);
		south.setStrokeWidth(5);
		
		

		 stackP.getChildren().addAll(r,south,east,south_e,circle);
		 
		 stackP.setAlignment(Pos.TOP_LEFT);

		 stackP.setAlignment(east,Pos.CENTER_RIGHT);
		 
		 stackP.setAlignment(south,Pos.BOTTOM_CENTER);
		 
		 stackP.setAlignment(south_e,Pos.BOTTOM_RIGHT);
		 
		 stackP.setAlignment(circle,Pos.CENTER);
	}
	else if(border.equals("CORNERUR")) {
		
		
		
		south_w = new Line(0,70,35,35);
		south_w.setStrokeWidth(5);
		
		south = new Line(0,0,0,35);
		south.setStrokeWidth(5);
		
		west = new Line(0,0,35,0);
		west.setStrokeWidth(5);
		

		 stackP.getChildren().addAll(r,south,west,south_w,circle);
		 
		 stackP.setAlignment(Pos.TOP_LEFT);
		 

		
		 
		 stackP.setAlignment(south,Pos.BOTTOM_CENTER);
		 
		 stackP.setAlignment(west,Pos.CENTER_LEFT);
		 
		 stackP.setAlignment(south_w,Pos.BOTTOM_LEFT);
		 
		 stackP.setAlignment(circle,Pos.CENTER);
	}
	else if(border.equals("CORNERDR")) {
		north_w = new Line(0,0,35,35);
		north_w.setStrokeWidth(5);
		
		north = new Line(35,0,35,35);
		north.setStrokeWidth(5);
		
		west = new Line(0,0,35,0);
		west.setStrokeWidth(5);
		

		 stackP.getChildren().addAll(r,north,west,north_w,circle);
		 
		 stackP.setAlignment(Pos.TOP_LEFT);
		 

		 stackP.setAlignment(north,Pos.TOP_CENTER);
		
		 
		 stackP.setAlignment(west,Pos.CENTER_LEFT);
		 
		 stackP.setAlignment(circle,Pos.CENTER);
	}
	else if(border.equals("CORNERDL")) {
		
		north_e = new Line(-35,35,0,0);
		north_e.setStrokeWidth(5);
		
		
		north = new Line(35,0,35,35);
		north.setStrokeWidth(5);
		
		east = new Line(0,0,35,0);
		east.setStrokeWidth(5);
		
		
		

		 stackP.getChildren().addAll(r,north,east,north_e,circle);
		 
		 stackP.setAlignment(Pos.TOP_LEFT);
		 

		 stackP.setAlignment(north,Pos.TOP_CENTER);
		 stackP.setAlignment(east,Pos.CENTER_RIGHT);
		 
		
		 stackP.setAlignment(north_e,Pos.TOP_RIGHT);
	
		 stackP.setAlignment(circle,Pos.CENTER);
	}
	else if(border.equals("UP")) {
		
		
		
		south_e = new Line(35,35,0,0);
		south_e.setStrokeWidth(5);
		
		south_w = new Line(0,70,35,35);
		south_w.setStrokeWidth(5);
		
		
		east = new Line(0,0,35,0);
		east.setStrokeWidth(5);
		
		south = new Line(0,0,0,35);
		south.setStrokeWidth(5);
		
		west = new Line(0,0,35,0);
		west.setStrokeWidth(5);
		

		 stackP.getChildren().addAll(r,south,east,west,south_e,south_w,circle);
		 
		 stackP.setAlignment(Pos.TOP_LEFT);
		 

		
		 stackP.setAlignment(east,Pos.CENTER_RIGHT);
		 
		 stackP.setAlignment(south,Pos.BOTTOM_CENTER);
		 
		 stackP.setAlignment(west,Pos.CENTER_LEFT);
		 
		
		 stackP.setAlignment(south_e,Pos.BOTTOM_RIGHT);
		 stackP.setAlignment(south_w,Pos.BOTTOM_LEFT);
		 
		 stackP.setAlignment(circle,Pos.CENTER);
	}
	else if(border.equals("LEFT")) {
		
		north_e = new Line(-35,35,0,0);
		north_e.setStrokeWidth(5);
		
		
		south_e = new Line(35,35,0,0);
		south_e.setStrokeWidth(5);
		
		
		north = new Line(35,0,35,35);
		north.setStrokeWidth(5);
		
		east = new Line(0,0,35,0);
		east.setStrokeWidth(5);
		
		south = new Line(0,0,0,35);
		south.setStrokeWidth(5);
		
		

		 stackP.getChildren().addAll(r,north,south,east,north_e,south_e,circle);
		 
		 stackP.setAlignment(Pos.TOP_LEFT);
		 

		 stackP.setAlignment(north,Pos.TOP_CENTER);
		 stackP.setAlignment(east,Pos.CENTER_RIGHT);
		 
		 stackP.setAlignment(south,Pos.BOTTOM_CENTER);
		
		 
		 stackP.setAlignment(north_e,Pos.TOP_RIGHT);
		 stackP.setAlignment(south_e,Pos.BOTTOM_RIGHT);
		
		 
		 stackP.setAlignment(circle,Pos.CENTER);
	}
	
	else if(border.equals("RIGHT")) {
		
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
		

		 stackP.getChildren().addAll(r,north,south,west,north_w,south_w,circle);
		 
		 stackP.setAlignment(Pos.TOP_LEFT);
		 

		 stackP.setAlignment(north,Pos.TOP_CENTER);
		 
		 stackP.setAlignment(south,Pos.BOTTOM_CENTER);
		 
		 stackP.setAlignment(west,Pos.CENTER_LEFT);
		 
	
		 stackP.setAlignment(south_w,Pos.BOTTOM_LEFT);
		 
		 stackP.setAlignment(circle,Pos.CENTER);
	}
	else if(border.equals("DOWN")) {
		north_w = new Line(0,0,35,35);
		north_w.setStrokeWidth(5);
		north_e = new Line(-35,35,0,0);
		north_e.setStrokeWidth(5);
		
		
		north = new Line(35,0,35,35);
		north.setStrokeWidth(5);
		
		east = new Line(0,0,35,0);
		east.setStrokeWidth(5);
		
	
		west = new Line(0,0,35,0);
		west.setStrokeWidth(5);
		

		 stackP.getChildren().addAll(r,north,east,west,north_e,north_w,circle);
		 
		 stackP.setAlignment(Pos.TOP_LEFT);
		 

		 stackP.setAlignment(north,Pos.TOP_CENTER);
		 stackP.setAlignment(east,Pos.CENTER_RIGHT);
		 
		
		 stackP.setAlignment(west,Pos.CENTER_LEFT);
		 
		 
		 stackP.setAlignment(north_e,Pos.TOP_RIGHT);
	
		
		 
		 stackP.setAlignment(circle,Pos.CENTER);
	}
	else { 
	
		stackP.getChildren().addAll(r,circle); 
		stackP.setAlignment(Pos.TOP_LEFT);
		stackP.setAlignment(circle,Pos.CENTER);
	 
	 }

		if (typ.equals("PORTAL1")) {
			setImagePortal1();

		}
		else if(typ.equals("PORTAL2")) {
			setImagePortal2();
		}

	
		stackP.setOnMouseClicked(e -> {
			/*if(!this.already_clicked) {
				already_clicked = true;
				mainScene.showBack(Point.this);
				this.circle.setStroke(Color.GOLD);
			}
			else { 
			already_clicked = false;
			mainScene.show(Point.this);
			}*/
			
		
			if(mainScene.getFirst_click()) {
				this.circle.setStroke(Color.GOLD);
				mainScene.setPrevious_point(this);
				mainScene.setFirst_click(false);
			
			}
			else {
			
				// ECHANGER POINT -> couleur et lettre
				if(mainScene.getPrevious_point().getUsed() && !this.getUsed()) {
					ObservableList<Node> childs = 	mainScene.getPrevious_point().stackP.getChildren();
					ObservableList<Node> childs_me = stackP.getChildren();
					
						
						this.circle.setFill(mainScene.getPrevious_point().color);
						this.color = mainScene.getPrevious_point().color;
						this.text = mainScene.getPrevious_point().text;
						Text text1 = mainScene.getPrevious_point().text;
						this.stackP.getChildren().addAll(text1);
						this.stackP.setAlignment(text1,Pos.CENTER);
					
					
					// enlever lettre
					this.setUsed(true);
					
					
					//IF PORTAL RESET IMAGE PORTAL
					if(mainScene.getPrevious_point().type.equals("PORTAL1")) {
						mainScene.getPrevious_point().resetP1();
					}
					else if(mainScene.getPrevious_point().type.equals("PORTAL2")) {
						mainScene.getPrevious_point().resetP2();
					}
					else {
					// reset used & color & letter
					mainScene.getPrevious_point().reset();
					}
					mainScene.setFirst_click(true);
					mainScene.getPrevious_point().text = null;
					mainScene.getPrevious_point().color = null;
					mainScene.getPrevious_point().used = false;
				//	mainScene.getPrevious_point().setUsed(false);
					mainScene.setPrevious_point(null);
					//mainScene.getPrevious_point().text = this.text;
					//UPDATE NEW COLOR AND TEXT
					
				
				}
				else {
					System.out.println("Change piece");
					mainScene.getPrevious_point().circle.setStroke(Color.BLACK);
					mainScene.setFirst_click(true);
				}
				}
			
				
			

		});

	}

	public void setImagePortal1() {
		Image im;
		try {
			im = new Image(new FileInputStream("image" + File.separator + "red_portal.jpg"));
			circle.setFill(new ImagePattern(im));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setImagePortal2() {
		Image im;
		try {
			im = new Image(new FileInputStream("image" + File.separator + "blue_portal.jpg"));
			circle.setFill(new ImagePattern(im));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void actionPerformed(java.awt.event.ActionEvent e) {
		//showBack();

	}

	public void showBack() {
		//this.north.setStroke(Color.GOLD);
			this.circle.setStroke(Color.GREEN);
	}
	
	public void show() {
		this.circle.setStroke(Color.BLACK);
	}
	public void preReset() {
		this.color = null;
		this.text = null;
		this.used = false;
	}
	public void reset() {
		preReset();
		ObservableList<Node> childs = stackP.getChildren();
		this.stackP.getChildren().remove(childs.size() - 1);	
		//enlever couleur
		this.circle.setFill(Color.WHITE);
		this.circle.setStroke(Color.BLACK);
		this.stackP.getChildren().addAll(circle);
	}
	public void resetP1() {
		preReset();
		ObservableList<Node> childs = stackP.getChildren();
		this.stackP.getChildren().remove(childs.size() - 1);	
		//enlever couleur
		setImagePortal1();
		this.circle.setStroke(Color.BLACK);
		this.stackP.getChildren().addAll(circle);
	}

	public void resetP2() {
		preReset();
		ObservableList<Node> childs = stackP.getChildren();
		this.stackP.getChildren().remove(childs.size() - 1);	
		//enlever couleur
		setImagePortal2();
		this.circle.setStroke(Color.BLACK);
		this.stackP.getChildren().addAll(circle);
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

	public void setConnectionDL() {
		ObservableList<Node> childs = stackP.getChildren();
		 
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
    		south_w = new Line(0,70,35,35);
    		south_w.setStrokeWidth(5);

			stackP.getChildren().addAll(south_w,circle);

			 stackP.setAlignment(Pos.TOP_LEFT);
			 
			 stackP.setAlignment(south_w,Pos.BOTTOM_LEFT);
			 
			 stackP.setAlignment(circle,Pos.CENTER);
        }
		
	}

	public void setConnectionL1() {
		ObservableList<Node> childs = stackP.getChildren();
		 
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
        	west = new Line(0,0,35,0);
    		west.setStrokeWidth(5);
    		south_w = new Line(0,70,35,35);
    		south_w.setStrokeWidth(5);

			stackP.getChildren().addAll(south_w,west,circle);

			 stackP.setAlignment(Pos.TOP_LEFT);
			
			 stackP.setAlignment(west,Pos.CENTER_LEFT);
			 stackP.setAlignment(south_w,Pos.BOTTOM_LEFT);
			 
			 stackP.setAlignment(circle,Pos.CENTER);
        }
	
		
	}
	public void setConnectionL2() {
		ObservableList<Node> childs = stackP.getChildren();
		 
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
        	west = new Line(0,0,35,0);
    		west.setStrokeWidth(5);
    		
    		   north_w = new Line(0,0,35,35);
       		north_w.setStrokeWidth(5);
       		

			stackP.getChildren().addAll(north_w,west,circle);

			 stackP.setAlignment(Pos.TOP_LEFT);
			
			 stackP.setAlignment(west,Pos.CENTER_LEFT);
		 	 
			 stackP.setAlignment(circle,Pos.CENTER);
        }
	
		
	}

	public void setConnectionUL() {
		ObservableList<Node> childs = stackP.getChildren();
		 
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
            north_w = new Line(0,0,35,35);
    		north_w.setStrokeWidth(5);
    		

			stackP.getChildren().addAll(north_w,circle);

			 stackP.setAlignment(Pos.TOP_LEFT);
			 
			
			 
			 stackP.setAlignment(circle,Pos.CENTER);
        }
		
		
	}

	public void setConnectionDR() {
		ObservableList<Node> childs = stackP.getChildren();
		 
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
            south_e = new Line(35,35,0,0);
			south_e.setStrokeWidth(5);
			
	
			stackP.getChildren().addAll(south_e,circle);

			 stackP.setAlignment(Pos.TOP_LEFT);
			 

			 stackP.setAlignment(south_e,Pos.BOTTOM_RIGHT);
	    		

			 
			 stackP.setAlignment(circle,Pos.CENTER);
        }
		
		
	}

	public void setConnectionR1() {
		ObservableList<Node> childs = stackP.getChildren();
		 
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
            south_e = new Line(35,35,0,0);
			south_e.setStrokeWidth(5);
			
			east = new Line(0,0,35,0);
			east.setStrokeWidth(5);
			
			
	
			stackP.getChildren().addAll(east,south_e,circle);

			 stackP.setAlignment(Pos.TOP_LEFT);
			 

			 stackP.setAlignment(south_e,Pos.BOTTOM_RIGHT);
	    		
			 stackP.setAlignment(east,Pos.CENTER_RIGHT);
			 	 
			 stackP.setAlignment(circle,Pos.CENTER);
        }
		
		
	}
	public void setConnectionR2() {
		ObservableList<Node> childs = stackP.getChildren();
		 
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
           
			east = new Line(0,0,35,0);
			east.setStrokeWidth(5);

			north_e = new Line(-35,35,0,0);
			north_e.setStrokeWidth(5);
		
			
	
			stackP.getChildren().addAll(east,north_e,circle);

			 stackP.setAlignment(Pos.TOP_LEFT);
			 
			 stackP.setAlignment(north_e,Pos.TOP_RIGHT);
			 stackP.setAlignment(east,Pos.CENTER_RIGHT);
			 	 
			 stackP.setAlignment(circle,Pos.CENTER);
        }
		
	}

	public void setConnectionUR() {
		ObservableList<Node> childs = stackP.getChildren();
		 
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
           
			
			north_e = new Line(-35,35,0,0);
			north_e.setStrokeWidth(5);
		
			
	
			stackP.getChildren().addAll(north_e,circle);

			 stackP.setAlignment(Pos.TOP_LEFT);
			 
			 stackP.setAlignment(north_e,Pos.TOP_RIGHT);
			
			 stackP.setAlignment(circle,Pos.CENTER);
        }
		
	}
	
	public void setBushi(String type) {
		this.used = true;
		if(type.equals("MONKEY1")) {
			
			this.color = Color.rgb(174, 37, 38);
			ObservableList<Node> childs = stackP.getChildren();
			 
	        if (childs.size() > 1) {
	            
	            stackP.getChildren().remove(childs.size()-1);   		
	           
	            Text text = new Text("M");
				text.setFill(Color.WHITE);
				text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,35));
				this.text = text;
				this.circle.setFill(Color.rgb(174, 37, 38));
				
				stackP.getChildren().addAll(circle,text);
				stackP.setAlignment(text,Pos.CENTER);
				
	        }
		}
		else if(type.equals("MONKEY2")) {
			
			this.color = Color.rgb(6,77,130);
			ObservableList<Node> childs = stackP.getChildren();
			 
	        if (childs.size() > 1) {
	            
	            stackP.getChildren().remove(childs.size()-1);   		
	           
	            Text text = new Text("M");
				text.setFill(Color.WHITE);
				text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,35));
				this.text = text;
				this.circle.setFill(Color.rgb(6,77,130));
				
				stackP.getChildren().addAll(circle,text);
				stackP.setAlignment(text,Pos.CENTER);
				
	        }
		}
		else if(type.equals("LION1")) {ObservableList<Node> childs = stackP.getChildren();
		
		this.color = Color.rgb(174, 37, 38);
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
           
            Text text = new Text("L");
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,35));
			this.text = text;
			this.circle.setFill(Color.rgb(174, 37, 38));
			
			stackP.getChildren().addAll(circle,text);
			stackP.setAlignment(text,Pos.CENTER);
			
        }}
		else if(type.equals("LION2")) {	ObservableList<Node> childs = stackP.getChildren();
		
		this.color = Color.rgb(6,77,130);
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
           
            Text text = new Text("L");
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,35));
			this.text = text;
			this.circle.setFill(Color.rgb(6,77,130));
			
			stackP.getChildren().addAll(circle,text);
			stackP.setAlignment(text,Pos.CENTER);
			
        }}

		else if(type.equals("DRAGON1")) {ObservableList<Node> childs = stackP.getChildren();
		
		this.color = Color.rgb(174, 37, 38);
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
           
            Text text = new Text("Dr");
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,35));
			this.text = text;
			this.circle.setFill(Color.rgb(174, 37, 38));
			
			stackP.getChildren().addAll(circle,text);
			stackP.setAlignment(text,Pos.CENTER);
			
        }}
		else if(type.equals("DRAGON2")) {	
			
		
		this.color = Color.rgb(6,77,130);
		ObservableList<Node> childs = stackP.getChildren();
		 
        if (childs.size() > 1) {
            
            stackP.getChildren().remove(childs.size()-1);   		
           
            Text text = new Text("Dr");
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,35));
			this.text = text;
			this.circle.setFill(Color.rgb(6,77,130));
			
			stackP.getChildren().addAll(circle,text);
			stackP.setAlignment(text,Pos.CENTER);
			
        }}
	}
	
}
