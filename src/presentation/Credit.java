package presentation;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Credit extends StackPane {
	
		public Credit() {
		
			Text text = new Text("Samir KAMAR 2020/2021");
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,20));
			
			this.getChildren().add(text);
			this.getChildren().get(0).setTranslateY(570);
			this.getChildren().get(0).setTranslateX(10);
		
		}
}
