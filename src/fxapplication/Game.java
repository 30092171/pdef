package fxapplication;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import pdef.PlayerPlanet;
import pdef.Projectile;
import pdef.SpawnHandler;
import pdef.PlayerInput;

public class Game extends Application {
	//root class.
	public ArrayList<Projectile> projectiles;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage mainStage) throws Exception {
		PlayerPlanet player = new PlayerPlanet();//make new model
		GUI gui = new GUI(mainStage); //frontend
		SpawnHandler spawnHandler = new SpawnHandler(projectiles, player);
		PlayerInput playerInput = new PlayerInput(projectiles);
		Controller c = new Controller(player, gui, spawnHandler, playerInput); //logic
		
		mainStage.setTitle("Planet Defenders");
		mainStage.show();
	}
}

