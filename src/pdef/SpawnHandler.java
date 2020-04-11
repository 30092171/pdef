/*
 * 
 */
package pdef;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

/**
 * This class handles projectile spawning.
 */
public class SpawnHandler {
	
	/** The projectile's unique name identifier */
	private static String names = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/** A counter for the number of projectile's currently on screen */
	private int counter = 0;
	
	/** The arraylist of projectiles. */
	public ArrayList<Projectile> projectiles;
	
	/**
	 * Constructs and initializes a new spawn handler
	 *
	 * @param projectiles The reference to the arraylist of projectiles.
	 */
	public SpawnHandler(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}
	
	/**
	 * Spawns a projectile a fixed initial distance
	 * away from the planet with a random starting angle
	 *
	 * @return The spawned projectile
	 */
	public Projectile spawnProjectile() {
		double initialDistance = 400; // Spawns outside of screen 
		double spawnAngle = (Math.random() * Math.PI * 2);
		PolarCoord p = new PolarCoord(initialDistance, spawnAngle, new Point2D(360,360));
		Circle circle = new Circle(p.getXCoord(), p.getYCoord(), 10);
		
		//Randomly generates different projectile types.
		Projectile newProjectile;
		double projType = Math.random();
		if (projType >= 0.8) {
			newProjectile = new RotatingProjectile("Projectile " + (projectiles.size() + 1), p, circle);
		} else if (projType >= 0.6 && projType < 0.8){
			newProjectile = new SpeedUpProjectile("Projectile " + (projectiles.size() + 1), p, circle);
		}else if (projType >= 0.55 && projType < 0.6){
			newProjectile = new UnstableProjectile("Projectile " + (projectiles.size() + 1), p, circle);
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
		double initialDistance = Math.random() * 70 + 10; 
		double spawnAngle = (Math.random() * Math.PI * 2);
		PolarCoord p = new PolarCoord(initialDistance, spawnAngle, new Point2D(0,0));
		return new DefaultProjectile(names.charAt(counter++) + "", p);
		
	}
	
	/**
	 * Spawns projectiles based on number of projectiles on screen.
	 * Ensures projectiles always exists by spawning 2 projectiles
	 * if none are on the screen
	 */
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
	 * Initially spawns 3 projectiles for the text-based version
	 */
	public void initialSpawn() {
		projectiles.add(oldProjectile());
		projectiles.add(oldProjectile());
		projectiles.add(oldProjectile());
	}
	
	/**
	 * Prints the projectile's unique name identifier, distance away from
	 * the planet, and x coordinate and y coordinate.
	 *
	 * @param player The player object
	 */
	public void printProjectileStatus(PlayerPlanet player) {
		for(Projectile element : projectiles) {
			PolarCoord p = element.getPolarCoordinates();
			Point2D p2 = p.getCartesianCoordinates();
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