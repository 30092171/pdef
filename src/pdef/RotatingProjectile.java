/*
 * 
 */
package pdef;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class represents a rotating-type projectile
 * which rotates towards the planet at a constant speed.
 */
public class RotatingProjectile extends Projectile {
	
	/**
	 * Constructs and initializes a new rotating-type projectile.
	 *
	 * @param name The rotating-type projectile's unique name identifier
	 * @param pc The rotating-type projectile's polar coordinates.
	 * @param c The rotating-type projectile's JavaFx screen circle object
	 */
	public RotatingProjectile(String name, PolarCoord pc, Circle c) {
		super(name, pc, c);
		this.getCircle().setFill(Color.BLUE);
	}
	
	/**
	 * Causes the rotating-type projectile to move by 1 turn increment
	 */
	public void turn() {

		PolarCoord pc = this.getPolarCoordinates();
		pc.setDistance(this.getPolarCoordinates().getDistance() - 0.6);
		pc.setRadians(this.getPolarCoordinates().getRadians() - 0.005);
		this.setPolarCoordinates(pc);
	}

}