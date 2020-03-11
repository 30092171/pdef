package fxapplication;
import pdef.*;

public class Controller {
	//all of the logic for modifying data and the gui goes in here.
	
	private PlayerPlanet planet;
	private GUI gui;
	private SpawnHandler spawnHandler;
	private PlayerInput playerInput;
	
	public Controller(PlayerPlanet planet, GUI gui, SpawnHandler spawnHandler, PlayerInput playerInput) {
		this.planet = planet;
		this.gui = gui;
		this.spawnHandler = spawnHandler;
		this.playerInput = playerInput;
	}
}
