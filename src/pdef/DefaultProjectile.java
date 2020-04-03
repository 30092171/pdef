/*
 * 
 */
package pdef;

import javafx.scene.shape.Circle;

/**
 * The Class DefaultProjectile represents the default projectile 
 */
public class DefaultProjectile extends Projectile {
	
	/**
	 * Instantiates a new default projectile.
	 *
	 * @param name The default projectile's unique name identifier
	 * @param pc The deafult projectile's polar coordinates
	 */
	public DefaultProjectile(String name, PolarCoord pc) {
		super(name, pc);
	}

	/**
	 * Instantiates a new default projectile.
	 *
	 * @param name The default projectile's unique name identifier
	 * @param pc The default projectile's polar coordinates
	 * @param projCircle The JavaFx screen circle object representing the default projectile on screen
	 */
	public DefaultProjectile(String name, PolarCoord pc, Circle projCircle) {
		super(name, pc, projCircle);
	}

	
	/**
	 * Causes the projectile to move by 1 turn increment
	 */
	public void turn() {
		PolarCoord pc = this.getPolarCoordinates();
		pc.setDistance(pc.getDistance() - 0.6);
		this.setPolarCoordinates(pc);
	}
}

