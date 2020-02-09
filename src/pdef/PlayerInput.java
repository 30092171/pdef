package pdef;

public class PlayerInput {
	
	private PlayerPlanet p;
	
	public PlayerInput(PlayerPlanet p) {
		this.p = p;
	}
	
	public void command(String input) {
		if (input.length() == 1) {
			char a = input.toUpperCase().charAt(0);
			//for (char pr : p.projectileArray) {
				//if (pr == a) {
				//pr.tryDamage();
				//}
			//}
		}
		//next: regular commands and actions
	}
	
	
}
