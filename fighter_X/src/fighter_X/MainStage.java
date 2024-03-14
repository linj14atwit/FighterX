package fighter_X;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class MainStage  extends Application{
	
	public static int i=0;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Pane bp = new Pane();
		
		Rectangle c = new Rectangle(60, 80);
		c.setX(10);
		c.setY(20);
		System.out.println(c.getX()+", "+ c.getY());
		bp.getChildren().add(c);
		bp.setMaxSize(1280, 720);
		primaryStage.setScene(new Scene(bp, bp.getMaxWidth(), bp.getMaxHeight()));
		primaryStage.setTitle("b");
		primaryStage.show();
		primaryStage.setResizable(false);
		
		
	}

}
