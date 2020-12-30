package presentation;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import application.Main;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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


public class MenuScene extends Scene {
	Pane root;
	Main main;
	public MenuScene(Main main) throws Exception {	
		
	
		super(new Pane());
		this.main = main;
		root = (Pane) (this.getRoot());
		
		
	//	Pane root = new Pane();
		root.setPrefSize(860,600);
		
		try ( InputStream is = Files.newInputStream(Paths.get("image/menu_bg.jpg"))) {
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(860);
			img.setFitHeight(600);
			root.getChildren().add(img);
		}
		catch(IOException e) {
			System.out.println("No Image");
		}
		
		Title title = new Title("S H I N G S H A N G");
		title.setTranslateX(75);
		title.setTranslateY(200);
		
		MenuBox menu = new MenuBox(
				new MenuItem("2 PLAYER GAME",main),
				new MenuItem("1 PLAYER VS A.I",main),
				new MenuItem("EXIT",main));
		menu.setTranslateX(100);
		menu.setTranslateY(300);
		
		
		
		
		
		root.getChildren().addAll(title,menu);
				
	}

	


	private static class Title extends StackPane{
		public Title(String name) {
			Rectangle bg = new Rectangle(300,60);
			bg.setStroke(Color.WHITE);
			bg.setStrokeWidth(2);
			bg.setFill(null);
			
			Text text = new Text(name);
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,50));
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg,text);
		}
		
		
		
		
	}
	private static class MenuBox extends VBox {
		
		public MenuBox(MenuItem... items) {
			getChildren().add(createSeparator());
			
			for(MenuItem item : items) {
				getChildren().addAll(item,createSeparator());
			}
		}
		
		private Line createSeparator() {
			Line sep = new Line();
			sep.setEndX(200);
			sep.setStroke(Color.DARKGREY);
			return sep;
		}
	}
	
	
	private static class MenuItem extends StackPane {
		public MenuItem(String name,Main main) {
			LinearGradient gradient = new LinearGradient(0,0,1,0,true,CycleMethod.NO_CYCLE, new Stop[] {
					new Stop(0,Color.SADDLEBROWN),
					new Stop(0.1,Color.BLACK),
					new Stop(0.9,Color.BLACK),
					new Stop(1,Color.SADDLEBROWN)
					});
			Rectangle bg = new Rectangle(200,30);
			bg.setOpacity(0.4);
			
			Text text = new Text(name);
			text.setFill(Color.DARKGREY);
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,22));
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg,text);
			
			setOnMouseEntered(event -> {
				bg.setFill(gradient);
				text.setFill(Color.WHITE);
			});
			
			setOnMouseExited(event -> {
				bg.setFill(Color.BLACK);
				text.setFill(Color.DARKGREY);
			});
			
			setOnMousePressed(event -> {
				bg.setFill(Color.SADDLEBROWN);	
			});
			
			setOnMouseReleased(event -> {
				if(name.equals("EXIT")){
					Platform.exit();
				}
				else if(name.equals("2 PLAYER GAME")) {
					try {
						main.start2();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				bg.setFill(gradient);	
			});
			
			
					
		}

		
		
		
		
		
	}
	
}
