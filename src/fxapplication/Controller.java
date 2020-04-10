/*
 * 
 */
package fxapplication;

import java.io.*;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import pdef.*;

/**
 * This class contains all of the logic for modifying
 * game data and sending those changes to the GUI.
 */
public class Controller {

	/** The score count. */
	private int scoreCount = 0;
	
	/** The highscore count. */
	private int highScoreCount;
	
	/** The life count. */
	private int lifeCount = 3;
	
	/** The play image. */
	private ImageView playImage;
	
	/** The pause image. */
	private ImageView pauseImage;

	/** The planet. */
	private PlayerPlanet planet;
	
	/**  The default projectile image. */
	private Image defaultProjectile;
	
	/**  The rotating projectile image. */
	private Image rotatingProjectile;
	
	/**  The speedup projectile image. */
	private Image speedUpProjectile;
	
	/**  The unstable projectile image. */
	private Image unstableProjectile;
	 
	/** The GUI. */
	private GUI gui;
	
	/** The spawn handler. */
	private SpawnHandler spawnHandler;
	
	/** The arraylist of projectiles. */
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	/** The spawnThread and the projectileThread. */
	private Thread spawnThread, projectileThread;

	/**
	 * Constructs and initializes a new controller.
	 *
	 * @param gui The gui
	 * @param spawnHandler The spawn handler
	 * @throws FileNotFoundException the file not found exception
	 */
	public Controller(GUI gui, SpawnHandler spawnHandler) throws FileNotFoundException {
		this.planet = new PlayerPlanet();
		this.gui = gui;
		this.spawnHandler = spawnHandler;
		this.pauseImage = new ImageView(new Image("https://i.imgur.com/YyHnk0H.png"));
		this.playImage = new ImageView(new Image("https://i.imgur.com/7zDd1B5.png"));
		this.defaultProjectile = new Image("https://i.imgur.com/7fviQFm.png");
		this.rotatingProjectile = new Image("https://i.imgur.com/cT4QMzd.png");
		this.speedUpProjectile = new Image("https://i.imgur.com/I4Tiqy3.png");
		this.unstableProjectile = new Image("https://i.imgur.com/8i4UqVG.png");
		gui.addCircle(this.planet.getCircle());
		init();
	}

	/**
	 * Initializes the game's main loop.
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
							if (proj instanceof UnstableProjectile) {
								for (int j = projectiles.size() - 1; j >= 0; j--) {
									removeProjectile(projectiles.get(j));
									Platform.runLater(()->{
										scoreCount = scoreCount + 100;
										gui.setScoreText(Integer.toString(scoreCount));
										if (scoreCount > highScoreCount) {
											highScoreCount = scoreCount;
										}
									});
								}
							break;
							}
							else {
								removeProjectile(proj);
								Platform.runLater(()->{
									scoreCount = scoreCount + 100;
									gui.setScoreText(Integer.toString(scoreCount));
									if (scoreCount > highScoreCount) {
										highScoreCount = scoreCount;
									}
								});
								continue;
							}
						}
						// Planet Collision Check
						if (planet.checkCollision(proj)) {
							lifeCount = lifeCount - 1;
							gui.setLivesDisplay(lifeCount);
							removeProjectile(proj);
							// Displays gameOver when lives = 0
							if (lifeCount == 0) {
								Platform.runLater(()->{
									saveHighscore();
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
					timeline.setRate(0.0);
			    	timeline.stop();
					gui.drawPause();
					gui.pauseButton.setGraphic(playImage);
				} else {
					timeline.setRate(1.0);
					timeline.play();
					gui.drawPlay();
					gui.pauseButton.setGraphic(pauseImage);
				}
			}
		});

		this.gui.resetButton.setOnAction((ActionEvent e) -> {
			this.lifeCount = 3;
			this.scoreCount = 0;
			saveHighscore();
			projectiles.clear();
			gui.resetGui();
			gui.pauseButton.setGraphic(pauseImage);
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
	 * Adds subtle variations in scale and rotation for newly spawned projectiles.
	 *
	 * @param newProj the new proj
	 */
	public void projectileVariation(Projectile newProj) {
		newProj.getCircle().setRotate(Math.random() * 360);
		newProj.setCircleRadius((int)(9 + Math.random() * 6));
	}
	
	/**
	 * Adds the projectile to the screen and the projectile arraylist.
	 */
	private void addProjectile() {
		Projectile newProj = spawnHandler.spawnProjectile();
		this.projectiles.add(newProj);
		if (newProj instanceof DefaultProjectile) {
			newProj.getCircle().setFill(new ImagePattern(defaultProjectile));
			projectileVariation(newProj);
		} else if (newProj instanceof SpeedUpProjectile) {
			newProj.getCircle().setFill(new ImagePattern(speedUpProjectile));
			projectileVariation(newProj);
		} else if (newProj instanceof UnstableProjectile) {
			newProj.getCircle().setFill(new ImagePattern(unstableProjectile));
			projectileVariation(newProj);
		} else {
			newProj.getCircle().setFill(new ImagePattern(rotatingProjectile));
			projectileVariation(newProj);
		}
		gui.addCircle(newProj.getCircle());
	}

	/**
	 * Removes the projectile from the screen and projectile arraylist.
	 *
	 * @param proj The reference to the projectile
	 */
	private void removeProjectile(Projectile proj) {
		gui.removeCircle(proj.getCircle());
		projectiles.remove(proj);
	}

	/**
	 * Post init.
	 */
	public void postInit() {
		this.loadHighscore();
		gui.getTimeline().play();
		Platform.runLater(()->{
			gui.setHighScoreText("Highscore: " + this.highScoreCount);
		});
	}
	
	/**
	 * Write players highest score to data file.
	 *
	 * @throws IOException
	 */
	public void saveHighscore() {
		try {
			File f = new File("highscore.dat");
			if (!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(f);
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeInt(this.highScoreCount);
			dos.close();
			fos.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	/**

	 * Reads players previous high score from data file.
	 *
	 * @throws IOException
	 */
	public void loadHighscore() {
		try {
			File f = new File("highscore.dat");
			if (!f.exists()) {
				return;
			}
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			this.highScoreCount = dis.readInt();
			dis.close();
			fis.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
