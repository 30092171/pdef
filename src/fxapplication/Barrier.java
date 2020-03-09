package fxapplication;

import javafx.scene.shape.Circle;

public class Barrier {

	int radius;
	Circle barrCircle = new Circle();
	
	public Circle getCircle() {
		return this.barrCircle;
	}	
	
	public double getCircleRadius() {
		return this.barrCircle.getRadius();
	}
}
