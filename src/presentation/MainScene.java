package presentation;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainScene extends Scene  {
	public MainScene() throws Exception {
		super(new BorderPane());
		BorderPane root = (BorderPane)(this.getRoot());
		root.setCenter(new ControlMap());
	}
}
