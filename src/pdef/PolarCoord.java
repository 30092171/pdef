package pdef;

import javafx.geometry.Point2D;

public class PolarCoord implements Cloneable {
	private double distance, radians;
	private Point2D origin;
	
	public PolarCoord(double distance, double radians, Point2D origin) {
		this.distance = distance;
		this.radians = radians;
		this.origin = origin;
	}
	
	public double getRadians() {
		return radians;
	}

	public void setRadians(double radians) {
		if (radians >= 0 && radians <= 2 * Math.PI) {
			this.radians = radians;
		}
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		if (distance >= 0) {
			this.distance = distance;
		}
	}
	
	public double getRawX() {
		return Math.cos(this.radians) * this.distance;
	}
	
	public double getRawY() {
		return Math.sin(this.radians) * this.distance;
	}
	
	public Point2D getRawCoordinates() {;
		return new Point2D(this.getRawX(),this.getRawY());
	}
	
	public Point2D getJCoordinates() {
		Point2D p = this.getRawCoordinates();
		return new Point2D(p.getX() + origin.getX(),p.getY() + origin.getY());
	}
	
	@Override
	public PolarCoord clone() {
		return new PolarCoord(distance, radians, new Point2D(origin.getX(), origin.getY()));
	}
	@Override
	public boolean equals(Object other) {
		if (other.getClass() != this.getClass()) {
			return false;
		}
		PolarCoord p = (PolarCoord)(other);
		return p.distance == this.distance 
				&& p.radians == this.radians;
	}
	@Override
	public String toString() {
		return "[" + this.getClass() + "]: " 
	+ "distance: " + this.distance + ", radians: " + this.radians;
	}
}
