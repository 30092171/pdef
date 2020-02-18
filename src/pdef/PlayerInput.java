package pdef;

import java.util.ArrayList;

public class PlayerInput {
	
	private ArrayList<Projectile> p;
	
	public PlayerInput(ArrayList<Projectile> p) {
		this.p = p;
	}
	
	public String command(String input) {
		input = input.toUpperCase();
		if (input.length() == 1) { // check if its a character
			String projUser = input;
			for (Projectile t : p) {
				String projName = t.getName();
				if (projUser.equals(projName)) {
				//p.tryDamage();
				}
				//if projectile named not in play
				else {
					
				}
			}
		}

		//to reset/start new game
		if(input.equals("RESET")) {
		
		}
		
		//for invalid input
		else {
			
		}
		
		return "";
	}

}

