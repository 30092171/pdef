package pdef;

import java.util.ArrayList;

public class PlayerInput {
	
	public ArrayList<Projectile> projectiles;
	
	public PlayerInput(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}
	
	//this part maybe be done by player planet
	/*public String printProjectileStatus() {
		String status = "";
		for (Projectile t : projectiles) {
			status += "Projectile" + t.getName() + " is " + t.getDistance() + " away.\n";
		}
		return status;
	}*/
	
	//Input prompt message
	public void promptInput() {
	System.out.print("Enter projectile to shoot:");
	}
	
	//Does task user imputed
	public int command(String input) {
		int scoreToAdd = 0;
		//If input is potentially valid projectile name
		input = input.toUpperCase();
		int initalP = projectiles.size();
		if (input.length() == 1) {
			for (int projectile = 0; projectile < projectiles.size(); projectile++) {
				//If input corresponds to projectile in play
				if (input.contentEquals(projectiles.get(projectile).getName())) {
					System.out.println("Projectile " + projectiles.get(projectile).getName() + " Destroyed!");
					projectiles.remove(projectile);
					scoreToAdd += 100;
				}
			}
			//If input doesen't corresponds to projectile in play
			if (initalP == projectiles.size()) {
				System.out.println("Miss!");
			}
		}
		
		//UNFINISHED PART START
		//to reset/start new game
		if(input.equals("RESET")) {
			System.out.println("RESET command unfinised");
			//NEED TO IMPLIMENT
		//UNFINISHED PART END
		}
		
		//if input isn't potentially a projectile name and isn't valid command
		else {
			if(input.length() != 1) {
				System.out.println("Invalid input");
			}
		}
		System.out.println("----------------------------------\n");
		return scoreToAdd;
	}

}




