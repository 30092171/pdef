/*
 * 
 */
package fxapplication;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pdef.Projectile;

/**
 * This class represents the barrier object
 * used to block projectiles from hitting the planet
 */
public class Barrier {

	/** The JavaFx screen circle object holding the barrier's screen x, screen y, and radius. */
	private Circle circle;
	
	/**
	 * Constructs and initializes a new barrier.
	 *
	 * @param root The GUI's root node
	 */
	public Barrier (BorderPane root) {
		this.circle = new Circle(20);
		this.circle.setFill(Color.WHITE);
		
		root.getChildren().add(this.circle);
	}	
	
	/**
	 * Moves the barrier to the given x and y 
	 *
	 * @param xCoord The barrier's center x coordinate
	 * @param yCoord The barrier's center y coordinate
	 */
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
	public boolean barrierCollisionCheck(Projectile p) {
		Circle other = p.getCircle(); //have to encapsulate this circle
		//Calculations to determine if projectile collides with barrier
		double dist = Math.hypot(circle.getCenterX() - other.getCenterX(),
				other.getCenterY() - circle.getCenterY());
		double radSum = other.getRadius() + circle.getRadius();
		return dist < radSum;

	}
}






