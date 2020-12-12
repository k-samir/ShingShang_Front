package presentation;

import javafx.scene.layout.StackPane;

public class ControlMap extends StackPane {

public static final String path = "map.jpg";
	ResizableCanvas rc;
	
	public ControlMap() {

        rc = new ResizableCanvas();
        rc.widthProperty().bind(this.widthProperty());
        rc.heightProperty().bind(this.heightProperty());
        this.getChildren().add(rc);

	}
}
