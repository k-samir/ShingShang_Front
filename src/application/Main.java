package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

import abstraction.game.Game;
import control.ControlPlayerName;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import presentation.MainScene;
import presentation.MenuScene;

public class Main extends Application {

	private MenuScene mainScene;
	private MainScene mainScene3;
	private Stage stage;
	private Game game;

	@Override
	public void start(Stage stage) throws Exception {
		Game shingshang = new Game();
		this.game = shingshang;
		this.stage = stage;
		this.mainScene = new MenuScene(this);
		stage.setTitle("SHINGSHANG_IHM");
		stage.setScene(this.mainScene);
		Image imageIcon;
		try {
			
				imageIcon = new Image(new FileInputStream("image" + File.separator + "brown_portal.jpg"),200,160,false,false);
				stage.getIcons().add(imageIcon);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		stage.show();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		stage.setResizable(false);

	}

	// Menu Scene
	public void start1() throws Exception {
		start(stage);
	}

	// Game Scene
	@SuppressWarnings("unused")
	public void start2() throws Exception {
		String[] players = askPlayersName();

		if (players[0] == "" || players[1] == "") {
			start1();
		} else {

			// CALL CONTROLLER
			ControlPlayerName controlPlayerName = new ControlPlayerName(players[0], players[1], game);

			stage.setTitle("SHINGSHANG_IHM");
			this.mainScene3 = new MainScene(this);
			stage.setScene(this.mainScene3);
			stage.show();
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
			stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

			stage.setResizable(false);
			game.createBoard();
			mainScene3.updateLabel();
			
		}

	}

	public void announceWinner(String winner, int winnerNumber) {
		@SuppressWarnings("unused")
		String action = "";
		@SuppressWarnings("rawtypes")
		Dialog dialog = new Dialog();

		dialog.setTitle("Winner");
		dialog.setHeaderText(winner + " wins the Game !!!" + "\n\n\n Do you want to try gain ?");

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");
		dialog.getDialogPane().getButtonTypes().addAll(yes, no);

		dialog.getDialogPane().setContent(grid);


		ImageView imageV = new ImageView();
		try {
			if (winnerNumber == 1) {
				imageV.setImage(new Image(new FileInputStream("image" + File.separator + "red_portal.jpg")));
				imageV.setFitWidth(200);
				imageV.setFitHeight(160);
			} else {
				imageV.setImage(new Image(new FileInputStream("image" + File.separator + "blue_portal.jpg")));
				imageV.setFitWidth(200);
				imageV.setFitHeight(160);
			}

		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		dialog.setGraphic(imageV);

		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		try {
			if (winnerNumber == 1) {
				stage.getIcons().add(new Image(new FileInputStream("image" + File.separator + "red_portal.jpg")));
			} else {
				stage.getIcons().add(new Image(new FileInputStream("image" + File.separator + "blue_portal.jpg")));
			}

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		Optional<ButtonType> result = dialog.showAndWait();
		System.out.println(result.get().getText().toString());

		if (result.get().getText().toString() == "Yes") {
			try {
				this.game = new Game();
				this.start1();
				this.start2();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		} else if (result.get().getText().toString() == "No") {
			Platform.exit();
		}

	}
	
	public void setGame(Game m) {this.game = m;}
	
	public String[] askPlayersName() throws Exception {

		// Popup asking Players Name
		
		String[] player = { " ", "" };

		TextInputDialog dialog = new TextInputDialog("P1");
		TextInputDialog dialog2 = new TextInputDialog("P2");
		dialog.setTitle("Name Player 1");
		dialog.setHeaderText("Choose a name for the player 1");
		dialog.setContentText("Name PLAYER 1 :");
		dialog.initModality(Modality.WINDOW_MODAL);

		dialog2.setTitle("Name Player 2");
		dialog2.setHeaderText("Choose a name for the player 2");
		dialog2.setContentText("Name PLAYER 2 :");
		dialog2.initModality(Modality.WINDOW_MODAL);

		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(new FileInputStream("image" + File.separator + "red_portal.jpg")));

		Stage stage2 = (Stage) dialog2.getDialogPane().getScene().getWindow();
		stage2.getIcons().add(new Image(new FileInputStream("image" + File.separator + "blue_portal.jpg")));

		Optional<String> reponse = dialog.showAndWait();
		if (reponse.isPresent()) {
			player[0] = reponse.get();
			
			Optional<String> reponse1 = dialog2.showAndWait();
			if (reponse1.isPresent()) {

				if (reponse1.get().equals(player[0])) {
					player[1] = "";

				} else {
					player[1] = reponse1.get();
					
				}
			}

			else {
			}

		} else {
		}
		return player;

	}

	@SuppressWarnings("unchecked")
	public void showRules() {

		@SuppressWarnings("rawtypes")
		Dialog dialog = new Dialog();

		dialog.setTitle("RULES");

		BorderPane grid = new BorderPane();

		grid.setMaxSize(100, 100);
		ButtonType back = new ButtonType("Back to game");

		// Root Item
		TreeItem<String> rootItem = new TreeItem<String>("RULES");
		rootItem.setExpanded(true);

		
		TreeItem<String> controls = new TreeItem<>("Controls");
		TreeItem<String> base = new TreeItem<String>("Base");
		TreeItem<String> moves = new TreeItem<>("Moves");
		TreeItem<String> shingshang_sequence = new TreeItem<>("ShingShang Sequence");
		TreeItem<String> special_rules = new TreeItem<>("Special Rules");
		TreeItem<String> end_game = new TreeItem<>("End of Game");
		

		// Add to Root
		rootItem.getChildren().addAll(controls,base, moves, shingshang_sequence, special_rules, end_game);

		TreeView<String> tree = new TreeView<String>(rootItem);

		// Create the TextArea
		TextArea textArea = new TextArea();
		textArea.setEditable(false);

		tree.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> updateTextArea(newValue.getValue(), textArea));

		textArea.setText("Welcome to the Rule Page, click any category to continue ...");

		textArea.setStyle("-fx-text-alignment: center;");
		textArea.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		textArea.setWrapText(true);

		grid.setPadding(new Insets(5));
		BorderPane treeBP = new BorderPane();
		treeBP.setCenter(tree);

		treeBP.setMaxHeight(180);
		grid.setTop(treeBP);

		grid.setBottom(textArea);

		Image imageIcon;
		try {
			
				imageIcon = new Image(new FileInputStream("image" + File.separator + "rules.png"),200,160,false,false);
				Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
				stage.getIcons().add(imageIcon);

		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}
		
		dialog.getDialogPane().getButtonTypes().addAll(back);

		dialog.getDialogPane().setContent(grid);

		Optional<ButtonType> result = dialog.showAndWait();
		try {
			

			if (result.get().getText().toString() == "Back to game") {
				try {
					dialog.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
		}

	}

	private void updateTextArea(String value, TextArea textArea) {
		
		if (value.equals("Base")) {
			textArea.setText("Players take turns performing one of the following two actions:\r\n" + "\r\n"
					+ " - a player can move one of his pieces on the board to another square on the board.\r\n" + "\r\n"
					+ " - a player may jump over another piece if it is smaller or the same size as the jumper's piece.");
		} else if (value.equals("Moves")) {
			textArea.setText("To move a piece on the board, it is necessary that the destination point is free.\r\n"
					+ "\r\n" + "You can move horizontally, vertically or diagonally, both forwards and backwards.\r\n"
					+ "\r\n"
					+ "To jump, the jumper's piece must be on a square adjacent to a point occupied by one of its own pieces or by the opposing player's piece.\r\n"
					+ "\r\n"
					+ "The jump may be made vertically, horizontally or diagonally, provided that the next square is empty.\r\n");
		} else if (value.equals("Special Rules")) {
			textArea.setText(" - Monkeys can move one or two point in any direction,"  
					+ "horizontally, vertically or diagonally, but without changing direction during the turn.\r\n\n" 
					+ " - Lions can move one square in any direction, horizontally, vertically or diagonally."+ "\r\n\n"
					+  "- Dragons can only move by jumping.");
		}
	else if(value.equals("ShingShang Sequence"))
	{
		textArea.setText(
				"Several jumps can be chained together during the same tour. This sequence of jumps is called a SHING SHANG.\r\n"
						+ "\r\n" + "If, during a SHING SHANG, one jumps over an opponent's piece, one must stop "
						+ "and the opponent's piece is removed from the board. However, you win an extra turn with another piece.");
	}else if(value.equals("End of Game"))
	{
		textArea.setText("A player wins the game when : \r\n\n - He manages to bring one of his dragons"+
	" to one of his opponent's portals (special boxes). \r\n\n OR \r\n\n  - Captures both of his opponent's dragons.");
	}
	else if(value.equals("Controls"))
	{
		textArea.setText("To Play you can use your mouse to first, click on the piece you want to move. Then the possible moves are displayed." + 
	" you can then click on the destination point to move your piece. \r\n\n You can Pass your turn by clicking the PASS Button or press the 'S' KEY to pass. \r\n\n" + 
				" If you want to read the rules there is a RULE Button at the top of the screen.");
	}

	}

	public Game getGame() {
		return game;
	}

	public static void main(String[] args) {
		launch();
	}
}
