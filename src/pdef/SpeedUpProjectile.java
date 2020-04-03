/*
 * 
 */
package pdef;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The Class SpeedUpProjectile represents a projectile that speeds up as time passes
 */
public class SpeedUpProjectile extends Projectile {
	

	/** The speed. */
	private double speed = 0.0;

	
	/**
	 * Instantiates a new speed up projectile.
	 *
	 * @param name the name
	 * @param pc the pc
	 * @param c the c
	 */
	public SpeedUpProjectile(String name, PolarCoord pc, Circle c) {
		super(name, pc, c);
		this.getCircle().setFill(Color.GREEN);
	}
	
	/**
	 * Causes the projectile to move by 1 increment
	 */
	public void turn() {

		speed = speed + 0.005;
		PolarCoord pc = this.getPolarCoordinates();
		pc.setDistance(this.getPolarCoordinates().getDistance() - (speed));
		pc.setRadians(this.getPolarCoordinates().getRadians());
		this.setPolarCoordinates(pc);

	}

}
