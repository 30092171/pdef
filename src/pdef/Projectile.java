/*
 * 
 */
package pdef;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The Abstract Class Projectile contains the abstract methods and instance variables
 * for the different types of projectiles
 */
public abstract class Projectile {
	
	/** The projectile's polar coordinates. */
	private PolarCoord pc;
	
	/** The projectile's unique name for use in the text-based version */
	private String name; 
	
	/** The projectile's JavaFX screen circle object. */
	private Circle projCircle;
	
	/**
	 * Instantiates a new projectile.
	 *
	 * @param name The projectile's unique name identifier
	 * @param pc The projectile's starting polar coordinates
	 */
	// This constructor gets a distance and a name from SpawnHandler
	public Projectile(String name, PolarCoord pc) {
		this.setName(name);
		this.setPolarCoordinates(pc);
	}
	
	/**
	 * Instantiates a new projectile.
	 *
	 * @param name The projectile's unique name identifier
	 * @param pc The projectile's starting polar coordinates
	 * @param projCircle the proj circle
	 */
	//
	public Projectile(String name, PolarCoord pc, Circle projCircle) {
		this.projCircle = projCircle;
		this.setName(name);
		this.setPolarCoordinates(pc);
		this.projCircle.setFill(Color.PINK);
	}
	
	/**
	 * Abtract method for turn
	 * The method is supposed to cause the projectile to move by 1 increment
	 */
	public abstract void turn();

	/**
	 * Sets the projectile's new unique name identifier.
	 *
	 * @param aName The projectile's new unique name identifier.
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
	 * @param pc The projectile's new polar coordinates.
	 */
	public void setPolarCoordinates(PolarCoord pc) {
		this.pc = pc;
		Point2D p = pc.getJCoordinates();
		if (this.projCircle != null) {
			this.projCircle.setCenterX(p.getX());
			this.projCircle.setCenterY(p.getY());
		}
	}
	
	/**
	 * Gets the JavaFx screen circle object.
	 *
	 * @return The JavaFX screen circle object.
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
