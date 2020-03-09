package pdef;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RotatingProjectile extends Projectile {

	public RotatingProjectile(String name, PolarCoord pc) {
		super(name, pc);
		// TODO Auto-generated constructor stub
	}
	
	public RotatingProjectile(String name, PolarCoord pc, Circle c) {
		super(name, pc, c);
		this.projCircle.setFill(Color.BLUE);
	}
	
	public void turn() {
		this.pc.setDistance(this.pc.getDistance() - 1);
		this.pc.setRadians(this.pc.getRadians() - 0.005);
		Point2D pos = this.pc.getJCoordinates();
		projCircle.setCenterX(pos.getX());
		projCircle.setCenterY(pos.getY());
	}

}
