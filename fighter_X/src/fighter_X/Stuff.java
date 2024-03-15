package fighter_X;

//for testing code, can ignore 

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;
import java.util.random.*;

public class Stuff extends Application{
	
	public static int i=0;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		BorderPane bp = new BorderPane();
		Circle c = new Circle(50);
		c.setFill(Color.WHITE);
		c.setStroke(Color.BLACK);
		
		c.setOnMouseClicked(e->{
			primaryStage.setTitle(String.format("%d", i));
			i++;
		});
		bp.setCenter(c);
		
		
		HBox hb = new HBox();
		
		Button enlarge = new Button("+");
		
		enlarge.setOnAction(e ->{
			c.setRadius(c.getRadius()+2);
		});
		
		
		final Button rng = new Button("rng");
		Random random = new Random();
		rng.setOnAction(e->{
			c.setFill(new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1));
		});
		
		hb.getChildren().addAll(enlarge, rng);
		
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(10);
		bp.setBottom(hb);
	
		primaryStage.setScene(new Scene(bp, 250, 200));
		primaryStage.setTitle("b");
		primaryStage.show();
		primaryStage.setResizable(false);
		
		
	}
	

	
}
