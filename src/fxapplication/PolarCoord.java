package fxapplication;

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
	
	public PolarCoord clone() {
		return new PolarCoord(distance, radians);
	}
}
