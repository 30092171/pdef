package fxapplication;

import java.awt.Point;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Game extends Application {
	
	private static Canvas CANVAS;
	private static Dimension2D SIZE;
	
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
		SIZE = new Dimension2D(800, 800);
		CANVAS = new Canvas(SIZE.getWidth(), SIZE.getHeight());
		Group root = new Group();
		Scene scene = new Scene(root);
		root.getChildren().add(CANVAS);
		mainStage.setScene(scene);
	}
	
	public void start(Stage mainStage) throws Exception {
		initWindow(mainStage);
		testDraw();
		mainStage.show();
	}
	
	public static void testDraw() {
		GraphicsContext gc = CANVAS.getGraphicsContext2D();
		
		int radius = 100;
		//**topleft x,y, width height
		gc.strokeOval(SIZE.getWidth()/2 - radius, SIZE.getHeight()/2 - radius, radius * 2, radius * 2);
	}
}
