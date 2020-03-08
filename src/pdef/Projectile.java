package pdef;

public class Projectile extends ScreenElements {
	
	private double distance;
	private String name; 
	private int spawnAngle;
	
	// This constructor gets a distance and a name from SpawnHandler
	public Projectile(double initialDistance, String aName, int spawnAngle, int xCoord, int yCoord) {
		super(xCoord, yCoord);
		this.distance = initialDistance;
		setName(aName);
		setSpawnAngle(spawnAngle);
	}
	
	// Setter methods for distance, name, and spawnAngle
	public void setDistance(int xCoord, int yCoord, PlayerPlanet player) {
		// Takes the x and y of the projectile and computes distance between planet's center
		this.distance = Math.sqrt(Math.pow(xCoord, 2) + Math.pow(yCoord, 2))-player.getPlanetRadius();
	}
	
	public void setName(String aName) {
		name = aName;
	}
	
	public void setSpawnAngle(int spawnAngle) {
		if (spawnAngle >= 0 && spawnAngle <= 360) {
			this.spawnAngle = spawnAngle;
		}
	}
	
	// Getter methods for distance, name, spawnAngle, and coordinates
	public double getDistance() {
		return distance;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSpawnAngle() {
		return spawnAngle;
	}
	
	public void setXCoord(int spawnAngle, int distanceToMove) {
		// Changes the xCoord to move based on the distanceToMove parameter
		super.setxCoordinate((int)((this.distance-distanceToMove) * Math.cos(spawnAngle)));
	}
	
	public void setYCoord(int spawnAngle, int distanceToMove) {
		// Changes the yCoord to move based on the distanceToMove parameter
		super.setyCoordinate((int)((this.distance-distanceToMove) * Math.sin(spawnAngle)));
	}
	
	public static void main(String[] args) {
		Projectile proj = new Projectile(100, "A", 90, 0, 100);
		System.out.println(proj.getName());
	}
}
