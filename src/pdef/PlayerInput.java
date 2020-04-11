/*
 * 
 */
package pdef;

import java.util.ArrayList;

import javafx.geometry.Point2D;

/**
 * This class handles all player input for the text-based version
 */
public class PlayerInput {
	
	/** The arraylist of projectiles */
	public ArrayList<Projectile> projectiles;
	
	/**
	 * Constructs and initializes a new player input handler.
	 *
	 * @param projectiles The arraylist of projectiles
	 */
	public PlayerInput(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	/**
	 * Deals with user input
	 *
	 * @param input The player's typed input
	 * @return -1, invalid input; scoreToAdd The score to add
	 * 
	 */
	public int command(String input) {
		int scoreToAdd = 0;
		//If input is potentially valid projectile name
		input = input.toUpperCase();
		int initalP = projectiles.size();
		if (input.length() == 1) { //Checks to see if it's a character
			for (int projectile = 0; projectile < projectiles.size(); projectile++) { //search for said projectile
				Projectile t = projectiles.get(projectile);
				if (input.equals(t.getName())) { //If we find it
					System.out.println("----------------------------------");
					System.out.println("Projectile " + t.getName() + " Destroyed!");
					projectiles.remove(t);
					scoreToAdd += 100;
					break;
				}
			}
			//If input doesen't corresponds to projectile in play
			if (initalP == projectiles.size()) { //check if we destroyed anything
				System.out.println("----------------------------------");
				System.out.println("Miss!");
			}
		}
		//If input isn't potentially a projectile name and isn't valid command
		else { //empty or length > 1
			System.out.println("----------------------------------");
			System.out.println("Invalid input");
			return -1; //invalid command code***
		}
		return scoreToAdd;
	}
	
	/**
	 * The main method is used to quickly test the PlayerInput class by
	 * creating a list of projectiles and tests the command method.
	 *
	 * @param args The arguments
	 */
	public static void main(String[] args) {
		ArrayList<Projectile> proj = new ArrayList<>();
		Point2D origin = new Point2D(400,300);
		proj.add(new DefaultProjectile("A",new PolarCoord(100,10, origin)));
		PlayerInput pi = new PlayerInput(proj);
		System.out.println(pi.command("A"));
	}

}
