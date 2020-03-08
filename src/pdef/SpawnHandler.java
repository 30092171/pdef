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
	private PlayerPlanet player;
	
	
	//Constructor, gets reference to projectile list and PlayerPlanet
	public SpawnHandler(ArrayList<Projectile> projectiles, PlayerPlanet player) {
		this.projectiles = projectiles;
		this.player = player;
	}
	
	public Projectile spawnProjectile() {
		int initialDistance = 800; // Spawns outside of screen 
		String name = (namesList[nameIndex]);
		int spawnAngle = (int)(Math.random() * 360);
		int xCoord = (int)(initialDistance * Math.cos(spawnAngle));
		int yCoord = (int)(initialDistance * Math.sin(spawnAngle));
		
		Projectile newProjectile = new Projectile(initialDistance, name, spawnAngle, xCoord, yCoord);
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
	
	public void updateProjectiles() {
		// Decreases projectile distance after each turn and removes projectile/life if
		// distance is 0.
		// public void updateProjectile() --> stay in main
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).setXCoord(projectiles.get(i).getSpawnAngle(), 5); // Moving 5 units away
			projectiles.get(i).setYCoord(projectiles.get(i).getSpawnAngle(), 5); // Moving 5 units away
			projectiles.get(i).setDistance(projectiles.get(i).getxCoordinate(),projectiles.get(i).getyCoordinate(), player);
			

			if (projectiles.get(i).getDistance() - player.getPlanetRadius() <= 0) {
				System.out.println("Impact Detected, -1 Life!");
				player.lostLife();
				projectiles.remove(i);
			}
		}
	}
	
	public void printProjectileStatus(PlayerPlanet player) {
		for(Projectile element : projectiles) {
			System.out.println("Projectile " + element.getName() 
					+ " is " + (element.getDistance() - player.getPlanetRadius()) + " units away at" +
					" (" + element.getxCoordinate() + "," + element.getyCoordinate() + ").");
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Projectile> proj = new ArrayList<>();
		PlayerPlanet player = new PlayerPlanet();
		SpawnHandler sp = new SpawnHandler(proj, player);
		sp.trySpawn();
		System.out.println(sp.projectiles);
	}
}

