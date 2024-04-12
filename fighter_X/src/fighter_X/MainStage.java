package fighter_X;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
	private static Stage stage;
	public static final long FRAME_RATE = 16666666;//value of 1 single frame
	
	private Rectangle healthbar2;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		stage = primaryStage;
		
		Pane bp = new Pane();
//		Canvas bp = new Canvas();
		
		
		bp.setMaxSize(1280, 720);
		
		Rectangle healthbg1 = new Rectangle(0, 0, 500, 50);
		healthbg1.setFill(new Color(0.1, 0.1, 0.3, 0.9));
		Rectangle healthbg2 = new Rectangle(1280-500, 0, 500, 50);
		healthbg2.setFill(new Color(0.1, 0.1, 0.3, 0.9));
		Rectangle healthbar1 = new Rectangle(5, 3, 490, 44);
		healthbar1.setFill(new Color(0.95, 0.4, 0.1, 0.9));
		 healthbar2 = new Rectangle(1280-495, 3, 490, 44);
		healthbar2.setFill(new Color(0.95, 0.4, 0.1, 0.9));
		bp.getChildren().add(healthbg1);
		bp.getChildren().add(healthbg2);
		bp.getChildren().add(healthbar1);
		bp.getChildren().add(healthbar2);
		
		player1 = new Character(bp, 60, 400, 60, 180);
		player2 = new Character(bp, 1040, 400, 60, 180);
		player1.addOpponent(player2);
		player2.addOpponent(player1);
		
		Rectangle ground = new Rectangle(0, player1.ground(), 1280, 4);
		ground.setFill(new Color(0.1, 0.1, 0.3, 0.9));
		
		bp.getChildren().add(player1);
		bp.getChildren().add(player2);
		bp.getChildren().add(ground);
		
		
		primaryStage.setScene(new Scene(bp, bp.getMaxWidth(), bp.getMaxHeight()));
		primaryStage.setTitle("FighterX");
		primaryStage.show();
		primaryStage.setResizable(false);
		
		player1.requestFocus();
		
		AnimationTimer animator = new AnimationTimer() {
			long startTime=0;
		    @Override
		    public void handle(long arg0) {
		        long currentTime = System.nanoTime();
		        if (FRAME_RATE <= (currentTime - startTime)) {
		            update(currentTime - startTime);
		            startTime = currentTime;
		        }
		    }
		};
		animator.start();
		
	}
	
	public void update(long deltaTime) {
		player1.update(deltaTime);
		player2.update(deltaTime);
		updateHealthBar(healthbar2);
		
	}
	
	public void updateHealthBar(Rectangle r) {
		r.setWidth(player2.getHP()/1000.0*490);
	}

}
