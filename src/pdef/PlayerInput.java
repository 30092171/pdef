package pdef;

import java.util.ArrayList;

import javafx.geometry.Point2D;

public class PlayerInput {
	
	public ArrayList<Projectile> projectiles;
	
	public PlayerInput(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	//Does task user inputed
	public int command(String input) {
		int scoreToAdd = 0;
		//If input is potentially valid projectile name
		input = input.toUpperCase();
		int initalP = projectiles.size();
		if (input.length() == 1) { //it's a character
			for (int projectile = 0; projectile < projectiles.size(); projectile++) { //search for said projectile
				Projectile t = projectiles.get(projectile);
				if (input.equals(t.getName())) { //If we find it
					System.out.println("----------------------------------");
					System.out.println("Projectile " + t.getName() + " Destroyed!");
					projectiles.remove(t);
					scoreToAdd += 100;
					break;
				}
			}
			//If input doesen't corresponds to projectile in play
			if (initalP == projectiles.size()) { //check if we destroyed anything
				System.out.println("----------------------------------");
				System.out.println("Miss!");
			}
		}
		//if input isn't potentially a projectile name and isn't valid command
		else { //empty or length > 1
			System.out.println("----------------------------------");
			System.out.println("Invalid input");
			return -1; //invalid command code***
		}
		return scoreToAdd;
	}
	
	public static void main(String[] args) {
		ArrayList<Projectile> proj = new ArrayList<>();
		Point2D origin = new Point2D(400,300);
		proj.add(new Projectile("A",new PolarCoord(100,10, origin)));
		PlayerInput pi = new PlayerInput(proj);
		System.out.println(pi.command("A"));
	}

}