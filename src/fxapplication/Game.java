/*
 * 
 */
package fxapplication;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import pdef.Projectile;
import pdef.SpawnHandler;

/**
 * This class represents the entry point of Planet Defenders GUI version. 
 * All necessary game logic and GUI objects are instantiated in this class.
 */
public class Game extends Application {
	
	/** The arraylist of projectiles. */
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	
	/**
	 * The main method calls launch to start the game.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Starts the game.
	 *
	 * @param mainStage The main stage of the JavaFx GUI
	 * @throws Exception The exception
	 */
	public void start(Stage mainStage) throws Exception {
		GUI gui = new GUI(mainStage); //frontend
		SpawnHandler spawnHandler = new SpawnHandler(projectiles);
		Controller c = new Controller(gui, spawnHandler, projectiles); //logic
		
		mainStage.setTitle("Planet Defenders");
		mainStage.show();
		c.postInit();
	}
}

