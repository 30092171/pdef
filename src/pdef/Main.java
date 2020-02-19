package pdef;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Main {
	

	private static Scanner sc = new Scanner(System.in);
	private static PlayerPlanet p = new PlayerPlanet();
	private static SpawnHandler sp;
	private static PlayerInput pi;
	private static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	public static void printInstructions() {
		System.out.println("----------------------------------");
		System.out.println("Planet Defenders");
		System.out.println("----------------------------------");
		System.out.println("- Increase your score by destroying projectiles, and survive as long as possible");
		System.out.println("- If a projectile gets too close, you will get hit and lose 1 of 3 lives");
		System.out.println("- Type the character of the projectile you want to destroy (Ex. <A>)");
		System.out.println("- Type <Reset> at any time to start over");
	}
	
	//Updates each projectile's distance from planet
	//and the number of projectiles after each turn
	public static void updateProjectiles() {
		for (int i = 0 ; i < projectiles.size(); i++) {
			int distance = projectiles.get(i).getDistance();
			projectiles.get(i).setDistance((int)( distance - ( 7 + (Math.random()*10))));
			 
			if (projectiles.get(i).getDistance() - p.getPlanetRadius() <= 0 ){
				System.out.println("Impact Detected, -1 Life!");
				p.lostLife();
				projectiles.remove(i);
			}
		}
		
	}
	
	public static void main(String[] args) {
		//Create the spawn handler
		sp = new SpawnHandler(projectiles);
		pi = new PlayerInput(projectiles);
		
		//Initial projectile list
		sp.initialSpawn();
		
		//Initial print statement, displays game info and basic instructions
		printInstructions();
		
		//Main game loop. Continues until player runs out of lives
		while(p.getLives() > 0) {
			p.printPlayerStatus();
			pi.printProjectileStatus();
			pi.promptInput();
			//Determine the input of user and print line + modify projectiles list accordingly
			//Also adds to score if projectile was destroyed
			p.addScore(pi.command(sc.nextLine()));

			// add RESET command in command method --> playerInput
			
			//Decreases projectile distance after each turn and removes projectile/life if distance is 0.
			updateProjectiles();
			
			//Spawn more projectiles if needed
			sp.trySpawn();
		}
		//Prints game over messages, execute if the main game loop is broken by running out of lives
		p.printGameOver();
	}

}
