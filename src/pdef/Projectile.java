package pdef;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Projectile {
	
	private PolarCoord pc;
	private String name; 
	private Circle projCircle;
	
	// This constructor gets a distance and a name from SpawnHandler
	public Projectile(String name, PolarCoord pc) {
		this.setName(name);
		this.setPolarCoordinates(pc);
	}

	
	public Projectile(String name, PolarCoord pc, Circle projCircle) {
		this.projCircle = projCircle;
		this.setName(name);
		this.setPolarCoordinates(pc);
		this.projCircle.setFill(Color.PINK);
	}
	
	public abstract void turn();

	public void setName(String aName) {
		name = aName;
	}
	
	public String getName() {
		return name;
	}
	
	public PolarCoord getPolarCoordinates() {
		return pc.clone();
	}

	public void setPolarCoordinates(PolarCoord pc) {
		this.pc = pc;
		Point2D p = pc.getJCoordinates();
		if (this.projCircle != null) {
			this.projCircle.setCenterX(p.getX());
			this.projCircle.setCenterY(p.getY());
		}
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
	
	//pretty sure this does nothing
	//remove before demo/no junit test
	public String toString() {
		return "Projectile: " + this.getPolarCoordinates();
	}
	
	public static void main(String[] args) {
		Point2D origin = new Point2D(400,300);
		Projectile proj = new DefaultProjectile("Proj A", new PolarCoord(100, Math.PI, origin));
		System.out.println(proj.getName());
	}
}
