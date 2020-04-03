/*
 * 
 */
package fxapplication;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pdef.PlayerPlanet;

/**
 * The Class GUI.
 */
public class GUI {
    //this class provides all the necessary elements to update and control the gui.

    /** The windowsize. */
    //Root Components and Formatting Elements
    private static Dimension2D WINDOWSIZE = new Dimension2D(720, 720);
    
    /** The origin. */
    private static Point2D ORIGIN = new Point2D(360, 360);
    
    /**
     * Gets the origin.
     *
     * @return the origin
     */
    public static Point2D getOrigin() {
    	return new Point2D(ORIGIN.getX(), ORIGIN.getY());
    }

    /** The main stage. */
    private Stage mainStage;
    
    /** The root. */
    private BorderPane root;
    
    /** The scene. */
    private Scene scene;
    
    /** The canvas. */
    private Canvas canvas;

    /** The score value. */
    //Screen Elements
    Label scoreValue;
    
    /** The life 3. */
    private ImageView life1, life2, life3;
    
    /** The stars. */
    private ImageView stars;
    
    /** The pause image. */
    private ImageView pauseImage;
    
    /** The reset image. */
    private ImageView resetImage;
    
    /** The planet. */
    private ImageView planet;
    
    /** The planet radius. */
    private int planetRadius = 75;
    
    /** The planet X. */
    private double planetX = WINDOWSIZE.getHeight()/2;
    
    /** The planet Y. */
    private double planetY = WINDOWSIZE.getWidth()/2;

    
    /** The player. */
    private PlayerPlanet player;
    
    /** The barrier. */
    Barrier barrier;
    
    /** The reset button. */
    Button pauseButton, resetButton;

    
    /** The timeline. */
    private Timeline timeline;

    /**
     * Instantiates a new gui.
     *
     * @param mainStage the main stage
     */
    public GUI(Stage mainStage) {
    	this.mainStage = mainStage;
    	this.mainStage.setResizable(false);
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

    	this.scoreValue.setTextFill(Color.WHITE);
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
    	this.resetButton.setScaleX(0.75);
    	this.resetButton.setScaleY(0.75);
    	this.resetButton.setGraphic(resetImage);
    	this.pauseButton.setStyle("-fx-background-color:#08121c");
    	this.resetButton.setStyle("-fx-background-color:#08121c");
    	
    	this.planet = new ImageView(new Image("https://i.imgur.com/jgcrrYv.png"));
    	this.planet.setFitHeight(planetRadius*2);
    	this.planet.setFitWidth(planetRadius*2);
    	this.planet.setX(planetX-75);
    	this.planet.setY(planetY-75);
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
    
    /**
     * Sets the lives display.
     *
     * @param lifeCount The new lives display
     */
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

    /**
     * Sets the score text.
     *
     * @param aScore the new score text
     */
    public void setScoreText(String aScore) {
        scoreValue.setText(aScore);
    }

    /**
     * Draw top HUD.
     */
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
        //VBox planetBox = new VBox();
        //planetBox.setAlignment(Pos.CENTER);
        //planetBox.setPadding(new Insets(-75,0,0,-75));
        //root.setCenter(planetBox);

        //Populate Lives HBox
        livesBox.getChildren().add(life1);
        livesBox.getChildren().add(life2);
        livesBox.getChildren().add(life3);

        //Populate Score VBox
        Label scoreText = new Label("SCORE");
        //Header Text
        scoreText.setFont(new Font("Arial", 14));
        scoreText.setTextFill(Color.WHITE);
        scoreBox.getChildren().add(scoreText);
        //Score Value
        scoreValue.setFont(new Font("Arial", 48));
        scoreBox.getChildren().add(scoreValue);

        //Populate Controls HBox
        controlsBox.getChildren().add(pauseButton);
        controlsBox.getChildren().add(resetButton);

        
        //Populate Planet VBox
        //planetBox.getChildren().add(planet);

    }
    
    /**
     * Draw game over.
     */
    public void drawGameOver() {
    	this.timeline.stop();
    	//Define VBox
    	VBox gameOverBox = new VBox();
    	gameOverBox.setPadding(new Insets(-75, 0, 0, -75));
    	gameOverBox.setAlignment(Pos.CENTER);
    	root.setCenter(gameOverBox);
    	this.planet.setOpacity(0);
    	
    	//Populate GameOver VBox
    	Rectangle gameOverBorder1 = new Rectangle(350,8);
    	gameOverBorder1.setFill(Color.WHITE);
    	gameOverBox.getChildren().add(gameOverBorder1);
    	Label gameOverText = new Label("GAME OVER");

    	gameOverText.setMinSize(360, 90);
    	gameOverText.setFont(new Font("Arial", 59));
    	gameOverText.setTextFill(Color.WHITE);

    	gameOverBox.getChildren().add(gameOverText);
    	Rectangle gameOverBorder2 = new Rectangle(350,8);
    	gameOverBorder2.setFill(Color.WHITE);
    	gameOverBox.getChildren().add(gameOverBorder2);
    }

	/**
	 * Gets the timeline.
	 *
	 * @return the timeline
	 */
	public Timeline getTimeline() {
		return this.timeline;
	}

	
	/**
	 * Resets the GUI after the reset button is pressed.
	 */
	public void resetGui() {
		this.root.getChildren().clear();
		this.drawTopHUD();
		this.setLivesDisplay(3);
		this.setScoreText("0");
		this.planet.setOpacity(1);
		this.barrier = new Barrier(root);
		this.root.getChildren().add(stars);
		this.root.getChildren().add(planet);
		timeline.playFromStart();
	}

	/**
	 * Removes circle objects (i.e. projectiles) from the GUI
	 *
	 * @param circle The circle object to be removed
	 */
	public void removeCircle(Circle circle) {
		Platform.runLater(()->{
			this.root.getChildren().remove(circle);
		});
	}
	
	/**
	 * Adds the circle object to the GUI.
	 *
	 * @param circle The circle object to be added
	 */
	public void addCircle(Circle circle) {
		Platform.runLater(()->{
			this.root.getChildren().add(circle);
		});
	}

}