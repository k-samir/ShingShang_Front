package application;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

import abstraction.game.Game;
import control.ControlPlayerName;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
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
			mainScene3.changeLabel("C'est le tour de : " + game.getTurnPlayer().getNumber());
		
		}

	}

	public String[] askPlayersName() throws Exception {

		String[] player = { " ", "" };

		TextInputDialog dialog = new TextInputDialog("Nantes");
		TextInputDialog dialog2 = new TextInputDialog("Nantes");
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
