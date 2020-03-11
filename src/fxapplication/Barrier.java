package fxapplication;

import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import pdef.Projectile;

public class Barrier {
	
	public static int radius = 20;
	private static Circle barrCircle = new Circle();
	
	
    public static Circle getCircle() {
		return barrCircle;
	}	
	
	public static double getCircleRadius() {
		return barrCircle.getRadius();
	}
	
	//Draw a new barrier
	public static void addBarrier(int xCoord, int yCoord, BorderPane root) {
       	Barrier.getCircle().setCenterX(xCoord);
    	Barrier.getCircle().setCenterY(yCoord);
    	Barrier.getCircle().setRadius(radius);
    	root.getChildren().add(Barrier.getCircle());
	}
	
	//Removes an old barrier
	public static void removeBarrier(BorderPane root) {
		root.getChildren().remove(Barrier.getCircle());
	}
	
	//Moves barrier removing old one and adding new one
	public static void moveBarrier(int xCoord, int yCoord, BorderPane root) {
		removeBarrier(root);
		addBarrier(xCoord,yCoord,root);
	}
	
	//Checks if the barrier collides with a projectile
	public static boolean barrierCollisionCheck(Projectile proj) {
	
		//Calculations to determine if projectile collides with barrier
		double dist = Math.hypot(proj.getCircle().getCenterX() - getCircle().getCenterX(), proj.getCircle().getCenterY() - getCircle().getCenterY());
		double radSum = (proj.getCircleRadius() + getCircleRadius());
	
		//Remove projectile if they do collide
		if (dist < radSum) {
			return true;
		}
		else {
			return false;
		}
	}
}






