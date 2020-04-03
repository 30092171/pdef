/*
 * 
 */
package fxapplication;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pdef.Projectile;

/**
 * The Class Barrier represents the barrier object the player uses to block the projectiles
 */
public class Barrier {

	/** The circle. */
	private Circle circle;
	
	/**
	 * Instantiates a new barrier.
	 *
	 * @param root The GUI's root node
	 */
	public Barrier (BorderPane root) {
		this.circle = new Circle(20);
		this.circle.setFill(Color.WHITE);
		root.getChildren().add(this.circle);
	}	
	
	/**
	 * Moves the barrier 
	 *
	 * @param xCoord The barrier's x coord
	 * @param yCoord The barrier's y coord
	 */
	//Moves barrier removing old one and adding new one
	public void moveBarrier(int xCoord, int yCoord) {
		this.circle.setCenterX(xCoord);
		this.circle.setCenterY(yCoord);
	}
	
	/**
	 * Checks barrier collision with projectile
	 *
	 * @param p The projectile's reference
	 * @return true, if collision has occurred. false, if no collision has occurred.
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






