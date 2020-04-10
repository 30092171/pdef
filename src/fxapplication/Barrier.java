/*
 * 
 */
package fxapplication;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import pdef.Projectile;

/**
 * This class represents the barrier object
 * used to block projectiles from hitting the planet
 */
public class Barrier {

	/** The JavaFx screen circle object holding the planets center x, center y, and radius. */
	private Circle circle;
	
	/** The barrier image */
	private Image barrier;
	
	/**
	 * Constructs and initializes a new barrier.
	 *
	 * @param root The GUI's root node
	 */
	public Barrier (BorderPane root) {
		this.circle = new Circle(20);
		this.barrier = new Image("https://i.imgur.com/uHEYA5I.png");
		this.circle.setFill(new ImagePattern(barrier));
		
		root.getChildren().add(this.circle);
	}	
	
	/**
	 * Moves the barrier to given x and y 
	 *
	 * @param xCoord The barrier's center x coordinate
	 * @param yCoord The barrier's center y coordinate
	 */
	//Moves barrier removing old one and adding new one
	public void moveBarrier(int xCoord, int yCoord) {
		this.circle.setCenterX(xCoord);
		this.circle.setCenterY(yCoord);
	}
	
	/**
	 * Checks for barrier collision with projectile
	 *
	 * @param p The projectile's reference
	 * @return true, if collision has occurred; false, otherwise.
	 */
	//Checks if the barrier collides with a projectile
	public boolean barrierCollisionCheck(Projectile p) {
		Circle other = p.getCircle(); //have to encapsulate this circle
		//Calculations to determine if projectile collides with barrier
		double dist = Math.hypot(circle.getCenterX() - other.getCenterX(),
				other.getCenterY() - circle.getCenterY());
		double radSum = other.getRadius() + circle.getRadius();
		return dist < radSum;

	}
}






