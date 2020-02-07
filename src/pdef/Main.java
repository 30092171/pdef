package pdef;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		PlayerPlanet p = new PlayerPlanet();
		
		PlayerInput pi = new PlayerInput(p);
		while(true) {
			System.out.println("player stats here");
			
			String input = sc.nextLine();
			//if(p.isGameOver){break;}
		}
	}

}
