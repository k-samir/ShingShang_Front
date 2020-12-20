package application;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import presentation.MainScene;

public class Main extends Application {

	private MainScene mainScene;

	public void start(Stage stage) throws Exception {
		stage.setTitle("SHINGSHANG_IHM");
		this.mainScene = new MainScene();
		stage.setScene(this.mainScene);
		stage.show();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

	}

	public static void main(String[] args) {
		launch();
	}
}
