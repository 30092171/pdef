package pdef;
import java.lang.Math;
import java.util.ArrayList;

public class SpawnHandler {
	//Instance variables
	private int nameIndex = 0;
	private ArrayList<Projectile> projectiles;
	private PlayerPlanet playerPlanet;
	static private String[] namesList = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 		
										"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
										"U", "V", "W", "X", "Y", "Z"};
	
	//Constructor, gets reference to projectile list
	public SpawnHandler(ArrayList<Projectile> projectiles, PlayerPlanet playerPlanet) {
		this.projectiles = projectiles;
		this.playerPlanet = playerPlanet;
	}
	
	//Get Projectile List
	public ArrayList<Projectile> getProjectileList(){
		return this.projectiles;
	}
	
	//Add to Projectile List
	public void addProjectile(Projectile newProjectile) {
		projectiles.add(newProjectile);
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
	
	public static void main(String[] args) {
		//ArrayList<Projectile> proj = new ArrayList<>();
		//SpawnHandler sp = new SpawnHandler(proj);
		//sp.trySpawn();
		//System.out.println(sp.projectiles);
	}
}

