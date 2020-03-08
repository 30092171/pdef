package pdef;

public class ScreenElements {
	private int xCoord;
	private int yCoord;
	
	
	public ScreenElements (int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	public int getxCoordinate() {
		return xCoord;
	}
	public void setxCoordinate(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getyCoordinate() {
		return yCoord;
	}
	public void setyCoordinate(int yCoord) {
		this.yCoord = yCoord;
	}
	
	

}
