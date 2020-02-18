package pdef;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Main {
	

	private static Scanner sc = new Scanner(System.in);
	private static PlayerPlanet p = new PlayerPlanet();
	private static SpawnHandler sp;
	private static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	public static void main(String[] args) {
		//Create the spawn handler
		sp = new SpawnHandler(projectiles);
		
		//Initial projectile list
		sp.initialSpawn();
		
		//Initial print statement, displays game info and basic instructions
		System.out.println("----------------------------------");
		System.out.println("Planet Defenders");
		System.out.println("----------------------------------");
		System.out.println("- Increase your score by destroying projectiles, and survive as long as possible");
		System.out.println("- If a projectile gets too close, you will get hit and lose 1 of 3 lives");
		System.out.println("- Type the character of the projectile you want to destroy (Ex. <A>)");
		System.out.println("- Type <Reset> at any time to start over");
		
		//Main game loop. continues until player runs out of lives
		while(p.getLives() > 0) {
			p.printStatus();
			
			// public void printProjectileStatus() --> playerPlanet
			for(int projectile = 0; projectile < projectiles.size(); projectile++) {
				System.out.println("Projectile " + projectiles.get(projectile).getName() + " is " + (projectiles.get(projectile).getDistance() - p.getPlanetRadius()) + " units away.");
			}
			
			// public void promptInput() --> playerInput class
			System.out.print("Enter projectile to shoot:");
			String input = sc.nextLine().toUpperCase();
			System.out.println("----------------------------------");
			// add public void playAgain() method --> playerInput
			
			//Checks hit or miss.
			// public int checkHit() --> playerInput()
			int projectilesBefore = projectiles.size(); //Used to compare if projectile is destroyed later
			for(int projectile = 0; projectile < projectiles.size(); projectile++) {
				if (input.contentEquals(projectiles.get(projectile).getName())) {
					System.out.println("Projectile " + projectiles.get(projectile).getName() + " Destroyed!");
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
			// public void updateProjectile() --> stay in main
			for (int i = 0 ; i < projectiles.size(); i++) {
				int distance = projectiles.get(i).getDistance();
				projectiles.get(i).setDistance((int)( distance - ( 7 + (Math.random()*10))));
				 
				if (projectiles.get(i).getDistance() - p.getPlanetRadius() <= 0 ){
					System.out.println("Impact Detected, -1 Life!");
					p.lostLife();
					projectiles.remove(i);
				}
			}
			
		//Spawn more projectiles if needed
		sp.trySpawn();
		}
		//Prints game over messages, execute if the main game loop is broken by running out of lives
		p.printGameOver(); // --> in playerPlanet
	}

}
