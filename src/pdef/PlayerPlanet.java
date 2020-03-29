package pdef;

import fxapplication.GUI;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class PlayerPlanet implements Collidable {
	private Circle circle;
	
	private int numLives;
	private int score;
	
	public PlayerPlanet() {
		Point2D o = GUI.getOrigin();
		this.circle = new Circle(o.getX(), o.getY(), 65);
		this.circle.setOpacity(0);
		numLives = 3;
		score = 0;
	}
	
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
	
	public void lostLife() {
		numLives -= 1;
	}
	
	public void addLife() {
		numLives += 1;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getPlanetRadius() {
		return (int)this.circle.getRadius();
	}
	
	public void addScore(int givenScore) {
		score += givenScore;
	}
	
	public int getLives() {
		return numLives;
	}
	
	public void printPlayerStatus() {
		System.out.println("----------------------------------");
		System.out.println("You have " + getLives() + " lives remaining");
		System.out.println("Current Score: " + getScore());
	}
	
	public void printGameOver() {
		System.out.println("----------------------------------");
		System.out.println("Game Over!");
		System.out.println("Final Score: " + getScore());
	}
	
	public static void main(String[] args) {
		PlayerPlanet player = new PlayerPlanet();
		player.printPlayerStatus();
		
	}

	public Circle getCircle() {
		return this.circle;
	}

}
