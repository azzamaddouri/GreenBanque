package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OperationsBancairesController {

    @FXML
    private TextField textc;

    @FXML
    private TextField textm;
    public ObservableList<CompteSalarier> datacs=FXCollections.observableArrayList();
    public ObservableList<CompteVIP> datacv=FXCollections.observableArrayList();
    public ObservableList<CompteEpargne> datace=FXCollections.observableArrayList();
    public ObservableList<Agence> agences=FXCollections.observableArrayList();
    public ObservableList<ClientSalarier> clients=FXCollections.observableArrayList();
    String types="Salarier";
    int res=0;
	int res1=0;
    @FXML
    void onRetraitClick(ActionEvent event) throws SQLException {
    	
    	String compte = textc.getText();
    	String  montant = textm.getText();
    	if(compte.isEmpty()==false && montant.isEmpty()==false ) {
    		try {
        		Connection con= dbConnection.connect();
        		String sqla="SELECT * FROM agence";
        		String sqlc="SELECT * FROM client";
        		String sqlco="SELECT * FROM compte WHERE rib='" + compte +"'";
        		PreparedStatement stata = con.prepareStatement(sqla);
        		ResultSet resa= stata.executeQuery();
        		PreparedStatement statc = con.prepareStatement(sqlc);
        		ResultSet resc= statc.executeQuery();
        		PreparedStatement statco = con.prepareStatement(sqlco);
        		ResultSet resco= statco.executeQuery();
        		while(resa.next() ) {
        	        agences.add(new Agence(resa.getString(1),resa.getString(2),resa.getString(3),resa.getString(4),
        	        		resa.getString(5),resa.getString(6)));
        		}
        		while(resc.next() ) {
        			clients.add(new ClientSalarier(resc.getString(1),resc.getString(2),resc.getString(3),resc.getString(4),resc.getString(5)));
        		}
        		        while(resco.next()) {
        		        	if((agences.get(0).getNom().equals(resco.getString(5)) && ( clients.get(0).getNom().equals(resco.getString(3))))){
        		        		datacs.add(new CompteSalarier(resco.getString(1),resco.getString(2),clients.get(0),resco.getString(4),agences.get(0),resco.getDate(6)));
        		        		if((datacs.get(0).getSolde()-Double.parseDouble(montant)) < -clients.get(0).getSalaire()*0.05) {
        		        			Alert alerte = new Alert(AlertType.NONE,"Vous avez depassé la somme de rouge",ButtonType.OK);
        		                    alerte.show();
        		        		}
        		        		else if((datacs.get(0).getSolde()-Double.parseDouble(montant))<0) {
            					datacs.get(0).retraitSalarier(Double.parseDouble(montant));
        		        		String sql="UPDATE compte SET solde ='"+datacs.get(0).getSolde()+"' WHERE rib = '" +compte+"'";
        		        		PreparedStatement stat = con.prepareStatement(sql);
        		        		 res= stat.executeUpdate();
        		        			String sql1="UPDATE agence SET debit_globale_agence ='"+datacs.get(0).getA().getDebit_globale_agence()+"' WHERE nom = '" +datacs.get(0).getA().getNom()+"'";
            		        		PreparedStatement stat1 = con.prepareStatement(sql1);
            		        		 res1= stat1.executeUpdate();
            		        		 Alert alerte = new Alert(AlertType.NONE," Vous avez entré dans la zone rouge !",ButtonType.OK);
            		                 alerte.show();
            		                 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        		        	}
        		        		else {
        		        			datacs.get(0).retraitSalarier(Double.parseDouble(montant));
            		        		String sql="UPDATE compte SET solde ='"+datacs.get(0).getSolde()+"' WHERE rib = '" +compte+"'";
            		        		PreparedStatement stat = con.prepareStatement(sql);
            		        		 res= stat.executeUpdate();
            		        			String sql1="UPDATE agence SET debit_globale_agence ='"+datacs.get(0).getA().getDebit_globale_agence()+"' WHERE nom = '" +datacs.get(0).getA().getNom()+"'";
                		        		PreparedStatement stat1 = con.prepareStatement(sql1);
                		        		 res1= stat1.executeUpdate(); 
                		        		 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                		        		 Alert alerte = new Alert(AlertType.NONE,"Operation de retrait faite avec succés",ButtonType.OK);
                                         alerte.show();
        		        		}
        		        }
        			}}catch(Exception e) {
        	    		e.getStackTrace();	
        	    	}    	}
    }

    @FXML
    void onVersementClick(ActionEvent event) {
    	String compte = textc.getText();
    	String  montant = textm.getText();
    	if(compte.isEmpty()==false && montant.isEmpty()==false ) {
    		try {
        		Connection con= dbConnection.connect();
        		String sqla="SELECT * FROM agence";
        		String sqlc="SELECT * FROM client";
        		String sqlco="SELECT * FROM compte WHERE rib='" + compte +"'";
        		PreparedStatement stata = con.prepareStatement(sqla);
        		ResultSet resa= stata.executeQuery();
        		PreparedStatement statc = con.prepareStatement(sqlc);
        		ResultSet resc= statc.executeQuery();
        		PreparedStatement statco = con.prepareStatement(sqlco);
        		ResultSet resco= statco.executeQuery();
        		while(resa.next() ) {
        	        agences.add(new Agence(resa.getString(1),resa.getString(2),resa.getString(3),resa.getString(4),
        	        		resa.getString(5),resa.getString(6)));
        		}
        		while(resc.next() ) {
        			clients.add(new ClientSalarier(resc.getString(1),resc.getString(2),resc.getString(3),resc.getString(4),resc.getString(5)));
        		}
        		        while(resco.next()) {
        		        	if((agences.get(0).getNom().equals(resco.getString(5)) && ( clients.get(0).getNom().equals(resco.getString(3))))){
        		        		datacs.add(new CompteSalarier(resco.getString(1),resco.getString(2),clients.get(0),resco.getString(4),agences.get(0),resco.getDate(6)));
            					datacs.get(0).versement(Double.parseDouble(montant));
        		        		String sql="UPDATE compte SET solde ='"+datacs.get(0).getSolde()+"' WHERE rib = '" +compte+"'";
        		        		PreparedStatement stat = con.prepareStatement(sql);
        		        		 res= stat.executeUpdate();
        		        			String sql1="UPDATE agence SET credit_globale_agence ='"+datacs.get(0).getA().getCredit_globale_agence()+"' WHERE nom = '" +datacs.get(0).getA().getNom()+"'";
            		        		PreparedStatement stat1 = con.prepareStatement(sql1);
            		        		 res1= stat1.executeUpdate();
        		        	}
        		        }
        			}catch(Exception e) {
        	    		e.getStackTrace();	
        	    	}    	}
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    	Alert alerte = new Alert(AlertType.NONE,"Operation de versement faite avec succés",ButtonType.OK);
        alerte.show();
    }

    @FXML
    void onVirementClick(ActionEvent event) {

    }
}