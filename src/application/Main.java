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
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
		stage.show();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		stage.setResizable(false);
		
		

	}

	public void start1() throws Exception {
		start(stage);
	}

	public void start2() throws Exception {
		String[] players = askPlayersName();

		if (players[0] == "" || players[1] == "") {
			start1();
		} else {
			
			// CALL CONTROLLER
			ControlPlayerName controlPlayerName = new ControlPlayerName(players[0],players[1],game);
			
			
			stage.setTitle("SHINGSHANG_IHM");
			this.mainScene3 = new MainScene(this);
			stage.setScene(this.mainScene3);
			stage.show();
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
			stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

			stage.setResizable(false);
			game.createBoard();
			mainScene3.changeLabel("It is now the turn of " + this.getGame().getTurnPlayer().getName()
					 + "/ Player " + this.getGame().getTurnPlayer().getNumber() );
		
		}

	}

	public void announceWinner(String winner,int winnerNumber) {
		String action = "";
		Dialog dialog = new Dialog();
		
		dialog.setTitle("Winner");
		dialog.setHeaderText(winner + " wins the Game !!!" + "\n\n\n Do you want to try gain ?");
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");
		dialog.getDialogPane().getButtonTypes().addAll(yes,no);
		
		dialog.getDialogPane().setContent(grid);



		//dialog.initModality(Modality.WINDOW_MODAL);
		ImageView imageV = new ImageView();
		try {
			if(winnerNumber == 1) {
				imageV.setImage(new Image(new FileInputStream("image" + File.separator + "red_portal.jpg")));
				imageV.setFitWidth(200);
				imageV.setFitHeight(160);
			}
			else {
				imageV.setImage(new Image(new FileInputStream("image" + File.separator + "blue_portal.jpg")));
				imageV.setFitWidth(200);
				imageV.setFitHeight(160);
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dialog.setGraphic(imageV);

		
		Stage stage  = (Stage) dialog.getDialogPane().getScene().getWindow();
		try {
			if(winnerNumber == 1) {
				stage.getIcons().add(new Image(new FileInputStream("image" + File.separator + "red_portal.jpg")));
			}
			else {
				stage.getIcons().add(new Image(new FileInputStream("image" + File.separator + "blue_portal.jpg")));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Optional<ButtonType> result = dialog.showAndWait();
		System.out.println(result.get().getText().toString());
		
		if (result.get().getText().toString() == "Yes"){
			try {
				this.start2();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(result.get().getText().toString() == "No") {
			Platform.exit();
		} 
		
		
	}
	public String[] askPlayersName() throws Exception {

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
		
		Stage stage  = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(new FileInputStream("image" + File.separator + "red_portal.jpg")));
		
		Stage stage2  = (Stage) dialog2.getDialogPane().getScene().getWindow();
		stage2.getIcons().add(new Image(new FileInputStream("image" + File.separator + "blue_portal.jpg")));
		

		Optional<String> reponse = dialog.showAndWait();
		if (reponse.isPresent()) {
			player[0] = reponse.get();
			System.out.println("PLAYER 1 => " + reponse.get());

			Optional<String> reponse1 = dialog2.showAndWait();
			if (reponse1.isPresent()) {

				if (reponse1.get().equals(player[0])) {
					player[1] = "";

				} else {
					player[1] = reponse1.get();
					System.out.println("PLAYER 2 => " + reponse1.get());
				}
			}

			else {
				System.out.println("Vous avez ferme sans repondre...");

			}

		} else {
			System.out.println("Vous avez ferme sans repondre...");

		}
		return player;

	}
	
	public Game getGame() {
		return game;
	}

	public static void main(String[] args) {
	//	shingshang.start();
		launch();
	}
}
