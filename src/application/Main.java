package application;
import javafx.application.Application;

import javafx.stage.Stage;
import presentation.MainScene;


public class Main extends Application {
	private MainScene mainScene;

	public void start(Stage stage) throws Exception {
		stage.setTitle("Composant Map");
		this.mainScene = new MainScene();
		stage.setScene(this.mainScene);
		stage.setWidth(300);
		stage.setHeight(200);
		stage.show();
	}
	public static void main(String[] args) {
		launch();
	}
}