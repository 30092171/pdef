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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * This class contains all the necessary elements to update and control the GUI.
 */
public class GUI {
	//Root Components and Formatting Elements
    /** The window size. */
    private static Dimension2D WINDOWSIZE = new Dimension2D(720, 720);
    
    /** The origin. */
    private static Point2D ORIGIN = new Point2D(360, 360);
    
    /**
     * Gets the origin.
     *
     * @return The origin
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

    //Screen Elements
    /** The score value. */
    Label scoreValue;
    
    /** The 3 life images. */
    private ImageView life1, life2, life3;
    
    /** The stars background image. */
    private ImageView stars;
    
    /** The pause image. */
    private ImageView pauseImage;
    
    /** The reset image. */
    private ImageView resetImage;
    
    /** The planet image. */
    private ImageView planet;
    
    /** The planet radius. */
    private int planetRadius = 75;
    
    /** The planet X. */
    private double planetX = WINDOWSIZE.getHeight()/2;
    
    /** The planet Y. */
    private double planetY = WINDOWSIZE.getWidth()/2;
    
    /** The menu border. */
    private Rectangle menuBorder;
    
    /** The menu logo. */
    private ImageView menuLogo;
    
    /** The menu instructions. */
    private Label menuInstructions;
    
    /** The menu V box. */
    private VBox menuVBox;
    
    /** The pause V box. */
    private VBox pauseVBox;
    
    /** The menu play button. */
    Button menuPlayButton;
    
    /** The menu play button image. */
    private ImageView menuPlayImage;
    
    /** The menu high score. */
    private Label menuHighScore;
    
    /** The menu stars background. */
    private ImageView menuStars;
    
    /** The barrier. */
    Barrier barrier;
    
    /** The explosion image */
    private ImageView explosion;
    
    /** The pause and reset buttons. */
    Button pauseButton, resetButton;
    
    /** The timeline. */
    private Timeline timeline;

    /**
     * Constructs and initializes a new GUI.
     *
     * @param mainStage the main stage
     */
    public GUI(Stage mainStage) {
    	this.mainStage = mainStage;
    	this.mainStage.setResizable(false); // Disables screen resizing
    	this.root = new BorderPane();
    	this.scene = new Scene(root);
    	this.canvas = new Canvas();
    	this.mainStage.setScene(this.scene);
    	this.root.getChildren().add(canvas);
    	this.root.setPrefSize(WINDOWSIZE.getWidth(), WINDOWSIZE.getHeight());
    	this.root.setStyle("-fx-background-color:#08121c");
    	
    	//Star background
    	this.stars = new ImageView(new Image("https://i.imgur.com/28YKGjT.png"));
    	this.stars.setOpacity(0.7);
    	this.root.getChildren().add(stars);

    	//Score Label
    	this.scoreValue = new Label("0");
    	this.scoreValue.setTextFill(Color.WHITE);
    	
    	//Lives
    	this.life1 = new ImageView(new Image("https://i.imgur.com/FzTRe09.png"));
    	this.life2 = new ImageView(new Image("https://i.imgur.com/FzTRe09.png"));
    	this.life3 = new ImageView(new Image("https://i.imgur.com/FzTRe09.png"));
    	this.life1.setScaleX(0.8);
    	this.life1.setScaleY(0.8);
    	this.life2.setScaleX(0.8);
    	this.life2.setScaleY(0.8);
    	this.life3.setScaleX(0.8);
    	this.life3.setScaleY(0.8);
    	
    	//Buttons
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
    	
    	//Planet
    	this.planet = new ImageView(new Image("https://i.imgur.com/jgcrrYv.png"));
    	this.planet.setFitHeight(planetRadius*2);
    	this.planet.setFitWidth(planetRadius*2);
    	this.planet.setX(planetX-72);
    	this.planet.setY(planetY-72);
    	this.root.getChildren().add(planet);
    	
    	//Game over explosion image
    	this.explosion = new ImageView(new Image("https://i.imgur.com/WirxLxw.png"));
    	this.explosion.setScaleX(0.5);
    	this.explosion.setScaleY(0.5);
    	this.explosion.setX(5);
    	this.explosion.setY(20);
    	
    	//Start menu
    	this.menuBorder = new Rectangle();
    	this.menuLogo = new ImageView(new Image("https://i.imgur.com/jVapTTJ.png"));
    	this.menuInstructions = new Label("Protect your planet against a barrage of \n asteroids for as long as possible! \n \n -Press LMB to place an asteroid barrier \n -Block asteroids to increase your score \n -If 3 asteroids make impact it's game over! \n \n");
    	this.menuVBox = new VBox();
    	this.pauseVBox = new VBox();
    	this.menuPlayButton = new Button();
    	this.menuPlayImage = new ImageView("https://i.imgur.com/PUiEP0c.png");
    	this.menuHighScore = new Label("High Score: 0");
    	this.menuStars = new ImageView(new Image("https://i.imgur.com/28YKGjT.png"));
    	this.menuStars.setOpacity(0.7);
    	
    	//Barrier
    	this.barrier = new Barrier(root);
    	
    	//Draws the barriers where mouse is clicked
    	root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
    		if (timeline.getRate() > 0.0) {
    			this.barrier.moveBarrier((int)event.getX(), (int)event.getY());
    		}
    		}
    	);
    
    
	this.timeline = new Timeline();	

	timeline.setCycleCount(Animation.INDEFINITE);
	//timeline.play();
	
	drawTopHUD();
	drawMenuHUD();


    }
    
    /**
     * Sets the lives display.
     *
     * @param lifeCount The given lifeCount
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
     * @param aScore The given score
     */
    public void setScoreText(String aScore) {
        scoreValue.setText(aScore);
    }
    
    /**
     * Sets the highscore text.
     *
     * @param aScore The given score
     */
    public void setHighScoreText(String aScore) {
        this.menuHighScore.setText(aScore);
    }

    /**
     * Draws the main menu.
     */
    public void drawMenuHUD() {
    	menuBorder.setWidth(3000);
    	menuBorder.setHeight(3000);
    	menuBorder.setFill(Color.rgb(8, 18, 28));
    	root.getChildren().add(menuBorder);
    	root.getChildren().add(menuStars);
    	
    	root.setCenter(menuVBox);
    	menuVBox.setPadding(new Insets(-150, 0, 0, -65));
    	menuVBox.setAlignment(Pos.CENTER);
    	menuVBox.setSpacing(25);
    	
    	menuVBox.getChildren().add(menuLogo);
        
    	menuInstructions.setTextAlignment(TextAlignment.CENTER);
        menuInstructions.setFont(new Font("Arial", 18));
        menuInstructions.setTextFill(Color.WHITE);
        menuVBox.getChildren().add(menuInstructions);
        
        menuHighScore.setTextAlignment(TextAlignment.CENTER);
        menuHighScore.setFont(new Font("Arial", 22));
        menuHighScore.setTextFill(Color.WHITE);
        
        menuVBox.getChildren().add(menuHighScore);
        
        menuPlayButton.setGraphic(menuPlayImage);
        menuPlayButton.setStyle("-fx-background-color: transparent");
        menuVBox.getChildren().add(menuPlayButton);
        //menuPlayButton.getChildrenUnmodifiable().add(playText);
        
        timeline.setRate(0.0);
        timeline.stop();
    }
    
    /**
     * Draws the top HUD.
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

    }
    
    /**
     * Draws the pause screen.
     */
    public void drawPause() {
    	this.pauseVBox.setPadding(new Insets(-75, 0, 0, -75));
    	this.pauseVBox.setAlignment(Pos.CENTER);
    	root.setCenter(this.pauseVBox);
    	
    	//Populate GameOver VBox
    	Rectangle pauseBorder1 = new Rectangle(200,8);
    	pauseBorder1.setFill(Color.WHITE);
    	this.pauseVBox.getChildren().add(pauseBorder1);
    	Label pauseText = new Label("PAUSE");

    	pauseText.setMinSize(160, 90);
    	pauseText.setFont(new Font("Arial", 59));
    	pauseText.setTextFill(Color.ORANGERED);

    	this.pauseVBox.getChildren().add(pauseText);
    	Rectangle pauseBorder2 = new Rectangle(200,8);
    	pauseBorder2.setFill(Color.WHITE);
    	this.pauseVBox.getChildren().add(pauseBorder2);
    }
    
    /**
     * Draws the play screen.
     */
    public void drawPlay() {
		this.pauseVBox.getChildren().clear();
    }
    
    /**
     * Draws the game over screen.
     */
    public void drawGameOver() {
    	this.timeline.setRate(0.0);
    	this.timeline.stop();
    	//Define VBox
    	VBox gameOverBox = new VBox();
    	gameOverBox.setPadding(new Insets(-75, 0, 0, -75));
    	gameOverBox.setAlignment(Pos.CENTER);
    	root.getChildren().add(explosion);
    	root.setCenter(gameOverBox);
    	this.planet.setOpacity(0);
    	
    	//Populate GameOver VBox
    	Rectangle gameOverBorder1 = new Rectangle(350,8);
    	gameOverBorder1.setFill(Color.WHITE);
    	gameOverBox.getChildren().add(gameOverBorder1);
    	Label gameOverText = new Label("GAME OVER");

    	gameOverText.setMinSize(360, 90);
    	gameOverText.setFont(new Font("Arial", 59));
    	gameOverText.setTextFill(Color.RED);

    	gameOverBox.getChildren().add(gameOverText);
    	Rectangle gameOverBorder2 = new Rectangle(350,8);
    	gameOverBorder2.setFill(Color.WHITE);
    	gameOverBox.getChildren().add(gameOverBorder2);
    }

	/**
	 * Gets the timeline object.
	 *
	 * @return The timeline object
	 */
	public Timeline getTimeline() {
		return this.timeline;
	}

	
	/**
	 * Resets the GUI to its initial state.
	 */
	public void resetGui() {
		this.root.getChildren().clear();
		this.pauseVBox.getChildren().clear();
		this.drawTopHUD();
		this.setLivesDisplay(3);
		this.setScoreText("0");
		this.planet.setOpacity(1);
		this.barrier = new Barrier(root);
		this.root.getChildren().add(stars);
		this.root.getChildren().add(planet);
		timeline.setRate(1.0);
		timeline.playFromStart();
	}

	/**
	 * Removes circle object (i.e. projectile) from the GUI
	 *
	 * @param circle The circle object to be removed
	 */
	public void removeCircle(Circle circle) {
		Platform.runLater(()->{
			this.root.getChildren().remove(circle);
		});
	}
	
	/**
	 * Adds the JavaFx screen circle object to the GUI.
	 *
	 * @param circle The JavaFX screen circle object to be added
	 */
	public void addCircle(Circle circle) {
		Platform.runLater(()->{
			this.root.getChildren().add(circle);
		});
	}
}