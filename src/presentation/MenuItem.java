package presentation;

import application.Main;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuItem extends StackPane {
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
			if(name.equals("1 PLAYER VS A.I")) {
				bg.setOpacity(0.2);
				text.setOpacity(0.2);
			}
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,22));
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg,text);
			
			setOnMouseEntered(event -> {
if(name.equals("1 PLAYER VS A.I")) {
					
				}else {
				bg.setFill(gradient);
				text.setFill(Color.WHITE);}
			});
			
			setOnMouseExited(event -> {
if(name.equals("1 PLAYER VS A.I")) {
					
				}else {
				bg.setFill(Color.BLACK);
				text.setFill(Color.DARKGREY);}
			});
			
			setOnMousePressed(event -> {
				if(name.equals("1 PLAYER VS A.I")) {
					
				}else {
					
				bg.setFill(Color.SADDLEBROWN);}	
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
				else if(name.equals("1 PLAYER VS A.I")) {
					
				}
				bg.setFill(gradient);	
			});
			
			
					
		}

}
