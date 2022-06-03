package application;
import java.io.IOException;

import javax.print.DocFlavor.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	  public void start(Stage primaryStage) throws Exception {
        final var loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
        final var root = loader.<Parent>load();
        final var scene = new Scene(root);
        primaryStage.setTitle("GreenBanque");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
