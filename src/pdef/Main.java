package pdef;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Main {
	
	private static Scanner sc = new Scanner(System.in);
	private static PlayerPlanet p = new PlayerPlanet();
	private static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public static void main(String[] args) {
		//Initial projectile list
		projectiles.add(SpawnHandler.spawnProjectile());
		projectiles.add(SpawnHandler.spawnProjectile());
		projectiles.add(SpawnHandler.spawnProjectile());
		
		//Main game loop. continues until player runs out of lives
		while(p.getLives() > 0) {
			p.printStatus();
			
			for(int projectile = 0; projectile < projectiles.size(); projectile++) {
				System.out.println("Projectile " + projectiles.get(projectile).name + " is " + projectiles.get(projectile).distance + " units away.");
			}
			
			System.out.print("Enter projectile to shoot:");
			String input = sc.nextLine().toUpperCase();
			System.out.println("----------------------------------");
			
			//Checks hit or miss
			int projectilesBefore = projectiles.size(); //Used to compare if projectile is destroyed later
			for(int projectile = 0; projectile < projectiles.size(); projectile++) {
				if (input.contentEquals(projectiles.get(projectile).name)) {
					System.out.println("Projectile " + projectiles.get(projectile).name + " Destroyed!");
					projectiles.remove(projectile);
					p.addScore(100);
					break;
				}
			}
			//If the number of projectiles hasn't changed, a miss has occured.
			if (projectiles.size() == projectilesBefore) {
				System.out.println("Miss!");
			}
			
			//Decreases projectile distance after each turn and removes projectile/life if distance is 0.
			for (int i = 0 ; i < projectiles.size(); i++) {
				int distance = projectiles.get(i).distance;
				projectiles.get(i).distance = (int)( distance - ( 7 + (Math.random()*10)));
				 
				if (projectiles.get(i).distance <= 0){
					System.out.println("Impact Detected, -1 Life!");
					p.lostLife();
					projectiles.remove(i);
				}
			}
			
			//Projectile spawning after the rest of the turn's game logic has occured. 
			//If there are no projectiles left, then more will always be spawned
			if(projectiles.size() < 1) {
				projectiles.add(SpawnHandler.spawnProjectile());
				projectiles.add(SpawnHandler.spawnProjectile());
			}
			if(projectiles.size() < 4) {
				if (Math.random() > 0.5) {
					projectiles.add(SpawnHandler.spawnProjectile());
					projectiles.add(SpawnHandler.spawnProjectile());
				}
			}
		}
		//Prints game over messages, execute if the main game loop is broken by running out of lives
		p.printGameOver();
	}

}
