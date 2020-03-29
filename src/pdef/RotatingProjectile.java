package pdef;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RotatingProjectile extends Projectile {
	
	public RotatingProjectile(String name, PolarCoord pc, Circle c) {
		super(name, pc, c);
		this.getCircle().setFill(Color.BLUE);
	}
	
	public void turn() {
		this.getPolarCoordinates().setDistance(this.getPolarCoordinates().getDistance() - 0.8);
		this.getPolarCoordinates().setRadians(this.getPolarCoordinates().getRadians() - 0.005);
		Point2D pos = this.getPolarCoordinates().getJCoordinates();
		getCircle().setCenterX(pos.getX());
		getCircle().setCenterY(pos.getY());
	}

}