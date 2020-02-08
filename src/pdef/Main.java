package pdef;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	static PlayerPlanet p = new PlayerPlanet();
	static ArrayList<String[]> projectiles = new ArrayList<String[]>();

	public static void main(String[] args) {
		//Temporary projectile list
		projectiles.add(new String[]{"A","100"});
		projectiles.add(new String[]{"B","75"});
		projectiles.add(new String[]{"C","50"});
		
		
		while(p.getLives() > 0 && projectiles.size() > 0) { //only added end to number of projectiles remaining since they aren't infinite yet
			System.out.println("You have " + p.getLives() + " lives remaining");
			
			for(int projectile = 0; projectile < projectiles.size(); projectile++) {
				System.out.println("Projectile " + projectiles.get(projectile)[0] + " is " + projectiles.get(projectile)[1] + " units away.");
			}
			
			System.out.print("Enter projectile to shoot:");
			String input = sc.nextLine();
			
			//Checks hit or miss
			int projectilesBefore = projectiles.size(); //Used to compare if projectile is destroyed later
			for(int projectile = 0; projectile < projectiles.size(); projectile++) {
				if (input.contentEquals(projectiles.get(projectile)[0])) {
					System.out.println("Projectile " + projectiles.get(projectile)[0] + " is destroyed!");
					projectiles.remove(projectile);
					System.out.println();
					p.addScore(100);
					break;
				}
			}
			//If the number of projectiles hasn't changed, a miss has occured.
			if (projectiles.size() == projectilesBefore) {
				System.out.println("Miss!");
				System.out.println();
			}
			
			//Decreases projcetile distance after each turn and removes projectile/life if distance is 0.
			for (int i = 0 ; i < projectiles.size(); i++) {
				int distance = Integer.parseInt(projectiles.get(i)[1]);
				projectiles.get(i)[1] = "" + (distance - 5); 
				if (projectiles.get(i)[1].equals("0")){
					p.lostLife();
					projectiles.remove(i);
				}
			}
			
		}
		System.out.println("Game Over!");
		System.out.println("Final Score: " + p.getScore());
	}

}
