package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GestionCompteController {

	    @FXML
	    private TableColumn<CompteEpargneBase, Date> datedernierversement;

	    @FXML
	    private TableColumn<CompteEpargneBase, Double> montantcumule;


    @FXML
    private TableColumn<CompteSalarierBase, Date> datecreation;

    @FXML
    private TableColumn<CompteVIPBase, Date> datecreation1;

    @FXML
    private TableColumn<CompteEpargneBase, Date> datecreation2;

    @FXML
    private TableColumn<CompteSalarierBase, String> rib;

    @FXML
    private TableColumn<CompteVIPBase, String> rib1;

    @FXML
    private TableColumn<CompteEpargneBase, String> rib2;

    @FXML
    private TableColumn<CompteSalarierBase, String> solde;

    @FXML
    private TableColumn<CompteVIPBase, String> solde1;

    @FXML
    private TableColumn<CompteEpargneBase, String> solde2;

    @FXML
    private TableView<CompteSalarierBase> table;

    @FXML
    private TableView<CompteVIPBase> table1;
    @FXML
    private Button retourner;
    @FXML
    private TableView<CompteEpargneBase> table2;
    public ObservableList<CompteSalarierBase> datacs=FXCollections.observableArrayList();
    public ObservableList<CompteVIPBase> datacv=FXCollections.observableArrayList();
    public ObservableList<CompteEpargneBase> datace=FXCollections.observableArrayList();
    String types="Salarier";
    @FXML
    void onshowCSClick(ActionEvent event) {
    	try {
    		Connection con= dbConnection.connect();
    		String sqlco="SELECT * FROM compte WHERE typecompte='" + types +"'";
    		PreparedStatement statco = con.prepareStatement(sqlco);
    		ResultSet resco= statco.executeQuery();
    		        while(resco.next()) {
    		        	if(Double.parseDouble(resco.getString(4))<0) {
    		        		Alert alerte = new Alert(AlertType.NONE," Solde négatif !",ButtonType.OK);
                            alerte.show();
    		        	}
    		        	else {
    		        	 
    		        		datacs.add(new CompteSalarierBase(resco.getString(2),resco.getString(4),resco.getDate(6)));
    		        } 
    		        	con.close();}
    			}catch(Exception e) {
    	    		e.getStackTrace();	
    	    	}
    	rib.setCellValueFactory(new PropertyValueFactory<CompteSalarierBase,String>("rib"));
    	solde.setCellValueFactory(new PropertyValueFactory<CompteSalarierBase,String>("solde"));
    	datecreation.setCellValueFactory(new PropertyValueFactory<CompteSalarierBase,Date>("datecreation"));
    	table.setItems(datacs);
    		
    		
    }
    String typev="VIP";
    @FXML
    void onshowCVClick(ActionEvent event) {
    	try {
    		Connection con= dbConnection.connect();
    		String sqlco="SELECT * FROM compte WHERE typecompte='" + typev +"'";
    		PreparedStatement statco = con.prepareStatement(sqlco);
    		ResultSet resco= statco.executeQuery();
    		        while(resco.next()) {
    		        		datacv.add(new CompteVIPBase(resco.getString(2),resco.getString(4),resco.getDate(6)));
    		        }
    			}catch(Exception e) {
    	    		e.getStackTrace();	
    	    	}
    	rib1.setCellValueFactory(new PropertyValueFactory<CompteVIPBase,String>("rib"));
    	solde1.setCellValueFactory(new PropertyValueFactory<CompteVIPBase,String>("solde"));
    	datecreation1.setCellValueFactory(new PropertyValueFactory<CompteVIPBase,Date>("datecreation"));
    	table1.setItems(datacv);
    		
    		
    }
    
    String typee="Epargne";
    @FXML
    void onshowCEClick(ActionEvent event) {
    	try {
    		Connection con= dbConnection.connect();
    		String sqlco="SELECT * FROM compte WHERE typecompte='" + typee +"'";
    		PreparedStatement statco = con.prepareStatement(sqlco);
    		ResultSet resco= statco.executeQuery();
    		while(resco.next()) {
    		        		datace.add(new CompteEpargneBase(resco.getString(2),resco.getString(4),resco.getDate(6),resco.getDate(7),resco.getDouble(8)));
    		        }
    			}catch(Exception e) {
    	    		e.getStackTrace();	
    	    	}
    	rib2.setCellValueFactory(new PropertyValueFactory<CompteEpargneBase,String>("rib"));
    	solde2.setCellValueFactory(new PropertyValueFactory<CompteEpargneBase,String>("solde"));
    	datecreation2.setCellValueFactory(new PropertyValueFactory<CompteEpargneBase,Date>("datecreation"));
    	datedernierversement.setCellValueFactory(new PropertyValueFactory<CompteEpargneBase,Date>("date_dernier_versement"));
    	 montantcumule.setCellValueFactory(new PropertyValueFactory<CompteEpargneBase,Double>("montant_cumule"));
    	table2.setItems(datace);
    	}
    @FXML
    void onReturnClick(ActionEvent event) throws IOException {
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
    void OnDeleteClick(ActionEvent event) throws IOException {
    	FXMLLoader loader= new FXMLLoader(getClass().getResource("Supprimer Compte.fxml"));
		Parent root=(Parent)loader.load();
		Stage operation=new Stage();
		SupprimerCompteController controller= loader.getController();
		Scene scene=new Scene(root);
		operation.setScene(scene);
		operation.show();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }
  
}