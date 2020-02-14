package pdef;

import java.util.ArrayList;

public class PlayerInput {
	
	private PlayerPlanet p;
	
	public PlayerInput(PlayerPlanet p) {
		this.p = p;
	}
	
	public void command(String input) {
		input = input.toUpperCase();
		if (input.length() == 1) {
			String projUser = input;
			for (int count = 0 ; count < Main.projectiles.size() ; count++) {
				String projName = Main.projectiles.get(count).name;
				if (projUser.equals(projName)) {
				//projUser.tryDamage();
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
	
	}

}

