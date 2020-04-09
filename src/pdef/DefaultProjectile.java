/*
 * 
 */
package pdef;

import javafx.scene.shape.Circle;

/**
 * This class represents the default-type projectile which
 * moves at a constant speed directly towards the planet.
 */
public class DefaultProjectile extends Projectile {
	
	/**
	 * Constructs and initializes a new default-type projectile.
	 *
	 * @param name The default-type projectile's unique name identifier 
	 * @param pc The default-type projectile's polar coordinates
	 */
	public DefaultProjectile(String name, PolarCoord pc) {
		super(name, pc);
	}

	/**
	 * Constructs and initializes a new default-type projectile
	 * with a JavaFx screen circle object.
	 *
	 * @param name The default projectile's unique name identifier
	 * @param pc The default projectile's polar coordinates
	 * @param projCircle The JavaFx screen circle object representing the default projectile on screen
	 */
	public DefaultProjectile(String name, PolarCoord pc, Circle projCircle) {
		super(name, pc, projCircle);
	}

	
	/**
	 * Causes the default-type projectile to move by 1 turn increment
	 */
	public void turn() {
		PolarCoord pc = this.getPolarCoordinates();
		pc.setDistance(pc.getDistance() - 0.6);
		this.setPolarCoordinates(pc);
	}
}

