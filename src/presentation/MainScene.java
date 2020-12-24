package presentation;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class MainScene extends Scene {
	private Point[][] points;
	GridPane root;

	public MainScene() throws Exception {
		super(new GridPane());
		root = (GridPane) (this.getRoot());
		// root.setHgap(10);
		// root.setVgap(10);
		reset();
	}

	public void reset() {
		this.points = new Point[10][10];

		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {

				if ((lig == 1 && (col == 4 || col == 5) || (lig == 8 && (col == 4 || col == 5)))) {
					points[lig][col] = new Point("PORTAL", this);
				}

				else if (col == 0 || col == 9) {
					points[lig][col] = new Point("NOTHING", this);

				} else {
					points[lig][col] = new Point("STANDARD", this);

				}
			}
		}

		points[4][0].setType("STANDARD");
		points[5][0].setType("STANDARD");
		points[4][9].setType("STANDARD");
		points[5][9].setType("STANDARD");

		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {
				if (points[lig][col].getType().equals("STANDARD") || points[lig][col].getType().equals("PORTAL")) {
					
				
					root.add(points[lig][col].getStackPane(), col, lig);

				}

			}
		}

	}

	public void showBack(Point point) {
		int x = 0, y = 0;
		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {

				if (points[lig][col] == point) {
					x = lig;
					y = col;
					break;
				}

			}
		}
		try {
			System.out.print("[" + x + " , " + y + "]");
			points[x + 1][y].showBack();
		} catch (Exception e) {
		}
		try {
			points[x - 1][y].showBack();
		} catch (Exception e) {
		}
		try {
			points[x][y + 1].showBack();
		} catch (Exception e) {
		}
		try {
			points[x][y - 1].showBack();
		} catch (Exception e) {
		}
		try {
		} catch (Exception e) {
		}

	}

	public void show(Point point) {
		int x = 0, y = 0;
		for (int lig = 0; lig < 10; lig++) {
			for (int col = 0; col < 10; col++) {

				if (points[lig][col] == point) {
					x = lig;
					y = col;
					break;
				}

			}
		}
		try {
			System.out.print("[" + x + " , " + y + "]");
			points[x + 1][y].show();
		} catch (Exception e) {
		}
		try {
			points[x - 1][y].show();
		} catch (Exception e) {
		}
		try {
			points[x][y + 1].show();
		} catch (Exception e) {
		}
		try {
			points[x][y - 1].show();
		} catch (Exception e) {
		}
		try {
		} catch (Exception e) {
		}

	}

	public void available(presentation.Point square2) {
		// TODO Auto-generated method stub

	}

}
