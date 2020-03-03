package fxapplication;

import java.awt.Point;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage mainStage) throws Exception{
		mainStage.setTitle("Planet Defenders");
		Point size =  new Point(1024, 1024);
		Canvas canvas = new Canvas(size.x, size.y);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Group root = new Group();
		Scene scene = new Scene(root);
		root.getChildren().add(canvas);
		mainStage.setScene(scene);
		
		int radius = 100;
		//**topleft x,y, width height
		gc.strokeOval(size.x/2 - radius, size.y/2 - radius, radius * 2, radius * 2);
		
		mainStage.show();
	}
}
