/*
 * 
 */
package pdef;

import javafx.geometry.Point2D;

/**
 * This class represents the polar coordinates of the projectiles
 */
public class PolarCoord implements Cloneable {
	
	/** The distance away from the planet. */
	private double distance;
	
	/** The radians from the positive horizontal axis in a counter-clockwise manner. */
	private double radians;
	
	/** A Point2D object representing the screen's origin. */
	private Point2D origin;
	
	/**
	 * Constructs and initializes the object holding the polar coordinates.
	 *
	 * @param distance The distance away from the planet's circumference
	 * @param radians The radians counter-clockwise from the positive horizontal axis. 
	 * @param origin A point object holding the center x and center y.
	 */
	public PolarCoord(double distance, double radians, Point2D origin) {
		this.distance = distance;
		this.radians = radians;
		this.origin = origin;
	}
	
	/**
	 * Gets the radians counter-clockwise from the positive horizontal axis. 
	 *
	 * @return The radians counter-clockwise from the positive horizontal axis. 
	 */
	public double getRadians() {
		return radians;
	}

	/**
	 * Sets the radians counter-clockwise from the positive horizontal axis.
	 *
	 * @param radians The radians counter-clockwise from the positive horizontal axis. 
	 */
	public void setRadians(double radians) {
		this.radians = radians;
	}

	/**
	 * Gets the distance between the object and the planet
	 *
	 * @return The distance between the object and the planet
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Sets the distance between the object and the planet
	 *
	 * @param distance The distance between the object and the planet
	 */
	public void setDistance(double distance) {
		if (distance >= 0) {
			this.distance = distance;
		}
	}
	
	/**
	 * Gets the x coordinate.
	 *
	 * @return The x coordinate
	 */
	public double getXCoord() {
		return Math.cos(this.radians) * this.distance;
	}
	
	/**
	 * Gets the y coordinate
	 *
	 * @return The y coordinate
	 */
	public double getYCoord() {
		return Math.sin(this.radians) * this.distance;
	}
	
	/**
	 * Gets the cartesian coordinates
	 *
	 * @return The cartesian coordinates
	 */
	public Point2D getCartesianCoordinates() {;
		return new Point2D(this.getXCoord(),this.getYCoord());
	}
	
	/**
	 * Gets the screen coordinates
	 *
	 * @return The screen coordinates
	 */
	public Point2D getScreenCoordinates() {
		Point2D p = this.getCartesianCoordinates();
		return new Point2D(p.getX() + origin.getX(),p.getY() + origin.getY()); // Adds base x and y to screen origin 
	}
	
	/**
	 * Creates a clone of the polar coordinates
	 *
	 * @return The clone of the polar coordinates
	 */
	@Override
	public PolarCoord clone() {
		return new PolarCoord(distance, radians, new Point2D(origin.getX(), origin.getY()));
	}
	
	/**
	 * Checks if 2 polar coordinates are equal to each other
	 *
	 * @param other The other polar coordinate
	 * @return true, if the two polar coordinates are equal; false, otherwise.
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
	 * The string representation consists of the Class, the distance 
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
