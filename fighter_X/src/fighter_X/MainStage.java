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
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class MainStage  extends Application{
	
	private static Character player1, player2;
	public static int i=0;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Pane bp = new Pane();
		
		
		bp.setMaxSize(1280, 720);
		
		player1 = new Character(40, 400, 60, 180, 40);
		
		bp.getChildren().add(player1);
		
		
		
		
		primaryStage.setScene(new Scene(bp, bp.getMaxWidth(), bp.getMaxHeight()));
		primaryStage.setTitle("FighterX");
		primaryStage.show();
		primaryStage.setResizable(false);
		
		player1.requestFocus();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16)));
		AnimationTimer animator = new AnimationTimer() {
			long startTime=0;
		    @Override
		    public void handle(long arg0) {
		        long currentTime = System.nanoTime();
		        if (60 <= (currentTime - startTime)) {
		            update();
		            startTime = currentTime;
		        }
		    }
		};
		animator.start();
		
	}
	
	public void update() {
		player1.update();
	}

}
