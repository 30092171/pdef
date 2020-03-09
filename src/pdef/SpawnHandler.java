package pdef;
import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class SpawnHandler {
	//Instance variables
	public ArrayList<Projectile> projectiles;
	
	//Constructor, gets reference to projectile list and PlayerPlanet
	public SpawnHandler(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}
	
	public Projectile spawnProjectile() {
		double initialDistance = 400; // Spawns outside of screen 
		double spawnAngle = (Math.random() * Math.PI * 2);
		PolarCoord p = new PolarCoord(initialDistance, spawnAngle, new Point2D(360,360));
		Circle circle = new Circle(p.getRawX(), p.getRawY(), 10);
		
		Projectile newProjectile;
		double projType = Math.random();
		if (projType >= 0.8) {
			newProjectile = new RotatingProjectile("Projectile " + (projectiles.size() + 1), p, circle);
		} else if (projType >= 0.6 && projType < 0.8){
			newProjectile = new SpeedUpProjectile("Projectile " + (projectiles.size() + 1), p, circle);
		} else {
			newProjectile = new Projectile("Projectile " + (projectiles.size() + 1), p, circle);
		}
		return  newProjectile;
		
	}
	
	//Projectile spawning after the rest of the turn's game logic has occured. 
	//If there are no projectiles left, then more will always be spawned
	
	public void updateProjectiles() {
		// Decreases projectile distance after each turn and removes projectile/life if
		// distance is 0.
		// public void updateProjectile() --> stay in main
		//for (int i = 0; i < projectiles.size(); i++) {
			//projectiles.get(i).setXCoord(projectiles.get(i).getSpawnAngle(), 5); // Moving 5 units away
			//projectiles.get(i).setYCoord(projectiles.get(i).getSpawnAngle(), 5); // Moving 5 units away
			//projectiles.get(i).setDistance(projectiles.get(i).getxCoordinate(),projectiles.get(i).getyCoordinate(), player);
			

			//if (projectiles.get(i).getDistance() - player.getPlanetRadius() <= 0) {
				//System.out.println("Impact Detected, -1 Life!");
				//player.lostLife();
				//projectiles.remove(i);
			//}
		//}
	}
	
	public void printProjectileStatus(PlayerPlanet player) {
		for(Projectile element : projectiles) {
			PolarCoord p = element.getPolarCoordinates();
			System.out.println("Projectile " + element.getName() 
					+ " is " + p + " units away at" +
					" (" + p.getRawCoordinates() + ").");
		}
	}
	
	
	
	public static void main(String[] args) {
		ArrayList<Projectile> proj = new ArrayList<>();
		SpawnHandler sp = new SpawnHandler(proj);
		System.out.println(sp.projectiles);
	}
}

