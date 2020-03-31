package pdef;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RotatingProjectile extends Projectile {
	
	public RotatingProjectile(String name, PolarCoord pc, Circle c) {
		super(name, pc, c);
		this.getCircle().setFill(Color.BLUE);
	}
	
	public void turn() {

		PolarCoord pc = this.getPolarCoordinates();
		pc.setDistance(this.getPolarCoordinates().getDistance() - 0.6);
		pc.setRadians(this.getPolarCoordinates().getRadians() - 0.005);
		this.setPolarCoordinates(pc);
	}

}