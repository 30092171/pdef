package pdef;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in); //string input
	static PlayerPlanet p = new PlayerPlanet(); //player model
	static ArrayList<Projectile> projectiles = new ArrayList<Projectile>(); //proj list
	static SpawnHandler sp = new SpawnHandler(projectiles); //Create the spawn handler
	static PlayerInput pi = new PlayerInput(projectiles); //Input handler
	
	public static void main(String[] args) {
		//Initial projectile list
		printInstructions();
		sp.initialSpawn();
		//Main game loop. continues until player runs out of lives
		while(p.getLives() > 0) {
			p.printPlayerStatus(); //print status every beginning of loop
			sp.printProjectileStatus(p);
			
			//Input prompt
			System.out.print("Enter projectile to shoot:");
			String input = sc.nextLine();
			if (input.toUpperCase().equals("RESET")) {
				p = new PlayerPlanet();
				sp.initialSpawn();
				projectiles.clear();
				sp = new SpawnHandler(projectiles);
				printInstructions();
				continue;
			}
			//Determine the input of user and print line + modify projectiles list accordingly
			//Also adds to score if projectile was destroyed
			int result = pi.command(input);
			switch(result) { //switch case for command result codes
				case -1:
					continue;
				default:
					p.addScore(result);
			}
			
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile pr = projectiles.get(i);
				PolarCoord pc = pr.getPolarCoordinates();
				pc.setDistance(pc.getDistance() - 5);
				if (pc.getDistance() + p.getPlanetRadius() <= p.getPlanetRadius() + 5) {
					System.out.println("Impact Detected, -1 Life!");
					p.lostLife();
					projectiles.remove(i);
				}
			}
			sp.trySpawn();
			}
			//Prints game over messages, execute if the main game loop is broken by running out of lives
			p.printGameOver(); // --> in playerPlanet
	}
	public static void printInstructions() {
		//Initial print statement, displays game info and basic instructions
		System.out.println("----------------------------------");
		System.out.println("Planet Defenders");
		System.out.println("----------------------------------");
		System.out.println("- Increase your score by destroying projectiles, and survive as long as possible");
		System.out.println("- If a projectile gets too close, you will get hit and lose 1 of 3 lives");
		System.out.println("- Type the character of the projectile you want to destroy (Ex. <A>)");
		System.out.println("- Type <Reset> at any time to start over");
	}

}
