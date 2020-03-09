package pdef;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpeedUpProjectile extends Projectile {

	public SpeedUpProjectile(String name, PolarCoord pc) {
		super(name, pc);
		// TODO Auto-generated constructor stub
	}
	
	public SpeedUpProjectile(String name, PolarCoord pc, Circle c) {
		super(name, pc, c);
		this.projCircle.setFill(Color.GREEN);
	}
	
	double speed = 0;
	
	public void turn() {
		speed = speed + 0.01;
		this.pc.setDistance(this.pc.getDistance() - (speed));
		this.pc.setRadians(this.pc.getRadians());
		Point2D pos = this.pc.getJCoordinates();
		projCircle.setCenterX(pos.getX());
		projCircle.setCenterY(pos.getY());
	}

}
