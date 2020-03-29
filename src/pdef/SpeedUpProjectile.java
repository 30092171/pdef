package pdef;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpeedUpProjectile extends Projectile {
	
	private double speed = 0;
	
	public SpeedUpProjectile(String name, PolarCoord pc, Circle c) {
		super(name, pc, c);
		this.getCircle().setFill(Color.GREEN);
	}
	
	public void turn() {
		speed = speed + 0.01;
		this.getPolarCoordinates().setDistance(this.getPolarCoordinates().getDistance() - (speed));
		this.getPolarCoordinates().setRadians(this.getPolarCoordinates().getRadians());
		Point2D pos = this.getPolarCoordinates().getJCoordinates();
		getCircle().setCenterX(pos.getX());
		getCircle().setCenterY(pos.getY());
	}

}
