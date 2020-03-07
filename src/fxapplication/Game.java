package fxapplication;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.geometry.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pdef.PlayerPlanet;

public class Game extends Application {
	//root class.
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage mainStage) throws Exception {
		PlayerPlanet player = new PlayerPlanet();//make new model
		GUI gui = new GUI(mainStage); //frontend
		Controller c = new Controller(player, gui); //logic
		
		mainStage.setTitle("Planet Defenders");
		mainStage.show();
	}
}

