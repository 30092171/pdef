/*
 * 
 */
package pdef;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the entry point for Planet Defenders text-based version 
 * and contains the game loop and handles all the game logic 
 */
public class Main {
	
	/** The scanner object to take in player input */
	static Scanner sc = new Scanner(System.in);
	
	/** The player's planet model. */
	static PlayerPlanet p = new PlayerPlanet();
	
	/** The arraylist of projectiles. */
	static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	/** The spawn handler */
	static SpawnHandler sp = new SpawnHandler(projectiles); 
	
	/** The player input handler */
	static PlayerInput pi = new PlayerInput(projectiles);
	
	/**
	 * Contains the text-based version's game loop and logic
	 *
	 * @param args The arguments
	 */
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
			
			//Handles reset input
			if (input.toUpperCase().equals("RESET")) {
				p = new PlayerPlanet();
				sp.initialSpawn();
				projectiles.clear();
				sp = new SpawnHandler(projectiles);
				printInstructions();
				continue;
			}
			//Determine the input of user and print line + modify projectiles list accordingly
			//Adds to score if projectile was destroyed
			int result = pi.command(input);
			switch(result) { //switch case for command result codes
				case -1:
					continue;
				default:
					p.addScore(result);
			}
			
			//Updates projectile distance and checks for collision after each turn
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile pr = projectiles.get(i);
				PolarCoord pc = pr.getPolarCoordinates();
				pc.setDistance(pc.getDistance() - 5);
				pr.setPolarCoordinates(pc);
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
	
	/**
	 * Prints the starting instructions. It displays game info and basic instructions
	 */
	public static void printInstructions() {
		System.out.println("----------------------------------");
		System.out.println("Planet Defenders");
		System.out.println("----------------------------------");
		System.out.println("- Increase your score by destroying projectiles, and survive as long as possible");
		System.out.println("- If a projectile gets too close, you will get hit and lose 1 of 3 lives");
		System.out.println("- Type the character of the projectile you want to destroy (Ex. <A>)");
		System.out.println("- Type <Reset> at any time to start over");
	}

}
