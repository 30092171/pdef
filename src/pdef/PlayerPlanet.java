package pdef;

public class PlayerPlanet {
	private int numLives;
	private int score;
	private int planetRadius;
	
	public PlayerPlanet() {
		numLives = 3;
		score = 0;
		planetRadius = 65; 
	}
	
	public void lostLife() {
		numLives -= 1;
	}
	
	public void addLife() {
		numLives += 1;
	}
	
	public boolean isGameOver() {
		if (numLives <= 0) {
			return true;
		}
		return false;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getPlanetRadius() {
		return planetRadius;
	}
	public void addScore(int givenScore) {
		score += givenScore;
	}
	
	public int getLives() {
		return numLives;
	}
	
	public void printPlayerStatus() {
		System.out.println("----------------------------------");
		System.out.println("You have " + getLives() + " lives remaining");
		System.out.println("Current Score: " + getScore());
	}
	
	public void printGameOver() {
		System.out.println("----------------------------------");
		System.out.println("Game Over!");
		System.out.println("Final Score: " + getScore());
	}
	
	public static void main(String[] args) {
		PlayerPlanet player = new PlayerPlanet();
		player.printPlayerStatus();
		
	}

}
