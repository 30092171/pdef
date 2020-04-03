/*
 * 
 */
package pdef;

import javafx.geometry.Point2D;

/**
 * The Class PolarCoord represents the polar coordinates of each projectile and the planet
 */
public class PolarCoord implements Cloneable {
	
	/** The radians from the positive horizontal axis in a counter-clockwise manner. */
	private double distance, radians;
	
	/** The origin. */
	private Point2D origin;
	
	/**
	 * Instantiates a new polar coord.
	 *
	 * @param distance The distance away from the object's circumference
	 * @param radians The radians of the object's counter-clockwise from the horizontal axis. 
	 * @param origin A point object holding the origin's x and y.
	 */
	public PolarCoord(double distance, double radians, Point2D origin) {
		this.distance = distance;
		this.radians = radians;
		this.origin = origin;
	}
	
	/**
	 * Gets the radians of the object counter-clockwise from the positive horizontal axis 
	 *
	 * @return The radians
	 */
	public double getRadians() {
		return radians;
	}

	/**
	 * Sets the radians.
	 *
	 * @param radians The new radian value
	 */
	public void setRadians(double radians) {
		this.radians = radians;
	}

	/**
	 * Gets the distance away from the planet
	 *
	 * @return The distance away from the planet
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Sets the distance away from the planet.
	 *
	 * @param distance The new distance away from the planet.
	 */
	public void setDistance(double distance) {
		if (distance >= 0) {
			this.distance = distance;
		}
	}
	
	/**
	 * Gets the raw X coordinate.
	 *
	 * @return The raw X coordinate
	 */
	public double getRawX() {
		return Math.cos(this.radians) * this.distance;
	}
	
	/**
	 * Gets the raw Y coordinate
	 *
	 * @return The raw Y coordinate
	 */
	public double getRawY() {
		return Math.sin(this.radians) * this.distance;
	}
	
	/**
	 * Gets the raw coordinates.
	 *
	 * @return the raw coordinates
	 */
	public Point2D getRawCoordinates() {;
		return new Point2D(this.getRawX(),this.getRawY());
	}
	
	/**
	 * Gets the j coordinates.
	 *
	 * @return the j coordinates
	 */
	public Point2D getJCoordinates() {
		Point2D p = this.getRawCoordinates();
		return new Point2D(p.getX() + origin.getX(),p.getY() + origin.getY());
	}
	
	/**
	 * Creates a clone of the polar coord
	 *
	 * @return The clone of the polar coord
	 */
	@Override
	public PolarCoord clone() {
		return new PolarCoord(distance, radians, new Point2D(origin.getX(), origin.getY()));
	}
	
	/**
	 * Checks if 2 polar coordinates are equal to each other
	 *
	 * @param other The other polar coordinate
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object other) {
		if (other.getClass() != this.getClass()) {
			return false;
		}
		PolarCoord p = (PolarCoord)(other);
		return p.distance == this.distance 
				&& p.radians == this.radians;
	}
	
	/**
	 * Returns a string representation of the polar coordinates.
	 * The string representation consists of the Class, the distnace 
	 * away from the planet, and the radians from the positive horizontal axis.
	 *
	 * @return A string representation of the polar coordinates.
	 */
	@Override
	public String toString() {
		return "[" + this.getClass() + "]: " 
	+ "distance: " + this.distance + ", radians: " + this.radians;
	}
}
