package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NouveauCompteController {

    @FXML
    private AnchorPane ScenePanee;

    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;

    @FXML
    private CheckBox epargne;

    @FXML
    private Button exitt;

    @FXML
    private TextField mc;

    @FXML
    private TextField na;

    @FXML
    private TextField nc;

    @FXML
    private TextField rib;

    @FXML
    private CheckBox salarier;

    @FXML
    private TextField solde;

    @FXML
    private CheckBox vip;
    public ObservableList<Agence> agences=FXCollections.observableArrayList();
    public ObservableList<Client> clients=FXCollections.observableArrayList();

    @FXML
    void onAddCompteClickk(ActionEvent event) {
    	String r = rib.getText();
    	String n = nc.getText();
    	String s = solde.getText();
    	String nagence = na.getText();
    	String date = date1.getValue().toString();
    	Date d1=Date.valueOf(date);
    	LocalDate d2 = date2.getValue();
    	String c = mc.getText();
    	String typecompte="Salarier";
    	String typecompte1="VIP";
    	String typecompte2="Epargne";
    	try {
    		Connection con= dbConnection.connect();
    		String sqla="SELECT * FROM agence";
    		String sqlc="SELECT * FROM client";
    		PreparedStatement stata = con.prepareStatement(sqla);
    		ResultSet resa= stata.executeQuery();
    		PreparedStatement statc = con.prepareStatement(sqlc);
    		ResultSet resc= statc.executeQuery();
    		while(resa.next() ) {
    	        agences.add(new Agence(resa.getString(1),resa.getString(2),resa.getString(3),resa.getString(4),
    	        		resa.getString(5),resa.getString(6)));
    		}
    		while(resc.next() ) {
    			clients.add(new Client(resc.getString(1),resc.getString(2),resc.getString(3),resc.getString(4)));
    		}
    		if(salarier.isSelected()) {
    			if(clients.get(2).getNom().equals(n) && agences.get(1).getNom().equals(nagence)) {
    				String sql="INSERT INTO compte(typecompte,rib,nomclient,solde,nomagence,datecreation,date_dernier_versement,montant_cumule) VALUES('" +typecompte+ "','" + r + "','" + n+ "','" + Double.parseDouble(s) + "','" + nagence + "','" +  d1 + "','" + d2.toString() +  "','" + Double.parseDouble(c) +"')";
             		Statement stat = con.createStatement();
             		stat.executeUpdate(sql);
             		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
             		Alert alerte = new Alert(AlertType.NONE,"Compte est ajouté avec succés !",ButtonType.OK);
                    alerte.show();
             		}
    			}
    		else if(vip.isSelected()) {
    			if(clients.get(3).getNom().equals(n) && agences.get(0).getNom().equals(nagence)) {
    				String sql="INSERT INTO compte(typecompte,rib,nomclient,solde,nomagence,datecreation,date_dernier_versement,montant_cumule) VALUES('" +typecompte1+ "','" + r + "','" + n+ "','" + Double.parseDouble(s) + "','" + nagence + "','" +  d1 + "','" + d2.toString() +  "','" + Double.parseDouble(c) +"')";
             		Statement stat = con.createStatement();
             		stat.executeUpdate(sql);
             		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
             		Alert alerte = new Alert(AlertType.NONE,"Compte est ajouté avec succés !",ButtonType.OK);
                    alerte.show();
             		}
    			}
    		else if(epargne.isSelected()) {
    			if(clients.get(3).getNom().equals(n) && agences.get(0).getNom().equals(nagence)) {
    				Alert alerte = new Alert(AlertType.NONE,"Un Client VIP ne peut pas créer un compte Epargne !",ButtonType.OK);
                    alerte.show();}
    			else {
    				if(clients.get(2).getNom().equals(n) && agences.get(1).getNom().equals(nagence)) {
    				String sql="INSERT INTO compte(typecompte,rib,nomclient,solde,nomagence,datecreation,date_dernier_versement,montant_cumule) VALUES('" +typecompte2+ "','" + r + "','" + n+ "','" + Double.parseDouble(s) + "','" + nagence + "','" +  d1 + "','" + d2.toString() +  "','" + Double.parseDouble(c) +"')";
             		Statement stat = con.createStatement();
             		stat.executeUpdate(sql);
             		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
             		Alert alerte = new Alert(AlertType.NONE,"Compte est ajouté avec succés !",ButtonType.OK);
                    alerte.show();
             		}}
             		}
    		}catch(Exception e) {
     			e.getStackTrace();
     		} 
    }
}
