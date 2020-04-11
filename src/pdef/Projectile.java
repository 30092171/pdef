/*
 * 
 */
package pdef;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This abstract class contains the abstract methods and instance
 * variables for the different types of in-game projectiles
 */
public abstract class Projectile {
	
	/** The projectile's polar coordinates. */
	private PolarCoord pc;
	
	/** The projectile's unique name identifier */
	private String name; 
	
	/** The JavaFx screen circle object that contains the screen x, screen y, and radius. */
	private Circle projCircle;
	
	/**
	 * Constructs and initializes a new projectile using 
	 * a distance and name from SpawnHandler
	 *
	 * @param name The projectile's unique name identifier
	 * @param pc The projectile's starting polar coordinates
	 */
	public Projectile(String name, PolarCoord pc) {
		this.setName(name);
		this.setPolarCoordinates(pc);
	}
	
	/**
	 * Constructs and initializes a new projectile 
	 * with a JavaFx screen circle object
	 *
	 * @param name The projectile's unique name identifier
	 * @param pc The projectile's starting polar coordinates
	 * @param projCircle The JavaFx screen circle object
	 */
	//
	public Projectile(String name, PolarCoord pc, Circle projCircle) {
		this.projCircle = projCircle;
		this.setName(name);
		this.setPolarCoordinates(pc);
		this.projCircle.setFill(Color.PINK);
	}
	
	/**
	 * The method causes the projectile to move by 1 turn increment
	 */
	public abstract void turn();

	/**
	 * Sets the projectile's unique name identifier.
	 *
	 * @param aName The projectile's unique name identifier.
	 */
	public void setName(String aName) {
		name = aName;
	}
	
	/**
	 * Gets the projectile's unique name identifier
	 *
	 * @return The projectile's unique name identifier
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the projectile's polar coordinates.
	 *
	 * @return The projectile's polar coordinates.
	 */
	public PolarCoord getPolarCoordinates() {
		return pc.clone();
	}

	/**
	 * Sets the projectile's polar coordinates.
	 *
	 * @param pc The projectile's polar coordinates.
	 */
	public void setPolarCoordinates(PolarCoord pc) {
		this.pc = pc;
		Point2D p = pc.getScreenCoordinates();
		if (this.projCircle != null) {
			this.projCircle.setCenterX(p.getX());
			this.projCircle.setCenterY(p.getY());
		}
	}
	
	/**
	 * Gets the JavaFx screen circle object that contains the screen x, screen y, and radius
	 *
	 * @return The JavaFx screen circle object that contains the screen x, screen y, and radius
	 */
	public Circle getCircle() {
		return this.projCircle;
	}
	
	/**
	 * Gets the JavaFx screen circle radius.
	 *
	 * @return The JavaFx screen circle radius
	 */
	public double getCircleRadius() {
		return this.projCircle.getRadius();
	}
	
	
	/**
	 * Sets the JavaFx screen circle radius.
	 *
	 * @param newRadius The new JavaFx screen circle radius
	 */
	public void setCircleRadius(int newRadius) {
		this.projCircle.setRadius(newRadius);
	}
	
	/**
	 * Formatted string of the projectile's polar coordinate
	 *
	 * @return A string of the projectile's polar coordinates.
	 */
	public String toString() {
		return "Projectile: " + this.getPolarCoordinates();
	}
	
	/**
	 * The main method performs a quick test to see if the program works
	 * by creating a projectile object and printing to the screen.
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Point2D origin = new Point2D(400,300);
		Projectile proj = new DefaultProjectile("Proj A", new PolarCoord(100, Math.PI, origin));
		System.out.println(proj.getName());
	}
}
