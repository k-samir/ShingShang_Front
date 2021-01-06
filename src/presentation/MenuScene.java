package presentation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import application.Main;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MenuScene extends Scene {
	Pane root;
	Main main;

	public MenuScene(Main main) throws Exception {	
		
	
		super(new Pane());
		this.main = main;
		root = (Pane) (this.getRoot());
	
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
		Credit credit = new Credit();
		root.getChildren().add(credit);
		Title title = new Title("S H I N G S H A N G");
		title.setTranslateX(75);
		title.setTranslateY(200);
		
		MenuBox menu = new MenuBox(
				new MenuItem("2 PLAYER GAME",main),
				
				new MenuItem("1 PLAYER VS A.I",main),
				new MenuItem("RULES",main),
				new MenuItem("EXIT",main));
						
		menu.setTranslateX(100);
		menu.setTranslateY(300);
		
		root.getChildren().addAll(title,menu);
	
			
	}

}
