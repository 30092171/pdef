package pdef;

import javafx.scene.shape.Circle;

public class DefaultProjectile extends Projectile {
	
	public DefaultProjectile(String name, PolarCoord pc) {
		super(name, pc);
	}

	public DefaultProjectile(String name, PolarCoord pc, Circle projCircle) {
		super(name, pc, projCircle);
	}
}
