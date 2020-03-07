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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;

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
    private int LIFESIZE;
    private Rectangle life1, life2, life3;
    private Circle planet;
    private int planetRadius = 65;
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
    	this.planet = new Circle(planetRadius);
    	
    	drawTopHUD();
        drawPlanet();
    	drawProjectile();
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler <ActionEvent>(){
			public void handle(ActionEvent event) {
				updateProjectile();
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
        root.setCenter(planet);
        planet.setTranslateX(-planetRadius/2);
        planet.setTranslateY(-planetRadius/2);
    }
    
    //projectile Elements + projectile creation
	int xP = 100;
	int yP = 100;
	int rP = 20;
	Color pC = Color.HOTPINK;
	Circle projectile = new Circle();
	
	
	//inital projectile draw (spawn)
	public void drawProjectile() {
       	projectile.setCenterX(xP);
    	projectile.setCenterY(yP);
    	projectile.setRadius(rP);
    	projectile.setFill(pC);
    	
    	root.getChildren().add (projectile);
	}
	
	//makes projectile move
	public void updateProjectile() {
		xP = xP + 1;
		yP = yP + 1;
		projectile.setCenterX(xP);
		projectile.setCenterY(yP);
    	projectile.setRadius(rP);
    	projectile.setFill(pC);
	}
}