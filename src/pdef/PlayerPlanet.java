/*
 * 
 */
package pdef;

import fxapplication.GUI;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

/**
 * This class represents the player's home planet
 */
public class PlayerPlanet implements Collidable {
	
	/** The JavaFx screen circle object holding the planets center x, center y, and radius. */
	private Circle circle;
	
	/** The number of lives. */
	private int numLives;
	
	/** The score. */
	private int score;
	
	/**
	 * Instantiates a new player planet.
	 */
	public PlayerPlanet() {
		Point2D o = GUI.getOrigin();
		this.circle = new Circle(o.getX(), o.getY(), 65);
		this.circle.setOpacity(0);
		numLives = 3;
		score = 0;
	}
	
	/**
	 * Checks for collision with the planet
	 *
	 * @param p The reference to a projectile object
	 * @return true, if collision has occurred. false, if collision hasn't occurred
	 */
	public boolean checkCollision(Projectile p) {
		Circle other = p.getCircle();
		
		//Calculations to determine if projectile collides with planet
		double dist = Math.hypot(other.getCenterX() - circle.getCenterX(),
				other.getCenterY() - circle.getCenterY());
		double radSum = other.getRadius() + circle.getRadius();
		
		//Checking if the projectile clips with the planet
		if (dist < radSum) {
			
			//Returns boolean so we can check if projectile hits (for removing lives)
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Decrease lives by 1.
	 */
	public void lostLife() {
		numLives -= 1;
	}
	
	/**
	 * Increases lives by 1.
	 */
	public void addLife() {
		numLives += 1;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return The score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Gets the planet radius
	 *
	 * @return The planet radius
	 */
	public int getPlanetRadius() {
		return (int)this.circle.getRadius();
	}
	
	/**
	 * Adds to the current score.
	 *
	 * @param givenScore The given score to add
	 */
	public void addScore(int givenScore) {
		score += givenScore;
	}
	
	/**
	 * Gets the lives.
	 *
	 * @return The number of lives
	 */
	public int getLives() {
		return numLives;
	}
	
	/**
	 * Prints the player status for the text-based version
	 */
	public void printPlayerStatus() {
		System.out.println("----------------------------------");
		System.out.println("You have " + getLives() + " lives remaining");
		System.out.println("Current Score: " + getScore());
	}
	
	/**
	 * Prints the game over status for the text-based version
	 */
	public void printGameOver() {
		System.out.println("----------------------------------");
		System.out.println("Game Over!");
		System.out.println("Final Score: " + getScore());
	}
	
	/**
	 * The main method tests the player planet class by creating a new playerPlanet object 
	 * and printing the player status to the screen.
	 * @param args The arguments
	 */
	public static void main(String[] args) {
		PlayerPlanet player = new PlayerPlanet();
		player.printPlayerStatus();
		
	}

	/**
	 * Gets the JavaFx screen circle object.
	 *
	 * @return The JavaFx screen circle object that contains the center x, center y, and radius
	 */
	public Circle getCircle() {
		return this.circle;
	}

}
