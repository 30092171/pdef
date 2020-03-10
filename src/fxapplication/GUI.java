package fxapplication;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import pdef.PolarCoord;
import pdef.Projectile;
import javafx.event.EventHandler;

import java.awt.Event;
import java.lang.Math;
import java.util.ArrayList;
import pdef.SpawnHandler;

public class GUI {
    //this class provides all the necessary elements to update and control the gui.

    //Root Components and Formatting Elements
    private static Dimension2D WINDOWSIZE = new Dimension2D(720, 720);
    private static Point2D ORIGIN = new Point2D(360, 360);

    private Stage mainStage;
    private BorderPane root;
    private Scene scene, gameOverScene;
    private Canvas canvas;

    //Screen Elements
    private Label scoreValue;
    private int scoreCount = 0;
    private int LIFESIZE;
    private Rectangle life1, life2, life3;
    private int lifeCount = 3;
    private Circle planet;
    private int planetRadius = 65;
    private double planetX = WINDOWSIZE.getHeight()/2;
    private double planetY = WINDOWSIZE.getWidth()/2;
    private Button pauseButton;
    private Button resetButton;
    private SpawnHandler spawnHandler;
    private Barrier barrier = new Barrier();
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    
    private Timeline timeline;

    public GUI(Stage mainStage) {
    	this.mainStage = mainStage;
    	this.root = new BorderPane();
    	this.scene = new Scene(root);
    	this.canvas = new Canvas();
    	this.mainStage.setScene(this.scene);
    	this.root.getChildren().add(canvas);
    	this.root.setPrefSize(720, 720);
    	this.root.setStyle("-fx-background-color: gray");

    	this.scoreValue = new Label("0");
    	this.LIFESIZE = 35;
    	this.life1 = new Rectangle(LIFESIZE, LIFESIZE);
    	this.life2 = new Rectangle(LIFESIZE, LIFESIZE);
    	this.life3 = new Rectangle(LIFESIZE, LIFESIZE);
    	this.pauseButton = new Button("Pause");
    	this.resetButton = new Button("Reset");
    	this.planet = new Circle(planetX,planetY,planetRadius);
    	this.projectiles = new ArrayList<Projectile>();
    	this.spawnHandler = new SpawnHandler(projectiles);
    	this.root.getChildren().add (planet);
    	
    	drawTopHUD();
    	addProjectile();
    	addProjectile();
    	addProjectile();
    	addProjectile();
    	addProjectile();
        
        //draws the barriers where mouse is clicked
    	root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
    		removeBarrier();
    		addBarrier((int)event.getX(), (int)event.getY());
    	}
    	);
    
    
	this.timeline = new Timeline();	
	this.timeline.getKeyFrames().add(new KeyFrame(Duration.millis(20), new EventHandler <ActionEvent>(){
		public void handle(ActionEvent event) {
			
			//Projectile respawning based on old trySpawn() method in spawnHandler
			if (projectiles.size()<1) {
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
			}
			
			//Planet Collision Check
			for (int projIndex = 0; projIndex < projectiles.size(); projIndex++) {
				if (planetCollisionCheck(projectiles.get(projIndex)) == true) {
					lifeCount = lifeCount - 1;
					setLivesDisplay(lifeCount);
					removeProjectile(projectiles.get(projIndex), projIndex);
					
					// Displays gameOver when lives = 0
					if (lifeCount == 0) {
						timeline.stop();
						drawGameOver();
					}
				}
			}
			
			//Barrier Collision Check
			for (int projIndex = 0; projIndex < projectiles.size(); projIndex++) {
				if (barrierCollisionCheck(projectiles.get(projIndex)) == true) {
					removeProjectile(projectiles.get(projIndex), projIndex);
					scoreCount = scoreCount + 100;
					setScoreText(Integer.toString(scoreCount));
				}
			}
		}
	}
	)
	);
	timeline.setCycleCount(Animation.INDEFINITE);
	timeline.play();
	this.pauseButton.setOnAction((ActionEvent e) -> {
		if (lifeCount != 0) {
			if (this.timeline.getRate() > 0.0) {
				this.timeline.setRate(0.0);
				this.timeline.stop();
			} else {
				this.timeline.setRate(1.0);
				this.timeline.play();
			}
		}
	});
	
	// Resets all variables back to starting state
		this.resetButton.setOnAction((ActionEvent e) -> {
			this.lifeCount = 3;
			this.scoreCount = 0;
			this.scoreValue.setText("0");
			projectiles.clear();
			root.getChildren().clear();
			drawTopHUD();
			this.root.getChildren().add (planet);
			float full = 1.0f;
			life1.setOpacity(full);
	        life2.setOpacity(full);
	        life3.setOpacity(full);
			timeline.playFromStart();
			
		});
	
    }
    
    public void setLivesDisplay(int lifeCount) {
    	float full = 1.0f;
    	float half = 0.35f;
    	
        if (lifeCount >= 3) {
            life1.setOpacity(full);
            life2.setOpacity(full);
            life3.setOpacity(full);
        } else if (lifeCount == 2) {
            life1.setOpacity(full);
            life2.setOpacity(full);
            life3.setOpacity(half);
        } else if (lifeCount == 1) {
            life1.setOpacity(full);
            life2.setOpacity(half);
            life3.setOpacity(half);
        } else if (lifeCount <= 0) {
            life1.setOpacity(half);
            life2.setOpacity(half);
            life3.setOpacity(half);
        }
    }

    public void setScoreText(String aScore) {
        scoreValue.setText(aScore);
    }

    public void drawTopHUD() {
        //Define Boxes
        HBox livesBox = new HBox();
        livesBox.setPadding(new Insets(-68, 0, 0, 20));
        livesBox.setAlignment(Pos.TOP_LEFT);
        livesBox.setSpacing(15);
        root.setLeft(livesBox);
        VBox scoreBox = new VBox();
        scoreBox.setPadding(new Insets(20, 0, 0, 0));
        scoreBox.setAlignment(Pos.TOP_CENTER);
        root.setTop(scoreBox);
        HBox controlsBox = new HBox();
        controlsBox.setPadding(new Insets(-68, 20, 0, 0));
        controlsBox.setAlignment(Pos.TOP_RIGHT);
        root.setRight(controlsBox);

        //Populate Lives HBox
        livesBox.getChildren().add(life1);
        livesBox.getChildren().add(life2);
        livesBox.getChildren().add(life3);

        //Populate Score VBox
        Label scoreText = new Label("SCORE");
        //Header Text
        scoreText.setFont(new Font("Arial", 14));
        scoreBox.getChildren().add(scoreText);
        //Score Value
        scoreValue.setFont(new Font("Arial", 48));
        scoreBox.getChildren().add(scoreValue);

        //Populate Controls HBox
        controlsBox.getChildren().add(pauseButton);
        controlsBox.getChildren().add(resetButton);
    }
    
    public void drawGameOver() {
    	//Define VBox
    	VBox gameOverBox = new VBox();
    	gameOverBox.setPadding(new Insets(-95, 90, 0, 0));
    	gameOverBox.setAlignment(Pos.CENTER);
    	root.setCenter(gameOverBox);
    	
    	//Populate GameOver VBox
    	Label gameOverText = new Label("GAME OVER");
    	gameOverText.setFont(new Font("Arial", 59));
    	gameOverText.setTextFill(Color.ORANGERED);
    	gameOverBox.getChildren().add(gameOverText);
    }

	//Draw a new projectile
	public void addProjectile() {
		Projectile newProj = spawnHandler.spawnProjectile();
		this.projectiles.add(newProj);
		System.out.println(newProj);
    	root.getChildren().add(newProj.getCircle());
	}
	
	//Removes a projectile from the screen and projectile list
	public void removeProjectile(Projectile proj, int index) {
		root.getChildren().remove(proj.getCircle());
		projectiles.remove(index);
	}
	
	//Checks if a projectile is clipping with the planet
	public boolean planetCollisionCheck(Projectile proj) {
		double planetXCoord = planet.getCenterX();
		double planetYCoord = planet.getCenterY();
		double planetRadius = planet.getRadius();
		
		//Calculations to determine if projectile collides with planet
		double dist = Math.hypot(proj.getCircle().getCenterX() - planetXCoord, proj.getCircle().getCenterY() - planetYCoord);
		double radSum = (proj.getCircleRadius() + planetRadius);
		
		//Checking if the projectile clips with the planet
		if (dist < radSum) {
			
			//Returns boolean so we can check if projectile hits (for removing lives)
			return true;
		}
		else {
			return false;
		}
	}
	
	//Draw a new barrier
	public void addBarrier(int xCoord, int yCoord) {
       	Barrier newBarrier = new Barrier();
    	newBarrier.getCircle().setCenterX(xCoord);
    	newBarrier.getCircle().setCenterY(yCoord);
    	newBarrier.getCircle().setRadius(20);
    	this.barrier = newBarrier;
    	root.getChildren().add(newBarrier.getCircle());
	}
	
	//Removes an old barrier
	public void removeBarrier() {
		root.getChildren().remove(this.barrier.getCircle());
	}
	
	//Checks if the barrier collides with a projectile
	public boolean barrierCollisionCheck(Projectile proj) {
		
		//Calculations to determine if projectile collides with barrier
		double dist = Math.hypot(proj.getCircle().getCenterX() - barrier.getCircle().getCenterX(), proj.getCircle().getCenterY() - barrier.getCircle().getCenterY());
		double radSum = (proj.getCircleRadius() + barrier.getCircleRadius());
		
		//Remove projectile if they do collide
		if (dist < radSum) {
			
			return true;
		}
		else {
			return false;
		}
	}
}