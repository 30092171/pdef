/*
 * 
 */
package pdef;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

/**
 * This class represents the spawn handler for the projectiles.
 */
public class SpawnHandler {
	
	/** The projectile's unique name identifier */
	private static String names = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/** A counter for the number of projectile's currently on screen */
	private int counter = 0;
	
	/** An arraylist that holds the projectiles */
	public ArrayList<Projectile> projectiles;
	
	/**
	 * Instantiates a new spawn handler
	 *
	 * @param projectiles the projectiles
	 */
	//Constructor, gets reference to projectile list and PlayerPlanet
	public SpawnHandler(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}
	
	/**
	 * Spawns a projectile a fixed initial distance away from the planet
	 * with a random starting angle
	 *
	 * @return The spawned projectile
	 */
	public Projectile spawnProjectile() {
		double initialDistance = 400; // Spawns outside of screen 
		double spawnAngle = (Math.random() * Math.PI * 2);
		PolarCoord p = new PolarCoord(initialDistance, spawnAngle, new Point2D(360,360));
		Circle circle = new Circle(p.getRawX(), p.getRawY(), 10);
		
		Projectile newProjectile;
		double projType = Math.random();
		if (projType >= 0.8) {
			newProjectile = new RotatingProjectile("Projectile " + (projectiles.size() + 1), p, circle);
		} else if (projType >= 0.6 && projType < 0.8){
			newProjectile = new SpeedUpProjectile("Projectile " + (projectiles.size() + 1), p, circle);
		} else {
			newProjectile = new DefaultProjectile("Projectile " + (projectiles.size() + 1), p, circle);
		}
		return  newProjectile;
		
	}
	
	/**
	 * Spawns projectile for text-based version
	 *
	 * @return The spawned projectile
	 */
	public Projectile oldProjectile() {
		double initialDistance = Math.random() * 70 + 10; // Spawns outside of screen 
		double spawnAngle = (Math.random() * Math.PI * 2);
		PolarCoord p = new PolarCoord(initialDistance, spawnAngle, new Point2D(0,0));
		return new DefaultProjectile(names.charAt(counter++) + "", p);
		
	}
	
	//Projectile spawning after the rest of the turn's game logic has occured. 
	/**
	 * Spawns projectiles based on number of projectiles on screen.
	 * Ensures projectiles always exists by spawning 2 projectiles if none are on the screen
	 */
	//If there are no projectiles left, then more will always be spawned
	public void trySpawn() {
		if(projectiles.size() < 1) {
			projectiles.add(oldProjectile());
			projectiles.add(oldProjectile());
		}
		if(projectiles.size() < 4) {
			if (Math.random() > 0.5) {
				projectiles.add(oldProjectile());
				projectiles.add(oldProjectile());
			}
		}
	}
	
	/**
	 * Initially spawns 3 projectiles
	 */
	public void initialSpawn() {
		projectiles.add(oldProjectile());
		projectiles.add(oldProjectile());
		projectiles.add(oldProjectile());
	}
	
	/**
	 * Prints the projectile's unique name identifier, distance away from planet, and
	 * x coordinate and y coordinate.
	 *
	 * @param player The player object
	 */
	public void printProjectileStatus(PlayerPlanet player) {
		for(Projectile element : projectiles) {
			PolarCoord p = element.getPolarCoordinates();
			Point2D p2 = p.getRawCoordinates();
			System.out.println("Projectile " + element.getName() 
					+ " is " + (int)p.getDistance() + " units away at" +
					" (" + (int)p2.getX() + ", " + (int)p2.getY()+ ").");

		}
	}
	
	
	
	/**
	 * The main method tests the spawnHandler class by creating a new spawnHandler object 
	 * and printing the projectile's to the console.
	 * @param args The arguments
	 */
	public static void main(String[] args) {
		ArrayList<Projectile> proj = new ArrayList<>();
		SpawnHandler sp = new SpawnHandler(proj);
		System.out.println(sp.projectiles);
	}
}