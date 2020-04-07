/*
 * 
 */
package pdef;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class represents the unstable-type projectile.
 * The unstable-type projectile moves directly towards
 * the planet, but speeds up as it gets closer.
 */
public class UnstableProjectile extends Projectile {

	
	/**
	 * Constructs and initializes a new unstable type projectile.
	 *
	 * @param name The speed up type's unique name identifier
	 * @param pc The speed up type's polar coordinates
	 * @param c The speed up type's JavaFx screen circle object
	 */
	public UnstableProjectile(String name, PolarCoord pc, Circle c) {
		super(name, pc, c);
		this.getCircle().setFill(Color.RED);
	}
	
	/**
	 * Causes the projectile to move by 1 turn increment
	 */
	public void turn() {
		
		PolarCoord pc = this.getPolarCoordinates();
		pc.setDistance(this.getPolarCoordinates().getDistance() - (2.222));
		pc.setRadians(this.getPolarCoordinates().getRadians() + 0.003);
		this.setPolarCoordinates(pc);
		
	}

}
