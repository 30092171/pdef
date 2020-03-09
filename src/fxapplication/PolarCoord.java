package fxapplication;

import javafx.geometry.Point2D;

public class PolarCoord implements Cloneable {
	private double distance, radians;
	
	public PolarCoord(double distance, double radians) {
		this.distance = distance;
		this.radians = radians;
	}
	
	public double getRadians() {
		return radians;
	}

	public void setRadians(double radians) {
		this.radians = radians;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public Point2D getRawCoordinates() {
		double x = Math.cos(this.radians) * this.distance;
		double y = Math.sin(this.radians) * this.distance;
		return new Point2D(x,y);
	}
	
	public Point2D getJCoordinates(Point2D origin) {
		Point2D p = this.getRawCoordinates();
		return new Point2D(p.getX() + origin.getX(),p.getY() + origin.getY());
	}
	
	public PolarCoord clone() {
		return new PolarCoord(distance, radians);
	}
}
