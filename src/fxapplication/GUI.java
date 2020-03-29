package fxapplication;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pdef.PlayerPlanet;

public class GUI {
    //this class provides all the necessary elements to update and control the gui.

    //Root Components and Formatting Elements
    private static Dimension2D WINDOWSIZE = new Dimension2D(720, 720);
    private static Point2D ORIGIN = new Point2D(360, 360);
    
    public static Point2D getOrigin() {
    	return new Point2D(ORIGIN.getX(), ORIGIN.getY());
    }

    private Stage mainStage;
    private BorderPane root;
    private Scene scene;
    private Canvas canvas;

    //Screen Elements
    Label scoreValue;
    
    private ImageView life1, life2, life3;
    private ImageView stars;
    private ImageView pauseImage;
    private ImageView resetImage;
    private ImageView planet;
    private int planetRadius = 75;
    private double planetX = WINDOWSIZE.getHeight()/2;
    private double planetY = WINDOWSIZE.getWidth()/2;
    
    private PlayerPlanet player;
    Barrier barrier;
    Button pauseButton, resetButton;
    
    private Timeline timeline;

    public GUI(Stage mainStage) {
    	this.mainStage = mainStage;
    	this.root = new BorderPane();
    	this.scene = new Scene(root);
    	this.canvas = new Canvas();
    	this.mainStage.setScene(this.scene);
    	this.root.getChildren().add(canvas);
    	this.root.setPrefSize(WINDOWSIZE.getWidth(), WINDOWSIZE.getHeight());
    	this.root.setStyle("-fx-background-color:#08121c");
    	this.stars = new ImageView(new Image("https://i.imgur.com/28YKGjT.png"));
    	this.stars.setOpacity(0.7);
    	this.root.getChildren().add(stars);

    	this.scoreValue = new Label("0");
    	this.life1 = new ImageView(new Image("https://i.imgur.com/FzTRe09.png"));
    	this.life2 = new ImageView(new Image("https://i.imgur.com/FzTRe09.png"));
    	this.life3 = new ImageView(new Image("https://i.imgur.com/FzTRe09.png"));
    	this.life1.setScaleX(0.8);
    	this.life1.setScaleY(0.8);
    	this.life2.setScaleX(0.8);
    	this.life2.setScaleY(0.8);
    	this.life3.setScaleX(0.8);
    	this.life3.setScaleY(0.8);
    	this.pauseImage = new ImageView(new Image("https://i.imgur.com/YyHnk0H.png"));
    	this.resetImage = new ImageView(new Image("https://i.imgur.com/EoWQhfs.png"));
    	this.pauseButton = new Button();
    	this.pauseButton.setGraphic(pauseImage);
    	this.pauseButton.setScaleX(0.75);
    	this.pauseButton.setScaleY(0.75);
    	this.resetButton = new Button();
    	this.resetButton.setGraphic(resetImage);
    	this.pauseButton.setScaleX(0.75);
    	this.pauseButton.setScaleY(0.75);
    	this.pauseButton.setStyle("-fx-background-color:#08121c");
    	this.resetButton.setStyle("-fx-background-color:#08121c");
    	
    	this.planet = new ImageView(new Image("https://i.imgur.com/jgcrrYv.png"));
    	this.planet.setFitHeight(planetRadius*2);
    	this.planet.setFitWidth(planetRadius*2);
    	this.planet.setX(planetX - 75);
    	this.planet.setY(planetY - 75);
    	this.root.getChildren().add(planet);
    	
    	this.barrier = new Barrier(root);
    	
    	drawTopHUD();
    	
    	//draws the barriers where mouse is clicked
    	root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
    		this.barrier.moveBarrier((int)event.getX(), (int)event.getY());
    		}
    	);
    
    
	this.timeline = new Timeline();	
	timeline.setCycleCount(Animation.INDEFINITE);
	//timeline.play();

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


	public Timeline getTimeline() {
		return this.timeline;
	}
	
	public void resetGui() {
		this.root.getChildren().clear();
		this.drawTopHUD();
		this.setLivesDisplay(3);
		this.setScoreText("0");
		this.barrier = new Barrier(root);
		this.root.getChildren().add(stars);
		this.root.getChildren().add(planet);
		timeline.playFromStart();
	}

	public void removeCircle(Circle circle) {
		this.root.getChildren().remove(circle);
	}
	
	public void addCircle(Circle circle) {
		this.root.getChildren().add(circle);
	}
}