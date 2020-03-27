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
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage mainStage) throws Exception {
		GUI gui = new GUI(mainStage); //frontend
		SpawnHandler spawnHandler = new SpawnHandler(projectiles);
		Controller c = new Controller(gui, spawnHandler); //logic
		
		mainStage.setTitle("Planet Defenders");
		mainStage.show();
		c.postInit();
	}
}

