package pdef;

public class Projectile {
	//private int health;
	private int distance;
	private String name; 
	//private int spawnAngle = 0;
	//private int spawnLocation = 0;
	//private int projTipXCoord = 0;
	//private int projTipYCoord = 0;
	
	// Constructor that sets initial health
	/*public Projectile() {
		health = 100;
	}*/
	
	// This constructor gets a distance and a name from SpawnHandler
	public Projectile(int aDistance, String aName) {
		setDistance(aDistance);
		setName(aName);
	}
	
	// Setter methods for distance and name
	public void setDistance(int aDistance) {
		distance = aDistance;
	}
	
	public void setName(String aName) {
		name = aName;
	}
	
	// Getter methods for distance, name and health
	public int getDistance() {
		return distance;
	}
	
	public String getName() {
		return name;
	}
	
	// Method for projectile losing health
	/*public void loseLife() {
		health -= 50;
	}*/
}
