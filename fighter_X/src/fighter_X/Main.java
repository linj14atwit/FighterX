package fighter_X;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {launch(args);}
	
	public static Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception{
//		primaryStage.setTitle("Hello World!");
//		this.stage = primaryStage;
		
		Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		
		final Button btn = new Button("Click Me!");
//		final StackPane root = new StackPane();
//		primaryStage.setScene(new Scene(root, 300, 250));
		
		primaryStage.setFullScreen(true);
		primaryStage.show();
		primaryStage.setResizable(false);
		
	}
}
