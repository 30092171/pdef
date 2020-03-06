package fxapplication;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.geometry.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Game extends Application {
	
	//Root Components and Formatting Elements
	private static Dimension2D SIZE = new Dimension2D(720, 720);
	private static Canvas CANVAS = new Canvas(SIZE.getWidth(), SIZE.getHeight());
	private static BorderPane root = new BorderPane();
	private static Scene scene = new Scene(root);
	
	//Screen Elements
	private static Label scoreValue = new Label("0");
	private static int LIFESIZE = 35;
	private static Rectangle life1 = new Rectangle(LIFESIZE,LIFESIZE);
	private static Rectangle life2 = new Rectangle(LIFESIZE,LIFESIZE);
	private static Rectangle life3 = new Rectangle(LIFESIZE,LIFESIZE);
	private static Button pauseButton = new Button("Pause");
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Canvas getMainCanvas() {
		return Game.CANVAS;
	}
	
	public static Dimension2D getWindowSize() {
		return Game.SIZE;
	}
	
	private static void initWindow(Stage mainStage) {
		mainStage.setTitle("Planet Defenders");
		root.getChildren().add(CANVAS);
		root.setPrefSize(720, 720);
		mainStage.setScene(scene);
	}
	
	public static void drawTopHUD() {
		
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
	
	public static void drawPlanet() {
		GraphicsContext gc = CANVAS.getGraphicsContext2D();
		int radius = 65;
		//**topleft x,y, width height
		gc.fillOval(SIZE.getWidth()/2 - radius, SIZE.getHeight()/2 - radius, radius * 2, radius * 2);
	}
	
	public void start(Stage mainStage) throws Exception {
		initWindow(mainStage);
		drawTopHUD();
		drawPlanet();
		mainStage.show();
	}
	
	public void setScoreText(String aScore) {
		scoreValue.setText(aScore);
	}
	
	public void setLivesDisplay(int lifeCount) {
		if(lifeCount >= 3) {
			life1.setOpacity(1.0);
			life2.setOpacity(1.0);
			life3.setOpacity(1.0);
		}
		else if(lifeCount == 2) {
			life1.setOpacity(1.0);
			life2.setOpacity(1.0);
			life3.setOpacity(.35);
		}
		else if(lifeCount == 1) {
			life1.setOpacity(1.0);
			life2.setOpacity(0.35);
			life3.setOpacity(.35);
		}
		else if(lifeCount <= 0) {
			life1.setOpacity(0.35);
			life2.setOpacity(0.35);
			life3.setOpacity(.35);
		}
	}

}

