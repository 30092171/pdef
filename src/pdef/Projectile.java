package pdef;

public class Projectile {
	
	private int health = 100;
	private int iDistance = 0;
	private int currentDistance = 0;
	private String projName = ""; 
	//private int spawnAngle = 0;
	//private int spawnLocation = 0;
	//private int projTipXCoord = 0;
	//private int projTipYCoord = 0;
	
	// Constructor that gets a initial distance from spawn handler class and a unique name
	public Projectile(int distance, String aName) {
		iDistance = distance;
		projName = aName;
	}
	
	public String getName() {
		return projName;
	}
	
	public void setName(String aName) {
		projName = aName;
	}
	
	
	//Stuff for GUI version
	/*public void getX() {
		
	}
	
	public void getY() {
		
	}
	
	public int setX() {
		
	}
	
	public int setY() {
		
	}*/
	
}
