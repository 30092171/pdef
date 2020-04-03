/*
 * 
 */
package fxapplication;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import pdef.PlayerPlanet;
import pdef.Projectile;
import pdef.SpawnHandler;
import pdef.PlayerInput;

/**
 * The Class Game represents the entry point of Planet Defenders. 
 * All neccessary objects are instantiated in this class.
 */
public class Game extends Application {
	
	/** The projectiles. */
	//root class.
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 * @throws Exception the exception
	 */
	public void start(Stage mainStage) throws Exception {
		GUI gui = new GUI(mainStage); //frontend
		SpawnHandler spawnHandler = new SpawnHandler(projectiles);
		Controller c = new Controller(gui, spawnHandler); //logic
		
		mainStage.setTitle("Planet Defenders");
		mainStage.show();
		c.postInit();
	}
}

