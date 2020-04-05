/*
 * 
 */
package fxapplication;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import pdef.*;

/**
 * This class represents the controller portion of the Model-View-Controller design.
 * It contains all of the logic for modifying game data and the GUI 
 */
public class Controller {

	/** The score count. */
	private int scoreCount = 0;
	
	/** The life count. */
	private int lifeCount = 3;
	
	/** The play image. */
	private ImageView playImage;
	
	/** The pause image. */
	private ImageView pauseImage;

	/** The planet. */
	private PlayerPlanet planet;
	
	/** The GUI. */
	private GUI gui;
	
	/** The spawn handler. */
	private SpawnHandler spawnHandler;
	
	/** The projectiles. */
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	/** The spawnThread and the projectileThread. */
	private Thread spawnThread, projectileThread;

	/**
	 * Instantiates a new controller.
	 *
	 * @param gui the gui
	 * @param spawnHandler the spawn handler
	 */
	public Controller(GUI gui, SpawnHandler spawnHandler) {
		this.planet = new PlayerPlanet();
		this.gui = gui;
		this.spawnHandler = spawnHandler;
		this.pauseImage = new ImageView(new Image("https://i.imgur.com/YyHnk0H.png"));
		this.playImage = new ImageView(new Image("https://i.imgur.com/7zDd1B5.png"));
		gui.addCircle(this.planet.getCircle());
		init();
	}

	/**
	 * Initializes the game's main loop
	 */
	private void init() {
		gui.getTimeline().getKeyFrames().add(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
			@Override
			public synchronized void handle(ActionEvent e) {
				spawnThread = new Thread(()-> {
					// Projectile respawning based on old trySpawn() method in spawnHandler
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
				});
				projectileThread = new Thread(()->{
					// Move Projectile
					for (int i = projectiles.size() - 1; i >= 0; i--) {
						Projectile proj = projectiles.get(i);
						proj.turn();
						// Barrier Collision Check
						if (gui.barrier.barrierCollisionCheck(proj)) {
							removeProjectile(proj);
							scoreCount = scoreCount + 100;
							Platform.runLater(()->{
								gui.setScoreText(Integer.toString(scoreCount));
							});
							continue;
						}
						// Planet Collision Check
						if (planet.checkCollision(proj)) {
							lifeCount = lifeCount - 1;
							gui.setLivesDisplay(lifeCount);
							removeProjectile(proj);
							// Displays gameOver when lives = 0
							if (lifeCount == 0) {
								Platform.runLater(()->{
									gui.drawGameOver();
								});
							}
						}
					}
				});
				spawnThread.start();
				projectileThread.start();
				try {
					spawnThread.join();
				} catch (InterruptedException e1) {
					
				}
				try {
					projectileThread.join();
				} catch (InterruptedException e2) {
					
				}
			}
		}));

		this.gui.pauseButton.setOnAction((ActionEvent e) -> {
			Timeline timeline = this.gui.getTimeline();
			if (lifeCount > 0) {
				if (timeline.getRate() > 0.0) {
					gui.drawPause();
					gui.pauseButton.setGraphic(playImage);
				} else {
					gui.drawPlay();
					gui.pauseButton.setGraphic(pauseImage);
				}
			}
		});
		
		this.gui.resetButton.setOnAction((ActionEvent e) -> {
			this.lifeCount = 3;
			this.scoreCount = 0;
			projectiles.clear();
			gui.resetGui();
			gui.addCircle(this.planet.getCircle());
			
		});
		
		this.gui.menuPlayButton.setOnAction((ActionEvent e) -> {
			Timeline timeline = this.gui.getTimeline();
			timeline.setRate(1.0);
			timeline.play();
			gui.resetGui();
		});
	}

	/**
	 * Adds the projectile to the screen and the projectile list.
	 */
	private void addProjectile() {
		Projectile newProj = spawnHandler.spawnProjectile();
		this.projectiles.add(newProj);
		System.out.println(newProj);
		gui.addCircle(newProj.getCircle());
	}

	/**
	 * Removes the projectile from the screen and projectile list.
	 *
	 * @param proj the proj
	 */
	private void removeProjectile(Projectile proj) {
		gui.removeCircle(proj.getCircle());
		projectiles.remove(proj);
	}

	/**
	 * Post init.
	 */
	public void postInit() {
		gui.getTimeline().play();
	}

}
