package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GestionUtilisateurController {

    @FXML
    private TableColumn<Utilisateur, String> motdepasse;

    @FXML
    private TableColumn<Utilisateur, String> prenom;

    @FXML
    private Button retourner;

    @FXML
    private TableView<Utilisateur> table;
    public ObservableList<Utilisateur> data=FXCollections.observableArrayList();

    @FXML
    void OnReturnClick(ActionEvent event) throws IOException {
    	FXMLLoader loader= new FXMLLoader(getClass().getResource("Administration.fxml"));
		Parent root=(Parent)loader.load();
		Stage operation=new Stage();
		AdministrationController controller= loader.getController();
		Scene scene=new Scene(root);
		operation.setScene(scene);
		operation.show();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();	

    }

    @FXML
    void onShowUtilisateurClick(ActionEvent event) {
    	try {
    		Connection con= dbConnection.connect();
    		String sql="SELECT * FROM utilisateur";
    		PreparedStatement stat = con.prepareStatement(sql);
    		ResultSet res= stat.executeQuery();
    		while(res.next()) {
    			data.add(new Utilisateur(res.getString(1),res.getString(2)));
    		}
    	}catch(Exception e) {
    		e.getStackTrace();	
    	}
    	prenom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
    	motdepasse.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("motdepasse"));
    	table.setItems(data);
    	

    }

}
