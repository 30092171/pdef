package pdef;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Projectile {
	
	private PolarCoord pc;
	private String name; 
	private Circle projCircle;
	
	// This constructor gets a distance and a name from SpawnHandler
	public Projectile(String name, PolarCoord pc) {
		this.setName(name);
		this.setPolarCoordinates(pc);
	}
	
	//
	public Projectile(String name, PolarCoord pc, Circle projCircle) {
		this(name, pc);
		this.projCircle = projCircle;
	}

	public void setName(String aName) {
		name = aName;
	}
	
	public String getName() {
		return name;
	}
	
	public PolarCoord getPolarCoordinates() {
		return pc;
	}

	public void setPolarCoordinates(PolarCoord pc) {
		this.pc = pc;
	}
	
	public Circle getCircle() {
		return this.projCircle;
	}
	
	public double getCircleRadius() {
		return this.projCircle.getRadius();
	}
	
	
	public void setCircleRadius(int newRadius) {
		this.projCircle.setRadius(newRadius);
	}
	
	public void setCircleColorPink() {
		this.projCircle.setFill(Color.PINK);
	}
	
	public String toString() {
		return "Projectile: " + this.getPolarCoordinates();
	}
	
	public static void main(String[] args) {
		Point2D origin = new Point2D(400,300);
		Projectile proj = new Projectile("Proj A", new PolarCoord(100, Math.PI, origin));
		System.out.println(proj.getName());
	}
}
