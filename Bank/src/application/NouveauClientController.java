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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NouveauClientController {

    @FXML
    private AnchorPane ScenePane;

    @FXML
    private TextField cha;

    @FXML
    private TextField cin;

    @FXML
    private TextField cin1;

    @FXML
    private Button exit;
    
    @FXML
    private TextField nbe;

    @FXML
    private TextField ne;

    @FXML
    private TextField nom;

    @FXML
    private TextField nom1;

    @FXML
    private TextField prenom;

    @FXML
    private TextField prenom1;

    @FXML
    private TextField salaire;

    @FXML
    private CheckBox salarier;

    @FXML
    private CheckBox vip;

    @FXML
    void onAddClick(ActionEvent event) {
    	String c = cin.getText();
    	String n = nom.getText();
    	String p = prenom.getText();
    	String  s = salaire.getText();
    	String  type = "Salarier";
    	String nomEntreprise="";
    	double chiffreAffaires=0;
    	int nbEmployes=0;
    	if(salarier.isSelected()) {
    				ClientSalarier cs= new ClientSalarier(c,n,p,type,s);
    				 try {
                 		Connection con= dbConnection.connect();
                 		String sql="INSERT INTO client(cin,nom,prenom,typeclient,salaire,nomEntreprise,chiffreAffaires,nbEmployes) VALUES('" +cs.getCin()+ "','" + cs.getNom() + "','" + cs.getPrenom() + "','" + cs.getTypeclient() + "','" + cs.getSalaire() + "','" +  nomEntreprise + "','" + chiffreAffaires +  "','" + nbEmployes +"')";
                 		Statement stat = con.createStatement();
                 		stat.executeUpdate(sql);
                 		con.close();
                        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                        FXMLLoader loader= new FXMLLoader(getClass().getResource("Gestion des Clients.fxml"));
        		    	Parent root=(Parent)loader.load();
        		    	Stage operation=new Stage();
        		    	GestionClientController controller= loader.getController();
        		    	Scene scene=new Scene(root);
        		    	operation.setScene(scene);
        		    	operation.show();
        		    	Alert alerte = new Alert(AlertType.NONE,"Client est ajouté avec succés !",ButtonType.OK);
                        alerte.show();
     			}catch(Exception e) {
         			e.getStackTrace();
         		} 
    	}  
    	else
    		if(vip.isSelected()) {
    		String c1 = cin1.getText();
        	String n1 = nom1.getText();
        	String p1 = prenom1.getText();
        	String  type1 = "VIP";
        	String nomEntreprise1=ne.getText();
        	String chiffreAffaires1=cha.getText();
        	String nbEmployes1=nbe.getText();
        	double salaire=0;
            					ClientVIP cv= new ClientVIP(c1,n1,p1,type1,nomEntreprise1,chiffreAffaires1,nbEmployes1);
                				 try {
                             		Connection con= dbConnection.connect();
                             		String sql1="INSERT INTO client(cin,nom,prenom,typeclient,salaire,nomEntreprise,chiffreAffaires,nbEmployes) VALUES('" +cv.getCin()+ "','" + cv.getNom() + "','" + cv.getPrenom() + "','" + cv.getTypeclient() + "','" + salaire + "','" +  cv.getNomEntreprise() + "','" + cv.getChiffreAffaires() +  "','" + cv.getNbEmployes() +"')";
                             		Statement stat1 = con.createStatement();
                             		stat1.executeUpdate(sql1);
             		        			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
             		        			FXMLLoader loader= new FXMLLoader(getClass().getResource("Gestion des Clients.fxml"));
             		   		    	Parent root=(Parent)loader.load();
             		   		    	Stage operation=new Stage();
             		   		    	GestionClientController controller= loader.getController();
             		   		    	Scene scene=new Scene(root);
             		   		    	operation.setScene(scene);
             		   		    	operation.show();
             		   		    Alert alerte = new Alert(AlertType.NONE,"Client est ajouté avec succés !",ButtonType.OK);
                                alerte.show();
                 			}catch(Exception e) {
                     			e.getStackTrace();
                     		}    		
    		
    	}
    		
    	}
        }


