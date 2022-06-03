package application;
import java.sql.Connection;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
public class SupprimerClientController {
    @FXML
    private TextField textc;
    @FXML
    void onSupprimerClick(ActionEvent event) {
    	String client=textc.getText();
    	if(client.isEmpty()==false) {
    		try {
         		Connection con= dbConnection.connect();
         		String sql="DELETE FROM client WHERE cin ='"+client+"'";
         		Statement stat = con.createStatement();
         		stat.executeUpdate(sql);
	        			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	        			FXMLLoader loader= new FXMLLoader(getClass().getResource("Gestion des Clients.fxml"));
	        			Parent root=(Parent)loader.load();
	        			Stage operation=new Stage();
	        			GestionClientController controller= loader.getController();
	        			Scene scene=new Scene(root);
	        			operation.setScene(scene);
	        			operation.show();
	        			Alert alerte = new Alert(AlertType.NONE,"Client est supprimé avec succés !",ButtonType.OK);
	                    alerte.show();
			}catch(Exception e) {
 			e.getStackTrace();
 		} 
    		
    	}

    }

}

