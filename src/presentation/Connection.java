package presentation;

import javafx.scene.shape.Line;

public class Connection {

	private MainScene mainScene;
	private Point p1;
	private Line line1, line2, line3, line4;

	public Connection(MainScene mainScen, Point p1) {
		this.mainScene = mainScen;

		this.p1 = p1;

		line1 = new Line();

		line1.setStartX(p1.getCircle().getCenterX() - 30);
		line1.setStartY(p1.getCircle().getCenterY() - 30);
		line1.setEndX(p1.getCircle().getCenterX() + 30);
		line1.setEndY(p1.getCircle().getCenterY() + 30);
		line1.setStrokeWidth(5);

		line2 = new Line();

		line2.setStartX(p1.getCircle().getCenterX() - 30);
		line2.setStartY(p1.getCircle().getCenterY());
		line2.setEndX(p1.getCircle().getCenterX() + 60);
		line2.setEndY(p1.getCircle().getCenterY());
		line2.setStrokeWidth(5);

	}

	public Line getLine1() {
		return line1;
	}

	public Line getLine2() {
		return line2;
	}

}
