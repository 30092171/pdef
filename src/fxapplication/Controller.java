package fxapplication;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import pdef.*;

public class Controller {
	//all of the logic for modifying data and the gui goes in here.
	
	private int scoreCount = 0;
	private int lifeCount = 3;
	
	private PlayerPlanet planet;
	private GUI gui;
	private SpawnHandler spawnHandler;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	public Controller(GUI gui, SpawnHandler spawnHandler) {
		this.planet = new PlayerPlanet();
		this.gui = gui;
		this.spawnHandler = spawnHandler;
		gui.addCircle(this.planet.getCircle());
		init();
	}
	
	private void init() {
		gui.getTimeline().getKeyFrames().add(new KeyFrame(Duration.millis(20), (ActionEvent event)-> {
			//Projectile respawning based on old trySpawn() method in spawnHandler
			if (projectiles.size() < 1) {
				addProjectile();
				addProjectile();
			}
			
			if (projectiles.size() < 5) {
				if (Math.random() > 0.5) {
					addProjectile();
					addProjectile();
				}
			}
			
			//Move Projectile
			for (Projectile proj : projectiles) {
				proj.turn();
				//Barrier Collision Check
				if (gui.barrier.barrierCollisionCheck(proj)) {
					removeProjectile(proj);
					scoreCount = scoreCount + 100;
					gui.setScoreText(Integer.toString(scoreCount));
				}
				//Planet Collision Check
				if (this.planet.checkCollision(proj)) {
					lifeCount = lifeCount - 1;
					gui.setLivesDisplay(lifeCount);
					removeProjectile(proj);
					
					// Displays gameOver when lives = 0
					if (lifeCount == 0) {
						gui.getTimeline().stop();
						gui.drawGameOver();
					}
				}
			}
		}));
		
		this.gui.pauseButton.setOnAction((ActionEvent e) -> {
			Timeline timeline = this.gui.getTimeline();
			if (lifeCount != 0) {
				if (timeline.getRate() > 0.0) {
				    timeline.setRate(0.0);
					timeline.stop();
					gui.pauseButton.setText("Play");
				} else {
					timeline.setRate(1.0);
					timeline.play();
					gui.pauseButton.setText("Pause");
				}
			}
		});
		
		// Resets all variables back to starting state
			this.gui.resetButton.setOnAction((ActionEvent e) -> {
				this.lifeCount = 3;
				this.scoreCount = 0;
				projectiles.clear();
				gui.resetGui();
			});
	}
	
	//Draw a new projectile
	private void addProjectile() {
		Projectile newProj = spawnHandler.spawnProjectile();
		this.projectiles.add(newProj);
		System.out.println(newProj);
    	gui.addCircle(newProj.getCircle());
	}
	
	//Removes a projectile from the screen and projectile list
	private void removeProjectile(Projectile proj) {
		gui.removeCircle(proj.getCircle());
		projectiles.remove(proj);
	}

	public void postInit() {
		gui.getTimeline().play();
	}
	
}
