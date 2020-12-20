package presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Point {

	private MainScene mainScene;
	private Circle circle;
	private String type;

	private Connection N;
	// , S, E, W, NE, NW, SE, SW;

	public Point(String typ, MainScene mainScen) {
		this.mainScene = mainScen;
		this.type = typ;

		// Drawing a Circle
		circle = new Circle();
		// Setting the properties of the circle
		circle.setCenterX(300.0f);
		circle.setCenterY(135.0f);
		circle.setRadius(30);
		// Setting other properties
		circle.setFill(Color.WHITE);
		circle.setStrokeWidth(5.0);
		circle.setStroke(Color.BLACK);

		if (typ.equals("PORTAL")) {
			Image im;
			try {
				im = new Image(new FileInputStream("image" + File.separator + "red_portal.jpg"));
				circle.setFill(new ImagePattern(im));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		N = new Connection(mainScen, this);

		circle.setOnMouseClicked(e -> {
			mainScene.showBack(Point.this);

		});

	}

	public void actionPerformed(java.awt.event.ActionEvent e) {
		showBack();

	}

	public void showBack() {
		this.circle.setStroke(Color.BLUE);
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

	public Connection getN() {
		return N;
	}

}
