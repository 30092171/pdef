package fxapplication;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import pdef.Projectile;
import javafx.event.EventHandler;
import java.lang.Math;
import java.util.ArrayList;

public class GUI {
    //this class provides all the necessary elements to update and control the gui.

    //Root Components and Formatting Elements
    private static Dimension2D WINDOWSIZE = new Dimension2D(720, 720);

    private Stage mainStage;
    private BorderPane root;
    private Scene scene;
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

    public GUI(Stage mainStage) {
    	this.mainStage = mainStage;
    	this.root = new BorderPane();
    	this.scene = new Scene(root);
    	this.canvas = new Canvas();
    	this.mainStage.setScene(this.scene);
    	this.root.getChildren().add(canvas);
    	this.root.setPrefSize(720, 720);

    	this.scoreValue = new Label("0");
    	this.LIFESIZE = 35;
    	this.life1 = new Rectangle(LIFESIZE, LIFESIZE);
    	this.life2 = new Rectangle(LIFESIZE, LIFESIZE);
    	this.life3 = new Rectangle(LIFESIZE, LIFESIZE);
    	this.pauseButton = new Button("Pause");
    	this.planet = new Circle(planetX,planetY,planetRadius);
    	this.projectiles = new ArrayList<>();
    	this.barriers = new ArrayList<>();
    	
    	drawTopHUD();
        drawPlanet();
        
        //first projectile spawn
        projectiles.add(projectileSpawn(xP,yP,rP));
        for (int projectileC = 0; projectileC < projectiles.size(); projectileC++) {
        	ProjectileTemp p = projectiles.get(projectileC);
        	newProjectile(p);
        }
        

        
        //draws the barriers where mouse is clicked
    	root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
    		for (int barrierC = 0; barrierC < barriers.size(); barrierC++) {
    			barriers.remove(barrierC);
    		}
    		barriers.add(barracade(event.getX(),event.getY()));
    		for (int barrierC = 0; barrierC < barriers.size(); barrierC++) {
				Barrier b = barriers.get(barrierC);
				oldBarrier();
				newBarrier(b);
    		}
        });
    	
    	
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler <ActionEvent>(){
			public void handle(ActionEvent event) {
				//checks if projectile hits planet
				for (int projectileC = 0; projectileC < projectiles.size(); projectileC++) {
					ProjectileTemp p = projectiles.get(projectileC);
					boolean hit = collideProjectile(p);
					//if projectile hits planet destroys projectile and removes life
					if (hit == true) {
						oldProjectile();
						lifeCount = lifeCount - 1;
						projectiles.remove(projectileC);
						//USED TO TEST
						//spawns a new projectile when old one removed
						projectiles.add(projectileSpawn(xP,yP,rP));
				        for (int projectileC1 = 0; projectileC1 < projectiles.size(); projectileC1++) {
				        	ProjectileTemp p1 = projectiles.get(projectileC1);
				        	p1.xP = 710;
							p1.yP = 710;
							p1.rP = 20;
				        	newProjectile(p1);
				        }
					}
					//checks if projectile hits barrier
					for (int barrierC = 0; barrierC < barriers.size(); barrierC++) {
						Barrier b = barriers.get(barrierC);
						boolean block = collideBarrier(b,p);
						//if projectile hits barrier destroys projectile and adds to score
						if (block == true){
							scoreCount = scoreCount + 100;
							oldProjectile();
							projectiles.remove(projectileC);
							//USED TO TEST
							//spawns a new projectile when old one removed
							projectiles.add(projectileSpawn(xP,yP,rP));
					        for (int projectileC2 = 0; projectileC2 < projectiles.size(); projectileC2++) {
					        	ProjectileTemp p2 = projectiles.get(projectileC2);
								p2.xP = 710;
								p2.yP = 710;
								p2.rP = 20;
					        	newProjectile(p2);
					        }
						}
					}
					updateProjectile(p);
				}
				setLivesDisplay(lifeCount);
				String scoreCountS = Integer.toString(scoreCount);
				setScoreText(scoreCountS);
			}
		}
		)
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		mainStage.show();
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
    }

    public void drawPlanet() {
        //GraphicsContext gc = canvas.getGraphicsContext2D();
        //int radius = 65;
        //**topleft x,y, width height
        //gc.fillOval(WINDOWSIZE.getWidth() / 2 - radius, WINDOWSIZE.getHeight() / 2 - radius, radius * 2, radius * 2);
    	
        /*root.setCenter(planet);
        planet.setTranslateX(-planetRadius/2);
        planet.setTranslateY(-planetRadius/2);*/
    	
    	root.getChildren().add (planet);
    }

    
    
    
    //setup variables for projectile
    double xP = 710;
	double yP = 710;
	int rP = 20;
	Circle projectile = new Circle();
	ArrayList<ProjectileTemp> projectiles;
	
	//uses Projectile class to make new projectile
	//using ProjectileTemp till Projectile gets sorted out
	public ProjectileTemp projectileSpawn(double aXP, double aYP, int aRP) {
		xP = aXP;
		yP = aYP;
		rP = aRP;
		return new ProjectileTemp(xP,yP,rP);
	}

	//draw new projectile
	public void newProjectile(ProjectileTemp p) {
		xP = p.xP;
		yP = p.yP;
		rP = p.rP;
    	
    	root.getChildren().add (projectile);
	}
	
	//removes old barrier
	public void oldProjectile() {
		root.getChildren().remove (projectile);
	}
	
	//makes projectile move
	public void updateProjectile(ProjectileTemp p) {
		p.xP = p.xP - 1;
		p.yP = p.yP - 1;
		projectile.setCenterX(p.xP);
		projectile.setCenterY(p.yP);
    	projectile.setRadius(p.rP);
	}
	
	//checks if projectile collides with planet
	public boolean collideProjectile(ProjectileTemp p) {
		double xPrC = p.xP;
		double yPrC = p.yP;
		double rPrC = p.rP;
		double xPlC = planet.getCenterX();
		double yPlC = planet.getCenterY();
		double rPlC = planet.getRadius();
		
		//calculations to determine if projectile collides with planet
		double dist = Math.hypot(xPrC-xPlC, yPrC-yPlC);
		double radSum = (rPrC + rPlC); 
		
		//checking if projectile collides with planet
		if (dist < radSum) {
			
			//returns boolean so we can check if projectile hits(for removing lives)
			return true;
		}
		else {
			return false;
		}
	}
	
	

	//setup variables for barrier
	double xB;
	double yB;
	int rB = 15;
	Circle barrier = new Circle();
	ArrayList<Barrier> barriers;
	
	//Uses Barrier class to make a new barrier
	public Barrier barracade(double eventXPos, double eventYPos) {
		return new Barrier(eventXPos,eventYPos,rB);
	}
	
	//draws new barrier
	public void newBarrier(Barrier b) {
		xB = b.xB;
		yB = b.yB;
		rB = b.rB;
       	barrier.setCenterX(xB);
    	barrier.setCenterY(yB);
    	barrier.setRadius(rB);
    	root.getChildren().add (barrier);
	}
	
	//removes old barrier
	public void oldBarrier() {
		root.getChildren().remove (barrier);
	}
	
	//checks if barrier collides with barrier
	public boolean collideBarrier(Barrier b,ProjectileTemp p) {
		
		double xBxC = b.xB;
		double yBxC = b.yB;
		double rBxC = b.rB;
		double xPrC = p.xP;
		double yPrC = p.yP;
		double rPrC = p.rP;
		
		//calculations to determine if projectile collides with barrier
		double dist = Math.hypot(xBxC-xPrC, yBxC-yPrC);
		double radSum = (rBxC + rPrC); 
		
		//remove projectile if they do collide
		if (dist < radSum) {
			
			return true;
		}
		else {
			return false;
		}
	}
}