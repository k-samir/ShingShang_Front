package application;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import presentation.MainScene;
import presentation.MenuScene;

public class Main extends Application {

	private MenuScene mainScene;
	//private MainPlayerName mainScene2;
	private MainScene mainScene3;
	private Stage stage;

	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.mainScene = new MenuScene(this);
		stage.setTitle("SHINGSHANG_IHM");
		stage.setScene(this.mainScene);
		stage.show();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		stage.setResizable(false);
	
	}
	
	public void start1() throws Exception{
		start(stage);
	}
	public void start2() throws Exception{
		
		stage.setTitle("SHINGSHANG_IHM");
		//this.mainScene2 = new MainScene(this);
		//stage.setScene(this.mainScene2);
		stage.show();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		
		stage.setResizable(false);
	}
	public void start3() throws Exception {
		
		
		stage.setTitle("SHINGSHANG_IHM");
		this.mainScene3 = new MainScene(this);
		stage.setScene(this.mainScene3);
		stage.show();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		
		stage.setResizable(false);
			
		
		}
		

	
	
	
	public static void main(String[] args) {
		launch();
	}
}

