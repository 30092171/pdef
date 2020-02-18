package pdef;
import java.lang.Math;

public class SpawnHandler {
	static private String[] namesList = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 		
										"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
										"U", "V", "W", "X", "Y", "Z"};
	static int nameIndex = 0;
	
	public static Projectile spawnProjectile() {
		String name = (namesList[nameIndex]);
		int distance = ((int) (10 + Math.random() * 100));
		Projectile newProjectile = new Projectile(distance, name);
		if ((nameIndex + 1) > (namesList.length - 1)) {
			nameIndex = 0;
		}
		else {
			nameIndex = nameIndex + 1;
		}
		return  newProjectile;
	}
	
}

