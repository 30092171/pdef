package pdef;
import java.lang.Math;
import java.util.ArrayList;

import javafx.scene.shape.Circle;

public class SpawnHandler {
	//Instance variables
	public ArrayList<Projectile> projectiles;
	
	//Constructor, gets reference to projectile list and PlayerPlanet
	public SpawnHandler(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}
	
	public Projectile spawnProjectile() {
		int initialDistance = 800; // Spawns outside of screen 
		int spawnAngle = (int)(Math.random() * 360);
		int xCoord = (int)((initialDistance * Math.cos(spawnAngle)) + 360);
		int yCoord = (int)((initialDistance * Math.sin(spawnAngle)) + 360);
		Circle circle = new Circle();
		circle.setCenterX(xCoord);
		circle.setCenterY(yCoord);
		circle.setRadius(10);
		Projectile newProjectile = new Projectile(initialDistance, spawnAngle, xCoord, yCoord, circle);
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
			System.out.println("Projectile " + element.getName() 
					+ " is " + (element.getDistance() - player.getPlanetRadius()) + " units away at" +
					" (" + element.getxCoordinate() + "," + element.getyCoordinate() + ").");
		}
	}
	
	
	
	public static void main(String[] args) {
		ArrayList<Projectile> proj = new ArrayList<>();
		SpawnHandler sp = new SpawnHandler(proj);
		System.out.println(sp.projectiles);
	}
}

