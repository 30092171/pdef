/*
 * 
 */
package pdef;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The Class RotatingProjectile represents a projectile that rotates.
 */
public class RotatingProjectile extends Projectile {
	
	/**
	 * Instantiates a new rotating projectile.
	 *
	 * @param name The rotating projectile's unique name identifier
	 * @param pc The rotating projectile's polar coordinates.
	 * @param c The rotating projectile's JavaFx screen circle object
	 */
	public RotatingProjectile(String name, PolarCoord pc, Circle c) {
		super(name, pc, c);
		this.getCircle().setFill(Color.BLUE);
	}
	
	/**
	 * Causes the projectile to move by 1 turn increment
	 */
	public void turn() {

		PolarCoord pc = this.getPolarCoordinates();
		pc.setDistance(this.getPolarCoordinates().getDistance() - 0.6);
		pc.setRadians(this.getPolarCoordinates().getRadians() - 0.005);
		this.setPolarCoordinates(pc);
	}

}