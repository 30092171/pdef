package pdef;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpeedUpProjectile extends Projectile {
	
	private double speed = 0.0;
	
	public SpeedUpProjectile(String name, PolarCoord pc, Circle c) {
		super(name, pc, c);
		this.getCircle().setFill(Color.GREEN);
	}
	
	public void turn() {
		speed = speed + 0.005;
		PolarCoord pc = this.getPolarCoordinates();
		pc.setDistance(this.getPolarCoordinates().getDistance() - (speed));
		pc.setRadians(this.getPolarCoordinates().getRadians());
		this.setPolarCoordinates(pc);
	}

}
