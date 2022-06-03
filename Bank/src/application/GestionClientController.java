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

public class GestionClientController {
	@FXML
    private Button suppression;
	 @FXML
	    private Button ajout;

    @FXML
    private TableColumn<ClientVIP, String> chiffreAffaires;

    @FXML
    private TableColumn<ClientSalarier, String> cin;

    @FXML
    private TableColumn<ClientVIP, String> cin1;

    @FXML
    private TableColumn<ClientVIP, String> nbEmployes;

    @FXML
    private TableColumn<ClientSalarier, String> nom;

    @FXML
    private TableColumn<ClientVIP, String> nom1;
    @FXML
    private Button retourner;

     @FXML
    private TableColumn<ClientVIP, String> nomEntreprise;

    @FXML
    private TableColumn<ClientSalarier, String> prenom;

    @FXML
    private TableColumn<ClientSalarier, String> salaire;

    @FXML
    private TableView<ClientSalarier> table;

    @FXML
    private TableView<ClientVIP> table1;
    public ObservableList<ClientSalarier> datas=FXCollections.observableArrayList();
    public ObservableList<ClientVIP> datav =FXCollections.observableArrayList();
    String types="Salarier";
    @FXML
    void onShowSalarierClick(ActionEvent event) {
    	try {
    		Connection con= dbConnection.connect();
    		String sql="SELECT * FROM client WHERE typeclient='" + types +"'";
    		PreparedStatement stat = con.prepareStatement(sql);
    		ResultSet res= stat.executeQuery();
    		while(res.next()) {
    			datas.add(new ClientSalarier(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)));
    		}
    	}catch(Exception e) {
    		e.getStackTrace();	
    	}
    	cin.setCellValueFactory(new PropertyValueFactory<ClientSalarier,String>("cin"));
    	nom.setCellValueFactory(new PropertyValueFactory<ClientSalarier,String>("nom"));
    	prenom.setCellValueFactory(new PropertyValueFactory<ClientSalarier,String>("prenom"));
    	salaire.setCellValueFactory(new PropertyValueFactory<ClientSalarier,String>("salaire"));
    	table.setItems(datas);

    }
    String typev="VIP";
    @FXML
    void onShowVIPClick(ActionEvent event) {
    	try {
    		Connection con= dbConnection.connect();
    		String sql="SELECT * FROM client WHERE typeclient='" + typev +"'";
    		PreparedStatement stat = con.prepareStatement(sql);
    		ResultSet res= stat.executeQuery();
    		while(res.next()) {
    			datav.add(new ClientVIP(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(6),res.getString(7),res.getString(8)));
    		}
    	}catch(Exception e) {
    		e.getStackTrace();	
    	}
    	cin1.setCellValueFactory(new PropertyValueFactory<ClientVIP,String>("cin"));
    	nom1.setCellValueFactory(new PropertyValueFactory<ClientVIP,String>("nom"));
    	nomEntreprise.setCellValueFactory(new PropertyValueFactory<ClientVIP,String>("nomEntreprise"));
    	chiffreAffaires.setCellValueFactory(new PropertyValueFactory<ClientVIP,String>("chiffreAffaires"));
    	nbEmployes.setCellValueFactory(new PropertyValueFactory<ClientVIP,String>("nbEmployes"));
    	table1.setItems(datav);

    }
    @FXML
    void onAjoutClick(ActionEvent event) throws IOException {
    	FXMLLoader loader= new FXMLLoader(getClass().getResource("Nouveau Client.fxml"));
		Parent root=(Parent)loader.load();
		Stage operation=new Stage();
		NouveauClientController controller= loader.getController();
		Scene scene=new Scene(root);
		operation.setScene(scene);
		operation.show();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }
    @FXML
    void onSupprimerClick(ActionEvent event) throws IOException {
    	FXMLLoader loader= new FXMLLoader(getClass().getResource("Supprimer Client.fxml"));
		Parent root=(Parent)loader.load();
		Stage operation=new Stage();
		SupprimerClientController controller= loader.getController();
		Scene scene=new Scene(root);
		operation.setScene(scene);
		operation.show();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }
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
    
}
