package pdef;
import java.lang.Math;
import java.util.ArrayList;

public class SpawnHandler {
	//Instance variables
	private int nameIndex = 0;
	public ArrayList<Projectile> projectiles;
	static private String[] namesList = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 		
										"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
										"U", "V", "W", "X", "Y", "Z"};
	
	//Constructor, gets reference to projectile list
	public SpawnHandler(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}
	
	//Spawn projectile with randomness
	public Projectile spawnProjectile() {
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
	
	//Initially spawns 3 projectiles
	public void initialSpawn() {
		projectiles.add(spawnProjectile());
		projectiles.add(spawnProjectile());
		projectiles.add(spawnProjectile());
	}
	
	//Projectile spawning after the rest of the turn's game logic has occured. 
	//If there are no projectiles left, then more will always be spawned
	public void trySpawn() {
		if(projectiles.size() < 1) {
			projectiles.add(spawnProjectile());
			projectiles.add(spawnProjectile());
		}
		if(projectiles.size() < 4) {
			if (Math.random() > 0.5) {
				projectiles.add(spawnProjectile());
				projectiles.add(spawnProjectile());
			}
		}
	}
}

