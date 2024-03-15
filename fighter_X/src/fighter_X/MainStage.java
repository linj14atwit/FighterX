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
		
		
		bp.setMaxSize(1280, 720);
		primaryStage.setScene(new Scene(bp, bp.getMaxWidth(), bp.getMaxHeight()));
		primaryStage.setTitle("FighterX");
		primaryStage.show();
		primaryStage.setResizable(false);
		
		
	}

}
