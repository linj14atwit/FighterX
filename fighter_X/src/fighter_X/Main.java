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
		
		Parent root = FXMLLoader.load(getClass().getResource("Character select.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		

		
//		primaryStage.setFullScreen(true);
		primaryStage.show();
		primaryStage.setResizable(false);
		
	}
}
