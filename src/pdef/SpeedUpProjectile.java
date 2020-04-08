/*
 * 
 */
package pdef;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class represents the speedup-type projectile
 * which moves directly towards the planet, but speeds
 * up as it gets closer.
 */
public class SpeedUpProjectile extends Projectile {
	

	/** The projectile's speed. */
	private double speed = 0.0;

	
	/**
	 * Constructs and initializes a new speedup type projectile.
	 *
	 * @param name The speed up type's unique name identifier
	 * @param pc The speed up type's polar coordinates
	 * @param c The speed up type's JavaFx screen circle object
	 */
	public SpeedUpProjectile(String name, PolarCoord pc, Circle c) {
		super(name, pc, c);
		this.getCircle().setFill(Color.GREEN);
	}
	
	/**
	 * Causes the projectile to move by 1 turn increment
	 */
	public void turn() {

		speed = speed + 0.005;
		PolarCoord pc = this.getPolarCoordinates();
		pc.setDistance(this.getPolarCoordinates().getDistance() - (speed));
		pc.setRadians(this.getPolarCoordinates().getRadians());
		this.setPolarCoordinates(pc);

	}

}
